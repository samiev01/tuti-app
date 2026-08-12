package app.tuti.tj.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import app.tuti.tj.MainActivity
import app.tuti.tj.R

object TutiNotificationManager {

    const val CHANNEL_ID = "tuti_reminders"
    private const val CHANNEL_NAME = "Ёдоварии Tuti"
    private const val CHANNEL_DESC = "Ёдовариҳои ҳаррӯзаи Tuti барои омӯзиш"

    fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT,
        ).apply {
            description = CHANNEL_DESC
        }
        val nm = context.getSystemService(NotificationManager::class.java)
        nm.createNotificationChannel(channel)
    }

    // ── not-studied messages ─────────────────────────

    private data class Msg(val title: String, val text: String)

    private val notStudiedMessages = listOf(
        Msg("🦜 Tuti интизори шумост!", "Имрӯз ҳанӯз дарс нахондед!"),
        Msg("🔥 Серияи шумо дар хатар аст!", "Биёед омӯзед!"),
        Msg("📚 5 дақиқа кифоя аст!", "Серияро нигоҳ доред!"),
        Msg("🎯 Имрӯз як дарс хонед!", "Кифоя аст!"),
        Msg("💪 Ҳар рӯз як қадам!", "Имрӯзро аз даст надиҳед!"),
    )

    private fun streakDangerMessage(streak: Int) = Msg(
        "🔥 Серия дар хатар!",
        "Агар имрӯз наомӯзед, серияи $streak рӯзаатон қатъ мешавад!",
    )

    private val almostThereMessages = listOf(
        Msg("💪 Каме монд!", ""),   // text filled dynamically
        Msg("🎯 Ҳадафатон наздик аст!", "Як машқи дигар ва ҳадафи имрӯза иҷро мешавад!"),
    )

    // ── public show helpers ──────────────────────────

    fun showNotStudiedReminder(context: Context, streak: Int, isLateReminder: Boolean) {
        val msg = if (isLateReminder && streak > 1) {
            streakDangerMessage(streak)
        } else {
            notStudiedMessages.random()
        }
        show(context, msg.title, msg.text, notificationId = 1001)
    }

    fun showAlmostThereReminder(
        context: Context,
        minutesStudied: Int,
        dailyGoal: Int,
    ) {
        val remaining = (dailyGoal - minutesStudied).coerceAtLeast(1)
        val msg = almostThereMessages.random()
        val text = msg.text.ifEmpty {
            "Шумо $minutesStudied дақиқа хондед. Боз $remaining дақиқа то ҳадаф!"
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
