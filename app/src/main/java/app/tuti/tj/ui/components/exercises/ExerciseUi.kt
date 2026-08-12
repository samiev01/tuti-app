package app.tuti.tj.ui.components.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ОБЩИЕ ЭЛЕМЕНТЫ УПРАЖНЕНИЙ
//
//  Все девять типов заданий раньше повторяли одну и ту же
//  разметку с чуть разными отступами и цветами. Здесь она
//  собрана в три примитива: задание, вариант ответа, сетка
//  вариантов. Благодаря этому переход между типами заданий
//  внутри урока перестал ощущаться как переход между экранами
//  разных приложений.
// ════════════════════════════════════════════════════════════════

/** Формулировка задания и, если есть, разбираемая фраза. */
@Composable
fun ExercisePrompt(
    prompt: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
) {
    val c = MaterialTheme.tutiColors
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = prompt,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        if (!hint.isNullOrBlank()) {
            Spacer(Modifier.height(TutiSpace.md))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(c.jade.soft)
                    .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.md),
            ) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.headlineSmall,
                    color = c.jade.onSoft,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(Modifier.height(TutiSpace.xl))
    }
}

/** Визуальное состояние варианта — общее для всех типов заданий. */
enum class ChoiceState { Idle, Selected, Correct, Wrong, Dimmed }

/**
 * Вычисляет состояние варианта по трём флагам. Вынесено,
 * потому что раньше эта логика была скопирована в пяти файлах
 * и в двух из них расходилась.
 */
fun choiceStateOf(
    isSelected: Boolean,
    isCorrect: Boolean,
    answeredCorrectly: Boolean?,
): ChoiceState = when {
    answeredCorrectly != null && isCorrect -> ChoiceState.Correct
    answeredCorrectly == false && isSelected -> ChoiceState.Wrong
    answeredCorrectly != null -> ChoiceState.Dimmed
    isSelected -> ChoiceState.Selected
    else -> ChoiceState.Idle
}

/** Одиночный вариант ответа. */
@Composable
fun ExerciseChoice(
    text: String,
    state: ChoiceState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    minHeight: androidx.compose.ui.unit.Dp = 56.dp,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.md)

    val bg by animateColorAsState(
        when (state) {
            ChoiceState.Idle, ChoiceState.Dimmed -> MaterialTheme.colorScheme.surface
            ChoiceState.Selected -> c.jade.soft
            ChoiceState.Correct -> c.correctBg
            ChoiceState.Wrong -> c.wrongBg
        },
        animationSpec = TutiMotion.fast(),
        label = "choiceBg",
    )
    val border by animateColorAsState(
        when (state) {
            ChoiceState.Idle, ChoiceState.Dimmed -> c.cardBorder
            ChoiceState.Selected -> c.jade.base
            ChoiceState.Correct -> c.correctText
            ChoiceState.Wrong -> c.wrongText
        },
        animationSpec = TutiMotion.fast(),
        label = "choiceBorder",
    )
    val fg = when (state) {
        ChoiceState.Idle -> MaterialTheme.colorScheme.onSurface
        ChoiceState.Dimmed -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f)
        ChoiceState.Selected -> c.jade.onSoft
        ChoiceState.Correct -> c.correctText
        ChoiceState.Wrong -> c.wrongText
    }

    Row(
        modifier = modifier
            .defaultMinSize(minHeight = minHeight)
            .clip(shape)
            .background(bg)
            .border(
                width = if (state == ChoiceState.Idle || state == ChoiceState.Dimmed) 1.5.dp else 2.dp,
                color = border,
                shape = shape,
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = TutiSpace.md, vertical = TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = fg,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f, fill = false),
        )
        when (state) {
            ChoiceState.Correct -> {
                Spacer(Modifier.width(TutiSpace.sm))
                Text("✓", style = MaterialTheme.typography.titleLarge, color = c.correctText)
            }
            ChoiceState.Wrong -> {
                Spacer(Modifier.width(TutiSpace.sm))
                Text("✕", style = MaterialTheme.typography.titleMedium, color = c.wrongText)
            }
            else -> Unit
        }
    }
}

/**
 * Сетка вариантов. Короткие варианты выкладываются по два в
 * ряд, длинные — в столбик: так текст не переносится посреди
 * слова и кнопки не «прыгают» по высоте.
 */
