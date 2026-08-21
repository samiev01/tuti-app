package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.R
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.FreeTopicsRegistry
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.data.subscription.PlusManager
import app.tuti.tj.ui.components.OnboardingOverlay
import app.tuti.tj.ui.components.OnboardingTooltips
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiDialog
import app.tuti.tj.ui.components.kit.TutiDialogActions
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.i18n.HomeStrings
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.localizedName
import app.tuti.tj.ui.i18n.localizedSubtitle
import app.tuti.tj.ui.components.onboardingSteps
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.TutiTheme
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// ════════════════════════════════════════════════════════════════
//  ГЛАВНЫЙ ЭКРАН
//
//  Порядок блоков задан приоритетом: сначала «что делать прямо
//  сейчас» (курс с кнопкой продолжения), и только потом —
//  вспомогательное (лимиты, свободные темы). Неделя занятий
//  живёт на отдельном экране календаря.
//  Шапки-панели у экрана нет: сверху только строка с языком и
//  календарём слева, серией и очками справа.
//
//  Оформление: очень светлый фон, белые карточки с тонкой
//  нейтральной границей, тёмно-синий текст, контурные иконки
//  вместо эмодзи в служебных ролях. Цвет ровно один — зелёный
//  [app.tuti.tj.ui.theme.TutiGreen] в `tutiColors.jade`: главная
//  кнопка, прогресс, активные и выбранные состояния. Эмодзи
//  остаются только там, где они содержание, а не украшение:
//  флаги языков, значок курса, значки тем.
// ════════════════════════════════════════════════════════════════

/** Форматирование очков: 1200 -> «1,200». */
private fun formatXp(xp: Int): String =
    if (xp >= 1000) "${xp / 1000},${(xp % 1000).toString().padStart(3, '0')}" else xp.toString()

/** Размер контурной иконки в чипах и строках карточек. */
private val homeIconSm = 16.dp
private val homeIconMd = 20.dp

/** Высота верхней строки: кружок флага и плашки показателей одного роста. */
private val topRowSize = 44.dp

/**
 * Насколько раздуть эмодзи-флаг, чтобы он закрыл круг по высоте.
 *
 * Флаг рисуется широким прямоугольником внутри em-квадрата и по
 * высоте занимает примерно две трети кегля, поэтому без запаса
 * в круге оставались бы поля сверху и снизу.
 */
private const val FLAG_FILL_SCALE = 1.70f

/**
 * Поправка вертикали флага внутри круга.
 *
 * Box центрирует строку целиком, а глиф стоит на базовой линии и
 * занимает её верхнюю часть — из-за этого флаг садился ниже центра
 * и срезалась нижняя полоса. Проверялось по увеличенному снимку:
 * полосы должны делить круг натрое поровну.
 */
private val flagBaselineNudge = (-2).dp

/** Флаг текущего языка обучения — он же значок кнопки переключения. */
private fun languageFlag(lang: String): String = when (lang) {
    "english" -> "🇬🇧"
    "both" -> "🇷🇺🇬🇧"
    else -> "🇷🇺"
}

private fun levelDisplay(level: String, s: HomeStrings): String = when (level) {
    "beginner" -> s.levelBeginner
    "elementary" -> s.levelElementary
    "intermediate" -> s.levelIntermediate
    "advanced" -> s.levelAdvanced
    else -> s.levelElementary
}

private fun Modifier.tooltipTarget(
    key: String,
    targets: MutableMap<String, Rect>,
): Modifier = onGloballyPositioned { coords ->
    val pos = coords.localToWindow(Offset.Zero)
    targets[key] = Rect(pos.x, pos.y, pos.x + coords.size.width, pos.y + coords.size.height)
}

