package app.tuti.tj.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.user.CityCatalog
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.theme.LocalDarkTheme
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.launch
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.OnboardingStrings
import app.tuti.tj.ui.i18n.TutiStrings
import app.tuti.tj.ui.i18n.label

// ════════════════════════════════════════════════════════════════
//  ОНБОРДИНГ
//
//  Каждый шаг окрашен в собственное акцентное семейство палитры.
//  Это не декор: смена цвета — самый дешёвый способ показать,
//  что вопрос сменился, и удержать внимание на семи экранах
//  подряд. Цвета берутся из дизайн-системы, а фон собирается
//  из мягких заливок того же семейства, а не из отдельных
//  «пастельных» наборов.
// ════════════════════════════════════════════════════════════════

private enum class StepTone { Grape, Mango, Leaf, Sky, Amber }

private val stepTones = listOf(
    StepTone.Grape,  // 0 — язык
    StepTone.Mango,  // 1 — уровень
    StepTone.Leaf,   // 2 — цель
    StepTone.Sky,    // 3 — время
    StepTone.Amber,  // 4 — город
)

private data class OptionItem(val emoji: String, val label: String, val sublabel: String)

private fun languageOptions(s: OnboardingStrings) = listOf(
    OptionItem("🇷🇺", s.optionRussian, s.optionRussianHint),
    OptionItem("🇬🇧", s.optionEnglish, s.optionEnglishHint),
)
private fun levelOptions(s: OnboardingStrings) = listOf(
    OptionItem("🌱", s.levelBeginner, s.levelBeginnerHint),
    OptionItem("📚", s.levelIntermediate, s.levelIntermediateHint),
    OptionItem("🚀", s.levelAdvanced, s.levelAdvancedHint),
)
private fun goalOptions(s: OnboardingStrings) = listOf(
    OptionItem("💼", s.goalWork, s.goalWorkHint),
    OptionItem("🎓", s.goalStudy, s.goalStudyHint),
    OptionItem("✈️", s.goalTravel, s.goalTravelHint),
    OptionItem("🧠", s.goalPersonal, s.goalPersonalHint),
)
private fun timeOptions(s: OnboardingStrings) = listOf("☕", "📖", "💪", "🔥")
    .zip(listOf(s.timeCalm, s.timeModerate, s.timeSerious, s.timeMax))
    .mapIndexed { idx, (emoji, hint) ->
        OptionItem(emoji, s.minutes(dailyMinutesValues[idx]), hint)
    }

/**
 * Список собирается из каталога: эмодзи и код лежат там, а название
 * и регион переводятся здесь. Раньше это были два параллельных
 * списка, которые надо было держать в одном порядке руками.
 */
private fun cityOptions(s: TutiStrings) = CityCatalog.all.map { city ->
    OptionItem(city.emoji, s.cities.name(city.tajikName), city.region.label(s))
}

/**
 * Пять страниц — ровно пять вопросов. Страницы знакомства здесь
 * больше нет: логотип, приветствия и маскота человек только что
 * видел на экране входа, показывать их второй раз незачем.
 */
private const val TOTAL_PAGES = 5

// ═══════════════════════════════════════════════════
//  ЭКРАН
// ═══════════════════════════════════════════════════

