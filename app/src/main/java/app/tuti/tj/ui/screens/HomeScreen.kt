package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.FreeTopicsRegistry
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.data.subscription.PlusManager
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.OnboardingTooltips
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiDialog
import app.tuti.tj.ui.components.kit.TutiDialogActions
import app.tuti.tj.ui.components.kit.TutiGradientCard
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSectionHeader
import app.tuti.tj.ui.components.kit.dashedOutline
import app.tuti.tj.ui.components.onboardingSteps
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// ════════════════════════════════════════════════════════════════
//  ГЛАВНЫЙ ЭКРАН
//
//  Порядок блоков задан приоритетом: сначала «кто я и как иду»
//  (шапка + серия), затем «что делать прямо сейчас» (курс с
//  кнопкой продолжения), и только потом — вспомогательное
//  (лимиты, свободные темы). Каждый блок — компонент из кита,
//  никаких локальных цветов.
// ════════════════════════════════════════════════════════════════

private val dayLabels = listOf("Дш", "Сш", "Чш", "Пш", "Ҷм", "Шн", "Яш")

/** Как часто Тӯтӣ меняет реплику в шапке. */
private const val PHRASE_INTERVAL_MS = 12_000L

private fun formatXp(xp: Int): String =
    if (xp >= 1000) "${xp / 1000},${(xp % 1000).toString().padStart(3, '0')}" else xp.toString()

private fun languageDisplayInfo(lang: String): Triple<String, String, String> = when (lang) {
    "english" -> Triple("🇬🇧", "English", "🇷🇺 Иваз кун")
    "both" -> Triple("🇷🇺🇬🇧", "Русӣ ва Англисӣ", "")
    else -> Triple("🇷🇺", "Русский язык", "🇬🇧 Иваз кун")
}

