package app.tuti.tj.data.subscription

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PlusManager {
    private const val PREFS = "tuti_plus"

    fun isPlusActive(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val expiryDate = prefs.getLong("plus_expiry", 0)
        return System.currentTimeMillis() < expiryDate
    }

    fun activatePlus(context: Context, durationDays: Int) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val expiry = System.currentTimeMillis() + (durationDays * 24 * 60 * 60 * 1000L)
        prefs.edit()
            .putLong("plus_expiry", expiry)
            .putBoolean("is_plus", true)
            .apply()

        val userId = Firebase.auth.currentUser?.uid
        if (userId != null) {
            // set(merge), а не update: у нового аккаунта документа users/{uid}
            // ещё нет, и update молча падал бы вместе с датой окончания.
            Firebase.firestore.collection("users").document(userId)
                .set(
                    mapOf("isPlusUser" to true, "plusExpiry" to expiry),
                    SetOptions.merge(),
                )
        }
    }

    fun getDaysRemaining(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val expiry = prefs.getLong("plus_expiry", 0)
        val remaining = expiry - System.currentTimeMillis()
        return if (remaining > 0) (remaining / (24 * 60 * 60 * 1000)).toInt() else 0
    }

    fun getPlusExpiryDate(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val expiry = prefs.getLong("plus_expiry", 0)
        if (expiry == 0L) return ""
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return sdf.format(Date(expiry))
    }
}