// ═══════════════════════════════════════════════════
//  ЭКРАН
// ═══════════════════════════════════════════════════

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onTopicClick: (String) -> Unit = {},
    onContinueCourse: (String) -> Unit = {},
    onOpenCourse: (String) -> Unit = {},
    onNavigateToPlus: () -> Unit = {},
    onOpenCalendar: () -> Unit = {},
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val uiState by viewModel.uiState.collectAsState()
    val u = uiState.user

    val prefs = remember { context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE) }
    var showTooltips by remember { mutableStateOf(!prefs.getBoolean("tooltips_shown", false)) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    val courseId = u.courseId

    // Пока идёт обучение, нижняя панель прячется: затемнение оверлея
    // до неё не достаёт, и яркая полоса вкладок оттягивала взгляд от
    // подсказки. На своём шаге она возвращается — см. OnboardingOverlay.
    DisposableEffect(showTooltips) {
        OnboardingOverlay.active = showTooltips
        onDispose { OnboardingOverlay.active = false }
    }

    val scrollState = rememberScrollState()
    val tooltipTargets = remember { mutableStateMapOf<String, Rect>() }
    var homeBoxWindowPos by remember { mutableStateOf(Offset.Zero) }
    var homeBoxSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .onGloballyPositioned { coords ->
                homeBoxWindowPos = coords.localToWindow(Offset.Zero)
                homeBoxSize = coords.size
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .statusBarsPadding()
                .padding(horizontal = TutiSpace.screen),
            // Один шаг между всеми блоками и внутри секций: раньше
            // снаружи было 16, а внутри 12, и лесенка читалась как
            // неровные просветы.
            verticalArrangement = Arrangement.spacedBy(TutiSpace.lg),
        ) {
            Spacer(Modifier.height(TutiSpace.sm))

            // Очки считаются по выбранному языку: у русского и
            // английского свой прогресс, они не суммируются.
            HomeTopRow(
                streak = uiState.languageStreak,
                xp = uiState.languageXp,
                language = u.selectedLanguage,
                onSwitchLanguage = { showLanguageDialog = true },
                onOpenCalendar = onOpenCalendar,
                streakModifier = Modifier.tooltipTarget("streak_chip", tooltipTargets),
                xpModifier = Modifier.tooltipTarget("xp_chip", tooltipTargets),
            )

            if (showLanguageDialog) {
                LanguageSwitchDialog(
                    currentLanguage = u.selectedLanguage,
                    onDismiss = { showLanguageDialog = false },
                    onSelect = { selectedLang ->
                        showLanguageDialog = false
                        if (selectedLang != u.selectedLanguage) {
                            viewModel.setLanguage(selectedLang)
                        }
                    },
                )
            }

            // Курс — главный блок экрана: он единственный несёт крупный
            // заголовок и залитую зелёную кнопку, поэтому взгляд падает
            // на него. Неделя занятий уехала на экран календаря.
            val course = uiState.course
            if (course != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .tooltipTarget("course_card", tooltipTargets),
                    verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
                ) {
                    YourCourseSection(
                        courseTitle = course.title,
                        courseEmoji = course.emoji,
                        language = u.selectedLanguage,
                        level = u.level,
                        progress = uiState.courseProgress,
                        onContinue = { onContinueCourse(courseId) },
                        onOpenCourse = { onOpenCourse(courseId) },
                    )
                }
            }

            DailyLimitsCard(onNavigateToPlus = onNavigateToPlus)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .tooltipTarget("free_topics", tooltipTargets),
                verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
            ) {
                FreeTopicsSection(topics = uiState.topics, onTopicClick = onTopicClick)
            }

            Spacer(Modifier.height(TutiSpace.bottomNavGap))
        }

        if (showTooltips) {
            // Вкладка практики живёт в нижней панели — она рисуется
            // Scaffold'ом снаружи экрана и кладёт свои координаты в
            // общее хранилище.
            OnboardingTooltips(
                targetBounds = tooltipTargets + OnboardingOverlay.bounds,
                onComplete = {
                    showTooltips = false
                    prefs.edit().putBoolean("tooltips_shown", true).apply()
                },
                onStepChanged = { stepIndex ->
                    val key = onboardingSteps[stepIndex].targetKey
                    if (key == "done" || key == "bottom_nav_practice") return@OnboardingTooltips
                    val targetRect = tooltipTargets[key] ?: return@OnboardingTooltips
                    val viewTop = homeBoxWindowPos.y
                    val viewBottom = homeBoxWindowPos.y + homeBoxSize.height
                    val margin = with(density) { 80.dp.toPx() }
                    if (targetRect.top < viewTop + margin || targetRect.bottom > viewBottom - margin) {
                        val delta = targetRect.center.y - (viewTop + homeBoxSize.height / 2f)
                        scope.launch {
                            scrollState.animateScrollTo(
                                (scrollState.value + delta).toInt().coerceAtLeast(0),
                            )
                        }
                    }
                },
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  ОБЩИЕ ЭЛЕМЕНТЫ ОФОРМЛЕНИЯ
// ═══════════════════════════════════════════════════

/**
 * Заголовок секции главного экрана.
 *
 * Отличается от кита тем, что действие набрано интерфейсным
 * зелёным, а счётчик — не заливкой, а спокойной серой цифрой:
 * на экране должно оставаться одно цветное пятно, и это кнопка
 * курса.
 */
@Composable
private fun HomeSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    counter: String? = null,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (counter != null) {
            Spacer(Modifier.width(TutiSpace.sm))
            Text(
                text = counter,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(Modifier.weight(1f))
        if (actionText != null && onAction != null) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.tutiColors.jade.base,
                maxLines = 1,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onAction() }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}

/** Контурная плашка-действие: значок, подпись, зелёный контур. */
@Composable
private fun HomeOutlineChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(c.jade.soft)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = c.jade.onSoft,
                modifier = Modifier.size(homeIconSm),
            )
            Spacer(Modifier.width(5.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = c.jade.onSoft,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

// ═══════════════════════════════════════════════════
//  1 · ВЕРХНЯЯ СТРОКА: язык, серия, очки
// ═══════════════════════════════════════════════════

/**
 * Строка над содержимым: слева язык и календарь, справа — серия и
 * набранные баллы.
 *
 * Ни подложки, ни заголовка: строка лежит прямо на фоне и едет
 * вместе с содержимым. Отдельную панель сверху с экрана уже
 * убирали — здесь только эти четыре элемента и ничего вокруг них.
 */
@Composable
private fun HomeTopRow(
    xp: Int,
    streak: Int,
    language: String,
    onSwitchLanguage: () -> Unit,
    onOpenCalendar: () -> Unit,
    modifier: Modifier = Modifier,
    streakModifier: Modifier = Modifier,
    xpModifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
    ) {
        LanguageFlagButton(
            flag = languageFlag(language),
            onClick = onSwitchLanguage,
        )
        CalendarButton(onClick = onOpenCalendar)
        Spacer(Modifier.weight(1f))
        StreakChip(streak = streak, modifier = streakModifier)
        XpChip(xp = xp, modifier = xpModifier)
    }
}

/**
 * Вход на экран календаря. Раньше неделя занятий лежала карточкой
 * прямо на главном, но смотрят на неё редко, а место над курсом
 * она занимала заметное.
 */
@Composable
private fun CalendarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val description = LocalTutiStrings.current.home.calendarTitle

    Box(
        modifier = modifier
            .size(topRowSize)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, c.cardBorder, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = description,
            tint = c.jade.base,
            modifier = Modifier.size(homeIconMd),
        )
    }
}

