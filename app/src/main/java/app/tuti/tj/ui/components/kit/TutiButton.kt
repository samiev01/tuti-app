package app.tuti.tj.ui.components.kit

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  КНОПКИ
//
//  Фирменный приём Tuti — «плита»: под кнопкой лежит полоса
//  того же цвета, но темнее. При нажатии кнопка проваливается
//  на её толщину. Это даёт физический отклик, который читается
//  без анимации цвета и работает даже на слабых устройствах.
//
//  Иерархия:
//    Primary   — одно главное действие на экран
//    Accent    — то же, но в другом акцентном семействе
//    Secondary — контурная, равнозначные действия
//    Ghost     — третьестепенное, без фона
// ════════════════════════════════════════════════════════════════

enum class TutiButtonTone { Jade, Mango, Grape, Sky, Coral, Leaf }

enum class TutiButtonSize { Small, Medium, Large }

@Composable
private fun toneColors(tone: TutiButtonTone): Pair<Color, Color> {
    val c = MaterialTheme.tutiColors
    val a = when (tone) {
        TutiButtonTone.Jade -> c.jade
        TutiButtonTone.Mango -> c.mango
        TutiButtonTone.Grape -> c.grape
        TutiButtonTone.Sky -> c.sky
        TutiButtonTone.Coral -> c.coral
        TutiButtonTone.Leaf -> c.leaf
    }
    return a.base to a.deep
}

private fun heightOf(size: TutiButtonSize): Dp = when (size) {
    TutiButtonSize.Small -> TutiSize.buttonSm
    TutiButtonSize.Medium -> TutiSize.buttonMd
    TutiButtonSize.Large -> TutiSize.buttonLg
}

private fun textSizeOf(size: TutiButtonSize) = when (size) {
    TutiButtonSize.Small -> 14.sp
    TutiButtonSize.Medium -> 16.sp
    TutiButtonSize.Large -> 18.sp
}

/**
 * Главная кнопка. Заливка сплошная либо градиентная, снизу —
 * «плита» толщиной [TutiSize.plate], которая уходит при нажатии.
 */
@Composable
fun TutiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tone: TutiButtonTone = TutiButtonTone.Jade,
    size: TutiButtonSize = TutiButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingEmoji: String? = null,
    trailingEmoji: String? = null,
    gradient: List<Color>? = null,
    fillWidth: Boolean = true,
    playSound: Boolean = true,
    attention: Boolean = false,
) {
    val (base, deep) = toneColors(tone)
    val colors = MaterialTheme.tutiColors
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val active = enabled && !loading

    val shape = RoundedCornerShape(TutiRadius.lg)
    val plate = TutiSize.plate
    val sink by animateDpAsState(
        targetValue = if (pressed && active) plate else 0.dp,
        animationSpec = TutiMotion.fast(),
        label = "buttonSink",
    )

    val faceBrush = when {
        !active -> Brush.horizontalGradient(listOf(colors.lockedBorder, colors.lockedBorder))
        gradient != null -> Brush.horizontalGradient(gradient)
        else -> Brush.horizontalGradient(listOf(base, base))
    }
    val plateColor = if (active) deep else colors.lockedBorder.copy(alpha = 0.6f)
    val contentColor = if (active) Color.White else colors.lockedContent

    Box(
        modifier = modifier
            .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
            .height(heightOf(size) + plate),
    ) {
        // плита — статичная тёмная подложка
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(heightOf(size) + plate)
                .clip(shape)
                .background(plateColor),
        )
        // лицевая часть — проваливается при нажатии
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sink)
                .height(heightOf(size))
                .clip(shape)
                .background(faceBrush)
                .then(if (attention && active) attentionSweep() else Modifier)
                .clickable(
                    interactionSource = interaction,
                    indication = null,
                    enabled = active,
                ) {
                    if (playSound) runCatching { TutiSoundManager.playButtonClick() }
                    onClick()
                },
            contentAlignment = Alignment.Center,
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    strokeWidth = 2.5.dp,
                    color = Color.White,
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 20.dp),
                ) {
                    if (leadingEmoji != null) {
                        Text(leadingEmoji, fontSize = textSizeOf(size))
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = textSizeOf(size),
                        color = contentColor,
                        textAlign = TextAlign.Center,
                    )
                    if (trailingEmoji != null) {
                        Spacer(Modifier.width(8.dp))
                        Text(trailingEmoji, fontSize = textSizeOf(size))
                    }
                }
            }
        }
    }
}

