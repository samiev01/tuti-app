package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_progress")
data class TopicProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topicId: String,
    val topicName: String,
    val language: String,
    val progressPercent: Int = 0,
    val totalQuestions: Int = 0,
    val correctAnswers: Int = 0,
    val isUnlocked: Boolean = false,
    val lastStudiedAt: Long? = null,
)
