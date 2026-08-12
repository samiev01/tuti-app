package app.tuti.tj.data.subscription

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FreeLimits {
    private const val PREFS = "tuti_limits"

    const val MAX_LESSONS_PER_DAY = 2
    const val MAX_CHAT_MESSAGES_PER_DAY = 5
    const val MAX_FLASHCARDS_PER_DAY = 10
    const val MAX_LISTENING_PER_DAY = 2

    fun canDoLesson(context: Context): Boolean {
        if (PlusManager.isPlusActive(context)) return true
        return getTodayCount(context, "lessons") < MAX_LESSONS_PER_DAY
    }

    fun canSendChatMessage(context: Context): Boolean {
        if (PlusManager.isPlusActive(context)) return true
        return getTodayCount(context, "chat") < MAX_CHAT_MESSAGES_PER_DAY
    }

    fun canDoFlashcards(context: Context): Boolean {
        if (PlusManager.isPlusActive(context)) return true
        return getTodayCount(context, "flashcards") < MAX_FLASHCARDS_PER_DAY
    }

    fun canDoListening(context: Context): Boolean {
        if (PlusManager.isPlusActive(context)) return true
        return getTodayCount(context, "listening") < MAX_LISTENING_PER_DAY
    }

    fun incrementCount(context: Context, type: String) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val today = todayString()
        val savedDate = prefs.getString("date", "")

        if (savedDate != today) {
            prefs.edit().clear().putString("date", today).apply()
        }

        val current = prefs.getInt(type, 0)
        prefs.edit().putInt(type, current + 1).apply()
    }

    fun getRemainingCount(context: Context, type: String, max: Int): Int {
        if (PlusManager.isPlusActive(context)) return 999
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val today = todayString()
        val savedDate = prefs.getString("date", "")
        if (savedDate != today) return max
        return (max - prefs.getInt(type, 0)).coerceAtLeast(0)
    }

    private fun getTodayCount(context: Context, type: String): Int {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val today = todayString()
        val savedDate = prefs.getString("date", "")
        if (savedDate != today) return 0
        return prefs.getInt(type, 0)
    }

    private fun todayString(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}
