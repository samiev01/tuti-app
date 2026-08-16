package app.tuti.tj.ui.i18n

import app.tuti.tj.data.content.FreeTopicDefinition
import app.tuti.tj.data.content.TopicInfo

// ════════════════════════════════════════════════════════════════
//  НАЗВАНИЯ ТЕМ
//
//  Сам учебный материал (слова, фразы, переводы) остаётся как есть:
//  это контент курса, а не интерфейс. А вот подписи тем в списках —
//  часть навигации, и на русском интерфейсе они должны читаться
//  по-русски. Поэтому у темы два названия, и здесь выбирается нужное.
//
//  Помощники живут в ui/i18n, чтобы слой данных ничего не знал про
//  язык интерфейса.
// ════════════════════════════════════════════════════════════════

/**
 * Город по умолчанию. В базе и в Firestore город всегда хранится
 * по-таджикски, поэтому и значение по умолчанию — таджикское.
 */
const val DEFAULT_CITY = "Душанбе"

fun FreeTopicDefinition.localizedName(strings: TutiStrings): String =
    if (strings.language == AppLanguage.RUSSIAN) nameRu else nameTj

fun TopicInfo.localizedName(strings: TutiStrings): String =
    if (strings.language == AppLanguage.RUSSIAN) nameRu else name

/**
 * Вторая строка карточки — перевод названия темы на изучаемый язык.
 * Если он совпал с заголовком (русский интерфейс + русский курс),
 * показывать его второй раз незачем.
 */
fun localizedSubtitle(title: String, subtitle: String): String =
    if (subtitle.equals(title, ignoreCase = true)) "" else subtitle