private fun levelDisplay(level: String): String = when (level) {
    "beginner" -> "Навомӯз"
    "elementary" -> "Ибтидоӣ"
    "intermediate" -> "Миёна"
    "advanced" -> "Пешрафта"
    else -> "Ибтидоӣ"
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
            verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            Spacer(Modifier.height(TutiSpace.xs))

            Box(Modifier.fillMaxWidth().tooltipTarget("header_row", tooltipTargets)) {
                HeaderRow(
                    xp = u.totalXp,
                    streak = u.currentStreak,
                )
            }

            Box(Modifier.fillMaxWidth().tooltipTarget("streak_card", tooltipTargets)) {
                WeeklyStreakCard(
                    streakDates = uiState.streakDates,
                    currentStreak = u.currentStreak,
                )
            }

            CurrentLanguageCard(
                language = u.selectedLanguage,
                level = u.level,
                onSwitchLanguage = { showLanguageDialog = true },
            )

            if (showLanguageDialog) {
                LanguageSwitchDialog(
                    currentLanguage = u.selectedLanguage,
                    onDismiss = { showLanguageDialog = false },
                    onSelect = { selectedLang ->
                        showLanguageDialog = false
                        if (selectedLang != u.selectedLanguage) viewModel.setLanguage(selectedLang)
                    },
                )
            }

            val course = uiState.course
            if (course != null) {
                Column(
                    modifier = Modifier.fillMaxWidth().tooltipTarget("course_card", tooltipTargets),
                    verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
                ) {
                    YourCourseSection(
                        courseTitle = course.title,
                        courseEmoji = course.emoji,
                        progress = uiState.courseProgress,
                        onContinue = { onContinueCourse(courseId) },
                        onOpenCourse = { onOpenCourse(courseId) },
                    )
                }
            }

            DailyLimitsCard(onNavigateToPlus = onNavigateToPlus)

            Column(
                modifier = Modifier.fillMaxWidth().tooltipTarget("free_topics", tooltipTargets),
                verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
            ) {
                FreeTopicsSection(topics = uiState.topics, onTopicClick = onTopicClick)
            }

            Spacer(Modifier.height(TutiSpace.bottomNavGap))
        }

        if (showTooltips) {
            OnboardingTooltips(
                targetBounds = tooltipTargets,
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
//  1 · ШАПКА
// ═══════════════════════════════════════════════════

@Composable
private fun HeaderRow(
    xp: Int,
    streak: Int,
) {
    val context = LocalContext.current
    val isPlusUser = remember { PlusManager.isPlusActive(context) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        // Здоровается сам Тӯтӣ, а не аватар пользователя: приветствие
        // должно исходить от наставника — так экран открывается
        // персонажем, а не служебной иконкой профиля.
        // size задаёт не габарит бокса, а базу: реальный рисунок
        // получается на 24×32 dp крупнее, поэтому здесь 34, а не 58.
        LivingTutiMascot(size = 34.dp, waving = true)

        MascotSpeech(
            streak = streak,
            isPlus = isPlusUser,
            modifier = Modifier.weight(1f),
        )

        Spacer(Modifier.width(TutiSpace.sm))

        // Кубок отсюда уехал в центр нижней панели — рейтинг стал
        // разделом, а не действием с главного экрана.
        XpBadge(xp = xp)
    }
}

/**
 * Реплика Тӯтӣ.
 *
 * Статичная строка приветствия была длинной, переносилась на две
 * строки и всё равно ничего не сообщала. Здесь вместо неё пузырь
 * с хвостиком к маскоту: фразы короткие (2–3 слова, иначе не
 * влезут рядом с очками), меняются каждые несколько секунд и
 * зависят от состояния — серии и подписки. За счёт этого шапка
 * читается как обращение персонажа, а не как подпись под ним.
 */
@Composable
private fun MascotSpeech(
    streak: Int,
    isPlus: Boolean,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors

    // Ровно 12 реплик: при интервале в 12 с полный круг занимает
    // больше двух минут, поэтому за обычный заход на главный одна
    // и та же фраза не повторяется. Каждая — не длиннее 16
    // символов: рядом стоят очки и кубок, и на экране 360 dp
    // пузырю остаётся около 130 dp.
    val phrases = remember(streak, isPlus) {
        listOf(
            "Салом! 👋",
            if (streak > 0) "🔥 $streak рӯз" else "Оғоз кунем! 🚀",
            "Омода ҳастед?",
            "Як дарс кофӣ ✨",
            "Имрӯз чӣ омӯзем?",
            "Вақти машқ! ⏰",
            if (isPlus) "Plus фаъол ⭐" else "Забон осон аст!",
            "Аъло меравед! 👏",
            "Ман интизорам 🦜",
            "Ҳар рӯз як қадам",
            "Сӯҳбат кунем? 💬",
            "Давом диҳед! 💪",
        )
    }

    var index by remember(phrases) { mutableIntStateOf(0) }
    LaunchedEffect(phrases) {
        while (true) {
            kotlinx.coroutines.delay(PHRASE_INTERVAL_MS)
            index = (index + 1) % phrases.size
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Хвостик пузыря смотрит на маскота — реплика читается
        // как сказанная им, а не как подпись рядом.
        Canvas(modifier = Modifier.size(7.dp, 13.dp)) {
            val tail = Path().apply {
                moveTo(0f, size.height / 2f)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                close()
            }
            drawPath(tail, c.jade.soft)
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(TutiRadius.md))
                .background(c.jade.soft)
                .padding(horizontal = 10.dp, vertical = 7.dp),
        ) {
            AnimatedContent(
                targetState = phrases[index],
                transitionSpec = {
                    (fadeIn(tween(TutiMotion.NORMAL)) +
                        slideInVertically(tween(TutiMotion.NORMAL)) { it / 3 })
                        .togetherWith(fadeOut(tween(TutiMotion.FAST)))
                },
                label = "tutiSpeech",
            ) { phrase ->
                Text(
                    text = phrase,
                    style = MaterialTheme.typography.labelMedium,
                    color = c.jade.onSoft,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/**
 * Очки рядом с рейтингом.
 *
 * Заменил кнопку достижений: медаль дублировала профиль. Ни
 * подложки, ни полосы прогресса — в шапке, где уже есть маскот
 * и приветствие, любое дополнительное пятно спорит с контентом.
 * Остались только значок и число, набранные крупно, чтобы
 * показатель читался с одного взгляда.
 */
@Composable
private fun XpBadge(xp: Int, modifier: Modifier = Modifier) {
    val c = MaterialTheme.tutiColors

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("💎", fontSize = 17.sp)
        Spacer(Modifier.width(5.dp))
        Text(
            text = formatXp(xp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 17.sp,
            color = c.grape.base,
        )
    }
}

// ═══════════════════════════════════════════════════
//  2 · СЕРИЯ ЗА НЕДЕЛЮ
// ═══════════════════════════════════════════════════

@Composable
private fun WeeklyStreakCard(streakDates: Set<String>, currentStreak: Int) {
    val c = MaterialTheme.tutiColors
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.US) }
    val todayStr = remember { dateFormat.format(Calendar.getInstance().time) }
    val weekDays = remember {
        val cal = Calendar.getInstance()
        val today = cal.get(Calendar.DAY_OF_WEEK)
        val mondayOffset = if (today == Calendar.SUNDAY) -6 else Calendar.MONDAY - today
        cal.add(Calendar.DAY_OF_YEAR, mondayOffset)
        (0 until 7).map {
            val date = dateFormat.format(cal.time)
            cal.add(Calendar.DAY_OF_YEAR, 1)
            date
        }
    }
    val todayIndex = weekDays.indexOf(todayStr)
    val doneThisWeek = weekDays.count { it in streakDates }

    TutiGradientCard(
        gradient = c.streakGradient,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = TutiSpace.lg,
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔥", fontSize = 18.sp)
                Spacer(Modifier.width(TutiSpace.sm))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Серияи ҳафтаина",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                    )
                    Text(
                        text = "$doneThisWeek аз 7 рӯз",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f),
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(TutiRadius.pill))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                ) {
                    Text(
                        text = "$currentStreak 🔥",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                    )
                }
            }

            Spacer(Modifier.height(TutiSpace.lg))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                dayLabels.forEachIndexed { i, label ->
                    val dateStr = weekDays.getOrNull(i) ?: ""
                    DayCircle(
                        label = label,
                        isCompleted = dateStr in streakDates,
                        isToday = i == todayIndex,
                        isFuture = todayIndex >= 0 && i > todayIndex,
                    )
                }
            }
        }
    }
}

