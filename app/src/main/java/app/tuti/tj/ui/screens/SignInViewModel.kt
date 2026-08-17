package app.tuti.tj.ui.screens

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tuti.tj.analytics.TutiAnalytics
import app.tuti.tj.data.auth.AuthErrorKind
import app.tuti.tj.data.auth.GoogleIdTokenProvider
import app.tuti.tj.data.auth.GoogleIdTokenResult
import app.tuti.tj.data.auth.toAuthErrorKind
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.data.user.AuthRepository
import app.tuti.tj.data.user.UserProfileRepository
import app.tuti.tj.data.user.applyLocally
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

// ════════════════════════════════════════════════════════════════
//  ВХОД — ПЕРВЫЙ ЭКРАН
//
//  Сеть нужна только здесь. Дальше онбординг работает офлайн:
//  ответы копятся в ViewModel и уходят одной записью в конце.
//
//  Развилка после входа — не «новый ли это аккаунт Google», а есть
//  ли документ users/{uid}. Аккаунт может быть тот же, а Tuti на
//  нём человек ещё не открывал.
// ════════════════════════════════════════════════════════════════

/** Сколько ждём Firebase, прежде чем признать это отказом сети. */
private const val SIGN_IN_TIMEOUT_MS = 20_000L

sealed interface SignInState {
    data object Idle : SignInState
    data object Loading : SignInState
    data class Error(val kind: AuthErrorKind) : SignInState

    /** Новый для Tuti аккаунт: впереди онбординг. */
    data object NeedsOnboarding : SignInState

    /** Профиль нашёлся — на главную. Прогресс из облака подняли не всегда. */
    data class Returning(val restored: Boolean) : SignInState
}

class SignInViewModel : ViewModel() {

    var state by mutableStateOf<SignInState>(SignInState.Idle)
        private set

    fun signIn(activity: Activity, context: Context, repository: TutiRepository) {
        if (state == SignInState.Loading) return

        viewModelScope.launch {
            state = SignInState.Loading

            val idToken = when (val result = GoogleIdTokenProvider.request(activity)) {
                is GoogleIdTokenResult.Token -> result.idToken

                // Закрыл окно выбора аккаунта — возвращаемся молча.
                GoogleIdTokenResult.Cancelled -> {
                    state = SignInState.Idle
                    return@launch
                }

                is GoogleIdTokenResult.Failure -> {
                    state = fail(result.kind)
                    return@launch
                }
            }

            // Firebase Auth на мёртвой сети сдаётся минуту с лишним.
            // Столько держать человека у спиннера нельзя.
            val signIn = withTimeoutOrNull(SIGN_IN_TIMEOUT_MS) {
                AuthRepository.signInWithGoogle(idToken)
            }

            if (signIn == null) {
                state = fail(AuthErrorKind.NO_NETWORK)
                return@launch
            }

            signIn
                .onFailure { state = fail(it.toAuthErrorKind()) }
                .onSuccess { uid ->
                    TutiAnalytics.signInSuccess()
                    AuthRepository.persistProfile(context)
                    state = resolveDestination(uid, context, repository)
                }
        }
    }

    private suspend fun resolveDestination(
        uid: String,
        context: Context,
        repository: TutiRepository,
    ): SignInState {
        if (!UserProfileRepository.hasProfile(uid)) return SignInState.NeedsOnboarding

        // Профиль есть — онбординг человек уже проходил, второй раз
        // спрашивать нечего.
        val restored = runCatching { CloudSyncManager.restoreProgress(context) }
            .getOrDefault(false)
        if (restored) return SignInState.Returning(restored = true)

        // Возвращать было нечего: users/{uid}/sync появляется только
        // после первого урока. Но сами ответы онбординга лежат в
        // профиле — поднимаем хотя бы их, иначе человек попадёт на
        // главную без курса и решит, что всё пропало.
        UserProfileRepository.readOnboarding(uid)?.applyLocally(context, repository)

        // Курс восстановлен, а прогресса не было вовсе — обещать
        // «всё вернулось» не за что, просто пускаем на главную.
        return SignInState.Returning(restored = false)
    }

    private fun fail(kind: AuthErrorKind): SignInState {
        TutiAnalytics.signInError(kind.name)
        return SignInState.Error(kind)
    }
}