/**
 * Плашка показателя: значок и число.
 *
 * Белая заливка и тонкая нейтральная граница вместо цветной
 * подложки — так две плашки рядом не спорят ни друг с другом,
 * ни с кнопкой курса. Ростом совпадают с кружком флага.
 */
@Composable
private fun TopRowChip(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    val c = MaterialTheme.tutiColors

    Row(
        modifier = modifier
            .height(topRowSize)
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, c.cardBorder, RoundedCornerShape(TutiRadius.pill))
            .padding(horizontal = TutiSpace.lg)
            .semantics { contentDescription = "$value $label" },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(Modifier.width(TutiSpace.sm))
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
        )
    }
}

/** Серия дней подряд. Значок фирменный и цветной, поэтому без tint. */
@Composable
private fun StreakChip(streak: Int, modifier: Modifier = Modifier) {
    TopRowChip(
        value = streak.toString(),
        label = LocalTutiStrings.current.common.streakLabel,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_streak),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(homeIconMd),
        )
    }
}

/** Набранные баллы по выбранному языку. */
@Composable
private fun XpChip(xp: Int, modifier: Modifier = Modifier) {
    val c = MaterialTheme.tutiColors
    TopRowChip(
        value = formatXp(xp),
        label = LocalTutiStrings.current.common.points,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Outlined.Diamond,
            contentDescription = null,
            tint = c.jade.base,
            modifier = Modifier.size(homeIconMd),
        )
    }
}

