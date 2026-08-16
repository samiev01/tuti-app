package app.tuti.tj.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.tuti.tj.data.local.entity.LearnedWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM learned_words WHERE topicId = :topicId")
    fun getWordsByTopic(topicId: String): Flow<List<LearnedWordEntity>>

    @Query("SELECT * FROM learned_words WHERE nextReviewAt <= :currentTime")
    fun getWordsForReview(currentTime: Long): Flow<List<LearnedWordEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: LearnedWordEntity)

    @Query(
        "UPDATE learned_words SET correctCount = :correctCount, wrongCount = :wrongCount, " +
                "nextReviewAt = :nextReviewAt, lastReviewedAt = :lastReviewedAt " +
                "WHERE id = :wordId"
    )
    suspend fun updateWordStats(
        wordId: Int,
        correctCount: Int,
        wrongCount: Int,
        nextReviewAt: Long,
        lastReviewedAt: Long = System.currentTimeMillis(),
    )

    /**
     * Слово считается выученным с первого верного ответа либо когда оно
     * пришло из пройденного урока курса. Порог correctCount >= 3 — это
     * «повторено до автоматизма», для счётчика в профиле он занижал цифру
     * до нуля сразу после урока.
     */
    @Query("SELECT COUNT(*) FROM learned_words WHERE correctCount >= 1")
    fun getTotalLearnedWords(): Flow<Int>

    @Query("SELECT COUNT(*) FROM learned_words WHERE correctCount >= 1 AND language = :language")
    fun getLearnedWordsForLanguage(language: String): Flow<Int>

    @Query("SELECT * FROM learned_words WHERE word = :word AND language = :language LIMIT 1")
    suspend fun getWordByText(word: String, language: String): LearnedWordEntity?

    @Query("SELECT * FROM learned_words ORDER BY nextReviewAt ASC, correctCount ASC")
    suspend fun getAllLearnedWordsSorted(): List<LearnedWordEntity>
}
