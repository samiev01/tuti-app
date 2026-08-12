package app.tuti.tj.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "module_progress",
    indices = [Index(value = ["moduleId"], unique = true)],
)
data class ModuleProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val moduleId: String,
    val courseId: String,
    val completed: Boolean = false,
    val lessonsCompleted: Int = 0,
    val totalLessons: Int = 0,
)
