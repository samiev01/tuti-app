package app.tuti.tj.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import app.tuti.tj.MainActivity
import app.tuti.tj.R
import app.tuti.tj.ui.i18n.LanguageManager

object TutiNotificationManager {

    const val CHANNEL_ID = "tuti_reminders"

    fun createChannel(context: Context) {
        // Имя канала система запоминает при создании: если язык
        // сменили позже, канал остаётся с прежним названием — это
        // ограничение Android, а не забытая строка.
        val strings = LanguageManager.strings.notifications
        val channel = NotificationChannel(
            CHANNEL_ID,
            strings.channelName,
            NotificationManager.IMPORTANCE_DEFAULT,
        ).apply {
            description = strings.channelDescription
        }
        val nm = context.getSystemService(NotificationManager::class.java)
        nm.createNotificationChannel(channel)
    }

    // ── not-studied messages ─────────────────────────

    private data class Msg(val title: String, val text: String)

    private fun notStudiedMessages() =
        LanguageManager.strings.notifications.reminders.map { (title, text) -> Msg(title, text) }

    private fun streakDangerMessage(streak: Int): Msg {
        val strings = LanguageManager.strings.notifications
        return Msg(strings.streakRiskTitle, strings.streakRiskText(streak))
    }

    private fun almostThereMessages(): List<Msg> {
        val strings = LanguageManager.strings.notifications
        return listOf(
            Msg(strings.goalTitle, ""),   // text filled dynamically
            Msg(strings.goalAlmostTitle, strings.goalAlmostText),
        )
    }

    // ── public show helpers ──────────────────────────

    fun showNotStudiedReminder(context: Context, streak: Int, isLateReminder: Boolean) {
        val msg = if (isLateReminder && streak > 1) {
            streakDangerMessage(streak)
        } else {
            notStudiedMessages().random()
        }
        show(context, msg.title, msg.text, notificationId = 1001)
    }

    fun showAlmostThereReminder(
        context: Context,
        minutesStudied: Int,
        dailyGoal: Int,
    ) {
        val remaining = (dailyGoal - minutesStudied).coerceAtLeast(1)
        val msg = almostThereMessages().random()
        val text = msg.text.ifEmpty {
            LanguageManager.strings.notifications.goalProgressText(minutesStudied, remaining)
        }
        show(context, msg.title, text, notificationId = 1002)
    }

    // ── core show ────────────────────────────────────

    private fun show(context: Context, title: String, text: String, notificationId: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pending = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setContentIntent(pending)
            .setColor(0xFF00BFA6.toInt())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val nm = context.getSystemService(NotificationManager::class.java)
        nm.notify(notificationId, notification)
    }
}
