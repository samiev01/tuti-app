package app.tuti.tj.data.auth

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException
import androidx.credentials.exceptions.NoCredentialException
import app.tuti.tj.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.FirebaseNetworkException
import java.io.IOException

// ════════════════════════════════════════════════════════════════
//  ТОКЕН GOOGLE
//
//  Берём ровно то, что нужно для привязки: idToken. Вход в Firebase
//  здесь не делается — иначе анонимный аккаунт заменился бы новым,
//  а вместе с ним потерялись бы ответы онбординга.
//
//  Механизм тот же, что уже был в проекте (Credential Manager из
//  rememberGoogleSignIn): переезжать на что-то другое в этой задаче
//  незачем.
// ════════════════════════════════════════════════════════════════

sealed interface GoogleIdTokenResult {
    data class Token(val idToken: String) : GoogleIdTokenResult

    /** Окно выбора аккаунта закрыто пользователем. Не ошибка. */
    data object Cancelled : GoogleIdTokenResult

    data class Failure(val kind: AuthErrorKind) : GoogleIdTokenResult
}

object GoogleIdTokenProvider {

    private const val TAG = "GoogleIdToken"

    suspend fun request(activity: Activity): GoogleIdTokenResult {
        // Без сети окно выбора аккаунта откроется (аккаунты лежат
        // локально), человек выберет свой — и только потом Firebase
        // будет минуту стучаться в сеть, прежде чем сдаться. Дешевле
        // сказать про интернет сразу.
        if (!activity.isOnline()) {
            Log.w(TAG, "no network before sign-in")
            return GoogleIdTokenResult.Failure(AuthErrorKind.NO_NETWORK)
        }

        // Проверка до окна выбора аккаунта: без сервисов Google
        // Credential Manager падает с невнятной ошибкой, а причина
        // у неё вполне конкретная и человеку её надо назвать.
        val servicesStatus = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(activity)
        if (servicesStatus != ConnectionResult.SUCCESS) {
            Log.w(TAG, "play services unavailable: $servicesStatus")
            return GoogleIdTokenResult.Failure(AuthErrorKind.PLAY_SERVICES)
        }

        return try {
            val option = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(activity.getString(R.string.default_web_client_id))
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(option)
                .build()

            val credential = CredentialManager.create(activity)
                .getCredential(activity, request)
                .credential

            if (credential.type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                Log.w(TAG, "unexpected credential type: ${credential.type}")
                return GoogleIdTokenResult.Failure(AuthErrorKind.UNKNOWN)
            }

            GoogleIdTokenResult.Token(
                GoogleIdTokenCredential.createFrom(credential.data).idToken,
            )
        } catch (_: GetCredentialCancellationException) {
            GoogleIdTokenResult.Cancelled
        } catch (e: Exception) {
            Log.w(TAG, "getCredential failed: ${e.message}", e)
            GoogleIdTokenResult.Failure(e.toAuthErrorKind())
        }
    }
}

/**
 * Есть ли вообще подключение. Не гарантия, что интернет работает,
 * но отсутствие сети ловит мгновенно — а именно этот случай и
 * заставляет ждать дольше всего.
 */
fun Context.isOnline(): Boolean {
    val manager = getSystemService(ConnectivityManager::class.java) ?: return true
    val capabilities = manager.getNetworkCapabilities(manager.activeNetwork) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

/**
 * Firebase Auth не заводит отдельный тип на каждый отказ сети:
 * в авиарежиме прилетает обычный FirebaseException с текстом
 * «Connection reset». Тип не отличить, а человеку надо сказать
 * про интернет — поэтому смотрим ещё и на текст.
 */
private val NETWORK_MARKERS = listOf(
    "network",
    "connection",
    "unable to resolve host",
    "timeout",
    "timed out",
    "unreachable",
    "failed to connect",
)

/**
 * Настоящая причина обычно лежит не в самом исключении, а глубже
 * в цепочке причин: Credential Manager заворачивает и сетевые
 * отказы, и проблемы с сервисами Google в свои типы.
 */
fun Throwable.toAuthErrorKind(): AuthErrorKind {
    var cause: Throwable? = this
    while (cause != null) {
        when (cause) {
            is IOException,
            is FirebaseNetworkException,
            -> return AuthErrorKind.NO_NETWORK

            is GetCredentialProviderConfigurationException,
            -> return AuthErrorKind.PLAY_SERVICES

            is NoCredentialException,
            -> return AuthErrorKind.UNKNOWN

            is ApiException -> return when (cause.statusCode) {
                CommonStatusCodes.NETWORK_ERROR -> AuthErrorKind.NO_NETWORK
                CommonStatusCodes.API_NOT_CONNECTED,
                ConnectionResult.SERVICE_MISSING,
                ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED,
                ConnectionResult.SERVICE_DISABLED,
                -> AuthErrorKind.PLAY_SERVICES

                else -> AuthErrorKind.UNKNOWN
            }
        }
        val message = cause.message?.lowercase()
        if (message != null && NETWORK_MARKERS.any { message.contains(it) }) {
            return AuthErrorKind.NO_NETWORK
        }
        cause = cause.cause
    }
    return AuthErrorKind.UNKNOWN
}
