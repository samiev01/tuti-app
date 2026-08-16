package app.tuti.tj.ui.screens

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tuti.tj.analytics.TutiAnalytics
import app.tuti.tj.data.auth.AccountLinkRepository
import app.tuti.tj.data.auth.AuthErrorKind
import app.tuti.tj.data.auth.GoogleIdTokenProvider
import app.tuti.tj.data.auth.GoogleIdTokenResult
import app.tuti.tj.data.auth.LinkOutcome
import app.tuti.tj.data.auth.toAuthErrorKind
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.data.user.CityCatalog
import app.tuti.tj.data.user.LearningGoal
import app.tuti.tj.data.user.LearningLanguage
import app.tuti.tj.data.user.ProficiencyLevel
import app.tuti.tj.data.user.TutiCity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

// ════════════════════════════════════════════════════════════════
//  ФИНАЛЬНЫЙ ШАГ ОНБОРДИНГА
//
//  Экран без кнопки «потом»: единственный путь дальше — привязать
//  аккаунт. Поэтому вся ценность здесь не в удачном сценарии, а в
//  отказах. Каждый из них должен получить понятный текст и хотя бы
//  одно действие, иначе человек упирается в стену и удаляет
//  приложение — а мы даже не узнаем, почему.
// ════════════════════════════════════════════════════════════════

/** Сколько ждём Firebase, прежде чем признать это отказом сети. */
private const val LINK_TIMEOUT_MS = 20_000L

sealed interface FinalStepState {
    data object Idle : FinalStepState
    data object Loading : FinalStepState
    data class Error(val kind: AuthErrorKind) : FinalStepState
    data object Success : FinalStepState

    /** Вошли старым аккаунтом, прогресс вернулся из облака. */
    data object Restored : FinalStepState
}

/**
 * Сводка ответов. Читается из Room и настроек, а не передаётся
 * с предыдущего экрана: на этот шаг попадают и после перезапуска
 * приложения, когда состояние онбординга давно потеряно.
 */
data class OnboardingSummary(
    val language: LearningLanguage,
    val goal: LearningGoal,
    val level: ProficiencyLevel,
    val dailyMinutes: Int,
    val city: TutiCity,
)

class FinalStepViewModel : ViewModel() {

    var state by mutableStateOf<FinalStepState>(FinalStepState.Idle)
        private set

    var summary by mutableStateOf<OnboardingSummary?>(null)
        private set

    private var loaded = false
    private var repository: TutiRepository? = null

    fun load(context: Context, repository: TutiRepository) {
        this.repository = repository
        if (loaded) return
        loaded = true
        viewModelScope.launch { readSummary(context) }
    }

    private suspend fun readSummary(context: Context) {
        val user = runCatching { repository?.getUserOnce() }.getOrNull() ?: return
        val cityCode = context
            .getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .getString("user_city_code", null)

        summary = OnboardingSummary(
            language = parse<LearningLanguage>(user.selectedLanguage)
                ?: LearningLanguage.RUSSIAN,
            goal = parse<LearningGoal>(user.goal) ?: LearningGoal.PERSONAL,
            level = parse<ProficiencyLevel>(user.level) ?: ProficiencyLevel.BEGINNER,
            dailyMinutes = user.dailyGoalMinutes,
            city = CityCatalog.byCode(cityCode),
        )
    }

    fun signIn(activity: Activity, context: Context) {
        if (state == FinalStepState.Loading) return
        TutiAnalytics.finalStepSignInClick()

        viewModelScope.launch {
            state = FinalStepState.Loading

            val idToken = when (val result = GoogleIdTokenProvider.request(activity)) {
                is GoogleIdTokenResult.Token -> result.idToken

                // Закрыл окно выбора аккаунта — возвращаемся молча.
                GoogleIdTokenResult.Cancelled -> {
                    state = FinalStepState.Idle
                    return@launch
                }

                is GoogleIdTokenResult.Failure -> {
                    state = fail(result.kind)
                    return@launch
                }
            }

            // Firebase Auth на мёртвой сети сдаётся минуту с лишним.
            // Столько держать человека у спиннера нельзя: обрываем
            // сами и говорим про интернет.
            val link = withTimeoutOrNull(LINK_TIMEOUT_MS) {
                AccountLinkRepository.linkGoogle(idToken)
            }

            if (link == null) {
                state = fail(AuthErrorKind.NO_NETWORK)
                return@launch
            }

            link
                .onFailure { state = fail(it.toAuthErrorKind()) }
                .onSuccess { outcome ->
                    AccountLinkRepository.persistProfile(context, Firebase.auth.currentUser)
                    state = when (outcome) {
                        LinkOutcome.Linked -> afterLink(context)
                        is LinkOutcome.SwitchedToExisting -> afterSwitch(context)
                    }
                }
        }
    }

    /**
     * uid не изменился, профиль онбординга уже лежит под ним. Здесь
     * важно выгрузить прогресс в облако: восстановление после
     * переустановки читает именно эти документы, и без первой
     * выгрузки возвращать было бы нечего.
     */
    private suspend fun afterLink(context: Context): FinalStepState {
        runCatching { CloudSyncManager.saveProgress(context) }
        publishLeaderboardCard(context)
        return FinalStepState.Success
    }

    /**
     * Переустановка: аккаунт оказался занят, мы перешли на старый
     * uid. Свежие ответы онбординга под брошенным анонимным uid
     * не жалко — накопленное важнее.
     */
    private suspend fun afterSwitch(context: Context): FinalStepState {
        val restored = runCatching { CloudSyncManager.restoreProgress(context) }
            .getOrDefault(false)
        // Сводка была собрана из свежих ответов, а после восстановления
        // в базе лежит профиль старого аккаунта. Показывать рядом с
        // «прогресс вернулся» чужие ответы — вранье, перечитываем.
        if (restored) readSummary(context)
        publishLeaderboardCard(context)
        // Возвращать было нечего — значит, старый аккаунт ничего не
        // накопил, и радоваться возвращению не за что.
        return if (restored) FinalStepState.Restored else FinalStepState.Success
    }

    /** Теперь у человека есть имя — карточка в рейтинге больше не безымянная. */
    private suspend fun publishLeaderboardCard(context: Context) {
        runCatching {
            val user = Firebase.auth.currentUser ?: return@runCatching
            val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            val city = prefs.getString("user_city", CityCatalog.default.tajikName)
                ?: CityCatalog.default.tajikName
            // Очки берём из базы уже после восстановления: подставить
            // сюда ноль значило бы обнулить человеку место в рейтинге.
            val local = repository?.getUserOnce()
            FirestoreManager.saveUserProfile(
                userId = user.uid,
                name = user.displayName.orEmpty(),
                city = city,
                xp = local?.totalXp ?: 0,
            )
            FirestoreManager.updateStreak(user.uid, local?.currentStreak ?: 0)
        }
    }

    private fun fail(kind: AuthErrorKind): FinalStepState {
        TutiAnalytics.finalStepError(kind.name)
        return FinalStepState.Error(kind)
    }
}

/** В базе ответы лежат строками в нижнем регистре: `russian`, `work`. */
private inline fun <reified T : Enum<T>> parse(value: String): T? =
    runCatching { enumValueOf<T>(value.uppercase()) }.getOrNull()
