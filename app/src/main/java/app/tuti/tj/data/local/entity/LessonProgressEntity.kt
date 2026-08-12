package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson_progress",
    indices = [Index(value = ["lessonId"], unique = true)],
)
data class LessonProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val lessonId: String,
    val courseId: String,
    val moduleId: String,
    val completed: Boolean = false,
    val stars: Int = 0,
    val score: Int = 0,
    val xpEarned: Int = 0,
    val completedAt: Long? = null,
)