/**
 * Переключатель языка обучения.
 *
 * Отдельной карточки под него на экране нет: смена языка — редкое
 * действие, и целый блок оно не оправдывало. Осталась круглая
 * кнопка с текущим флагом в левом верхнем углу, а сам выбор
 * по-прежнему открывается диалогом.
 */
@Composable
private fun LanguageFlagButton(
    flag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val description = LocalTutiStrings.current.home.switchLanguage

    Box(
        modifier = modifier
            .size(topRowSize)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, c.cardBorder, CircleShape)
            .clickable { onClick() }
            .semantics { contentDescription = description },
        contentAlignment = Alignment.Center,
    ) {
        // Флаг круглый, а не прямоугольный: эмодзи увеличено так, что
        // по высоте закрывает круг, а бока срезает `clip` родителя —
        // получается кружок-флаг, как в списках стран. Цвета при этом
        // остаются родными: это смысловая иллюстрация, а не иконка.
        Text(
            text = flag,
            fontSize = 40.sp,
            modifier = Modifier
                .offset(y = flagBaselineNudge)
                .scale(FLAG_FILL_SCALE),
        )
    }
}

private data class LangOption(val flag: String, val name: String, val sub: String, val key: String)

private fun langOptions(s: HomeStrings) = listOf(
    LangOption("🇷🇺", s.russianLanguage, s.russianLanguageHint, "russian"),
    LangOption("🇬🇧", s.englishLanguage, s.englishLanguageHint, "english"),
)

@Composable
private fun LanguageSwitchDialog(
    currentLanguage: String,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current.home
    var selectedKey by remember { mutableStateOf(currentLanguage) }

    TutiDialog(
        onDismiss = onDismiss,
        title = s.chooseLanguageTitle,
        message = s.chooseLanguageMessage,
        mascotState = TutiState.THINKING,
        accent = c.jade.base,
    ) {
        Spacer(Modifier.height(TutiSpace.xl))
        Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
            langOptions(s).forEach { opt ->
                val isSelected = selectedKey == opt.key
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(TutiRadius.md))
                        .background(
                            if (isSelected) c.jade.soft else MaterialTheme.colorScheme.surface,
                        )
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) c.jade.base else c.cardBorder,
                            shape = RoundedCornerShape(TutiRadius.md),
                        )
                        .clickable { selectedKey = opt.key }
                        .padding(TutiSpace.md),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(opt.flag, fontSize = 28.sp)
                    Spacer(Modifier.width(TutiSpace.md))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = opt.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = opt.sub,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    SelectionCheck(selected = isSelected, color = c.jade.base)
                }
            }
        }
        TutiDialogActions(
            primaryText = s.chooseLanguageConfirm,
            onPrimary = { onSelect(selectedKey) },
            secondaryText = LocalTutiStrings.current.common.cancel,
            onSecondary = onDismiss,
            tone = TutiButtonTone.Jade,
        )
    }
}