/**
 * Волна внимания: световая полоса пробегает по кнопке слева направо.
 *
 * Между пробегами держится пауза больше секунды — непрерывное
 * мерцание в интерфейсе, куда возвращаются каждый день, быстро
 * начинает раздражать, а разовый проблеск взгляд всё равно ловит.
 * Полоса рисуется поверх заливки и обрезается формой кнопки.
 *
 * Ставится только на главное действие экрана: если подсветить
 * несколько кнопок сразу, приём перестаёт работать.
 */
@Composable
private fun attentionSweep(): Modifier {
    val inf = rememberInfiniteTransition(label = "attention")
    val x by inf.animateFloat(
        initialValue = -0.4f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                -0.4f at 0
                -0.4f at 1300      // пауза перед пробегом
                1.4f at 2400       // сам пробег
                1.4f at 3000       // пауза после
            },
        ),
        label = "attentionX",
    )

    return Modifier.drawWithContent {
        drawContent()
        val band = size.width * 0.3f
        val cx = x * size.width
        drawRect(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = 0.32f),
                    Color.Transparent,
                ),
                startX = cx - band / 2f,
                endX = cx + band / 2f,
            ),
        )
    }
}

/**
 * Контурная кнопка. Тот же провал при нажатии, но грань —
 * это утолщённая рамка, а не заливка.
 */
@Composable
fun TutiSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tone: TutiButtonTone = TutiButtonTone.Jade,
    size: TutiButtonSize = TutiButtonSize.Medium,
    enabled: Boolean = true,
    leadingEmoji: String? = null,
    fillWidth: Boolean = true,
) {
    val (base, _) = toneColors(tone)
    val colors = MaterialTheme.tutiColors
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()

    val shape = RoundedCornerShape(TutiRadius.lg)
    val plate = TutiSize.plate
    val sink by animateDpAsState(
        targetValue = if (pressed && enabled) plate else 0.dp,
        animationSpec = TutiMotion.fast(),
        label = "secondarySink",
    )
    val lineColor = if (enabled) colors.cardBorder else colors.lockedBorder
    val label = if (enabled) base else colors.lockedContent

    Box(
        modifier = modifier
            .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
            .height(heightOf(size) + plate),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(heightOf(size) + plate)
                .clip(shape)
                .background(lineColor),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sink)
                .height(heightOf(size))
                .clip(shape)
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, lineColor, shape)
                .clickable(
                    interactionSource = interaction,
                    indication = null,
                    enabled = enabled,
                ) {
                    runCatching { TutiSoundManager.playButtonClick() }
                    onClick()
                },
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                if (leadingEmoji != null) {
                    Text(leadingEmoji, fontSize = textSizeOf(size))
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = textSizeOf(size),
                    color = label,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

/** Третьестепенное действие: без фона и без плиты. */
@Composable
fun TutiGhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color? = null,
) {
    val resolved = color ?: MaterialTheme.colorScheme.onSurfaceVariant
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.md))
            .clickable(enabled = enabled) {
                runCatching { TutiSoundManager.playButtonClick() }
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 14.sp,
            color = if (enabled) resolved else MaterialTheme.tutiColors.lockedContent,
        )
    }
}

/**
 * Круглая кнопка-иконка. Используется в шапках экранов и
 * на карточках (звук, закрыть, назад).
 *
 * [showBackground] = false убирает подложку и оставляет один
 * значок: там, где такие кнопки стоят рядом с текстом, заливка
 * начинает соперничать с содержимым экрана.
 */