@Composable
private fun DayCircle(label: String, isCompleted: Boolean, isToday: Boolean, isFuture: Boolean) {
    // Сегодняшний невыполненный день пульсирует — единственный
    // бесконечный цикл на экране, поэтому он и притягивает взгляд.
    val pulseAlpha = if (isToday && !isCompleted) {
        val inf = rememberInfiniteTransition(label = "todayPulse")
        val a by inf.animateFloat(
            initialValue = 0.5f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                tween(1400, easing = FastOutSlowInEasing),
                RepeatMode.Restart,
            ),
            label = "pulseAlpha",
        )
        a
    } else 0f

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            if (isToday && !isCompleted) {
                Box(
                    Modifier
                        .size(44.dp)
                        .border(2.dp, Color.White.copy(alpha = pulseAlpha), CircleShape),
                )
            }
            val bg = when {
                isCompleted -> Color.White
                isToday -> Color.White.copy(alpha = 0.24f)
                else -> Color.White.copy(alpha = 0.1f)
            }
            val borderMod = when {
                isToday && !isCompleted -> Modifier.border(2.5.dp, Color.White, CircleShape)
                isFuture -> Modifier.dashedOutline(Color.White.copy(alpha = 0.35f), 18.dp, 1.dp)
                else -> Modifier
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .then(borderMod)
                    .background(bg, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    isCompleted -> Text(
                        "✓",
                        color = MaterialTheme.tutiColors.jade.base,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    isToday -> Text("·", color = Color.White, fontSize = 22.sp)
                    else -> Text("·", color = Color.White.copy(alpha = 0.4f), fontSize = 18.sp)
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = if (isCompleted || isToday) 1f else 0.55f),
        )
    }
}

// ═══════════════════════════════════════════════════
//  3 · ВЫБОР ЯЗЫКА
// ═══════════════════════════════════════════════════

private data class LangOption(val flag: String, val name: String, val sub: String, val key: String)

private val langOptions = listOf(
    LangOption("🇷🇺", "Русский язык", "Забони русӣ", "russian"),
    LangOption("🇬🇧", "English", "Забони англисӣ", "english"),
)

@Composable
private fun LanguageSwitchDialog(
    currentLanguage: String,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit,
) {
    val c = MaterialTheme.tutiColors
    var selectedKey by remember { mutableStateOf(currentLanguage) }

    TutiDialog(
        onDismiss = onDismiss,
        title = "Кадом забон?",
        message = "Забони омӯзишро интихоб кунед",
        mascotState = TutiState.THINKING,
        accent = c.grape.base,
    ) {
        Spacer(Modifier.height(TutiSpace.xl))
        Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
            langOptions.forEach { opt ->
                val isSelected = selectedKey == opt.key
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(TutiRadius.md))
                        .background(if (isSelected) c.grape.soft else c.tileBg)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) c.grape.base else c.cardBorder,
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
                    SelectionCheck(selected = isSelected, color = c.grape.base)
                }
            }
        }
        TutiDialogActions(
            primaryText = "Интихоб кардан",
            onPrimary = { onSelect(selectedKey) },
            secondaryText = "Бекор кардан",
            onSecondary = onDismiss,
            tone = app.tuti.tj.ui.components.kit.TutiButtonTone.Grape,
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
        if (selected) Text("✓", color = Color.White, fontSize = 14.sp)
    }
}

