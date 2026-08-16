package app.tuti.tj.ui.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Строки текущего языка внутри Compose.
 *
 * Берётся как `val s = LocalTutiStrings.current` — тем же способом,
 * что и цвета через MaterialTheme. Локаль статическая: язык меняют
 * редко, зато чтение строк не добавляет подписок на каждое место
 * использования.
 */
val LocalTutiStrings = staticCompositionLocalOf<TutiStrings> { TjStrings }

/**
 * Оборачивает дерево экранов: как только LanguageManager отдаёт
 * другой язык, весь интерфейс перерисовывается с новыми строками —
 * без перезапуска Activity.
 */
@Composable
fun ProvideTutiStrings(content: @Composable () -> Unit) {
    val language by LanguageManager.language.collectAsState()
    CompositionLocalProvider(LocalTutiStrings provides stringsFor(language), content = content)
}
