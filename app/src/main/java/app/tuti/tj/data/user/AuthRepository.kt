package app.tuti.tj.data.user

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

// ════════════════════════════════════════════════════════════════
//  ВХОД
//
//  Вход через Google стоит первым, до онбординга. Анонимных
//  аккаунтов больше не заводится, а значит нет и линковки: занимать
//  чужой Google-аккаунт нечем, конфликтовать не с чем.
//
//  DI в проекте нет, поэтому репозиторий — object, как соседние
//  FirestoreManager и PlusManager.
// ════════════════════════════════════════════════════════════════

object AuthRepository {

    private const val TAG = "AuthRepository"

    private val auth: FirebaseAuth get() = Firebase.auth

    val currentUid: String? get() = auth.currentUser?.uid

    val displayName: String get() = auth.currentUser?.displayName.orEmpty()

    suspend fun signInWithGoogle(idToken: String): Result<String> = runCatching {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).await().user!!.uid
    }.onSuccess { uid ->
        Log.d(TAG, "signed in as $uid")
    }.onFailure { e ->
        Log.e(TAG, "signInWithGoogle failed: ${e.message}", e)
    }

    /**
     * Имя, почта и аватар нужны офлайн — профиль и рейтинг рисуются
     * до того, как Firebase успеет ответить.
     */
    fun persistProfile(context: Context) {
        val user = auth.currentUser ?: return
        context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("google_name", user.displayName.orEmpty())
            .putString("google_email", user.email.orEmpty())
            .putString("google_photo_url", user.photoUrl?.toString().orEmpty())
            .putBoolean("google_signed_in", true)
            .apply()
    }
}
