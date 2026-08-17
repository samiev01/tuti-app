package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.user.AuthRepository
import app.tuti.tj.data.user.CityCatalog
import app.tuti.tj.data.user.LearningGoal
import app.tuti.tj.data.user.LearningLanguage
import app.tuti.tj.data.user.OnboardingProfile
import app.tuti.tj.data.user.ProficiencyLevel
import app.tuti.tj.data.user.UserProfileRepository
import app.tuti.tj.data.user.courseIdFor
import app.tuti.tj.data.user.dbValue
import app.tuti.tj.notifications.NotificationScheduler
import kotlinx.coroutines.withTimeoutOrNull

// ════════════════════════════════════════════════════════════════
//  СОСТОЯНИЕ ОНБОРДИНГА
//
//  Ответы копятся здесь и уходят наружу один раз, на последнем
//  шаге. Не после каждого экрана: это пять оплачиваемых операций
//  Firestore вместо одной на каждого пользователя, при том что
//  весь онбординг занимает полминуты.
//
//  Второй повод для ViewModel, а не remember: поворот экрана на
//  четвёртом вопросе больше не стирает первые три ответа.
// ════════════════════════════════════════════════════════════════

/** Сколько ждём Firestore, прежде чем пустить человека на главный экран. */
private const val SAVE_TIMEOUT_MS = 3_000L

private val languageValues = LearningLanguage.entries
private val levelValues = ProficiencyLevel.entries
private val goalValues = LearningGoal.entries
val dailyMinutesValues = listOf(5, 10, 15, 20)

class OnboardingViewModel : ViewModel() {

    var languageIndex by mutableStateOf<Int?>(null)
        private set
    var levelIndex by mutableStateOf<Int?>(null)
        private set
    var goalIndex by mutableStateOf<Int?>(null)
        private set
    var timeIndex by mutableStateOf<Int?>(null)
        private set
    var cityIndex by mutableStateOf<Int?>(null)
        private set

    fun selectLanguage(index: Int) { languageIndex = index }
    fun selectLevel(index: Int) { levelIndex = index }
    fun selectGoal(index: Int) { goalIndex = index }
    fun selectTime(index: Int) { timeIndex = index }
    fun selectCity(index: Int) { cityIndex = index }

    /**
     * Пропуск шагов разрешён кнопкой «дальше» наверху, поэтому у
     * каждого ответа есть значение по умолчанию: профиль собирается
     * даже из пустого состояния.
     */
    private fun profile(): OnboardingProfile = OnboardingProfile(
        language = languageValues.getOrElse(languageIndex ?: 0) { LearningLanguage.RUSSIAN },
        goal = goalValues.getOrElse(goalIndex ?: 0) { LearningGoal.PERSONAL },
        level = levelValues.getOrElse(levelIndex ?: 0) { ProficiencyLevel.BEGINNER },
        dailyMinutes = dailyMinutesValues.getOrElse(timeIndex ?: 0) { 5 },
        cityCode = CityCatalog.byIndex(cityIndex).code,
    )

    /**
     * Порядок здесь не случайный: сначала всё локальное, потом сеть.
     * Room и настройки должны быть на месте к моменту, когда откроется
     * главный экран, а запись в Firestore ждать себя не заставляет —
     * [SAVE_TIMEOUT_MS] и дальше без неё.
     */
    suspend fun complete(context: Context, repository: TutiRepository) {
        val profile = profile()
        val city = CityCatalog.byCode(profile.cityCode)
        val courseId = courseIdFor(profile.goal, profile.language)

        runCatching {
            repository.saveOnboardingData(
                language = profile.language.dbValue,
                level = profile.level.dbValue,
                goal = profile.goal.dbValue,
                dailyMinutes = profile.dailyMinutes,
                courseId = courseId,
            )
            repository.initCourseProgress(courseId)
        }

        context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .edit()
            // Лидерборд по-прежнему группирует по таджикскому названию,
            // код лежит рядом — на него перейдут вместе с рейтингом.
            .putString("user_city", city.tajikName)
            .putString("user_city_code", city.code)
            .apply()

        // Без сети set() встаёт в локальную очередь Firestore и await()
        // не возвращается вовсе. Держать на этом человека нельзя:
        // ждём три секунды и уходим дальше, а очередь досинхронизируется
        // сама, когда сеть появится.
        withTimeoutOrNull(SAVE_TIMEOUT_MS) {
            UserProfileRepository.saveOnboarding(profile)
        }

        // Карточка в рейтинге: сюда человек попадает уже с именем,
        // безымянных аккаунтов больше не бывает.
        AuthRepository.currentUid?.let { uid ->
            FirestoreManager.saveUserProfile(
                uid, AuthRepository.displayName, city.tajikName, 0,
            )
        }

        runCatching { NotificationScheduler.scheduleDailyReminders(context) }
    }
}