@Composable
fun TutiIconButton(
    emoji: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = TutiSize.iconTileSm,
    tone: TutiButtonTone = TutiButtonTone.Jade,
    filled: Boolean = false,
    showBackground: Boolean = true,
    enabled: Boolean = true,
    contentDescription: String? = null,
) {
    val (base, _) = toneColors(tone)
    val accentSoft = when (tone) {
        TutiButtonTone.Jade -> MaterialTheme.tutiColors.jade.soft
        TutiButtonTone.Mango -> MaterialTheme.tutiColors.mango.soft
        TutiButtonTone.Grape -> MaterialTheme.tutiColors.grape.soft
        TutiButtonTone.Sky -> MaterialTheme.tutiColors.sky.soft
        TutiButtonTone.Coral -> MaterialTheme.tutiColors.coral.soft
        TutiButtonTone.Leaf -> MaterialTheme.tutiColors.leaf.soft
    }
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateDpAsState(
        targetValue = if (pressed) size - 3.dp else size,
        animationSpec = TutiMotion.fast(),
        label = "iconBtn",
    )

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(scale)
                .clip(RoundedCornerShape(TutiRadius.sm))
                .background(
                    when {
                        !showBackground -> Color.Transparent
                        filled -> base
                        else -> accentSoft
                    },
                )
                .clickable(
                    interactionSource = interaction,
                    indication = null,
                    enabled = enabled,
                ) {
                    runCatching { TutiSoundManager.playButtonClick() }
                    onClick()
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = emoji, fontSize = (size.value * 0.44f).sp)
        }
    }
}

/**
 * Вариант ответа в упражнении. Отдельный компонент, потому что
 * у него четыре состояния и он должен выглядеть одинаково во
 * всех типах заданий.
 */
enum class TutiOptionState { Idle, Selected, Correct, Wrong }

@Composable
fun TutiOptionButton(
    text: String,
    state: TutiOptionState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leading: String? = null,
    minHeight: Dp = 56.dp,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.md)

    val bg = when (state) {
        TutiOptionState.Idle -> MaterialTheme.colorScheme.surface
        TutiOptionState.Selected -> c.jade.soft
        TutiOptionState.Correct -> c.correctBg
        TutiOptionState.Wrong -> c.wrongBg
    }
    val border = when (state) {
        TutiOptionState.Idle -> c.cardBorder
        TutiOptionState.Selected -> c.jade.base
        TutiOptionState.Correct -> c.correctBorder
        TutiOptionState.Wrong -> c.wrongBorder
    }
    val fg = when (state) {
        TutiOptionState.Idle -> MaterialTheme.colorScheme.onSurface
        TutiOptionState.Selected -> c.jade.onSoft
        TutiOptionState.Correct -> c.correctText
        TutiOptionState.Wrong -> c.wrongText
    }
    val borderWidth = if (state == TutiOptionState.Idle) 1.5.dp else 2.dp

    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val sink by animateDpAsState(
        targetValue = if (pressed && enabled) 3.dp else 0.dp,
        animationSpec = TutiMotion.fast(),
        label = "optionSink",
    )

    Box(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = minHeight + 3.dp)
                .clip(shape)
                .background(border.copy(alpha = 0.55f)),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sink)
                .defaultMinSize(minHeight = minHeight)
                .clip(shape)
                .background(bg)
                .border(borderWidth, border, shape)
                .clickable(
                    interactionSource = interaction,
                    indication = null,
                    enabled = enabled,
                ) { onClick() }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leading != null) {
                Text(leading, fontSize = 22.sp)
                Spacer(Modifier.width(12.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = fg,
                modifier = Modifier.weight(1f),
            )
            when (state) {
                TutiOptionState.Correct -> Text("✓", fontSize = 20.sp, color = c.correctText)
                TutiOptionState.Wrong -> Text("✕", fontSize = 18.sp, color = c.wrongText)
                else -> Unit
            }
        }
    }
}
