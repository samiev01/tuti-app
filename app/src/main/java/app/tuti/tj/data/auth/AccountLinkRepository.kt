package app.tuti.tj.data.auth

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

// ════════════════════════════════════════════════════════════════
//  ПРИВЯЗКА GOOGLE К АНОНИМНОМУ АККАУНТУ
//
//  Обычный случай: у человека уже есть анонимный uid из первого
//  запуска, под ним лежит профиль онбординга. Google подключается
//  к тому же uid — данные остаются на месте.
//
//  Особый случай, ради которого всё это и написано:
//  FirebaseAuthUserCollisionException. Это переустановка. Человек
//  пользовался Tuti, снёс, поставил заново, прошёл онбординг под
//  новым анонимным uid — и входит своим старым Google.
//
//  Правило: данные старого аккаунта главнее свежего онбординга.
//  Переключаемся на существующий uid, а полуминутные ответы под
//  брошенным анонимным uid выбрасываем. Иначе человек терял бы
//  всё накопленное при каждой переустановке.
// ════════════════════════════════════════════════════════════════

sealed interface LinkOutcome {
    /** Google подключён к текущему uid: ничего не потеряно. */
    data object Linked : LinkOutcome

    /** Аккаунт уже принадлежал другому профилю — перешли на него. */
    data class SwitchedToExisting(val uid: String) : LinkOutcome
}

object AccountLinkRepository {

    private const val TAG = "AccountLink"

    suspend fun linkGoogle(idToken: String): Result<LinkOutcome> = runCatching {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val auth = Firebase.auth
        val user = auth.currentUser

        if (user != null && user.isAnonymous) {
            try {
                user.linkWithCredential(credential).await()
                Log.d(TAG, "linked google to ${user.uid}")
                LinkOutcome.Linked
            } catch (_: FirebaseAuthUserCollisionException) {
                // Этот Google-аккаунт уже привязан к другому профилю.
                val result = auth.signInWithCredential(credential).await()
                val uid = result.user!!.uid
                Log.d(TAG, "collision, switched to existing $uid")
                LinkOutcome.SwitchedToExisting(uid)
            }
        } else {
            val result = auth.signInWithCredential(credential).await()
            val uid = result.user!!.uid
            Log.d(TAG, "signed in as existing $uid")
            LinkOutcome.SwitchedToExisting(uid)
        }
    }.onFailure { e ->
        Log.e(TAG, "linkGoogle failed: ${e.message}", e)
    }

    /**
     * Имя, почта и аватар нужны офлайн — профиль и лидерборд
     * рисуются до того, как Firebase успеет ответить.
     */
    fun persistProfile(context: Context, user: FirebaseUser?) {
        user ?: return
        context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("google_name", user.displayName.orEmpty())
            .putString("google_email", user.email.orEmpty())
            .putString("google_photo_url", user.photoUrl?.toString().orEmpty())
            .putBoolean("google_signed_in", true)
            .apply()
    }
}