/** Кружок выбора — один и тот же во всех списках с одиночным выбором. */
@Composable
fun SelectionCheck(selected: Boolean, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(26.dp)
            .border(2.dp, if (selected) color else MaterialTheme.tutiColors.lockedBorder, CircleShape)
            .background(if (selected) color else Color.Transparent, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(homeIconSm),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  2 · ТЕКУЩИЙ КУРС — главный призыв к действию
// ═══════════════════════════════════════════════════

@Composable
private fun YourCourseSection(
    courseTitle: String,
    courseEmoji: String,
    language: String,
    level: String,
    progress: List<LessonProgressEntity>,
    onContinue: () -> Unit,
    onOpenCourse: () -> Unit,
) {
    val s = LocalTutiStrings.current.home
    val completed = progress.count { it.completed }
    val total = progress.size.coerceAtLeast(1)
    val fraction = completed.toFloat() / total
    val pct = (fraction * 100).toInt()

    // Порядок берём из контента курса: строки прогресса в БД могут идти вразнобой.
    val courseId = progress.firstOrNull()?.courseId
    val completedIds = progress.filter { it.completed }.map { it.lessonId }.toSet()
    val nextLessonId = courseId?.let { ContentProvider.getNextLessonId(it, completedIds) }
    val nextLessonContent = nextLessonId?.let { ContentProvider.getLesson(it) }

    CourseCard(
        badgeText = s.yourCourse,
        // Уровень переехал сюда из карточки языка: она с экрана ушла,
        // а сам показатель относится к тому, что человек учит.
        levelText = levelDisplay(level, s),
        seeAllText = s.seeAll,
        courseTitle = courseTitle,
        courseEmoji = courseEmoji,
        languageFlag = languageFlag(language),
        lessonsProgressText = s.lessonsProgress(completed, total),
        fraction = fraction,
        percent = pct,
        nextLessonTitle = nextLessonContent?.title,
        nextLessonEmoji = nextLessonContent?.emoji,
        onContinue = onContinue,
        onOpenCourse = onOpenCourse,
    )
}

/**
 * Карточка курса — главный блок экрана.
 *
 * Читается сверху вниз одной колонкой: что это за раздел →
 * название курса → на каком языке и сколько уроков пройдено →
 * доля в процентах → крупное действие → что именно откроется.
 * Иллюстрация курса стоит справа от заголовка и не участвует в
 * этой вертикали — она опознавательный знак, а не ступень.
 *
 * Всё остальное на экране намеренно спокойнее: только здесь
 * крупный заголовок, залитая кнопка и волна внимания на ней.
 */
@Composable
private fun CourseCard(
    badgeText: String,
    levelText: String,
    seeAllText: String,
    courseTitle: String,
    courseEmoji: String,
    languageFlag: String,
    lessonsProgressText: String,
    fraction: Float,
    percent: Int,
    nextLessonTitle: String?,
    nextLessonEmoji: String?,
    onContinue: () -> Unit,
    onOpenCourse: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val common = LocalTutiStrings.current.common

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onOpenCourse,
        radius = TutiRadius.xl,
        contentPadding = TutiSpace.xl,
    ) {
        // Бейдж вместо заголовка секции над карточкой: подпись «что
        // это» и сам блок не должны разъезжаться на два уровня, иначе
        // главный блок теряет цельность. Справа — переход ко всем
        // урокам курса: раньше он стоял в заголовке секции.
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CourseBadge(
                text = badgeText,
                background = c.jade.soft,
                contentColor = c.jade.onSoft,
            )
            Spacer(Modifier.width(TutiSpace.sm))
            CourseBadge(
                text = levelText,
                background = c.tileBg,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = seeAllText,
                style = MaterialTheme.typography.labelMedium,
                color = c.jade.base,
                maxLines = 1,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onOpenCourse() }
                    .padding(horizontal = TutiSpace.sm, vertical = TutiSpace.xs),
            )
        }

        Spacer(Modifier.height(TutiSpace.md))

        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    text = courseTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(TutiSpace.sm))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Флаг остаётся в своих цветах: это смысловая
                    // иллюстрация, а не иконка интерфейса.
                    Text(text = languageFlag, fontSize = 16.sp)
                    Spacer(Modifier.width(TutiSpace.sm))
                    Text(
                        text = lessonsProgressText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Spacer(Modifier.width(TutiSpace.md))

            // Значок курса — содержательная иллюстрация, поэтому эмодзи
            // и крупно: по нему курс узнают раньше, чем прочитают.
            Text(
                text = courseEmoji,
                fontSize = 52.sp,
                modifier = Modifier.padding(top = TutiSpace.md),
            )
        }

        Spacer(Modifier.height(TutiSpace.lg))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$percent%",
                style = MaterialTheme.typography.titleLarge,
                color = c.jade.base,
                maxLines = 1,
            )
            Spacer(Modifier.width(TutiSpace.md))
            TutiProgressBar(
                progress = fraction,
                modifier = Modifier.weight(1f),
                height = TutiSize.progressThick,
                colors = listOf(c.jade.base, c.jade.base),
                showHighlight = false,
            )
        }

        if (nextLessonTitle != null) {
            Spacer(Modifier.height(TutiSpace.xl))
            // Единственная кнопка на главном с волной внимания: это
            // действие, ради которого экран и открывают. Подпись
            // короткая — что именно откроется, сказано строкой ниже,
            // иначе название урока растягивало бы кнопку на две строки.
            TutiButton(
                text = common.continueLong,
                onClick = onContinue,
                tone = TutiButtonTone.Jade,
                size = TutiButtonSize.Large,
                attention = true,
            )
            Spacer(Modifier.height(TutiSpace.md))
            NextLessonLink(
                title = nextLessonTitle,
                emoji = nextLessonEmoji,
                onClick = onContinue,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

/** Плашка в шапке карточки курса: раздел и уровень. */
@Composable
private fun CourseBadge(
    text: String,
    background: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.sm))
            .background(background)
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            maxLines = 1,
        )
    }
}

