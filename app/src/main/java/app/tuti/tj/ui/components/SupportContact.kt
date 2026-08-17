package app.tuti.tj.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri

// ════════════════════════════════════════════════════════════════
//  СВЯЗЬ С ПОДДЕРЖКОЙ
//
//  Один адрес на все точки входа: «Кӯмак» на экране входа и
//  «Бо мо тамос гиред» на экране Plus. Разъедутся они — и половина
//  людей будет писать в заброшенный канал.
// ════════════════════════════════════════════════════════════════

const val SUPPORT_TELEGRAM_URL = "https://t.me/tutitj"

/**
 * Открывает Telegram. Молча ничего не делает, если открыть нечем:
 * ошибка здесь ничего не исправит, а человек уже видит экран, с
 * которого пришёл.
 */
fun Context.openSupportChat() {
    runCatching {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(SUPPORT_TELEGRAM_URL))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}
