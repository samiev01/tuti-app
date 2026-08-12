package app.tuti.tj.ui.components

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import app.tuti.tj.R
import app.tuti.tj.data.auth.GoogleAuthManager
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  ВХОД ЧЕРЕЗ GOOGLE
//
//  Только логика, без разметки: экраны рисуют кнопку по-своему
//  (полноширинная на приветствии, компактная капсула в профиле),
//  но сам сценарий — Credential Manager → Firebase → обработка
//  отказов — должен быть один. Раньше он был скопирован в двух
//  местах и расходился в обработке ошибок.
// ════════════════════════════════════════════════════════════════

@Stable
class GoogleSignIn internal constructor(
    /** Идёт ли сейчас вход — экран показывает индикатор вместо надписи. */
    val isRunning: Boolean,
    /** Запускает системное окно выбора аккаунта. */
    val launch: () -> Unit,
)

/**
 * [onSignedIn] вызывается только при успешном входе. Отмена
 * пользователем — не ошибка и молча ничего не делает.
 */
@Composable
fun rememberGoogleSignIn(onSignedIn: () -> Unit): GoogleSignIn {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isRunning by remember { mutableStateOf(false) }
    val currentOnSignedIn by rememberUpdatedState(onSignedIn)

    return GoogleSignIn(isRunning = isRunning) {
        if (!isRunning) {
            scope.launch {
                isRunning = true
                try {
                    val activity = context.findActivity()
                    val credentialManager = CredentialManager.create(context)
                    val webClientId = context.getString(R.string.default_web_client_id)

                    val googleIdOption = GetGoogleIdOption.Builder()
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId(webClientId)
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(googleIdOption)
                        .build()

                    val credential = credentialManager.getCredential(activity, request).credential

                    if (credential.type ==
                        GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                    ) {
                        val googleIdToken = GoogleIdTokenCredential.createFrom(credential.data)
                        val firebaseCredential =
                            GoogleAuthProvider.getCredential(googleIdToken.idToken, null)
                        val result = GoogleAuthManager(context).signInWithGoogle(firebaseCredential)
                        if (result.isSuccess) {
                            currentOnSignedIn()
                        } else {
                            Toast.makeText(
                                context,
                                "Хатогӣ дар ворид шудан",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Навъи аккаунт дастгирӣ намешавад",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                } catch (_: GetCredentialCancellationException) {
                    // Пользователь закрыл окно выбора — это не ошибка.
                } catch (_: NoCredentialException) {
                    Toast.makeText(
                        context,
                        "Аккаунти Google ёфт нашуд. Аввал аккаунт илова кунед.",
                        Toast.LENGTH_LONG,
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Хатогӣ: ${e.localizedMessage ?: "Дубора кӯшиш кунед"}",
                        Toast.LENGTH_SHORT,
                    ).show()
                } finally {
                    isRunning = false
                }
            }
        }
    }
}

/**
 * Credential Manager требует именно Activity, а в Compose доступен
 * обёрнутый Context — разворачиваем цепочку.
 */
internal fun Context.findActivity(): Activity {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    error("Activity not found in context chain")
}