/** Что откроется по кнопке: значок урока, название и стрелка. */
@Composable
private fun NextLessonLink(
    title: String,
    emoji: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.sm))
            .clickable { onClick() }
            .padding(horizontal = TutiSpace.sm, vertical = TutiSpace.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (emoji != null) {
            Text(text = emoji, fontSize = 15.sp)
            Spacer(Modifier.width(6.dp))
        }
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = c.jade.base,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = c.jade.base,
            modifier = Modifier.size(homeIconMd),
        )
    }
}

// ═══════════════════════════════════════════════════
//  3 · ДНЕВНЫЕ ЛИМИТЫ
// ═══════════════════════════════════════════════════

@Composable
private fun DailyLimitsCard(onNavigateToPlus: () -> Unit) {
    val context = LocalContext.current
    val strings = LocalTutiStrings.current
    val s = strings.home
    val isPlus = remember { PlusManager.isPlusActive(context) }

    // У Plus лимитов нет, а плашка «всё безлимитно» на главном не несёт
    // действия — статус подписки виден в профиле. Просто не показываем.
    if (isPlus) return

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        radius = TutiRadius.xl,
        contentPadding = TutiSpace.lg,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = s.dailyLimit,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(TutiSpace.sm))
            HomeOutlineChip(
                text = s.getPlus,
                icon = Icons.Outlined.WorkspacePremium,
                onClick = onNavigateToPlus,
            )
        }

        Spacer(Modifier.height(TutiSpace.sm))

        // Строки вместо ряда тесных плиток: у каждого лимита теперь
        // есть название, а не только значок, и остаток читается как
        // список, а не как четыре одинаковых квадрата.
        LimitRow(
            icon = Icons.AutoMirrored.Outlined.MenuBook,
            title = strings.common.lessonsLabel,
            left = FreeLimits.getRemainingCount(context, "lessons", FreeLimits.MAX_LESSONS_PER_DAY),
            max = FreeLimits.MAX_LESSONS_PER_DAY,
        )
        LimitRow(
            icon = Icons.Outlined.Forum,
            title = strings.practice.chatTitle,
            left = FreeLimits.getRemainingCount(context, "chat", FreeLimits.MAX_CHAT_MESSAGES_PER_DAY),
            max = FreeLimits.MAX_CHAT_MESSAGES_PER_DAY,
        )
        LimitRow(
            icon = Icons.Outlined.Style,
            title = strings.practice.flashcardsTitle,
            left = FreeLimits.getRemainingCount(context, "flashcards", FreeLimits.MAX_FLASHCARDS_PER_DAY),
            max = FreeLimits.MAX_FLASHCARDS_PER_DAY,
        )
        LimitRow(
            icon = Icons.Outlined.Headphones,
            title = strings.practice.listeningTitle,
            left = FreeLimits.getRemainingCount(context, "listening", FreeLimits.MAX_LISTENING_PER_DAY),
            max = FreeLimits.MAX_LISTENING_PER_DAY,
            showDivider = false,
        )
    }
}

