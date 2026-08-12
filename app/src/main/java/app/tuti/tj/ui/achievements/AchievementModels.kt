package app.tuti.tj.ui.achievements

data class Achievement(
    val id: String,
    val icon: String,
    val title: String,
    val description: String,
    val target: Int,
    val currentProgress: Int,
    val xpReward: Int = 50,
    val isCompleted: Boolean = false,
) {
    val progressFraction: Float
        get() = if (target <= 0) 1f else (currentProgress.toFloat() / target.toFloat()).coerceIn(0f, 1f)
}

fun achievementClaimedPrefKey(id: String) = "achievement_${id}_claimed"

fun migrateChatMessagePrefs(prefs: android.content.SharedPreferences) {
    try {
        val total = prefs.getInt("total_chat_messages", -1)
        if (total < 0) {
            val legacy = prefs.getInt("chat_count", 0)
            prefs.edit().putInt("total_chat_messages", legacy).apply()
        }
    } catch (_: Exception) { }
}

/**
 * Real metrics from Room + prefs; call from a composable with collected Flow values.
 */
fun buildAchievements(
    streak: Int,
    lessonsCompleted: Int,
    wordsLearned: Int,
    chatMessages: Int,
    perfectLessons: Int,
    modulesCompleted: Int,
    languagesStarted: Int,
): List<Achievement> {
    fun ach(
        id: String,
        icon: String,
        title: String,
        description: String,
        target: Int,
        current: Int,
        xp: Int,
    ) = Achievement(
        id = id,
        icon = icon,
        title = title,
        description = description,
        target = target,
        currentProgress = current.coerceAtLeast(0),
        xpReward = xp,
        isCompleted = current >= target,
    )

    return listOf(
        ach("streak_3", "\uD83D\uDD25", "Серияи 3 рӯза", "3 рӯз пай дар пай омӯхтед", 3, streak, 30),
        ach("streak_7", "\uD83D\uDD25", "Серияи 7 рӯза", "7 рӯз пай дар пай омӯхтед", 7, streak, 50),
        ach("streak_30", "\uD83D\uDD25", "Серияи 30 рӯза", "30 рӯз пай дар пай омӯхтед", 30, streak, 200),
        ach("words_50", "\u2B50", "50 калима", "50 калимаи нав омӯхтед", 50, wordsLearned, 50),
        ach("words_100", "\u2B50", "100 калима", "100 калимаи нав омӯхтед", 100, wordsLearned, 100),
        ach("words_500", "\u2B50", "500 калима", "500 калимаи нав омӯхтед", 500, wordsLearned, 300),
        ach("lessons_5", "\uD83D\uDCDA", "5 дарс", "5 дарс тамом кардед", 5, lessonsCompleted, 30),
        ach("lessons_20", "\uD83D\uDCDA", "20 дарс", "20 дарс тамом кардед", 20, lessonsCompleted, 100),
        ach("lessons_50", "\uD83D\uDCDA", "50 дарс", "50 дарс тамом кардед", 50, lessonsCompleted, 200),
        ach("chat_10", "\uD83D\uDCAC", "Гуфтугӯчӣ", "10 суҳбат бо Tuti", 10, chatMessages, 50),
        ach("chat_50", "\uD83D\uDCAC", "Сӯҳбатдон", "50 суҳбат бо Tuti", 50, chatMessages, 150),
        ach("perfect_5", "\uD83C\uDFAF", "Бехато", "5 дарс бе хато тамом кардед", 5, perfectLessons, 100),
        ach("module_1", "\uD83C\uDFC6", "Модули аввал", "Модули 1-ро тамом кардед", 1, modulesCompleted, 50),
        ach("modules_5", "\uD83C\uDFC6", "5 модул", "5 модулро тамом кардед", 5, modulesCompleted, 150),
        ach("modules_10", "\uD83D\uDC51", "Устод", "10 модулро тамом кардед", 10, modulesCompleted, 500),
        ach("langs_2", "\uD83C\uDF0D", "Ду забон", "Ҳар 2 забонро оғоз кунед", 2, languagesStarted, 100),
    )
}

fun isLockedState(progressFraction: Float): Boolean = progressFraction < 0.1f
