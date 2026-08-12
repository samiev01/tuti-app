package app.tuti.tj.data.auth

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class GoogleAuthManager(private val context: Context) {

    private val auth = Firebase.auth

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    fun isLoggedIn(): Boolean = auth.currentUser != null

    suspend fun signInWithGoogle(credential: AuthCredential): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithCredential(credential).await()
            val user = result.user ?: return Result.failure(Exception("User is null"))
            // Persist key info for offline access
            context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                .edit()
                .putString("google_name", user.displayName ?: "")
                .putString("google_email", user.email ?: "")
                .putString("google_photo_url", user.photoUrl?.toString() ?: "")
                .putBoolean("google_signed_in", true)
                .apply()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
        context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .edit()
            .remove("google_name")
            .remove("google_email")
            .remove("google_photo_url")
            .putBoolean("google_signed_in", false)
            .apply()
    }
}
