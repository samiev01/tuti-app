package app.tuti.tj.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            if (prefs.getBoolean("reminders_enabled", true)) {
                NotificationScheduler.scheduleDailyReminders(context)
            }
        }
    }
}
