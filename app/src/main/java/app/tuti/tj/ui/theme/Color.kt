package app.tuti.tj.ui.theme

import androidx.compose.ui.graphics.Color

// ════════════════════════════════════════════════════════════════
//  TUTI DESIGN SYSTEM · ПАЛИТРА
//
//  Характер: тропический попугай — насыщенно, энергично, но
//  по-взрослому. Никаких пастельных «дошкольных» тонов: каждый
//  цвет имеет полную насыщенность и тёмную пару для глубины.
//
//  Структура: Brand (Tuti Green) → Accents (Mango/Grape/Sky/Coral/Leaf)
//             → Ink (нейтрали) → Semantic → Dark theme
// ════════════════════════════════════════════════════════════════

// ── БРЕНД · Tuti Green ──────────────────────────────────────────
// Основной цвет Tuti: чистая зелень без ухода в бирюзу. Раньше
// здесь был Jade (#00C48C) — зелёный с сильным бирюзовым уклоном.
// На светлом интерфейсе он читался как «мятный», спорил с
// нейтралями и не совпадал с главной кнопкой экрана.
//
// Это единственный акцент интерфейса: главные кнопки, прогресс,
// проценты, активные и выбранные состояния. Семейство остальных
// акцентов (Mango/Grape/Sky/Coral/Leaf) отвечает за смысл —
// награды, ошибки, верные ответы, — а не за бренд.
val TutiGreen = Color(0xFF35B85A)
val TutiGreenDeep = Color(0xFF2A9648)     // нажатие / нижняя грань кнопки
val TutiGreenDark = Color(0xFF1E7838)     // текст на светлой заливке
val TutiGreenSoft = Color(0xFFE7F6EC)     // контейнер / заливка чипа
val TutiGreenMist = Color(0xFFF2FBF5)     // фон секции
val TutiGreenBright = Color(0xFF5FD07F)   // акцент в тёмной теме
val TutiGreenGlow = Color(0xFF6FD98C)     // светлый конец градиента прогресса

// ── Цвета вкладок нижней навигации ──────────────────────────────
// Совпадают с заливкой фирменных иконок вкладок: у каждого раздела
// свой цвет, и подпись с подложкой берут его отсюда, а не из
// акцентных семейств. Одинаковы в светлой и тёмной теме — сами
// иконки цветные, перекрашивать их под тему нельзя.
val NavHomeColor = TutiGreen
val NavLessonsColor = Color(0xFF4F7DF3)
val NavRankingColor = Color(0xFFF2B84B)
val NavPracticeColor = Color(0xFFF26B5E)
val NavProfileColor = Color(0xFF8B6FE8)

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
// Фон темнее карточек ровно настолько, чтобы их границы читались:
// совсем белый сливал экран в одну стену, откровенно серый выглядел
// пыльно. Белый остаётся только у карточек. Разницу с карточкой
// держит не столько сам тон, сколько их граница [Ink10], поэтому
// фон можно вести ближе к белому, не теряя карточки.
val Cloud = Color(0xFFF7F9FC)             // фон приложения
val Snow = Color(0xFFFFFFFF)              // поверхность карточек

// ════════════════════════════════════════════════════════════════
//  СОВМЕСТИМОСТЬ · старые имена токенов = новые значения
// ════════════════════════════════════════════════════════════════

// ── Светлая палитра ─────────────────────────────────────────────
val TutiPrimary = TutiGreen
val TutiPrimaryDark = TutiGreenDeep
val TutiPrimaryContainer = TutiGreenSoft
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
// База почти нейтральная, с едва заметным сине-зелёным уходом.
// Раньше здесь была чернильно-синяя гамма: на телефоне она читалась
// как «синий экран», а не как тёмная тема, и спорила с зелёным
// акцентом. Теперь тон холодный ровно настолько, чтобы не быть
// плоско-серым, а карточка отличается от фона на одну ступень —
// её видно и без границы.
val TutiDarkPrimary = TutiGreenBright
val TutiDarkPrimaryContainer = Color(0xFF10331E)
val TutiDarkBackground = Color(0xFF131F24)   // фон приложения
val TutiDarkSurface = Color(0xFF202F36)      // карточки, панели
val TutiDarkSurfaceVariant = Color(0xFF2A3A42)
val TutiDarkOnBackground = Color(0xFFF1F7FB)
val TutiDarkOnSurface = Color(0xFFE8EFF3)
val TutiDarkTextMuted = Color(0xFF8B9EA8)
val TutiDarkOutline = Color(0xFF37464F)
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
