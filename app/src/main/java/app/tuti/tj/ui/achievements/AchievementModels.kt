package app.tuti.tj.ui.achievements

import app.tuti.tj.ui.i18n.AchievementsStrings

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
    s: AchievementsStrings,
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
        ach("streak_3", "\uD83D\uDD25", s.streak3, s.streak3Desc, 3, streak, 30),
        ach("streak_7", "\uD83D\uDD25", s.streak7, s.streak7Desc, 7, streak, 50),
        ach("streak_30", "\uD83D\uDD25", s.streak30, s.streak30Desc, 30, streak, 200),
        ach("words_50", "\u2B50", s.words50, s.words50Desc, 50, wordsLearned, 50),
        ach("words_100", "\u2B50", s.words100, s.words100Desc, 100, wordsLearned, 100),
        ach("words_500", "\u2B50", s.words500, s.words500Desc, 500, wordsLearned, 300),
        ach("lessons_5", "\uD83D\uDCDA", s.lessons5, s.lessons5Desc, 5, lessonsCompleted, 30),
        ach("lessons_20", "\uD83D\uDCDA", s.lessons20, s.lessons20Desc, 20, lessonsCompleted, 100),
        ach("lessons_50", "\uD83D\uDCDA", s.lessons50, s.lessons50Desc, 50, lessonsCompleted, 200),
        ach("chat_10", "\uD83D\uDCAC", s.chat10, s.chat10Desc, 10, chatMessages, 50),
        ach("chat_50", "\uD83D\uDCAC", s.chat50, s.chat50Desc, 50, chatMessages, 150),
        ach("perfect_5", "\uD83C\uDFAF", s.perfect5, s.perfect5Desc, 5, perfectLessons, 100),
        ach("module_1", "\uD83C\uDFC6", s.module1, s.module1Desc, 1, modulesCompleted, 50),
        ach("modules_5", "\uD83C\uDFC6", s.modules5, s.modules5Desc, 5, modulesCompleted, 150),
        ach("modules_10", "\uD83D\uDC51", s.modules10, s.modules10Desc, 10, modulesCompleted, 500),
        ach("langs_2", "\uD83C\uDF0D", s.langs2, s.langs2Desc, 2, languagesStarted, 100),
    )
}

fun isLockedState(progressFraction: Float): Boolean = progressFraction < 0.1f