/**
 * Строка лимита: значок в круге, название, остаток числом.
 *
 * Исчерпанный лимит гаснет в серый — цветной тревоги на чистом
 * экране не нужно, достаточно того, что строка перестала быть
 * зелёной.
 */
@Composable
private fun LimitRow(
    icon: ImageVector,
    title: String,
    left: Int,
    max: Int,
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
) {
    val c = MaterialTheme.tutiColors
    val empty = left == 0
    val accent = if (empty) c.lockedContent else c.jade.base

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = TutiSpace.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (empty) c.lockedBg else c.jade.soft),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accent,
                    modifier = Modifier.size(homeIconMd),
                )
            }
            Spacer(Modifier.width(TutiSpace.md))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = if (empty) c.lockedContent else MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(TutiSpace.sm))
            Text(
                text = "$left/$max",
                style = MaterialTheme.typography.labelMedium,
                color = if (empty) c.lockedContent else MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
            )
        }
        if (showDivider) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(c.divider),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  4 · СВОБОДНЫЕ ТЕМЫ
// ═══════════════════════════════════════════════════

@Composable
private fun FreeTopicsSection(topics: List<TopicProgressEntity>, onTopicClick: (String) -> Unit) {
    val s = LocalTutiStrings.current.home
    val isEnglish = topics.any { it.topicId.startsWith("en_") }
    val studyLang = if (isEnglish) "english" else "russian"
    val topicOrder = remember(studyLang) { FreeTopicsRegistry.orderedTopicIds(studyLang) }
    val progressMap = remember(topics) { topics.associateBy { it.topicId } }

    val displayTopics = remember(progressMap, topicOrder, studyLang) {
        topicOrder.map { id ->
            progressMap[id] ?: run {
                val def = FreeTopicsRegistry.definitionFor(id)
                TopicProgressEntity(
                    topicId = id,
                    topicName = def?.nameTj ?: id,
                    language = studyLang,
                    isUnlocked = def?.defaultUnlocked == true,
                )
            }
        }
    }

    HomeSectionHeader(
        title = s.freeTopics,
        counter = s.topicsCount(displayTopics.size),
    )

    // Темы едут горизонтальной лентой: секция не растягивает главный
    // экран на пятнадцать карточек.
    LazyRow(horizontalArrangement = Arrangement.spacedBy(TutiSpace.md)) {
        items(displayTopics, key = { it.topicId }) { topic ->
            TopicMiniCard(
                topic = topic,
                onClick = { if (topic.isUnlocked) onTopicClick(topic.topicId) },
            )
        }
    }
}

/** Ширина карточки в ленте: два с половиной элемента в видимой части. */
private val topicMiniCardWidth = 158.dp

