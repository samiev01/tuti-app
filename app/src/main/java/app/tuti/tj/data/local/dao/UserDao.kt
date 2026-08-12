package app.tuti.tj.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.tuti.tj.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = 1")
    fun getUser(): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE id = 1")
    suspend fun getUserOnce(): UserEntity?

    @Query("SELECT * FROM users WHERE id = 1")
    fun getUserSync(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE users SET totalXp = totalXp + :xpToAdd WHERE id = 1")
    suspend fun updateXp(xpToAdd: Int)

    @Query("UPDATE users SET currentStreak = :streak WHERE id = 1")
    suspend fun updateStreak(streak: Int)

    @Query("UPDATE users SET currentStreak = :streak, longestStreak = :longest WHERE id = 1")
    suspend fun updateStreakWithLongest(streak: Int, longest: Int)

    @Query("UPDATE users SET onboardingCompleted = :completed WHERE id = 1")
    suspend fun setOnboardingCompleted(completed: Boolean)

    @Query("UPDATE users SET selectedLanguage = :language, courseId = :courseId WHERE id = 1")
    suspend fun updateLanguageAndCourse(language: String, courseId: String)
}
