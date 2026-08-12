package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Фирӯз",
    val selectedLanguage: String = "russian",
    val level: String = "beginner",
    val goal: String = "personal",
    val dailyGoalMinutes: Int = 5,
    val totalXp: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val onboardingCompleted: Boolean = false,
    val courseId: String = "",
)
