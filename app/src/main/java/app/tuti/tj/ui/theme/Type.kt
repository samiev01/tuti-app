package app.tuti.tj.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import app.tuti.tj.R

// ════════════════════════════════════════════════════════════════
//  TUTI DESIGN SYSTEM · ТИПОГРАФИКА
//
//  Две гарнитуры вместо одной — это даёт «характер» без крика:
//
//  · Rubik  — заголовки, числа, кнопки.
//    Геометрический гротеск со слегка смягчёнными углами:
//    энергичный, но не инфантильный. Читается одинаково хорошо
//    и подростком, и взрослым.
//
//  · Inter  — основной текст и мелкие подписи.
//    Максимальная разборчивость на малых кеглях.
//
//  Обе гарнитуры покрывают Cyrillic Extended (U+0460–U+052F) —
//  то есть все таджикские буквы: Ғғ Ӣӣ Ққ Ӯӯ Ҳҳ Ҷҷ.
//  Nunito оставлен третьим в цепочке фолбэков как проверенный
//  на этом проекте запасной вариант.
// ════════════════════════════════════════════════════════════════

val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val RubikFont = GoogleFont("Rubik")
private val InterFont = GoogleFont("Inter")
private val BalooFont = GoogleFont("Baloo 2")
val NunitoFont = GoogleFont("Nunito")

private fun rubik(weight: FontWeight) =
    Font(googleFont = RubikFont, fontProvider = fontProvider, weight = weight)

private fun inter(weight: FontWeight) =
    Font(googleFont = InterFont, fontProvider = fontProvider, weight = weight)

private fun nunito(weight: FontWeight) =
    Font(googleFont = NunitoFont, fontProvider = fontProvider, weight = weight)

private fun baloo(weight: FontWeight) =
    Font(googleFont = BalooFont, fontProvider = fontProvider, weight = weight)

/**
 * Гарнитура логотипа — только для слова «Tuti» на заставке.
 *
 * Интерфейсный Rubik в роли логотипа читается как «просто жирный
 * текст»: у него плоские окончания и нейтральный характер, он для
 * этого и выбран. Baloo 2 — плотный округлый гротеск с высоким
 * ростом строчных: буквы почти смыкаются в единое пятно, и слово
 * работает как знак, а не как набранный текст.
 *
 * Хвост из Rubik и Nunito — фолбэк на случай, если провайдер
 * шрифтов недоступен: логотип не должен исчезать.
 */
val TutiLogoFamily = FontFamily(
    baloo(FontWeight.Bold),
    baloo(FontWeight.ExtraBold),
    rubik(FontWeight.Black),
    nunito(FontWeight.ExtraBold),
)

/** Дисплейная гарнитура: заголовки, кнопки, числа, счётчики. */
val TutiDisplayFamily = FontFamily(
    rubik(FontWeight.Medium),
    rubik(FontWeight.SemiBold),
    rubik(FontWeight.Bold),
    rubik(FontWeight.ExtraBold),
    rubik(FontWeight.Black),
    // фолбэки, если провайдер шрифтов недоступен
    nunito(FontWeight.Bold),
    nunito(FontWeight.ExtraBold),
)

/** Текстовая гарнитура: параграфы, подписи, поля ввода. */
val TutiTextFamily = FontFamily(
    inter(FontWeight.Light),
    inter(FontWeight.Normal),
    inter(FontWeight.Medium),
    inter(FontWeight.SemiBold),
    inter(FontWeight.Bold),
    nunito(FontWeight.Normal),
    nunito(FontWeight.SemiBold),
)

/**
 * Сохранено ради обратной совместимости со старым кодом.
 * Указывает на текстовую гарнитуру дизайн-системы.
 */
val NunitoFontFamily = TutiTextFamily

// ── Шкала ───────────────────────────────────────────────────────
// Таджикские строки в среднем на 15–20 % длиннее русских, поэтому
// межстрочные интервалы намеренно щедрые, а трекинг на крупных
// кеглях отрицательный — чтобы заголовки собирались в плотный блок.

val TutiTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp,
        lineHeight = 46.sp,
        letterSpacing = (-0.8).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.6).sp,
    ),
    displaySmall = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 29.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.4).sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 26.sp,
        lineHeight = 33.sp,
        letterSpacing = (-0.4).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 29.sp,
        letterSpacing = (-0.2).sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 27.sp,
        letterSpacing = (-0.2).sp,
    ),
    titleLarge = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 25.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 23.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = TutiTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = TutiTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.1.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = TutiTextFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.15.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.2.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = TutiDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.4.sp,
    ),
)

// ── Специальные стили ───────────────────────────────────────────

/** Крупные счётчики: XP, серия, проценты. Плотный и весомый. */
val TutiNumeralStyle = TextStyle(
    fontFamily = TutiDisplayFamily,
    fontWeight = FontWeight.Black,
    fontSize = 28.sp,
    lineHeight = 32.sp,
    letterSpacing = (-1).sp,
)

/** Надзаголовок секции: КАПСОМ, разреженный. */
val TutiOverlineStyle = TextStyle(
    fontFamily = TutiDisplayFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    letterSpacing = 1.2.sp,
)

/** Изучаемое слово в карточке/упражнении — крупно и спокойно. */
val TutiWordStyle = TextStyle(
    fontFamily = TutiDisplayFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp,
    lineHeight = 38.sp,
    letterSpacing = (-0.5).sp,
)
