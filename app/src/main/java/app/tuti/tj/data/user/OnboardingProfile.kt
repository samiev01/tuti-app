package app.tuti.tj.data.user

// ════════════════════════════════════════════════════════════════
//  ОТВЕТЫ ОНБОРДИНГА
//
//  Пять вопросов, на которые человек отвечает при первом запуске.
//  Здесь они лежат типизированно, а в Firestore уходят строками
//  (`name` перечисления) — так документ читается глазами в консоли
//  и не ломается при переименовании констант в Kotlin.
//
//  Это слой данных: про язык интерфейса он ничего не знает.
//  Подписи для экрана собираются в ui/i18n по коду.
// ════════════════════════════════════════════════════════════════

/** Язык, который человек УЧИТ (не язык интерфейса — тот в [app.tuti.tj.ui.i18n.AppLanguage]). */
enum class LearningLanguage { RUSSIAN, ENGLISH }

enum class ProficiencyLevel { BEGINNER, INTERMEDIATE, ADVANCED }

enum class LearningGoal { WORK, STUDY, TRAVEL, PERSONAL }

data class OnboardingProfile(
    val language: LearningLanguage,
    val goal: LearningGoal,
    val level: ProficiencyLevel,
    val dailyMinutes: Int,
    val cityCode: String,
)

/**
 * Локальные значения курса собираются из тех же ответов: Room и
 * ContentProvider работают со строчными кодами (`russian`, `work`),
 * а не с перечислениями.
 */
val LearningLanguage.dbValue: String get() = name.lowercase()
val ProficiencyLevel.dbValue: String get() = name.lowercase()
val LearningGoal.dbValue: String get() = name.lowercase()

/** Идентификатор курса: цель + язык, как их ждёт ContentProvider. */
fun courseIdFor(goal: LearningGoal, language: LearningLanguage): String =
    "${goal.dbValue}_${language.dbValue}"
