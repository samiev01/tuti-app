package app.tuti.tj.data.content

/**
 * Single source of truth for free-topic metadata: ids, display strings, emojis,
 * counts, default unlock, and language. Used by [ContentProvider], [TutiRepository],
 * and UI (e.g. [app.tuti.tj.ui.screens.HomeScreen]).
 */
data class FreeTopicDefinition(
    val id: String,
    /** Tajik title shown in the app */
    val nameTj: String,
    /** Secondary line: Russian gloss for RU track, English gloss for EN track */
    val subtitle: String,
    val emoji: String,
    val totalWords: Int,
    val totalQuestions: Int,
    val defaultUnlocked: Boolean,
    /** "russian" or "english" — which study language this row belongs to */
    val language: String,
)

fun FreeTopicDefinition.toTopicInfo(): TopicInfo =
    TopicInfo(
        id = id,
        name = nameTj,
        subtitle = subtitle,
        emoji = emoji,
        totalWords = totalWords,
        totalQuestions = totalQuestions,
    )

object FreeTopicsRegistry {

    val russianTopics: List<FreeTopicDefinition> = listOf(
        FreeTopicDefinition("greetings", "Салом!", "Приветствия", "\uD83D\uDC4B", 10, 10, true, "russian"),
        FreeTopicDefinition("numbers", "Рақамҳо", "Числа", "\uD83D\uDD22", 10, 10, true, "russian"),
        FreeTopicDefinition("food", "Хӯрок", "Еда и напитки", "\uD83C\uDF4E", 10, 10, true, "russian"),
        FreeTopicDefinition("family", "Оила", "Семья", "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67", 10, 10, false, "russian"),
        FreeTopicDefinition("city", "Шаҳр", "Город", "\uD83C\uDFD9\uFE0F", 10, 10, false, "russian"),
        FreeTopicDefinition("colors", "Рангҳо", "Цвета", "\uD83C\uDFA8", 8, 8, false, "russian"),
        FreeTopicDefinition("time", "Вақт", "Время", "\u23F0", 10, 8, false, "russian"),
        FreeTopicDefinition("animals", "Ҳайвонот", "Животные", "\uD83D\uDC3E", 8, 8, false, "russian"),
        FreeTopicDefinition("clothes", "Либосҳо", "Одежда", "\uD83D\uDC54", 8, 8, false, "russian"),
        FreeTopicDefinition("body", "Бадан", "Тело", "\uD83E\uDEC1", 8, 8, false, "russian"),
        FreeTopicDefinition("nature", "Табиат", "Природа", "\uD83C\uDF3F", 8, 8, false, "russian"),
        FreeTopicDefinition("professions", "Касбҳо", "Профессии", "\uD83D\uDC68\u200D\u2695\uFE0F", 8, 8, false, "russian"),
        FreeTopicDefinition("fruits", "Мева ва сабзавот", "Фрукты и овощи", "\uD83C\uDF4E", 8, 8, false, "russian"),
        FreeTopicDefinition("emotions", "Ҳиссиёт", "Эмоции", "\uD83D\uDE0A", 8, 8, false, "russian"),
        FreeTopicDefinition("home", "Хона", "Дом", "\uD83C\uDFE0", 8, 8, false, "russian"),
    )

    val englishTopics: List<FreeTopicDefinition> = listOf(
        FreeTopicDefinition("en_greetings", "Салом!", "Greetings", "\uD83D\uDC4B", 10, 10, true, "english"),
        FreeTopicDefinition("en_numbers", "Рақамҳо", "Numbers", "\uD83D\uDD22", 10, 10, true, "english"),
        FreeTopicDefinition("en_food", "Хӯрок", "Food & Drinks", "\uD83C\uDF4E", 10, 10, true, "english"),
        FreeTopicDefinition("en_family", "Оила", "Family", "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67", 10, 10, false, "english"),
        FreeTopicDefinition("en_city", "Шаҳр", "City", "\uD83C\uDFD9\uFE0F", 10, 10, false, "english"),
        FreeTopicDefinition("en_colors", "Рангҳо", "Colors", "\uD83C\uDFA8", 10, 10, false, "english"),
        FreeTopicDefinition("en_time", "Вақт", "Time", "\u23F0", 10, 10, false, "english"),
        FreeTopicDefinition("en_animals", "Ҳайвонот", "Animals", "\uD83D\uDC3E", 10, 10, false, "english"),
        FreeTopicDefinition("en_clothes", "Либосҳо", "Clothes", "\uD83D\uDC54", 10, 10, false, "english"),
        FreeTopicDefinition("en_body", "Бадан", "Body", "\uD83E\uDEC1", 10, 10, false, "english"),
    )

    private val byId: Map<String, FreeTopicDefinition> =
        (russianTopics + englishTopics).associateBy { it.id }

    fun definitionFor(topicId: String): FreeTopicDefinition? = byId[topicId]

    fun orderedTopicIds(studyLanguage: String): List<String> =
        when (studyLanguage) {
            "english" -> englishTopics.map { it.id }
            else -> russianTopics.map { it.id }
        }

    fun expectedTopicIds(studyLanguage: String): Set<String> =
        orderedTopicIds(studyLanguage).toSet()

    fun topicInfos(studyLanguage: String): List<TopicInfo> =
        when (studyLanguage) {
            "english" -> englishTopics.map { it.toTopicInfo() }
            else -> russianTopics.map { it.toTopicInfo() }
        }

    fun definitionsFor(studyLanguage: String): List<FreeTopicDefinition> =
        when (studyLanguage) {
            "english" -> englishTopics
            else -> russianTopics
        }
}