@Composable
fun OnboardingScreen(
    repository: TutiRepository,
    onComplete: () -> Unit,
    viewModel: OnboardingViewModel = viewModel(),
) {
    val isDark = LocalDarkTheme.current
    val c = MaterialTheme.tutiColors
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val strings = LocalTutiStrings.current
    val s = strings.onboarding

    var page by remember { mutableIntStateOf(0) }
    // Экрана входа в онбординге больше нет. Аккаунт создаётся
    // анонимно на старте приложения, поэтому uid уже готов, а
    // Google подключается позже к нему же.
    var isSaving by remember { mutableStateOf(false) }

    val langIdx = viewModel.languageIndex
    val levelIdx = viewModel.levelIndex
    val goalIdx = viewModel.goalIndex
    val timeIdx = viewModel.timeIndex
    val cityIdx = viewModel.cityIndex

    // Акцент шага берётся из палитры дизайн-системы
    val accentPair = when (stepTones[page.coerceIn(stepTones.indices)]) {
        StepTone.Grape -> c.grape
        StepTone.Mango -> c.mango
        StepTone.Leaf -> c.leaf
        StepTone.Sky -> c.sky
        StepTone.Amber -> c.mango
    }

    val animAccent by animateColorAsState(accentPair.base, tween(TutiMotion.SLOW), label = "acc")
    val animAccentDeep by animateColorAsState(accentPair.deep, tween(TutiMotion.SLOW), label = "accD")

    // Фон — мягкая заливка того же семейства, растворяющаяся в
    // цвет приложения. Никаких отдельных «пастельных» палитр.
    val bgTop by animateColorAsState(
        if (isDark) accentPair.soft else accentPair.soft,
        tween(TutiMotion.SLOW), label = "bgTop",
    )
    val bgBottom = MaterialTheme.colorScheme.background

    val canAdvance = when (page) {
        0 -> langIdx != null
        1 -> levelIdx != null
        2 -> goalIdx != null
        3 -> timeIdx != null
        4 -> cityIdx != null
        else -> true
    }

    // Подзаголовок шага «уровень» зависит от выбранного языка —
    // вопрос должен звучать про конкретный язык, а не абстрактно.
    // Индекс 1 — английский, порядок задан LearningLanguage.entries.
    val levelSubtitle = if (langIdx == 1) s.levelSubtitleEnglish else s.levelSubtitleRussian

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(bgTop, bgBottom, bgBottom))),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = TutiSpace.xl)
                .padding(top = TutiSpace.md, bottom = TutiSpace.md),
        ) {
            TopRow(
                page = page,
                accent = animAccent,
                onBack = { if (page > 0) page-- },
                onSkip = { page = TOTAL_PAGES - 1 },
            )

            Spacer(Modifier.height(TutiSpace.md))

            ProgressDots(current = page, accent = animAccent)

            Spacer(Modifier.height(TutiSpace.md))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (page) {
                    0 -> SelectionPage(
                        title = s.languageTitle,
                        subtitle = s.languageSubtitle,
                        options = languageOptions(s),
                        selected = langIdx,
                        accent = animAccent,
                        onSelect = viewModel::selectLanguage,
                    )
                    1 -> SelectionPage(
                        title = s.levelTitle,
                        subtitle = levelSubtitle,
                        options = levelOptions(s),
                        selected = levelIdx,
                        accent = animAccent,
                        onSelect = viewModel::selectLevel,
                    )
                    2 -> SelectionPage(
                        title = s.goalTitle,
                        subtitle = s.goalSubtitle,
                        options = goalOptions(s),
                        selected = goalIdx,
                        accent = animAccent,
                        onSelect = viewModel::selectGoal,
                    )
                    3 -> SelectionPage(
                        title = s.timeTitle,
                        subtitle = s.timeSubtitle,
                        options = timeOptions(s),
                        selected = timeIdx,
                        accent = animAccent,
                        onSelect = viewModel::selectTime,
                    )
                    4 -> SelectionPage(
                        title = s.cityTitle,
                        subtitle = s.citySubtitle,
                        options = cityOptions(strings),
                        selected = cityIdx,
                        accent = animAccent,
                        onSelect = viewModel::selectCity,
                    )
                }
            }

            Spacer(Modifier.height(TutiSpace.md))

            TutiButton(
                text = if (page < TOTAL_PAGES - 1) {
                    strings.common.continueShort
                } else {
                    strings.common.startAction
                },
                onClick = {
                    if (page < TOTAL_PAGES - 1) {
                        page++
                    } else {
                        // Сохранение не может провалиться так, чтобы человек
                        // застрял на онбординге: локальная часть обёрнута в
                        // runCatching, облачная ограничена таймаутом.
                        scope.launch {
                            isSaving = true
                            viewModel.complete(context, repository)
                            isSaving = false
                            onComplete()
                        }
                    }
                },
                enabled = canAdvance && !isSaving,
                loading = isSaving,
                size = if (page == TOTAL_PAGES - 1) TutiButtonSize.Large
                else TutiButtonSize.Medium,
                trailingEmoji = if (page < TOTAL_PAGES - 1) "→" else "🚀",
                gradient = listOf(animAccent, animAccentDeep),
            )

            Spacer(Modifier.height(TutiSpace.xs))
        }
    }
}

