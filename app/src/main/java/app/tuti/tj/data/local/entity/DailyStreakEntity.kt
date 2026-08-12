package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_streaks")
data class DailyStreakEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val minutesStudied: Int = 0,
    val xpEarned: Int = 0,
    val lessonsCompleted: Int = 0,
)
