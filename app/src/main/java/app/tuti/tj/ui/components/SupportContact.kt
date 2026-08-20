package app.tuti.tj.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri

// ════════════════════════════════════════════════════════════════
//  ВНЕШНИЕ ССЫЛКИ
//
//  Все адреса, которые уводят из приложения, лежат здесь. Один
//  адрес на все точки входа: «Кӯмак» на экране входа и «Бо мо
//  тамос гиред» на экране Plus ведут в один и тот же Telegram.
//  Разъедутся они — и половина людей будет писать в заброшенный
//  канал.
// ════════════════════════════════════════════════════════════════

const val SUPPORT_TELEGRAM_URL = "https://t.me/tutitj"

const val PRIVACY_POLICY_URL = "https://tutitj.com/privacy"

fun Context.openSupportChat() = openLink(SUPPORT_TELEGRAM_URL)

fun Context.openPrivacyPolicy() = openLink(PRIVACY_POLICY_URL)

/**
 * Молча ничего не делает, если открыть нечем: ошибка здесь ничего
 * не исправит, а человек уже видит экран, с которого пришёл.
 */
private fun Context.openLink(url: String) {
    runCatching {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}