@Composable
private fun CurrentLanguageCard(language: String, level: String, onSwitchLanguage: () -> Unit) {
    val (flag, langName, switchText) = languageDisplayInfo(language)
    val c = MaterialTheme.tutiColors

    TutiCard(modifier = Modifier.fillMaxWidth(), radius = TutiRadius.lg) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TutiIconTile(emoji = flag, size = TutiSize.iconTileMd, background = c.tileBg)
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = langName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(4.dp))
                TutiPill(
                    text = levelDisplay(level),
                    leadingEmoji = "📊",
                    background = c.jade.soft,
                    contentColor = c.jade.onSoft,
                )
            }
            if (switchText.isNotEmpty()) {
                TutiPill(
                    text = switchText,
                    background = c.sky.soft,
                    contentColor = c.sky.onSoft,
                    onClick = onSwitchLanguage,
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  4 · ТЕКУЩИЙ КУРС — главный призыв к действию
// ═══════════════════════════════════════════════════

@Composable
private fun YourCourseSection(
    courseTitle: String,
    courseEmoji: String,
    progress: List<LessonProgressEntity>,
    onContinue: () -> Unit,
    onOpenCourse: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val completed = progress.count { it.completed }
    val total = progress.size.coerceAtLeast(1)
    val fraction = completed.toFloat() / total
    val pct = (fraction * 100).toInt()

    // Порядок берём из контента курса: строки прогресса в БД могут идти вразнобой.
    val courseId = progress.firstOrNull()?.courseId
    val completedIds = progress.filter { it.completed }.map { it.lessonId }.toSet()
    val nextLessonId = courseId?.let { ContentProvider.getNextLessonId(it, completedIds) }
    val nextLessonContent = nextLessonId?.let { ContentProvider.getLesson(it) }

    TutiSectionHeader(title = "Курси шумо", actionText = "Ҳама →", onAction = onOpenCourse)

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onOpenCourse,
        contentPadding = TutiSpace.lg,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TutiIconTile(
                emoji = courseEmoji,
                size = TutiSize.iconTileLg,
                background = c.jade.soft,
                radius = TutiRadius.md,
            )
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = courseTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$completed аз $total дарс",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.width(TutiSpace.sm))
                    TutiPill(text = "$pct%")
                }
            }
        }

        Spacer(Modifier.height(TutiSpace.md))

        TutiProgressBar(
            progress = fraction,
            height = TutiSize.progressThick,
            colors = c.progressGradient,
        )

        if (nextLessonContent != null) {
            Spacer(Modifier.height(TutiSpace.lg))
            // Единственная кнопка на главном с волной внимания: это
            // действие, ради которого экран и открывают. Остальные
            // кнопки намеренно спокойные — иначе подсветка обесценится.
            TutiButton(
                text = "Давоми дарс: ${nextLessonContent.title}",
                onClick = onContinue,
                leadingEmoji = nextLessonContent.emoji,
                trailingEmoji = "→",
                size = TutiButtonSize.Medium,
                attention = true,
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  5 · ДНЕВНЫЕ ЛИМИТЫ
// ═══════════════════════════════════════════════════

@Composable
private fun DailyLimitsCard(onNavigateToPlus: () -> Unit) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val isPlus = remember { PlusManager.isPlusActive(context) }

    // У Plus лимитов нет, а плашка «всё безлимитно» на главном не несёт
    // действия — статус подписки виден в профиле. Просто не показываем.
    if (isPlus) return

    TutiCard(modifier = Modifier.fillMaxWidth(), radius = TutiRadius.lg) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Лимити имрӯза",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            TutiPill(
                text = "Plus гиред",
                leadingEmoji = "⭐",
                background = c.mango.soft,
                contentColor = c.mango.onSoft,
                onClick = onNavigateToPlus,
            )
        }
        Spacer(Modifier.height(TutiSpace.md))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            LimitChip(
                "📚", FreeLimits.getRemainingCount(context, "lessons", FreeLimits.MAX_LESSONS_PER_DAY),
                FreeLimits.MAX_LESSONS_PER_DAY, c.jade.base, Modifier.weight(1f),
            )
            LimitChip(
                "🦜", FreeLimits.getRemainingCount(context, "chat", FreeLimits.MAX_CHAT_MESSAGES_PER_DAY),
                FreeLimits.MAX_CHAT_MESSAGES_PER_DAY, c.grape.base, Modifier.weight(1f),
            )
            LimitChip(
                "🃏", FreeLimits.getRemainingCount(context, "flashcards", FreeLimits.MAX_FLASHCARDS_PER_DAY),
                FreeLimits.MAX_FLASHCARDS_PER_DAY, c.mango.base, Modifier.weight(1f),
            )
            LimitChip(
                "🎧", FreeLimits.getRemainingCount(context, "listening", FreeLimits.MAX_LISTENING_PER_DAY),
                FreeLimits.MAX_LISTENING_PER_DAY, c.sky.base, Modifier.weight(1f),
            )
        }
    }
}

