package app.tuti.tj.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

object NotificationScheduler {

    private const val RC_EVENING = 1001
    private const val RC_LATE = 1002

    /** Schedule both the evening (gentle) and late (urgent) daily alarms. */
    fun scheduleDailyReminders(context: Context) {
        val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
        val eveningHour = prefs.getInt("reminder_hour", 19)
        val eveningMinute = prefs.getInt("reminder_minute", 0)
        scheduleAlarm(context, hour = eveningHour, minute = eveningMinute, requestCode = RC_EVENING, type = "evening")
        scheduleAlarm(context, hour = (eveningHour + 2).coerceAtMost(23), minute = eveningMinute, requestCode = RC_LATE, type = "late")
    }

    /** Cancel all Tuti reminder alarms. */
    fun cancelAll(context: Context) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(makePendingIntent(context, RC_EVENING, "evening"))
        am.cancel(makePendingIntent(context, RC_LATE, "late"))
    }

    /** Re-schedule with a custom evening hour and minute (user picks time in settings). */
    fun rescheduleEvening(context: Context, hour: Int, minute: Int) {
        cancelAll(context)
        scheduleAlarm(context, hour = hour, minute = minute, requestCode = RC_EVENING, type = "evening")
        scheduleAlarm(context, hour = (hour + 2).coerceAtMost(23), minute = minute, requestCode = RC_LATE, type = "late")
    }

    // ── internal ─────────────────────────────────────

    private fun scheduleAlarm(context: Context, hour: Int, minute: Int, requestCode: Int, type: String) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(Calendar.getInstance())) add(Calendar.DAY_OF_MONTH, 1)
        }

        val pi = makePendingIntent(context, requestCode, type)

        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pi,
        )
    }

    private fun makePendingIntent(context: Context, requestCode: Int, type: String): PendingIntent {
        val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
            putExtra("type", type)
        }
        return PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }
}
