package app.tuti.tj.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.tuti.tj.data.local.entity.LanguageStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageStatsDao {

    @Query("SELECT * FROM language_stats WHERE language = :language LIMIT 1")
    fun getStats(language: String): Flow<LanguageStatsEntity?>

    @Query("SELECT * FROM language_stats WHERE language = :language LIMIT 1")
    suspend fun getStatsOnce(language: String): LanguageStatsEntity?

    @Query("SELECT COUNT(*) FROM language_stats")
    suspend fun countRows(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: LanguageStatsEntity)

    @Query("UPDATE language_stats SET totalXp = totalXp + :xpToAdd WHERE language = :language")
    suspend fun addXp(language: String, xpToAdd: Int)

    @Query(
        "UPDATE language_stats SET currentStreak = :streak, longestStreak = :longest " +
                "WHERE language = :language"
    )
    suspend fun updateStreak(language: String, streak: Int, longest: Int)
}