/**
 * Остаток лимита показан не только числом, но и мини-полосой:
 * «сколько осталось» считывается периферийным зрением.
 */
@Composable
private fun LimitChip(
    emoji: String,
    left: Int,
    max: Int,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.sm))
            .background(c.tileBg)
            .padding(vertical = TutiSpace.sm, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(emoji, fontSize = 16.sp)
        Spacer(Modifier.height(4.dp))
        Text(
            text = "$left/$max",
            style = MaterialTheme.typography.labelSmall,
            color = if (left == 0) c.coral.base else MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(4.dp))
        TutiProgressBar(
            progress = if (max == 0) 0f else left.toFloat() / max,
            height = 3.dp,
            colors = listOf(color, color),
        )
    }
}

// ═══════════════════════════════════════════════════
//  6 · СВОБОДНЫЕ ТЕМЫ
// ═══════════════════════════════════════════════════

@Composable
private fun FreeTopicsSection(topics: List<TopicProgressEntity>, onTopicClick: (String) -> Unit) {
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

    TutiSectionHeader(
        title = "Мавзуъҳои озод",
        counter = "${displayTopics.size} мавзуъ",
    )

    Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
        displayTopics.forEach { topic ->
            TopicCard(topic = topic, onClick = { if (topic.isUnlocked) onTopicClick(topic.topicId) })
        }
    }
}

@Composable
private fun TopicCard(topic: TopicProgressEntity, onClick: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val progress = topic.progressPercent / 100f
    val done = progress >= 1f
    val isLocked = !topic.isUnlocked
    val def = FreeTopicsRegistry.definitionFor(topic.topicId)
    val emoji = def?.emoji ?: "📚"
    val subtitle = def?.subtitle ?: ""

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = if (isLocked) null else onClick,
        radius = TutiRadius.lg,
        contentPadding = TutiSpace.md,
        background = when {
            done -> c.leaf.soft
            isLocked -> c.lockedBg
            else -> MaterialTheme.colorScheme.surface
        },
        borderColor = when {
            done -> c.correctBorder
            isLocked -> c.lockedBorder
            else -> c.cardBorder
        },
        dashed = isLocked,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = if (isLocked) Modifier.alpha(0.75f) else Modifier,
        ) {
            TutiIconTile(
                emoji = emoji,
                background = if (done) c.leaf.soft else c.tileBg,
                dimmed = isLocked,
            )
            Spacer(Modifier.width(TutiSpace.md))

            Column(Modifier.weight(1f)) {
                Text(
                    text = topic.topicName,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isLocked) c.lockedContent else MaterialTheme.colorScheme.onSurface,
                )
                if (subtitle.isNotBlank()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (!isLocked) {
                    Spacer(Modifier.height(TutiSpace.sm))
                    TutiProgressBar(
                        progress = progress,
                        colors = if (done) listOf(c.leaf.base, c.leaf.base.copy(alpha = 0.75f))
                        else c.progressGradient,
                    )
                }
            }

            Spacer(Modifier.width(TutiSpace.sm))

            when {
                isLocked -> Text("🔒", fontSize = 18.sp)
                done -> TutiPill(
                    text = "Тамом",
                    leadingEmoji = "⭐",
                    background = c.mango.soft,
                    contentColor = c.mango.onSoft,
                )
                else -> TutiPill(text = "${topic.progressPercent}%")
            }
        }
    }
}
