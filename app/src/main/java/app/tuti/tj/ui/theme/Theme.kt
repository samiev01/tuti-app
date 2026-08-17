package app.tuti.tj.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ════════════════════════════════════════════════════════════════
//  TUTI DESIGN SYSTEM · ТЕМА
//
//  Material-схема закрывает базу, а [TutiExtendedColors] — всё,
//  для чего у Material нет слота: акцентные семейства навыков,
//  градиенты, «нижние грани» кнопок, состояния упражнений.
//
//  Правило: ни один экран не объявляет свои Color(0xFF…).
//  Любой новый цвет сначала попадает сюда.
// ════════════════════════════════════════════════════════════════

/**
 * Акцентное семейство: базовый тон, тёмная пара для нажатия
 * и грани, мягкая заливка контейнера и цвет текста на этой
 * заливке. Один и тот же набор работает и в светлой, и в
 * тёмной теме — экраны не пишут `if (isDark)`.
 */
data class TutiAccent(
    val base: Color,
    val deep: Color,
    val soft: Color,
    val onSoft: Color,
)

data class TutiExtendedColors(
    // ── акцентные семейства ──
    val jade: TutiAccent,
    val mango: TutiAccent,
    val grape: TutiAccent,
    val sky: TutiAccent,
    val coral: TutiAccent,
    val leaf: TutiAccent,

    // ── градиенты ──
    /** Заливка любого прогресс-бара по умолчанию */
    val progressGradient: List<Color>,
    val streakGradient: List<Color>,
    /** Спокойная серая подложка серии на главном: блок фоновый, не акцентный */
    val streakCalmBg: Color,
    val streakCalmBorder: Color,
    val courseCardGradient: List<Color>,
    val plusGradient: List<Color>,
    val heroGradient: List<Color>,
    val xpGradient: List<Color>,

    // ── состояния упражнений ──
    val correctBg: Color,
    val correctText: Color,
    val correctBorder: Color,
    val wrongBg: Color,
    val wrongText: Color,
    val wrongBorder: Color,

    // ── структура ──
    val cardBorder: Color,
    val divider: Color,
    val scrim: Color,
    /** Фон элемента, который заблокирован или ещё не открыт */
    val lockedBg: Color,
    val lockedBorder: Color,
    val lockedContent: Color,
    /** Дорожка любого прогресс-бара */
    val progressTrack: Color,
    /** Подложка «поднятой» плитки внутри карточки */
    val tileBg: Color,

    // ── тематические фоны режимов практики ──
    val practiceBg1: Color,
    val practiceBg2: Color,
    val practiceBg3: Color,
    val practiceBg4: Color,
    val messageBg: Color,
    val statChipBg: Color,

    // ── медали рейтинга ──
    val gold: Color,
    val silver: Color,
    val bronze: Color,
)

private fun lightColors() = TutiExtendedColors(
    jade = TutiAccent(Jade, JadeDeep, JadeSoft, JadeDark),
    mango = TutiAccent(Mango, MangoDeep, MangoSoft, Color(0xFF8A4B00)),
    grape = TutiAccent(Grape, GrapeDeep, GrapeSoft, Color(0xFF4B2FB0)),
    sky = TutiAccent(Sky, SkyDeep, SkySoft, Color(0xFF0E5DA8)),
    coral = TutiAccent(Coral, CoralDeep, CoralSoft, Color(0xFFB02525)),
    leaf = TutiAccent(Leaf, LeafDeep, LeafSoft, Color(0xFF1E7A28)),

    progressGradient = listOf(Jade, JadeGlow),
    streakGradient = listOf(Jade, Color(0xFF00B4A8), Sky),
    streakCalmBg = Color(0xFFEDF1F5),
    streakCalmBorder = Color(0xFFDCE4EC),
    courseCardGradient = listOf(JadeDeep, Jade, Color(0xFF4BD9A8)),
    plusGradient = listOf(Color(0xFFFFC24D), Mango, Color(0xFFFF7A29)),
    heroGradient = listOf(Color(0xFFEDFBF6), Color(0xFFDCF4FF)),
    xpGradient = listOf(Grape, GrapeBright),

    correctBg = LeafSoft,
    correctText = LeafDeep,
    correctBorder = Color(0xFF9DE3A6),
    wrongBg = CoralSoft,
    wrongText = CoralDeep,
    wrongBorder = Color(0xFFFFB3B3),

    cardBorder = Ink10,
    divider = Color(0xFFEDF2F6),
    scrim = Ink.copy(alpha = 0.55f),
    lockedBg = Color(0xFFF1F5F8),
    lockedBorder = Ink20,
    lockedContent = Ink40,
    // Дорожка прогресса живёт на белой карточке, поэтому темнеет
    // вместе с фоном — иначе на ней перестаёт быть видно пустую часть.
    progressTrack = Color(0xFFDDE5EC),
    tileBg = Color(0xFFF1F5F8),

    practiceBg1 = JadeMist,
    practiceBg2 = SkySoft,
    practiceBg3 = MangoSoft,
    practiceBg4 = GrapeSoft,
    messageBg = Color(0xFFF6F9FB),
    statChipBg = Snow,

    gold = Color(0xFFFFB020),
    silver = Color(0xFFA8B8C4),
    bronze = Color(0xFFC97B3C),
)