// ═══════════════════════════════════════════════════
//  ВЕРХНЯЯ СТРОКА
// ═══════════════════════════════════════════════════

@Composable
private fun TopRow(
    page: Int,
    accent: Color,
    onBack: () -> Unit,
    onSkip: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // На первом шаге кнопки «назад» нет: языком занялся экран
        // запуска, а возвращаться из приветствия некуда.
        if (page > 0) {
            Text(
                text = LocalTutiStrings.current.common.back,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onBack() }
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            )
        }

        Spacer(Modifier.weight(1f))

        if (page < TOTAL_PAGES - 1) {
            Text(
                text = LocalTutiStrings.current.common.skipArrow,
                style = MaterialTheme.typography.labelMedium,
                color = accent,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onSkip() }
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  ИНДИКАТОР ШАГОВ
// ═══════════════════════════════════════════════════

@Composable
private fun ProgressDots(current: Int, accent: Color) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(TOTAL_PAGES) { i ->
            val isActive = i == current
            val isPassed = i < current
            val w by animateDpAsState(
                if (isActive) 30.dp else 8.dp,
                tween(TutiMotion.NORMAL),
                label = "dotW$i",
            )
            val color by animateColorAsState(
                when {
                    isActive -> accent
                    isPassed -> accent.copy(alpha = 0.45f)
                    else -> c.progressTrack
                },
                tween(TutiMotion.NORMAL),
                label = "dotC$i",
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .height(8.dp)
                    .width(w)
                    .clip(RoundedCornerShape(TutiRadius.pill))
                    .background(color),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  ВОПРОСЫ
// ═══════════════════════════════════════════════════

@Composable
private fun SelectionPage(
    title: String,
    subtitle: String,
    options: List<OptionItem>,
    selected: Int?,
    accent: Color,
    onSelect: (Int) -> Unit,
) {
    val inf = rememberInfiniteTransition(label = "sel")
    val floatY by inf.animateFloat(
        initialValue = 0f, targetValue = 7f,
        animationSpec = infiniteRepeatable(
            tween(2400, easing = FastOutSlowInEasing), RepeatMode.Reverse,
        ),
        label = "sFloatY",
    )

    Spacer(Modifier.height(TutiSpace.sm))

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.offset { IntOffset(0, -floatY.toInt()) }) {
            LivingTutiMascot(size = 92.dp)
        }
    }

    Spacer(Modifier.height(TutiSpace.md))

    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(Modifier.height(TutiSpace.xs))
    Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(horizontal = TutiSpace.lg),
    )

    Spacer(Modifier.height(TutiSpace.xl))

    Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
        options.forEachIndexed { idx, opt ->
            OptionCard(
                option = opt,
                isSelected = selected == idx,
                accent = accent,
                onClick = { onSelect(idx) },
            )
        }
    }

    Spacer(Modifier.height(TutiSpace.lg))
}

@Composable
private fun OptionCard(
    option: OptionItem,
    isSelected: Boolean,
    accent: Color,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.lg)

    val bg by animateColorAsState(
        if (isSelected) accent.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
        tween(TutiMotion.NORMAL), label = "cardBg",
    )
    val borderColor by animateColorAsState(
        if (isSelected) accent else c.cardBorder,
        tween(TutiMotion.NORMAL), label = "brdC",
    )
    val borderW by animateDpAsState(
        if (isSelected) 2.dp else 1.dp, tween(TutiMotion.NORMAL), label = "brdW",
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(bg)
            .border(borderW, borderColor, shape)
            .clickable { onClick() }
            .padding(TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TutiIconTile(
            emoji = option.emoji,
            size = TutiSize.iconTileMd,
            background = if (isSelected) accent.copy(alpha = 0.16f) else c.tileBg,
        )
        Spacer(Modifier.width(TutiSpace.md))
        Column(Modifier.weight(1f)) {
            Text(
                text = option.label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = option.sublabel,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(Modifier.width(TutiSpace.sm))
        SelectionCheck(selected = isSelected, color = accent)
    }
}

