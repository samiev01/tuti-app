package app.tuti.tj.ui.theme

import androidx.compose.ui.graphics.Color

// ════════════════════════════════════════════════════════════════
//  TUTI DESIGN SYSTEM · ПАЛИТРА
//
//  Характер: тропический попугай — насыщенно, энергично, но
//  по-взрослому. Никаких пастельных «дошкольных» тонов: каждый
//  цвет имеет полную насыщенность и тёмную пару для глубины.
//
//  Структура: Brand (Jade) → Accents (Mango/Grape/Sky/Coral/Leaf)
//             → Ink (нейтрали) → Semantic → Dark theme
// ════════════════════════════════════════════════════════════════

// ── БРЕНД · Jade ────────────────────────────────────────────────
// Основной цвет Tuti. Перо попугая: зелёный с уходом в бирюзу.
val Jade = Color(0xFF00C48C)
val JadeDeep = Color(0xFF00A374)          // нажатие / нижняя грань кнопки
val JadeDark = Color(0xFF007D59)          // текст на светлом контейнере
val JadeSoft = Color(0xFFD3F7EA)          // контейнер / заливка чипа
val JadeMist = Color(0xFFEDFBF6)          // фон секции
val JadeBright = Color(0xFF22E3A8)        // акцент в тёмной теме
val JadeGlow = Color(0xFF4BE0B8)          // светлый конец градиента прогресса

// ── Палитра аватаров ────────────────────────────────────────────
// Цвет подставляется по хешу имени, когда у пользователя нет фото.
// Держится отдельно от семантики: это декор, а не смысл.
val AvatarPalette = listOf(
    Color(0xFFFF7043), Color(0xFF42A5F5), Color(0xFF3DC44B),
    Color(0xFF7C5CFF), Color(0xFF00C48C), Color(0xFFFF5C5C),
    Color(0xFF5C6BC0), Color(0xFFFF9F1C),
)

// ── АКЦЕНТЫ ─────────────────────────────────────────────────────
// Mango — энергия: серия, огонь, Plus, награды
val Mango = Color(0xFFFF9F1C)
val MangoDeep = Color(0xFFE07C00)
val MangoSoft = Color(0xFFFFF0D6)
val MangoBright = Color(0xFFFFC24D)

// Grape — прогресс и «валюта»: XP, кристаллы, AI
val Grape = Color(0xFF7C5CFF)
val GrapeDeep = Color(0xFF5B3FD1)
val GrapeSoft = Color(0xFFEAE4FF)
val GrapeBright = Color(0xFFA88CFF)

// Sky — аудирование, информация, спокойные состояния
val Sky = Color(0xFF2E9BFF)
val SkyDeep = Color(0xFF1477D6)
val SkySoft = Color(0xFFDCEDFF)
val SkyBright = Color(0xFF6BB8FF)

// Coral — ошибка, потеря жизни, срочность
val Coral = Color(0xFFFF5C5C)
val CoralDeep = Color(0xFFD93B3B)
val CoralSoft = Color(0xFFFFE2E2)
val CoralBright = Color(0xFFFF8A8A)

// Leaf — верный ответ, завершено
val Leaf = Color(0xFF3DC44B)
val LeafDeep = Color(0xFF2AA136)
val LeafSoft = Color(0xFFDCF6DE)
val LeafBright = Color(0xFF6FE07C)

// ── НЕЙТРАЛИ · Ink ──────────────────────────────────────────────
// Глубокий сине-чернильный вместо серого: держит контраст и не
// выглядит «пыльно» рядом с насыщенными акцентами.
val Ink = Color(0xFF0F1E2E)               // заголовки, основной текст
val Ink70 = Color(0xFF48596B)             // вторичный текст
val Ink40 = Color(0xFF8496A8)             // подписи, плейсхолдеры
val Ink20 = Color(0xFFC3CFDA)             // разделители на светлом
val Ink10 = Color(0xFFE2E9EF)             // границы карточек
// Фон заметно темнее карточек — иначе экран читается как сплошная
// белая стена, на которой границы карточек теряются. Белый остаётся
// только у самих карточек: контраст между ними и фоном и делает
// вёрстку различимой.
val Cloud = Color(0xFFE9EEF3)             // фон приложения
val Snow = Color(0xFFFFFFFF)              // поверхность карточек

// ════════════════════════════════════════════════════════════════
//  СОВМЕСТИМОСТЬ · старые имена токенов = новые значения
// ════════════════════════════════════════════════════════════════

// ── Светлая палитра ─────────────────────────────────────────────
val TutiPrimary = Jade
val TutiPrimaryDark = JadeDeep
val TutiPrimaryContainer = JadeSoft
val TutiSecondary = Mango
val TutiSecondaryContainer = MangoSoft
val TutiBackground = Cloud
val TutiSurface = Snow
val TutiError = Coral
val TutiSuccess = Leaf
val TutiOnPrimary = Color(0xFFFFFFFF)
val TutiOnSecondary = Ink
val TutiOnBackground = Ink
val TutiOnSurface = Ink
val TutiTextMuted = Ink40

// ── Тёмная палитра ──────────────────────────────────────────────
// Сине-чернильная база вместо чёрной: бренд остаётся тёплым.
val TutiDarkPrimary = JadeBright
val TutiDarkPrimaryContainer = Color(0xFF063D2F)
val TutiDarkBackground = Color(0xFF0B141F)
val TutiDarkSurface = Color(0xFF13202E)
val TutiDarkSurfaceVariant = Color(0xFF1C2C3D)
val TutiDarkOnBackground = Color(0xFFE9F1F7)
val TutiDarkOnSurface = Color(0xFFDCE7F0)
val TutiDarkTextMuted = Color(0xFF8FA3B6)
val TutiDarkOutline = Color(0xFF27394D)
val TutiDarkError = CoralBright
val TutiDarkSuccess = LeafBright

// ── Обратная связь: верно / неверно ─────────────────────────────
val CorrectBgLight = LeafSoft
val CorrectTextLight = LeafDeep
val WrongBgLight = CoralSoft
val WrongTextLight = CoralDeep

val CorrectBgDark = Color(0xFF10301A)
val CorrectTextDark = LeafBright
val WrongBgDark = Color(0xFF3A1622)
val WrongTextDark = CoralBright
