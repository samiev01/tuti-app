package app.tuti.tj.data

import android.content.Context

object TutiTipsManager {
    private const val PREFS = "tuti_tips"

    const val TIP_FIRST_DIALOGUE = "tip_first_dialogue"
    const val TIP_FIRST_WORDS = "tip_first_words"
    const val TIP_FIRST_GRAMMAR = "tip_first_grammar"
    const val TIP_FIRST_EXERCISE = "tip_first_exercise"
    const val TIP_FIRST_CORRECT = "tip_first_correct"
    const val TIP_FIRST_WRONG = "tip_first_wrong"
    const val TIP_FIRST_COMPLETE = "tip_first_complete"
    const val TIP_FIRST_FLASHCARD = "tip_first_flashcard"
    const val TIP_FIRST_CHAT = "tip_first_chat"

    fun shouldShowTip(context: Context, tipId: String): Boolean {
        return try {
            !context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getBoolean(tipId, false)
        } catch (_: Exception) {
            false
        }
    }

    fun markTipShown(context: Context, tipId: String) {
        try {
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(tipId, true)
                .apply()
        } catch (_: Exception) { }
    }

    fun resetAllTips(context: Context) {
        try {
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
        } catch (_: Exception) { }
    }
}
