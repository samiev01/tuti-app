package app.tuti.tj.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.tuti.tj.data.local.entity.DailyStreakEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {

    @Query("SELECT * FROM topic_progress WHERE language = :language ORDER BY id ASC")
    fun getAllProgress(language: String): Flow<List<TopicProgressEntity>>

    @Query("SELECT * FROM topic_progress WHERE topicId = :topicId AND language = :language LIMIT 1")
    fun getTopicProgress(topicId: String, language: String): Flow<TopicProgressEntity?>

    @Query("SELECT * FROM topic_progress WHERE topicId = :topicId AND language = :language LIMIT 1")
    suspend fun getTopicProgressOnce(topicId: String, language: String): TopicProgressEntity?

    @Query("SELECT * FROM topic_progress WHERE language = :language ORDER BY id ASC")
    suspend fun getAllProgressOnce(language: String): List<TopicProgressEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: TopicProgressEntity)

    @Query(
        "UPDATE topic_progress SET progressPercent = :progressPercent, " +
                "correctAnswers = :correctAnswers, lastStudiedAt = :timestamp " +
                "WHERE topicId = :topicId AND language = :language"
    )
    suspend fun updateProgress(
        topicId: String,
        language: String,
        progressPercent: Int,
        correctAnswers: Int,
        timestamp: Long = System.currentTimeMillis(),
    )

    @Query("UPDATE topic_progress SET isUnlocked = 1 WHERE topicId = :topicId AND language = :language")
    suspend fun unlockTopic(topicId: String, language: String)

    @Query("DELETE FROM topic_progress WHERE language = :language")
    suspend fun deleteAllProgressForLanguage(language: String)

    @Query("SELECT COUNT(*) FROM topic_progress WHERE progressPercent >= 100 AND language = :language")
    fun getCompletedTopicsCount(language: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM topic_progress WHERE progressPercent >= 100 AND language = :language")
    suspend fun getCompletedTopicsCountOnce(language: String): Int

    // ── daily streaks ─────────────────────────────

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyStreak(streak: DailyStreakEntity)

    @Query("SELECT * FROM daily_streaks WHERE date = :date LIMIT 1")
    suspend fun getDailyStreakOnce(date: String): DailyStreakEntity?

    @Query("SELECT * FROM daily_streaks WHERE date = :date LIMIT 1")
    fun getDailyStreakSync(date: String): DailyStreakEntity?

    @Query("SELECT * FROM daily_streaks WHERE date = :date LIMIT 1")
    fun getTodayStats(date: String): Flow<DailyStreakEntity?>

    @Query("SELECT * FROM daily_streaks ORDER BY date DESC LIMIT 7")
    fun getWeekStreaks(): Flow<List<DailyStreakEntity>>
}
