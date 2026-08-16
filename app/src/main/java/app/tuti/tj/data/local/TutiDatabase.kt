package app.tuti.tj.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import app.tuti.tj.data.local.dao.UserDao
import app.tuti.tj.data.local.dao.ProgressDao
import app.tuti.tj.data.local.dao.WordDao
import app.tuti.tj.data.local.dao.CourseProgressDao
import app.tuti.tj.data.local.dao.LanguageStatsDao
import app.tuti.tj.data.local.entity.DailyStreakEntity
import app.tuti.tj.data.local.entity.LanguageStatsEntity
import app.tuti.tj.data.local.entity.LearnedWordEntity
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.ModuleProgressEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TopicProgressEntity::class,
        LearnedWordEntity::class,
        DailyStreakEntity::class,
        LessonProgressEntity::class,
        ModuleProgressEntity::class,
        LanguageStatsEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class TutiDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun progressDao(): ProgressDao
    abstract fun wordDao(): WordDao
    abstract fun courseProgressDao(): CourseProgressDao
    abstract fun languageStatsDao(): LanguageStatsDao

    companion object {
        @Volatile
        private var INSTANCE: TutiDatabase? = null

        fun getDatabase(context: Context): TutiDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TutiDatabase::class.java,
                    "tuti_database",
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                    .also { INSTANCE = it }
            }
        }

        /**
         * Defensive migration from the early schema to the current one.
         * It preserves existing user/topic/word data and only creates or extends
         * tables that were later introduced by the app.
         */
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                if (!hasColumn(db, "users", "courseId")) {
                    db.execSQL("ALTER TABLE users ADD COLUMN courseId TEXT NOT NULL DEFAULT ''")
                }

                if (!hasColumn(db, "topic_progress", "totalQuestions")) {
                    db.execSQL("ALTER TABLE topic_progress ADD COLUMN totalQuestions INTEGER NOT NULL DEFAULT 0")
                }
                if (!hasColumn(db, "topic_progress", "correctAnswers")) {
                    db.execSQL("ALTER TABLE topic_progress ADD COLUMN correctAnswers INTEGER NOT NULL DEFAULT 0")
                }
                if (!hasColumn(db, "topic_progress", "lastStudiedAt")) {
                    db.execSQL("ALTER TABLE topic_progress ADD COLUMN lastStudiedAt INTEGER")
                }

                if (!hasColumn(db, "learned_words", "lastReviewedAt")) {
                    db.execSQL("ALTER TABLE learned_words ADD COLUMN lastReviewedAt INTEGER")
                }

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS daily_streaks (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        date TEXT NOT NULL,
                        minutesStudied INTEGER NOT NULL DEFAULT 0,
                        xpEarned INTEGER NOT NULL DEFAULT 0,
                        lessonsCompleted INTEGER NOT NULL DEFAULT 0
                    )
                    """.trimIndent(),
                )

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS lesson_progress (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        lessonId TEXT NOT NULL,
                        courseId TEXT NOT NULL,
                        moduleId TEXT NOT NULL,
                        completed INTEGER NOT NULL DEFAULT 0,
                        stars INTEGER NOT NULL DEFAULT 0,
                        score INTEGER NOT NULL DEFAULT 0,
                        xpEarned INTEGER NOT NULL DEFAULT 0,
                        completedAt INTEGER
                    )
                    """.trimIndent(),
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_lesson_progress_lessonId ON lesson_progress(lessonId)",
                )

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS module_progress (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        moduleId TEXT NOT NULL,
                        courseId TEXT NOT NULL,
                        completed INTEGER NOT NULL DEFAULT 0,
                        lessonsCompleted INTEGER NOT NULL DEFAULT 0,
                        totalLessons INTEGER NOT NULL DEFAULT 0
                    )
                    """.trimIndent(),
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_module_progress_moduleId ON module_progress(moduleId)",
                )
            }
        }

        /**
         * Очки и серия становятся раздельными по языкам.
         * Всё, что накоплено до обновления, отдаём текущему языку
         * пользователя: второй язык начинается с чистого листа.
         */
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS language_stats (
                        language TEXT PRIMARY KEY NOT NULL,
                        totalXp INTEGER NOT NULL DEFAULT 0,
                        currentStreak INTEGER NOT NULL DEFAULT 0,
                        longestStreak INTEGER NOT NULL DEFAULT 0
                    )
                    """.trimIndent(),
                )

                if (!hasColumn(db, "daily_streaks", "language")) {
                    db.execSQL(
                        "ALTER TABLE daily_streaks ADD COLUMN language TEXT NOT NULL DEFAULT ''",
                    )
                }

                val currentLanguage = db.query(
                    "SELECT selectedLanguage FROM users WHERE id = 1",
                ).use { cursor ->
                    if (cursor.moveToFirst()) cursor.getString(0) else null
                } ?: "russian"

                db.execSQL(
                    "UPDATE daily_streaks SET language = ? WHERE language = ''",
                    arrayOf(currentLanguage),
                )

                db.execSQL(
                    """
                    INSERT OR REPLACE INTO language_stats (language, totalXp, currentStreak, longestStreak)
                    SELECT ?, totalXp, currentStreak, longestStreak FROM users WHERE id = 1
                    """.trimIndent(),
                    arrayOf(currentLanguage),
                )
            }
        }

        private fun hasColumn(
            db: SupportSQLiteDatabase,
            tableName: String,
            columnName: String,
        ): Boolean {
            db.query("PRAGMA table_info($tableName)").use { cursor ->
                val nameIndex = cursor.getColumnIndex("name")
                while (cursor.moveToNext()) {
                    if (nameIndex >= 0 && cursor.getString(nameIndex) == columnName) {
                        return true
                    }
                }
            }
            return false
        }
    }
}
