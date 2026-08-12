package app.tuti.tj.data.content

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val emoji: String,
    val goalType: String,
    val language: String,
    val modules: List<Module>,
)

data class Module(
    val id: String,
    val courseId: String,
    val title: String,
    val description: String,
    val emoji: String,
    val orderIndex: Int,
    val lessons: List<Lesson>,
)

data class Lesson(
    val id: String,
    val moduleId: String,
    val title: String,
    val description: String,
    val emoji: String,
    val orderIndex: Int,
    val dialogue: Dialogue?,
    val newWords: List<WordItem>,
    val grammarTip: GrammarTip?,
    val exercises: List<Exercise>,
)

// Порядок прохождения задаёт только контент, а не порядок строк в БД:
// строки lesson_progress могут прийти из облака в произвольном порядке.
val Course.orderedModules: List<Module>
    get() = modules.sortedBy { it.orderIndex }

val Module.orderedLessons: List<Lesson>
    get() = lessons.sortedBy { it.orderIndex }

/** Все уроки курса в порядке прохождения: модуль за модулем, урок за уроком. */
val Course.orderedLessons: List<Lesson>
    get() = orderedModules.flatMap { it.orderedLessons }

data class Dialogue(
    val title: String,
    val lines: List<DialogueLine>,
)

data class DialogueLine(
    val speaker: String,
    val text: String,
    val translation: String,
)

data class GrammarTip(
    val title: String,
    val explanation: String,
    val examples: List<String>,
)

data class Exercise(
    val id: String,
    val type: ExerciseType,
    val prompt: String,
    val hint: String? = null,
    val options: List<String>? = null,
    val correctAnswer: String,
    val correctIndex: Int? = null,
    val explanation: String,
    val pairs: List<Pair<String, String>>? = null,
    val words: List<String>? = null,
)

enum class ExerciseType {
    MULTIPLE_CHOICE,
    FILL_BLANK,
    TRANSLATE_SENTENCE,
    BUILD_SENTENCE,
    MATCH_PAIRS,
    LISTEN_CHOOSE,
    TYPE_ANSWER,
    DIALOGUE_COMPLETE,
}
