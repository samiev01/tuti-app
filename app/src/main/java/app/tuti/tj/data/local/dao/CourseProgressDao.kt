package app.tuti.tj.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.ModuleProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseProgressDao {

    // ── lesson progress ────────────────────────────

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLessonProgress(progress: LessonProgressEntity)

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    fun getLessonProgress(lessonId: String): Flow<LessonProgressEntity?>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    suspend fun getLessonProgressOnce(lessonId: String): LessonProgressEntity?

    @Query("SELECT * FROM lesson_progress WHERE courseId = :courseId ORDER BY id ASC")
    fun getAllLessonProgress(courseId: String): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE courseId = :courseId ORDER BY id ASC")
    suspend fun getAllLessonProgressOnce(courseId: String): List<LessonProgressEntity>

    @Query("SELECT * FROM lesson_progress WHERE moduleId = :moduleId ORDER BY id ASC")
    fun getLessonProgressForModule(moduleId: String): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE moduleId = :moduleId ORDER BY id ASC")
    suspend fun getLessonProgressForModuleOnce(moduleId: String): List<LessonProgressEntity>

    @Query(
        "UPDATE lesson_progress SET completed = 1, stars = :stars, score = :score, " +
                "xpEarned = :xpEarned, completedAt = :completedAt WHERE lessonId = :lessonId"
    )
    suspend fun markLessonCompleted(
        lessonId: String,
        stars: Int,
        score: Int,
        xpEarned: Int,
        completedAt: Long = System.currentTimeMillis(),
    )

    @Query("SELECT * FROM lesson_progress WHERE courseId = :courseId AND completed = 0 ORDER BY id ASC LIMIT 1")
    suspend fun getNextUncompletedLesson(courseId: String): LessonProgressEntity?

    /**
     * Снимает отметку о прохождении. xpEarned остаётся как след того,
     * что очки за урок уже начислены — забирать их назад мы не хотим.
     */
    @Query(
        "UPDATE lesson_progress SET completed = 0, stars = 0, score = 0, completedAt = NULL " +
                "WHERE lessonId = :lessonId"
    )
    suspend fun clearLessonCompletion(lessonId: String)

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE courseId = :courseId AND completed = 1")
    fun getCompletedLessonsCount(courseId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE courseId = :courseId")
    fun getTotalLessonsCount(courseId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE completed = 1")
    fun getGlobalCompletedLessonsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE completed = 1 AND courseId LIKE '%' || :lang || '%'")
    fun getCompletedLessonsCountForLanguageCourse(lang: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE completed = 1 AND courseId LIKE '%' || :lang || '%'")
    suspend fun getCompletedLessonsCountForLanguageCourseOnce(lang: String): Int

    // ── module progress ────────────────────────────

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertModuleProgress(progress: ModuleProgressEntity)

    @Query("SELECT * FROM module_progress WHERE moduleId = :moduleId LIMIT 1")
    fun getModuleProgress(moduleId: String): Flow<ModuleProgressEntity?>

    @Query("SELECT * FROM module_progress WHERE courseId = :courseId ORDER BY id ASC")
    fun getCourseModuleProgress(courseId: String): Flow<List<ModuleProgressEntity>>

    @Query("SELECT * FROM module_progress WHERE courseId = :courseId ORDER BY id ASC")
    suspend fun getCourseModuleProgressOnce(courseId: String): List<ModuleProgressEntity>

    @Query(
        "UPDATE module_progress SET lessonsCompleted = :lessonsCompleted, " +
                "completed = CASE WHEN :lessonsCompleted >= totalLessons THEN 1 ELSE 0 END " +
                "WHERE moduleId = :moduleId"
    )
    suspend fun updateModuleProgress(moduleId: String, lessonsCompleted: Int)

    @Query("SELECT COUNT(*) FROM module_progress WHERE completed = 1")
    fun getGlobalCompletedModulesCount(): Flow<Int>
}
