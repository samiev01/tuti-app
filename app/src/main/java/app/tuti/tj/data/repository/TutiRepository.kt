package app.tuti.tj.data.repository

import android.util.Log
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.FreeTopicsRegistry
import app.tuti.tj.data.local.dao.CourseProgressDao
import app.tuti.tj.data.local.dao.ProgressDao
import app.tuti.tj.data.local.dao.UserDao
import app.tuti.tj.data.local.dao.WordDao
import app.tuti.tj.data.local.entity.DailyStreakEntity
import app.tuti.tj.data.local.entity.LearnedWordEntity
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.ModuleProgressEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.local.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TutiRepository(
    private val userDao: UserDao,
    private val progressDao: ProgressDao,
    private val wordDao: WordDao,
    private val courseProgressDao: CourseProgressDao,
) {

    // ── user ──────────────────────────────────────

    fun getUserFlow(): Flow<UserEntity?> = userDao.getUser()

    suspend fun getUserOnce(): UserEntity? = userDao.getUserOnce()

    suspend fun saveOnboardingData(
        language: String,
        level: String,
        goal: String,
        dailyMinutes: Int,
        courseId: String = "",
    ) = withContext(Dispatchers.IO) {
        val user = UserEntity(
            id = 1,
            selectedLanguage = language,
            level = level,
            goal = goal,
            dailyGoalMinutes = dailyMinutes,
            onboardingCompleted = true,
            createdAt = System.currentTimeMillis(),
            courseId = courseId,
        )
        userDao.insertUser(user)
    }

    suspend fun isOnboardingCompleted(): Boolean = withContext(Dispatchers.IO) {
        userDao.getUserOnce()?.onboardingCompleted == true
    }

    suspend fun addXp(amount: Int) = withContext(Dispatchers.IO) {
        userDao.updateXp(amount)
    }

    /**
     * Sets the study language and picks the matching course for the user's goal.
     * No-op if [targetLanguage] is already selected.
     */
    suspend fun setLanguage(targetLanguage: String) = withContext(Dispatchers.IO) {
        val user = userDao.getUserOnce() ?: return@withContext
        if (user.selectedLanguage == targetLanguage) return@withContext
        val goal = user.goal
        val idealCourseId = "${goal}_$targetLanguage"
        val course = ContentProvider.getCourseById(idealCourseId)
        val courseId = if (course != null) idealCourseId
            else ContentProvider.getAllCourses(targetLanguage).firstOrNull()?.id ?: idealCourseId
        userDao.updateLanguageAndCourse(targetLanguage, courseId)
        if (ContentProvider.getCourseById(courseId) != null) {
            initCourseProgress(courseId)
        }
    }

    suspend fun switchLanguage() = withContext(Dispatchers.IO) {
        val user = userDao.getUserOnce() ?: return@withContext
        val newLang = if (user.selectedLanguage == "english") "russian" else "english"
        setLanguage(newLang)
    }

    // ── topic progress ────────────────────────────

    fun getTopicProgress(language: String): Flow<List<TopicProgressEntity>> =
        progressDao.getAllProgress(language)

    /**
     * Seeds the topic_progress table for [language] if it has no rows yet.
     * Safe to call repeatedly — does nothing when rows already exist.
     * Fixes the PrepopulateCallback timing issue where INSTANCE was null during onCreate.
     */
    suspend fun ensureTopicsExist(language: String) = withContext(Dispatchers.IO) {
        val existing = progressDao.getAllProgressOnce(language)

        val expectedIds = FreeTopicsRegistry.expectedTopicIds(language)
        if (language == "english") {
            val enTopicIds = existing.filter { it.topicId.startsWith("en_") }.map { it.topicId }.toSet()
            if (enTopicIds.containsAll(expectedIds)) return@withContext
            if (existing.isNotEmpty()) progressDao.deleteAllProgressForLanguage(language)
        } else {
            if (existing.isNotEmpty()) return@withContext
        }

        FreeTopicsRegistry.definitionsFor(language).map { def ->
            TopicProgressEntity(
                topicId = def.id,
                topicName = def.nameTj,
                language = language,
                isUnlocked = def.defaultUnlocked,
            )
        }.forEach { progressDao.insertProgress(it) }
    }

    fun getCompletedTopicsCount(language: String): Flow<Int> =
        progressDao.getCompletedTopicsCount(language)

    suspend fun recordLessonComplete(
        topicId: String,
        language: String,
        correctAnswers: Int,
        totalQuestions: Int,
        xpEarned: Int,
    ) = withContext(Dispatchers.IO) {
        val existing = progressDao.getTopicProgressOnce(topicId, language)
        val newCorrect = (existing?.correctAnswers ?: 0) + correctAnswers
        val newTotal = (existing?.totalQuestions ?: 0) + totalQuestions
        val newPercent = if (newTotal > 0) (newCorrect * 100) / newTotal else 0

        progressDao.updateProgress(
            topicId = topicId,
            language = language,
            progressPercent = newPercent.coerceAtMost(100),
            correctAnswers = newCorrect,
        )

        userDao.updateXp(xpEarned)

        if (newPercent >= 70) {
            unlockNextTopic(topicId, language)
        }

        updateDailyStreak(xpEarned = xpEarned, lessonsCompleted = 1)
        checkAndUpdateStreak()
    }

    private suspend fun unlockNextTopic(currentTopicId: String, language: String) {
        val topicOrder = listOf(
            "greetings", "numbers", "food", "family", "city",
            "colors", "time", "animals", "clothes", "body",
            "nature", "professions", "fruits", "emotions", "home",
        )
        val currentIndex = topicOrder.indexOf(currentTopicId)
        if (currentIndex in 0 until topicOrder.lastIndex) {
            progressDao.unlockTopic(topicOrder[currentIndex + 1], language)
        }
    }

    // ── words ─────────────────────────────────────

    fun getTotalLearnedWords(): Flow<Int> = wordDao.getTotalLearnedWords()

    fun getWordsForReview(currentTime: Long): Flow<List<LearnedWordEntity>> =
        wordDao.getWordsForReview(currentTime)

    /** Returns all saved words, prioritising due reviews, for the flashcard deck. */
    suspend fun getFlashcardDeck(): List<LearnedWordEntity> = withContext(Dispatchers.IO) {
        wordDao.getAllLearnedWordsSorted()
    }

    /** Called when user taps "✅ Медонам" on a flashcard. */
    suspend fun markWordKnown(word: LearnedWordEntity) = withContext(Dispatchers.IO) {
        val newCorrect = word.correctCount + 1
        val intervalHours = when {
            newCorrect >= 5 -> 168L  // 1 week
            newCorrect >= 3 -> 48L   // 2 days
            newCorrect >= 1 -> 12L
            else -> 1L
        }
        wordDao.updateWordStats(
            wordId = word.id,
            correctCount = newCorrect,
            wrongCount = word.wrongCount,
            nextReviewAt = System.currentTimeMillis() + intervalHours * 3_600_000L,
        )
    }

    /** Called when user taps "❌ Намедонам" on a flashcard. */
    suspend fun markWordUnknown(word: LearnedWordEntity) = withContext(Dispatchers.IO) {
        wordDao.updateWordStats(
            wordId = word.id,
            correctCount = 0,
            wrongCount = word.wrongCount + 1,
            nextReviewAt = System.currentTimeMillis(), // review again immediately
        )
    }

    suspend fun addLearnedWord(
        word: String,
        translation: String,
        language: String,
        topicId: String,
        isCorrect: Boolean,
    ) = withContext(Dispatchers.IO) {
        try {
            Log.d("TutiRepo", "addLearnedWord: word='$word' trans='$translation' lang=$language topic=$topicId correct=$isCorrect")
            val existing = wordDao.getWordByText(word, language)
            if (existing != null) {
                Log.d("TutiRepo", "  Word exists (id=${existing.id}), updating stats")
                val newCorrect = existing.correctCount + if (isCorrect) 1 else 0
                val newWrong = existing.wrongCount + if (isCorrect) 0 else 1
                val intervalHours = when {
                    newCorrect >= 5 -> 168L
                    newCorrect >= 3 -> 48L
                    newCorrect >= 1 -> 12L
                    else -> 1L
                }
                wordDao.updateWordStats(
                    wordId = existing.id,
                    correctCount = newCorrect,
                    wrongCount = newWrong,
                    nextReviewAt = System.currentTimeMillis() + intervalHours * 3_600_000L,
                )
            } else {
                Log.d("TutiRepo", "  New word, inserting")
                wordDao.insertWord(
                    LearnedWordEntity(
                        word = word,
                        translation = translation,
                        language = language,
                        topicId = topicId,
                        correctCount = if (isCorrect) 1 else 0,
                        wrongCount = if (isCorrect) 0 else 1,
                        nextReviewAt = System.currentTimeMillis() + 3_600_000L,
                    )
                )
                Log.d("TutiRepo", "  Insert OK")
            }
        } catch (e: Exception) {
            Log.e("TutiRepo", "addLearnedWord CRASHED for '$word'", e)
        }
    }

    // ── course progress ───────────────────────────

    suspend fun initCourseProgress(courseId: String) = withContext(Dispatchers.IO) {
        val course = ContentProvider.getCourseById(courseId) ?: return@withContext
        for (module in course.modules) {
            courseProgressDao.insertModuleProgress(
                ModuleProgressEntity(
                    moduleId = module.id,
                    courseId = course.id,
                    totalLessons = module.lessons.size,
                )
            )
            for (lesson in module.lessons) {
                courseProgressDao.insertLessonProgress(
                    LessonProgressEntity(
                        lessonId = lesson.id,
                        courseId = course.id,
                        moduleId = module.id,
                    )
                )
            }
        }
        // Чиним прогресс, испорченный старой логикой открытия уроков
        // (и тот, что приедет из облака): пройденное должно идти подряд.
        CourseProgressRepair.repairLessonOrder(courseProgressDao, course.id)
    }

    fun getAllLessonProgress(courseId: String): Flow<List<LessonProgressEntity>> =
        courseProgressDao.getAllLessonProgress(courseId)

    fun getCourseModuleProgress(courseId: String): Flow<List<ModuleProgressEntity>> =
        courseProgressDao.getCourseModuleProgress(courseId)

    fun getLessonProgressForModule(moduleId: String): Flow<List<LessonProgressEntity>> =
        courseProgressDao.getLessonProgressForModule(moduleId)

    fun getCompletedLessonsCount(courseId: String): Flow<Int> =
        courseProgressDao.getCompletedLessonsCount(courseId)

    fun getTotalLessonsCount(courseId: String): Flow<Int> =
        courseProgressDao.getTotalLessonsCount(courseId)

    fun getGlobalCompletedLessonsCount(): Flow<Int> =
        courseProgressDao.getGlobalCompletedLessonsCount()

    fun getGlobalCompletedModulesCount(): Flow<Int> =
        courseProgressDao.getGlobalCompletedModulesCount()

    fun getCompletedLessonsCountForLanguageCourse(languageToken: String): Flow<Int> =
        courseProgressDao.getCompletedLessonsCountForLanguageCourse(languageToken)

    /**
     * Russian vs English “started” for achievements: topic completed or any lesson completed
     * in a course whose id contains the token.
     */
    suspend fun computeLanguagesStartedCount(): Int = withContext(Dispatchers.IO) {
        try {
            val ruTopics = progressDao.getCompletedTopicsCountOnce("russian")
            val enTopics = progressDao.getCompletedTopicsCountOnce("english")
            val ruLessons = courseProgressDao.getCompletedLessonsCountForLanguageCourseOnce("russian")
            val enLessons = courseProgressDao.getCompletedLessonsCountForLanguageCourseOnce("english")
            var n = 0
            if (ruTopics > 0 || ruLessons > 0) n++
            if (enTopics > 0 || enLessons > 0) n++
            n
        } catch (_: Exception) {
            0
        }
    }

    /**
     * Следующий урок берём по порядку контента, а не по порядку строк в БД:
     * после восстановления прогресса из облака id строк идут вразнобой.
     */
    suspend fun getNextUncompletedLesson(courseId: String): LessonProgressEntity? =
        withContext(Dispatchers.IO) {
            val rows = courseProgressDao.getAllLessonProgressOnce(courseId)
            val completedIds = rows.filter { it.completed }.map { it.lessonId }.toSet()
            val nextId = ContentProvider.getNextLessonId(courseId, completedIds)
                ?: return@withContext null
            rows.find { it.lessonId == nextId }
                ?: LessonProgressEntity(
                    lessonId = nextId,
                    courseId = courseId,
                    moduleId = ContentProvider.getLesson(nextId)?.moduleId ?: "",
                )
        }

    suspend fun saveLessonResult(
        lessonId: String,
        stars: Int,
        score: Int,
        xpEarned: Int,
    ) = withContext(Dispatchers.IO) {
        courseProgressDao.markLessonCompleted(lessonId, stars, score, xpEarned)
        userDao.updateXp(xpEarned)

        val lesson = courseProgressDao.getLessonProgressOnce(lessonId) ?: return@withContext
        val moduleLessons = courseProgressDao.getLessonProgressForModuleOnce(lesson.moduleId)
        val completedCount = moduleLessons.count { it.completed }
        courseProgressDao.updateModuleProgress(lesson.moduleId, completedCount)

        // Слова урока попадают в словарь сразу: карточка результата обещает
        // «N калима», и профиль должен показать их не дожидаясь бэкфилла.
        saveLessonWords(lessonId, lesson.courseId)

        updateDailyStreak(xpEarned = xpEarned, lessonsCompleted = 1)
        checkAndUpdateStreak()
    }

    suspend fun isLessonUnlocked(lessonId: String, courseId: String): Boolean =
        withContext(Dispatchers.IO) {
            val completedIds = courseProgressDao.getAllLessonProgressOnce(courseId)
                .filter { it.completed }
                .map { it.lessonId }
                .toSet()
            lessonId in ContentProvider.getUnlockedLessonIds(courseId, completedIds)
        }

    /**
     * Кладёт новые слова пройденного урока в словарь.
     * Уже известные слова не трогаем — иначе повторное прохождение
     * урока накручивало бы статистику повторений.
     *
     * @return сколько слов добавлено.
     */
    private suspend fun saveLessonWords(lessonId: String, courseId: String): Int {
        val lesson = ContentProvider.getLesson(lessonId) ?: return 0
        val language = ContentProvider.getCourseById(courseId)?.language ?: "russian"
        var inserted = 0
        for (w in lesson.newWords) {
            if (wordDao.getWordByText(w.word, language) != null) continue
            wordDao.insertWord(
                LearnedWordEntity(
                    word = w.word,
                    translation = w.translation,
                    language = language,
                    topicId = lesson.id,
                    correctCount = 1,
                    wrongCount = 0,
                    nextReviewAt = System.currentTimeMillis() + 3_600_000L,
                )
            )
            inserted++
        }
        return inserted
    }

    /** Догоняет словарь для уроков, пройденных до того, как слова стали сохраняться сразу. */
    suspend fun backfillLearnedWordsFromCourses() = withContext(Dispatchers.IO) {
        try {
            val user = userDao.getUserOnce()
            if (user == null) { Log.d("TutiRepo", "backfill: no user"); return@withContext }
            val courseId = user.courseId
            if (courseId.isNullOrBlank()) { Log.d("TutiRepo", "backfill: no courseId"); return@withContext }
            val course = ContentProvider.getCourseById(courseId)
            if (course == null) { Log.d("TutiRepo", "backfill: course not found $courseId"); return@withContext }
            val allProgress = courseProgressDao.getAllLessonProgressOnce(courseId)
            Log.d("TutiRepo", "backfill: ${allProgress.size} lesson records, ${allProgress.count { it.completed }} completed")
            val completedLessons = allProgress.filter { it.completed }.map { it.lessonId }.toSet()
            var inserted = 0
            for (lesson in course.modules.flatMap { it.lessons }) {
                if (lesson.id !in completedLessons) continue
                inserted += saveLessonWords(lesson.id, courseId)
            }
            Log.d("TutiRepo", "backfill: inserted $inserted new words")
        } catch (e: Exception) {
            Log.e("TutiRepo", "backfill FAILED", e)
        }
    }

    // ── streaks ───────────────────────────────────

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun getTodayStats(): Flow<DailyStreakEntity?> =
        progressDao.getTodayStats(todayString())

    fun getWeekStreaks(): Flow<List<DailyStreakEntity>> =
        progressDao.getWeekStreaks()

    private suspend fun updateDailyStreak(xpEarned: Int, lessonsCompleted: Int) {
        val today = todayString()
        val existing = progressDao.getDailyStreakOnce(today)
        if (existing != null) {
            progressDao.insertDailyStreak(
                existing.copy(
                    xpEarned = existing.xpEarned + xpEarned,
                    lessonsCompleted = existing.lessonsCompleted + lessonsCompleted,
                    minutesStudied = existing.minutesStudied + 1,
                )
            )
        } else {
            progressDao.insertDailyStreak(
                DailyStreakEntity(
                    date = today,
                    xpEarned = xpEarned,
                    lessonsCompleted = lessonsCompleted,
                    minutesStudied = 1,
                )
            )
        }
    }

    suspend fun checkAndUpdateStreak() = withContext(Dispatchers.IO) {
        val user = userDao.getUserOnce() ?: return@withContext
        val today = todayString()
        val yesterday = run {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_YEAR, -1)
            dateFormat.format(cal.time)
        }

        val studiedToday = progressDao.getDailyStreakOnce(today) != null
        val studiedYesterday = progressDao.getDailyStreakOnce(yesterday) != null

        val newStreak = when {
            studiedToday && studiedYesterday -> user.currentStreak.coerceAtLeast(1)
            studiedToday && !studiedYesterday -> 1
            else -> 0
        }

        if (studiedToday && newStreak == 1 && user.currentStreak == 0) {
            val updatedStreak = user.currentStreak + 1
            val longest = maxOf(updatedStreak, user.longestStreak)
            userDao.updateStreakWithLongest(updatedStreak, longest)
        } else if (studiedToday && studiedYesterday && newStreak >= user.currentStreak) {
            val updatedStreak = user.currentStreak + 1
            val longest = maxOf(updatedStreak, user.longestStreak)
            userDao.updateStreakWithLongest(updatedStreak, longest)
        }
    }

    private fun todayString(): String = dateFormat.format(System.currentTimeMillis())
}
