package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Очки и серия отдельно для каждого изучаемого языка.
 * В [UserEntity] те же поля остаются общими по аккаунту: их показывает
 * рейтинг и шлёт облако, а главный экран и профиль читают эту таблицу.
 */
@Entity(tableName = "language_stats")
data class LanguageStatsEntity(
    @PrimaryKey val language: String,
    val totalXp: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
)
