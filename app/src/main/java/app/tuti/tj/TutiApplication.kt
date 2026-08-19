package app.tuti.tj

import android.app.Application
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.local.TutiDatabase
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.notifications.NotificationScheduler
import app.tuti.tj.notifications.TutiNotificationManager
import app.tuti.tj.data.user.AgeGateManager
import app.tuti.tj.ui.i18n.LanguageManager
import app.tuti.tj.ui.theme.ThemeManager
import com.google.firebase.Firebase
import com.google.firebase.initialize

class TutiApplication : Application() {
    val database by lazy { TutiDatabase.getDatabase(this) }
    val repository by lazy {
        TutiRepository(
            database.userDao(),
            database.progressDao(),
            database.wordDao(),
            database.courseProgressDao(),
            database.languageStatsDao(),
        )
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        ThemeManager.init(this)
        // Язык нужен раньше канала уведомлений: имя канала берётся
        // из словаря, а после создания канала система его уже не меняет.
        LanguageManager.init(this)
        // Возрастной порог спрашивается до входа, поэтому вердикт
        // нужен уже к первому кадру.
        AgeGateManager.init(this)
        TutiSoundManager.init(this)
        TutiNotificationManager.createChannel(this)

        val prefs = getSharedPreferences("tuti_prefs", MODE_PRIVATE)
        if (prefs.getBoolean("reminders_enabled", true)) {
            NotificationScheduler.scheduleDailyReminders(this)
        }
    }
}