@Composable
private fun TopicMiniCard(topic: TopicProgressEntity, onClick: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current
    val progress = topic.progressPercent / 100f
    val done = progress >= 1f
    val isLocked = !topic.isUnlocked
    val def = FreeTopicsRegistry.definitionFor(topic.topicId)
    val emoji = def?.emoji ?: "📚"
    val title = def?.localizedName(s) ?: topic.topicName
    val subtitle = localizedSubtitle(title, def?.subtitle ?: "")

    TutiCard(
        modifier = Modifier.width(topicMiniCardWidth),
        onClick = if (isLocked) null else onClick,
        radius = TutiRadius.lg,
        contentPadding = TutiSpace.md,
        background = if (isLocked) c.lockedBg else MaterialTheme.colorScheme.surface,
        borderColor = if (done) c.jade.base.copy(alpha = 0.45f) else c.cardBorder,
    ) {
        Column(modifier = if (isLocked) Modifier.alpha(0.75f) else Modifier) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TutiIconTile(
                    emoji = emoji,
                    size = TutiSize.iconTileSm,
                    radius = TutiRadius.pill,
                    background = if (done) c.jade.soft else c.tileBg,
                    dimmed = isLocked,
                )
                Spacer(Modifier.width(TutiSpace.sm))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isLocked) c.lockedContent else MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (subtitle.isNotBlank()) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Spacer(Modifier.height(TutiSpace.md))

            // Нижняя строка одинаковой высоты у всех карточек — иначе лента
            // получается рваной по нижнему краю.
            Row(verticalAlignment = Alignment.CenterVertically) {
                when {
                    isLocked -> Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = null,
                        tint = c.lockedContent,
                        modifier = Modifier.size(homeIconSm),
                    )
                    done -> Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        tint = c.jade.base,
                        modifier = Modifier.size(homeIconSm),
                    )
                    else -> Text(
                        text = "${topic.progressPercent}%",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                    )
                }
                Spacer(Modifier.width(TutiSpace.sm))
                TutiProgressBar(
                    progress = if (isLocked) 0f else progress,
                    modifier = Modifier.weight(1f),
                    height = 4.dp,
                    colors = listOf(c.jade.base, c.jade.base),
                    showHighlight = false,
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  PREVIEW
//
//  Данные здесь — только образцы для рендера в Android Studio;
//  в приложении все значения приходят из HomeViewModel.
// ═══════════════════════════════════════════════════

@Preview(showBackground = true, backgroundColor = 0xFFF7F9FC, widthDp = 360)
@Composable
private fun HomeCourseCardPreview() {
    TutiTheme {
        Column(
            modifier = Modifier.padding(TutiSpace.screen),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            CourseCard(
                badgeText = "Курси шумо",
                levelText = "Навомӯз",
                seeAllText = "Ҳама →",
                courseTitle = "Русӣ барои кор",
                courseEmoji = "💼",
                languageFlag = "🇷🇺",
                lessonsProgressText = "2 аз 40 дарс",
                fraction = 0.05f,
                percent = 5,
                nextLessonTitle = "Чӣ кор мекунед?",
                nextLessonEmoji = "👋",
                onContinue = {},
                onOpenCourse = {},
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF7F9FC, widthDp = 360)
@Composable
private fun HomeTopicsPreview() {
    TutiTheme {
        Column(
            modifier = Modifier.padding(TutiSpace.screen),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            HomeSectionHeader(title = "Мавзуъҳои озод", counter = "15 мавзуъ")
            Row(horizontalArrangement = Arrangement.spacedBy(TutiSpace.md)) {
                TopicMiniCard(
                    topic = TopicProgressEntity(
                        topicId = "greetings",
                        topicName = "Салом!",
                        language = "russian",
                        progressPercent = 40,
                        isUnlocked = true,
                    ),
                    onClick = {},
                )
                TopicMiniCard(
                    topic = TopicProgressEntity(
                        topicId = "family",
                        topicName = "Оила",
                        language = "russian",
                        isUnlocked = false,
                    ),
                    onClick = {},
                )
            }
        }
    }
}