@Composable
fun ExerciseChoiceGrid(
    options: List<String>,
    selectedIndex: Int?,
    correctIndex: Int?,
    answeredCorrectly: Boolean?,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val useTwoColumns = options.size >= 3 && options.all { it.length <= 14 }

    if (useTwoColumns) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            options.chunked(2).forEachIndexed { rowIdx, rowOptions ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
                ) {
                    rowOptions.forEachIndexed { colIdx, option ->
                        val idx = rowIdx * 2 + colIdx
                        ExerciseChoice(
                            text = option,
                            state = choiceStateOf(
                                isSelected = selectedIndex == idx,
                                isCorrect = correctIndex == idx,
                                answeredCorrectly = answeredCorrectly,
                            ),
                            enabled = answeredCorrectly == null,
                            onClick = { onSelect(idx) },
                            modifier = Modifier.weight(1f),
                            minHeight = 64.dp,
                        )
                    }
                    if (rowOptions.size < 2) Spacer(Modifier.weight(1f))
                }
            }
        }
    } else {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            options.forEachIndexed { idx, option ->
                ExerciseChoice(
                    text = option,
                    state = choiceStateOf(
                        isSelected = selectedIndex == idx,
                        isCorrect = correctIndex == idx,
                        answeredCorrectly = answeredCorrectly,
                    ),
                    enabled = answeredCorrectly == null,
                    onClick = { onSelect(idx) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

/**
 * Перенос элементов по строкам.
 *
 * Намеренно не используется `FlowRow`: он помечен как
 * `@ExperimentalLayoutApi`, и его сигнатура менялась между
 * Compose Foundation 1.7 и 1.8 (тип параметра overflow). В этом
 * проекте компиляция идёт против 1.7.2, а в APK попадает 1.9.2,
 * поэтому вызов FlowRow падал в рантайме с NoSuchMethodError.
 * Здесь тот же результат собран на стабильном [Layout], который
 * от версии библиотеки не зависит.
 *
 * Строки центрируются по горизонтали, элементы внутри строки —
 * по вертикали.
 */
@Composable
fun WrapRow(
    modifier: Modifier = Modifier,
    horizontalSpacing: Dp = TutiSpace.sm,
    verticalSpacing: Dp = TutiSpace.sm,
    content: @Composable () -> Unit,
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val hGap = horizontalSpacing.roundToPx()
        val vGap = verticalSpacing.roundToPx()

        // Дети меряются свободно по высоте: строка подстраивается
        // под самый высокий элемент.
        val placeables = measurables.map {
            it.measure(constraints.copy(minWidth = 0, minHeight = 0))
        }

        val rowLimit = if (constraints.hasBoundedWidth) constraints.maxWidth else Int.MAX_VALUE

        val rows = mutableListOf<List<Placeable>>()
        var row = mutableListOf<Placeable>()
        var rowWidth = 0
        placeables.forEach { p ->
            val projected = if (row.isEmpty()) p.width else rowWidth + hGap + p.width
            if (row.isNotEmpty() && projected > rowLimit) {
                rows += row
                row = mutableListOf(p)
                rowWidth = p.width
            } else {
                row += p
                rowWidth = projected
            }
        }
        if (row.isNotEmpty()) rows += row

        val rowHeights = rows.map { r -> r.maxOf { it.height } }
        val gapsHeight = vGap * (rows.size - 1).coerceAtLeast(0)
        val totalHeight = (rowHeights.sum() + gapsHeight).coerceAtLeast(constraints.minHeight)
        val totalWidth = if (constraints.hasBoundedWidth) {
            constraints.maxWidth
        } else {
            rows.maxOfOrNull { r -> r.sumOf { it.width } + hGap * (r.size - 1) } ?: 0
        }

        layout(totalWidth, totalHeight) {
            var y = 0
            rows.forEachIndexed { i, r ->
                val lineWidth = r.sumOf { it.width } + hGap * (r.size - 1).coerceAtLeast(0)
                var x = ((totalWidth - lineWidth) / 2).coerceAtLeast(0)
                r.forEach { p ->
                    p.placeRelative(x, y + (rowHeights[i] - p.height) / 2)
                    x += p.width + hGap
                }
                y += rowHeights[i] + vGap
            }
        }
    }
}

/** Слово-«плитка» для сборки предложения и подобных заданий. */
@Composable
fun ExerciseWordChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    used: Boolean = false,
    filled: Boolean = false,
    enabled: Boolean = true,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.sm)

    val bg = when {
        used -> c.lockedBg
        filled -> c.jade.soft
        else -> MaterialTheme.colorScheme.surface
    }
    val border = when {
        used -> c.lockedBorder
        filled -> c.jade.base
        else -> c.cardBorder
    }
    val fg = when {
        used -> c.lockedContent
        filled -> c.jade.onSoft
        else -> MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(bg)
            .border(1.5.dp, border, shape)
            .clickable(enabled = enabled && !used) { onClick() }
            .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.md),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = fg,
        )
    }
}
