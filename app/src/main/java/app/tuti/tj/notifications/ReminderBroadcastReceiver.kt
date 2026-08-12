package app.tuti.tj.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import app.tuti.tj.data.local.TutiDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReminderBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
        if (!prefs.getBoolean("reminders_enabled", true)) return

        val lastStudyDate = prefs.getString("last_study_date", "")
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

        if (lastStudyDate == today) {
            Log.d("TutiReminder", "User studied today, skipping notification")
            return
        }

        val db = TutiDatabase.getDatabase(context)
        val user = db.userDao().getUserSync() ?: return
        if (!user.onboardingCompleted) return

        val type = intent?.getStringExtra("type") ?: "evening"
        val isLate = type == "late"

        TutiNotificationManager.showNotStudiedReminder(
            context = context,
            streak = user.currentStreak,
            isLateReminder = isLate,
        )
        Log.d("TutiReminder", "Notification sent (type=$type, streak=${user.currentStreak})")
    }
}
