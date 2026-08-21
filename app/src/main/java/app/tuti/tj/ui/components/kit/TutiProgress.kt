package app.tuti.tj.ui.components.kit

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ПРОГРЕСС
//
//  Один компонент на всё приложение. Заполнение всегда
//  градиентное и всегда анимируется — движение полосы вперёд
//  и есть главная награда за правильный ответ.
// ════════════════════════════════════════════════════════════════

@Composable
fun TutiProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    height: Dp = TutiSize.progressThin,
    colors: List<Color>? = null,
    trackColor: Color? = null,
    animated: Boolean = true,
    showShine: Boolean = false,
    /**
     * Светлая полоса поверх заливки. Нужна градиентным полосам,
     * чтобы они не выглядели плоской плашкой; там, где полоса
     * залита одним чистым цветом, блик только мутит его.
     */
    showHighlight: Boolean = true,
) {
    val c = MaterialTheme.tutiColors
    val fill = colors ?: c.progressGradient
    val track = trackColor ?: c.progressTrack

    val target = progress.coerceIn(0f, 1f)
    val animatedValue by animateFloatAsState(
        targetValue = target,
        animationSpec = if (animated) tween(TutiMotion.SLOW, easing = TutiMotion.standard)
        else tween(0),
        label = "progressFill",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(track),
    ) {
        if (animatedValue > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedValue)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(TutiRadius.pill))
                    .background(Brush.horizontalGradient(fill))
                    .then(if (showShine) shineModifier() else Modifier),
            ) {
                // светлая полоса сверху — даёт полосе объём
                if (showHighlight) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height / 3)
                            .padding(horizontal = 3.dp)
                            .clip(RoundedCornerShape(TutiRadius.pill))
                            .background(Color.White.copy(alpha = 0.28f)),
                    )
                }
            }
        }
    }
}

/** Пробегающий блик — для «живого» прогресса во время загрузки. */
@Composable
private fun shineModifier(): Modifier {
    val inf = rememberInfiniteTransition(label = "shine")
    val x by inf.animateFloat(
        initialValue = -0.4f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(tween(1600), RepeatMode.Restart),
        label = "shineX",
    )
    return Modifier.drawWithContent {
        drawContent()
        val w = size.width * 0.3f
        drawRect(
            brush = Brush.horizontalGradient(
                listOf(Color.Transparent, Color.White.copy(alpha = 0.45f), Color.Transparent),
                startX = x * size.width - w / 2,
                endX = x * size.width + w / 2,
            ),
            topLeft = Offset.Zero,
            size = Size(size.width, size.height),
        )
    }
}

/**
 * Посегментный прогресс урока: по одному сегменту на задание.
 * Пользователь сразу видит, сколько осталось — это снижает
 * тревожность на длинных уроках.
 */
@Composable
fun TutiSegmentedProgress(
    total: Int,
    current: Int,
    modifier: Modifier = Modifier,
    height: Dp = TutiSize.progressThin,
    activeColors: List<Color>? = null,
) {
    val c = MaterialTheme.tutiColors
    val fill = activeColors ?: c.progressGradient
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(total.coerceAtLeast(1)) { index ->
            val done = index < current
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(height)
                    .clip(RoundedCornerShape(TutiRadius.pill))
                    .background(
                        if (done) Brush.horizontalGradient(fill)
                        else Brush.horizontalGradient(listOf(c.progressTrack, c.progressTrack))
                    ),
            )
        }
    }
}

/**
 * Кольцевой индикатор — для показателей, которые важно видеть
 * как «долю целого»: дневная цель, точность ответов.
 */
@Composable
fun TutiRingProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
    stroke: Dp = 8.dp,
    color: Color? = null,
    centerLabel: String? = null,
) {
    val c = MaterialTheme.tutiColors
    val ringColor = color ?: c.jade.base
    val animated by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(TutiMotion.SLOW, easing = TutiMotion.standard),
        label = "ring",
    )

    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        androidx.compose.foundation.Canvas(Modifier.fillMaxSize()) {
            val sw = stroke.toPx()
            drawArc(
                color = c.progressTrack,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = sw,
                    cap = androidx.compose.ui.graphics.StrokeCap.Round,
                ),
                topLeft = Offset(sw / 2, sw / 2),
                size = Size(this.size.width - sw, this.size.height - sw),
            )
            drawArc(
                color = ringColor,
                startAngle = -90f,
                sweepAngle = 360f * animated,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = sw,
                    cap = androidx.compose.ui.graphics.StrokeCap.Round,
                ),
                topLeft = Offset(sw / 2, sw / 2),
                size = Size(this.size.width - sw, this.size.height - sw),
            )
        }
        if (centerLabel != null) {
            Text(
                text = centerLabel,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

/**
 * Счётчик «жизней» в уроке. Сердце гаснет, а не исчезает —
 * так виден исходный запас.
 */
@Composable
fun TutiHearts(
    total: Int,
    remaining: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(total) { i ->
            Text(
                text = if (i < remaining) "❤️" else "🖤",
                fontSize = 15.sp,
            )
        }
    }
}

/** Строка «серии»: огонь и число дней. */
@Composable
fun TutiStreakChip(days: Int, modifier: Modifier = Modifier) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(c.mango.soft)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("🔥", fontSize = 13.sp)
        Spacer(Modifier.width(4.dp))
        Text(
            text = days.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = c.mango.onSoft,
        )
    }
}