private fun darkColors() = TutiExtendedColors(
    jade = TutiAccent(JadeBright, Jade, Color(0xFF0C3A2E), Color(0xFF6EF0C4)),
    mango = TutiAccent(MangoBright, Mango, Color(0xFF3D2A0C), Color(0xFFFFD383)),
    grape = TutiAccent(GrapeBright, Grape, Color(0xFF261B4D), Color(0xFFC7B4FF)),
    sky = TutiAccent(SkyBright, Sky, Color(0xFF0E2842), Color(0xFF9CD0FF)),
    coral = TutiAccent(CoralBright, Coral, Color(0xFF3D1A1A), Color(0xFFFFB0B0)),
    leaf = TutiAccent(LeafBright, Leaf, Color(0xFF12331A), Color(0xFF9CEBA5)),

    progressGradient = listOf(Jade, JadeGlow),
    streakGradient = listOf(Color(0xFF00614C), Color(0xFF07526B)),
    streakCalmBg = Color(0xFF18232F),
    streakCalmBorder = Color(0xFF26333F),
    courseCardGradient = listOf(Color(0xFF06402F), Color(0xFF0A6349), Color(0xFF0E8A66)),
    plusGradient = listOf(Color(0xFF8A5A0A), Color(0xFFB87200), Color(0xFFD98A18)),
    heroGradient = listOf(Color(0xFF0E1C29), Color(0xFF0B141F)),
    xpGradient = listOf(Grape, GrapeBright),

    correctBg = CorrectBgDark,
    correctText = CorrectTextDark,
    correctBorder = Color(0xFF2C6B38),
    wrongBg = WrongBgDark,
    wrongText = WrongTextDark,
    wrongBorder = Color(0xFF7A2E3A),

    cardBorder = TutiDarkOutline,
    divider = Color(0xFF1E2E3E),
    scrim = Color(0xFF04090F).copy(alpha = 0.72f),
    lockedBg = Color(0xFF16222F),
    lockedBorder = Color(0xFF2B3D50),
    lockedContent = Color(0xFF6D8296),
    progressTrack = Color(0xFF223447),
    tileBg = Color(0xFF1A2836),

    practiceBg1 = Color(0xFF0E2A24),
    practiceBg2 = Color(0xFF0E2438),
    practiceBg3 = Color(0xFF2E2312),
    practiceBg4 = Color(0xFF211B3D),
    messageBg = Color(0xFF1A2836),
    statChipBg = Color(0xFF1C2C3D),

    gold = Color(0xFFFFC44D),
    silver = Color(0xFFB9C7D2),
    bronze = Color(0xFFD9944F),
)

val LocalTutiColors = staticCompositionLocalOf { lightColors() }

/** True, когда приложение рисуется в тёмной теме (учитывает ThemeManager). */
val LocalDarkTheme = staticCompositionLocalOf { false }

/** Доступ к расширенной палитре: `MaterialTheme.tutiColors.cardBorder` */
val MaterialTheme.tutiColors: TutiExtendedColors
    @Composable @ReadOnlyComposable
    get() = LocalTutiColors.current

// ── Material-схемы ──────────────────────────────────────────────

private val TutiLightColorScheme = lightColorScheme(
    primary = Jade,
    onPrimary = Snow,
    primaryContainer = JadeSoft,
    onPrimaryContainer = JadeDark,
    secondary = Mango,
    onSecondary = Snow,
    secondaryContainer = MangoSoft,
    onSecondaryContainer = Color(0xFF8A4B00),
    tertiary = Grape,
    onTertiary = Snow,
    tertiaryContainer = GrapeSoft,
    onTertiaryContainer = Color(0xFF4B2FB0),
    background = Cloud,
    onBackground = Ink,
    surface = Snow,
    onSurface = Ink,
    surfaceVariant = Color(0xFFE2E9EF),
    onSurfaceVariant = Ink70,
    error = Coral,
    onError = Snow,
    errorContainer = CoralSoft,
    onErrorContainer = CoralDeep,
    outline = Ink10,
    outlineVariant = Color(0xFFEDF2F6),
    scrim = Ink,
)

private val TutiDarkColorScheme = darkColorScheme(
    primary = JadeBright,
    onPrimary = Color(0xFF00281D),
    primaryContainer = TutiDarkPrimaryContainer,
    onPrimaryContainer = Color(0xFF6EF0C4),
    secondary = MangoBright,
    onSecondary = Color(0xFF2E1C00),
    secondaryContainer = Color(0xFF3D2A0C),
    onSecondaryContainer = Color(0xFFFFD383),
    tertiary = GrapeBright,
    onTertiary = Color(0xFF1B1040),
    tertiaryContainer = Color(0xFF261B4D),
    onTertiaryContainer = Color(0xFFC7B4FF),
    background = TutiDarkBackground,
    onBackground = TutiDarkOnBackground,
    surface = TutiDarkSurface,
    onSurface = TutiDarkOnSurface,
    surfaceVariant = TutiDarkSurfaceVariant,
    onSurfaceVariant = TutiDarkTextMuted,
    error = CoralBright,
    onError = Color(0xFF43000A),
    errorContainer = Color(0xFF3D1A1A),
    onErrorContainer = Color(0xFFFFB0B0),
    outline = TutiDarkOutline,
    outlineVariant = Color(0xFF1E2E3E),
    scrim = Color(0xFF04090F),
)

// ── Композабл темы ──────────────────────────────────────────────

@Composable
fun TutiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) TutiDarkColorScheme else TutiLightColorScheme
    val extendedColors = if (darkTheme) darkColors() else lightColors()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = !darkTheme
            controller.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalTutiColors provides extendedColors,
        LocalDarkTheme provides darkTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = TutiTypography,
            shapes = TutiShapes,
            content = content,
        )
    }
}
