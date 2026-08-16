package app.tuti.tj.data.sync

import android.content.Context
import android.util.Log
import app.tuti.tj.data.local.TutiDatabase
import app.tuti.tj.data.local.entity.LearnedWordEntity
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.repository.CourseProgressRepair
import app.tuti.tj.data.subscription.PlusManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object CloudSyncManager {

    private const val TAG = "CloudSync"
    private val db by lazy { Firebase.firestore }

    // ═══════════════════════════════════════════════════
    //  SAVE
    // ═══════════════════════════════════════════════════

    suspend fun saveProgress(context: Context) {
        val userId = Firebase.auth.currentUser?.uid ?: return

        withContext(Dispatchers.IO) {
            try {
                val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                val plusPrefs = context.getSharedPreferences("tuti_plus", Context.MODE_PRIVATE)

                val roomDb = TutiDatabase.getDatabase(context)
                val user = roomDb.userDao().getUserOnce()

                val progressData = hashMapOf<String, Any?>(
                    "selectedLanguage" to (user?.selectedLanguage ?: prefs.getString("selected_language", "russian")),
                    "selectedGoal" to (user?.goal ?: prefs.getString("user_goal", "work")),
                    "selectedLevel" to (user?.level ?: prefs.getString("user_level", "beginner")),
                    "selectedTime" to (user?.dailyGoalMinutes ?: prefs.getInt("daily_time", 10)),
                    "selectedCity" to (prefs.getString("user_city", "") ?: ""),
                    "courseId" to (user?.courseId ?: ""),
                    "userName" to (user?.name ?: ""),
                    "totalXp" to (user?.totalXp ?: 0),
                    "currentStreak" to (user?.currentStreak ?: 0),
                    "longestStreak" to (user?.longestStreak ?: 0),
                    "lastStudyDate" to (prefs.getString("last_study_date", "") ?: ""),
                    "plusExpiry" to plusPrefs.getLong("plus_expiry", 0),
                    "isPlusUser" to PlusManager.isPlusActive(context),
                    "soundEnabled" to prefs.getBoolean("sounds_enabled", true),
                    "themeMode" to (prefs.getString("theme_mode", "system") ?: "system"),
                    "reminderEnabled" to prefs.getBoolean("reminders_enabled", false),
                    "reminderHour" to prefs.getInt("reminder_hour", 19),
                    "reminderMinute" to prefs.getInt("reminder_minute", 0),
                    "tooltipsShown" to prefs.getBoolean("tooltips_shown", false),
                    "onboardingCompleted" to (user?.onboardingCompleted ?: false),
                    "lastSync" to FieldValue.serverTimestamp(),
                )

                db.collection("users").document(userId)
                    .collection("sync")
                    .document("progress")
                    .set(progressData, SetOptions.merge())
                    .await()

                saveLessonProgress(context, userId)
                saveTopicProgress(context, userId)
                saveLearnedWords(context, userId)

                Log.d(TAG, "Progress saved successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Save error", e)
            }
        }
    }

    private suspend fun saveLessonProgress(context: Context, userId: String) {
        try {
            val roomDb = TutiDatabase.getDatabase(context)
            val user = roomDb.userDao().getUserOnce() ?: return
            val courseId = user.courseId
            if (courseId.isBlank()) return

            val lessons = roomDb.courseProgressDao().getAllLessonProgressOnce(courseId)
            if (lessons.isEmpty()) return

            val lessonsData = hashMapOf<String, Any>()
            lessons.forEach { lp ->
                lessonsData[lp.lessonId] = hashMapOf(
                    "courseId" to lp.courseId,
                    "moduleId" to lp.moduleId,
                    "completed" to lp.completed,
                    "stars" to lp.stars,
                    "score" to lp.score,
                    "xpEarned" to lp.xpEarned,
                    "completedAt" to (lp.completedAt ?: 0L),
                )
            }

            db.collection("users").document(userId)
                .collection("sync")
                .document("lessons")
                .set(lessonsData, SetOptions.merge())
                .await()
        } catch (e: Exception) {
            Log.e(TAG, "Lesson save error", e)
        }
    }

    private suspend fun saveTopicProgress(context: Context, userId: String) {
        try {
            val roomDb = TutiDatabase.getDatabase(context)
            val user = roomDb.userDao().getUserOnce() ?: return
            val lang = user.selectedLanguage
            val topics = roomDb.progressDao().getAllProgressOnce(lang)
            if (topics.isEmpty()) return

            val topicsData = hashMapOf<String, Any>()
            topics.forEach { tp ->
                topicsData[tp.topicId] = hashMapOf(
                    "topicName" to tp.topicName,
                    "language" to tp.language,
                    "progressPercent" to tp.progressPercent,
                    "totalQuestions" to tp.totalQuestions,
                    "correctAnswers" to tp.correctAnswers,
                    "isUnlocked" to tp.isUnlocked,
                    "lastStudiedAt" to (tp.lastStudiedAt ?: 0L),
                )
            }

            db.collection("users").document(userId)
                .collection("sync")
                .document("topics")
                .set(topicsData, SetOptions.merge())
                .await()
        } catch (e: Exception) {
            Log.e(TAG, "Topic save error", e)
        }
    }

    private suspend fun saveLearnedWords(context: Context, userId: String) {
        try {
            val roomDb = TutiDatabase.getDatabase(context)
            val words = roomDb.wordDao().getAllLearnedWordsSorted()
            if (words.isEmpty()) return

            val wordsData = hashMapOf<String, Any>()
            words.forEach { w ->
                val key = "${w.language}_${w.word}".replace("/", "_").replace(".", "_")
                wordsData[key] = hashMapOf(
                    "word" to w.word,
                    "translation" to w.translation,
                    "language" to w.language,
                    "topicId" to w.topicId,
                    "correctCount" to w.correctCount,
                    "wrongCount" to w.wrongCount,
                    "nextReviewAt" to w.nextReviewAt,
                    "lastReviewedAt" to (w.lastReviewedAt ?: 0L),
                )
            }

            db.collection("users").document(userId)
                .collection("sync")
                .document("words")
                .set(wordsData, SetOptions.merge())
                .await()
        } catch (e: Exception) {
            Log.e(TAG, "Words save error", e)
        }
    }

    // ═══════════════════════════════════════════════════
    //  RESTORE
    // ═══════════════════════════════════════════════════

    suspend fun restoreProgress(context: Context): Boolean {
        val userId = Firebase.auth.currentUser?.uid ?: return false

        return withContext(Dispatchers.IO) {
            try {
                val progressDoc = db.collection("users").document(userId)
                    .collection("sync")
                    .document("progress")
                    .get()
                    .await()

                if (!progressDoc.exists()) return@withContext false

                val onboardingDone = progressDoc.getBoolean("onboardingCompleted") ?: false
                if (!onboardingDone) return@withContext false

                val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                val editor = prefs.edit()

                progressDoc.getString("selectedLanguage")?.let { editor.putString("selected_language", it) }
                progressDoc.getString("selectedGoal")?.let { editor.putString("user_goal", it) }
                progressDoc.getString("selectedLevel")?.let { editor.putString("user_level", it) }
                progressDoc.getLong("selectedTime")?.toInt()?.let { editor.putInt("daily_time", it) }
                progressDoc.getString("selectedCity")?.let { editor.putString("user_city", it) }
                progressDoc.getLong("currentStreak")?.toInt()?.let { editor.putInt("current_streak", it) }
                progressDoc.getString("lastStudyDate")?.let { editor.putString("last_study_date", it) }
                progressDoc.getBoolean("soundEnabled")?.let { editor.putBoolean("sounds_enabled", it) }
                progressDoc.getString("themeMode")?.let { editor.putString("theme_mode", it) }
                progressDoc.getBoolean("reminderEnabled")?.let { editor.putBoolean("reminders_enabled", it) }
                progressDoc.getLong("reminderHour")?.toInt()?.let { editor.putInt("reminder_hour", it) }
                progressDoc.getLong("reminderMinute")?.toInt()?.let { editor.putInt("reminder_minute", it) }
                progressDoc.getBoolean("tooltipsShown")?.let { editor.putBoolean("tooltips_shown", it) }
                editor.putBoolean("onboarding_completed", true)
                editor.apply()

                val plusExpiry = progressDoc.getLong("plusExpiry") ?: 0L
                if (plusExpiry > System.currentTimeMillis()) {
                    val plusPrefs = context.getSharedPreferences("tuti_plus", Context.MODE_PRIVATE)
                    plusPrefs.edit()
                        .putLong("plus_expiry", plusExpiry)
                        .putBoolean("is_plus", true)
                        .apply()
                }

                val roomDb = TutiDatabase.getDatabase(context)

                val selectedLang = progressDoc.getString("selectedLanguage") ?: "russian"
                val courseId = progressDoc.getString("courseId") ?: ""
                val userName = progressDoc.getString("userName") ?: Firebase.auth.currentUser?.displayName ?: "Tuti"
                val totalXp = progressDoc.getLong("totalXp")?.toInt() ?: 0
                val currentStreak = progressDoc.getLong("currentStreak")?.toInt() ?: 0
                val longestStreak = progressDoc.getLong("longestStreak")?.toInt() ?: 0
                val selectedGoal = progressDoc.getString("selectedGoal") ?: "personal"
                val selectedLevel = progressDoc.getString("selectedLevel") ?: "beginner"
                val dailyTime = progressDoc.getLong("selectedTime")?.toInt() ?: 10

                roomDb.userDao().insertUser(
                    app.tuti.tj.data.local.entity.UserEntity(
                        id = 1,
                        name = userName,
                        selectedLanguage = selectedLang,
                        level = selectedLevel,
                        goal = selectedGoal,
                        dailyGoalMinutes = dailyTime,
                        totalXp = totalXp,
                        currentStreak = currentStreak,
                        longestStreak = longestStreak,
                        onboardingCompleted = true,
                        courseId = courseId,
                    )
                )

                // В облаке цифры общие по аккаунту — отдаём их языку, который
                // был выбран при выгрузке: локально статистика теперь по языкам.
                roomDb.languageStatsDao().insertStats(
                    app.tuti.tj.data.local.entity.LanguageStatsEntity(
                        language = if (selectedLang == "english") "english" else "russian",
                        totalXp = totalXp,
                        currentStreak = currentStreak,
                        longestStreak = longestStreak,
                    )
                )

                restoreTopicProgress(context, userId)
                restoreLessonProgress(context, userId)
                restoreLearnedWords(context, userId)

                Log.d(TAG, "Progress restored successfully")
                true
            } catch (e: Exception) {
                Log.e(TAG, "Restore error", e)
                false
            }
        }
    }

    private suspend fun restoreLessonProgress(context: Context, userId: String) {
        try {
            val doc = db.collection("users").document(userId)
                .collection("sync")
                .document("lessons")
                .get()
                .await()

            if (!doc.exists()) return

            val roomDb = TutiDatabase.getDatabase(context)
            val restoredCourseIds = mutableSetOf<String>()
            doc.data?.forEach { (lessonId, value) ->
                @Suppress("UNCHECKED_CAST")
                val data = value as? Map<String, Any> ?: return@forEach
                val progress = LessonProgressEntity(
                    lessonId = lessonId,
                    courseId = data["courseId"] as? String ?: "",
                    moduleId = data["moduleId"] as? String ?: "",
                    completed = data["completed"] as? Boolean ?: false,
                    stars = (data["stars"] as? Long)?.toInt() ?: 0,
                    score = (data["score"] as? Long)?.toInt() ?: 0,
                    xpEarned = (data["xpEarned"] as? Long)?.toInt() ?: 0,
                    completedAt = data["completedAt"] as? Long,
                )
                roomDb.courseProgressDao().insertLessonProgress(progress)
                if (progress.completed) {
                    roomDb.courseProgressDao().markLessonCompleted(
                        lessonId = progress.lessonId,
                        stars = progress.stars,
                        score = progress.score,
                        xpEarned = 0,
                        completedAt = progress.completedAt ?: System.currentTimeMillis(),
                    )
                }
                restoredCourseIds.add(progress.courseId)
            }

            // Из облака строки приходят в произвольном порядке, а часть
            // прогресса была получена ещё старой логикой открытия уроков —
            // приводим порядок прохождения в порядок сразу после загрузки.
            restoredCourseIds.filter { it.isNotBlank() }.forEach { courseId ->
                CourseProgressRepair.repairLessonOrder(
                    roomDb.courseProgressDao(),
                    courseId,
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Lesson restore error", e)
        }
    }

    private suspend fun restoreTopicProgress(context: Context, userId: String) {
        try {
            val doc = db.collection("users").document(userId)
                .collection("sync")
                .document("topics")
                .get()
                .await()

            if (!doc.exists()) return

            val roomDb = TutiDatabase.getDatabase(context)
            doc.data?.forEach { (topicId, value) ->
                @Suppress("UNCHECKED_CAST")
                val data = value as? Map<String, Any> ?: return@forEach
                val topic = TopicProgressEntity(
                    topicId = topicId,
                    topicName = data["topicName"] as? String ?: topicId,
                    language = data["language"] as? String ?: "russian",
                    progressPercent = (data["progressPercent"] as? Long)?.toInt() ?: 0,
                    totalQuestions = (data["totalQuestions"] as? Long)?.toInt() ?: 0,
                    correctAnswers = (data["correctAnswers"] as? Long)?.toInt() ?: 0,
                    isUnlocked = data["isUnlocked"] as? Boolean ?: false,
                    lastStudiedAt = data["lastStudiedAt"] as? Long,
                )
                roomDb.progressDao().insertProgress(topic)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Topic restore error", e)
        }
    }

    private suspend fun restoreLearnedWords(context: Context, userId: String) {
        try {
            val doc = db.collection("users").document(userId)
                .collection("sync")
                .document("words")
                .get()
                .await()

            if (!doc.exists()) return

            val roomDb = TutiDatabase.getDatabase(context)
            doc.data?.forEach { (_, value) ->
                @Suppress("UNCHECKED_CAST")
                val data = value as? Map<String, Any> ?: return@forEach
                val word = LearnedWordEntity(
                    word = data["word"] as? String ?: return@forEach,
                    translation = data["translation"] as? String ?: "",
                    language = data["language"] as? String ?: "russian",
                    topicId = data["topicId"] as? String ?: "",
                    correctCount = (data["correctCount"] as? Long)?.toInt() ?: 0,
                    wrongCount = (data["wrongCount"] as? Long)?.toInt() ?: 0,
                    nextReviewAt = data["nextReviewAt"] as? Long ?: System.currentTimeMillis(),
                    lastReviewedAt = data["lastReviewedAt"] as? Long,
                )
                roomDb.wordDao().insertWord(word)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Words restore error", e)
        }
    }
}
