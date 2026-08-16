package app.tuti.tj.data.content

data class WordItem(
    val id: String,
    val word: String,
    val translation: String,
    val pronunciation: String,
    val example: String,
    val exampleTranslation: String,
    val topicId: String,
)

data class QuizQuestion(
    val id: String,
    val topicId: String,
    val type: QuestionType,
    val prompt: String,
    val hint: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
)

enum class QuestionType {
    TRANSLATE,
    CHOOSE_TRANSLATION,
    FILL_BLANK,
    MATCH_WORD,
    LISTEN,
}

data class TopicInfo(
    val id: String,
    val name: String,
    /** Русское название темы — для интерфейса на русском языке. */
    val nameRu: String = name,
    val subtitle: String,
    val emoji: String,
    val totalWords: Int,
    val totalQuestions: Int,
)
