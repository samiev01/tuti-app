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
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.data.user.AuthRepository
import app.tuti.tj.data.user.UserProfileRepository
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

    fun signIn(activity: Activity, context: Context) {
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
                    state = resolveDestination(uid, context)
                }
        }
    }

    private suspend fun resolveDestination(uid: String, context: Context): SignInState {
        if (!UserProfileRepository.hasProfile(uid)) return SignInState.NeedsOnboarding

        // Профиль есть — онбординг человек уже проходил, второй раз
        // спрашивать нечего. А вот прогресс мог и не выгрузиться:
        // тогда обещать «всё вернулось» нельзя, просто пускаем на
        // главную без лишних слов.
        val restored = runCatching { CloudSyncManager.restoreProgress(context) }
            .getOrDefault(false)
        return SignInState.Returning(restored)
    }

    private fun fail(kind: AuthErrorKind): SignInState {
        TutiAnalytics.signInError(kind.name)
        return SignInState.Error(kind)
    }
}
