package app.tuti.tj.ui.theme

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ThemeMode { SYSTEM, LIGHT, DARK }

object ThemeManager {
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    fun init(context: Context) {
        _themeMode.value = load(context)
    }

    fun setThemeMode(context: Context, mode: ThemeMode) {
        _themeMode.value = mode
        context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .edit().putString("theme_mode", mode.name).apply()
    }

    private fun load(context: Context): ThemeMode {
        val saved = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            .getString("theme_mode", ThemeMode.SYSTEM.name)
        return runCatching { ThemeMode.valueOf(saved ?: ThemeMode.SYSTEM.name) }
            .getOrDefault(ThemeMode.SYSTEM)
    }
}
