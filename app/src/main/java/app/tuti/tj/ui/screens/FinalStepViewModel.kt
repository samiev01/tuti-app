package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tuti.tj.analytics.TutiAnalytics
import app.tuti.tj.data.auth.AccountLinkResult
import app.tuti.tj.data.auth.AccountLinkStub
import app.tuti.tj.data.auth.AuthErrorKind
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.user.CityCatalog
import app.tuti.tj.data.user.LearningGoal
import app.tuti.tj.data.user.LearningLanguage
import app.tuti.tj.data.user.ProficiencyLevel
import app.tuti.tj.data.user.TutiCity
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  ФИНАЛЬНЫЙ ШАГ ОНБОРДИНГА
//
//  Экран без кнопки «потом»: единственный путь дальше — привязать
//  аккаунт. Поэтому вся ценность здесь не в удачном сценарии, а в
//  отказах. Каждый из них должен получить понятный текст и хотя бы
//  одно действие, иначе человек упирается в стену и удаляет
//  приложение — а мы даже не узнаем, почему.
// ════════════════════════════════════════════════════════════════

sealed interface FinalStepState {
    data object Idle : FinalStepState
    data object Loading : FinalStepState
    data class Error(val kind: AuthErrorKind) : FinalStepState
    data object Success : FinalStepState
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

    fun load(context: Context, repository: TutiRepository) {
        if (loaded) return
        loaded = true
        viewModelScope.launch {
            val user = runCatching { repository.getUserOnce() }.getOrNull() ?: return@launch
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
    }

    fun signIn() {
        if (state == FinalStepState.Loading) return
        TutiAnalytics.finalStepSignInClick()
        viewModelScope.launch {
            state = FinalStepState.Loading
            state = when (val result = AccountLinkStub.link()) {
                AccountLinkResult.Success -> FinalStepState.Success

                // Закрыл окно выбора аккаунта — возвращаемся молча.
                AccountLinkResult.Cancelled -> FinalStepState.Idle

                is AccountLinkResult.Failure -> {
                    TutiAnalytics.finalStepError(result.kind.name)
                    FinalStepState.Error(result.kind)
                }
            }
        }
    }
}

/** В базе ответы лежат строками в нижнем регистре: `russian`, `work`. */
private inline fun <reified T : Enum<T>> parse(value: String): T? =
    runCatching { enumValueOf<T>(value.uppercase()) }.getOrNull()
