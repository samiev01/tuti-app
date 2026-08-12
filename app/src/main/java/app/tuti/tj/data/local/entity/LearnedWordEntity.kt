package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learned_words")
data class LearnedWordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val translation: String,
    val language: String,
    val topicId: String,
    val correctCount: Int = 0,
    val wrongCount: Int = 0,
    val nextReviewAt: Long = System.currentTimeMillis(),
    val lastReviewedAt: Long? = null,
)
