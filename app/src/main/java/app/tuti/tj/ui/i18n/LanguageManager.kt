package app.tuti.tj.ui.i18n

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ════════════════════════════════════════════════════════════════
//  ЯЗЫК ИНТЕРФЕЙСА
//
//  Важно не путать два разных «языка» в приложении:
//    • UserEntity.selectedLanguage — язык, который пользователь
//      УЧИТ (english / russian);
//    • AppLanguage здесь — язык, на котором с ним РАЗГОВАРИВАЕТ
//      само приложение (таджикский или русский).
//
//  Хранится он рядом с темой, в тех же tuti_prefs, и работает так
//  же: StateFlow меняется — Compose перерисовывает интерфейс без
//  перезапуска Activity.
// ════════════════════════════════════════════════════════════════

enum class AppLanguage(
    /** Код локали — пригодится для форматирования дат и TTS. */
    val code: String,
    /** Название языка на нём самом: так его подписывают в настройках. */
    val nativeName: String,
    val flag: String,
) {
    TAJIK("tg", "Тоҷикӣ", "🇹🇯"),
    RUSSIAN("ru", "Русский", "🇷🇺"),
}

object LanguageManager {

    private const val PREFS = "tuti_prefs"
    private const val KEY = "app_language"

    private val _language = MutableStateFlow(AppLanguage.TAJIK)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    /**
     * Выбирал ли пользователь язык сам. Пока false — показывается
     * экран выбора: угадывать язык по системной локали приложение
     * не берётся, потому что телефон с русской системой в
     * Таджикистане ничего не говорит о том, на каком языке человеку
     * удобнее учиться.
     */
    private val _isChosen = MutableStateFlow(false)
    val isChosen: StateFlow<Boolean> = _isChosen.asStateFlow()

    /**
     * Строки текущего языка для мест, где нет Compose: уведомления,
     * ViewModel, менеджеры. Внутри composable лучше брать
     * [LocalTutiStrings], иначе смена языка не вызовет перерисовку.
     */
    val strings: TutiStrings get() = stringsFor(_language.value)

    fun init(context: Context) {
        val saved = load(context)
        _isChosen.value = saved != null
        _language.value = saved ?: AppLanguage.TAJIK
    }

    fun setLanguage(context: Context, language: AppLanguage) {
        _language.value = language
        _isChosen.value = true
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putString(KEY, language.name).apply()
    }

    /**
     * null — выбора ещё не было: язык спросят на первом запуске.
     * Значение по умолчанию до ответа — таджикский, но пользователь
     * его не увидит: экран выбора подписан на обоих языках сразу.
     */
    private fun load(context: Context): AppLanguage? {
        val saved = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY, null) ?: return null
        return runCatching { AppLanguage.valueOf(saved) }.getOrNull()
    }
}

fun stringsFor(language: AppLanguage): TutiStrings = when (language) {
    AppLanguage.TAJIK -> TjStrings
    AppLanguage.RUSSIAN -> RuStrings
}
