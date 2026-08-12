package app.tuti.tj.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  МАСКОТ В КОЛЬЦЕ ПРИВЕТСТВИЙ
//
//  Витрина языков: Тӯтӣ в центре, вокруг него на пунктирной орбите
//  висят облачка с приветствиями. Языков четыре, хотя приложение
//  пока учит двум — это заявка на будущий набор, а не список
//  доступных курсов.
//
//  Облачка появляются по очереди, а не пачкой: так читается, что
//  здоровается именно персонаж.
// ════════════════════════════════════════════════════════════════

private data class Greeting(
    val flag: String,
    val text: String,
    val align: Alignment,
    val dx: Int,
    val dy: Int,
    /** Хвостик слева — значит облачко висит слева от маскота. */
    val tailAtStart: Boolean,
)

private val greetings = listOf(
    Greeting("🇬🇧", "Hello", Alignment.TopStart, 4, 20, tailAtStart = false),
    Greeting("🇷🇺", "Привет", Alignment.TopEnd, -4, 2, tailAtStart = true),
    Greeting("🇨🇳", "你好", Alignment.CenterStart, -4, 28, tailAtStart = false),
    Greeting("🇸🇦", "مرحبا", Alignment.CenterEnd, 4, 44, tailAtStart = true),
)

@Composable
fun GreetingOrbit(
    modifier: Modifier = Modifier,
    stageHeight: Dp = 320.dp,
    mascotSize: Dp = 126.dp,
) {
    val c = MaterialTheme.tutiColors

    val mascotIn = remember { Animatable(0f) }
    val bubbleIn = remember { List(greetings.size) { Animatable(0f) } }
    val bubbleRise = remember { List(greetings.size) { Animatable(14f) } }

    LaunchedEffect(Unit) {
        launch { mascotIn.animateTo(1f, tween(420, easing = FastOutSlowInEasing)) }
        greetings.indices.forEach { i ->
            launch {
                delay(260L + i * 150L)
                launch { bubbleIn[i].animateTo(1f, tween(400, easing = FastOutSlowInEasing)) }
                launch { bubbleRise[i].animateTo(0f, tween(400, easing = TutiMotion.overshoot)) }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(stageHeight),
    ) {
        // Пунктирная орбита, на которой «висят» облачка
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(mascotIn.value),
        ) {
            drawCircle(
                color = c.jade.base.copy(alpha = 0.35f),
                radius = size.width * 0.36f,
                center = Offset(size.width / 2f, size.height * 0.60f),
                style = Stroke(
                    width = 1.4.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(9f, 11f)),
                ),
            )
        }

        LivingTutiMascot(
            size = mascotSize,
            waving = true,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .alpha(mascotIn.value)
                .scale(0.92f + 0.08f * mascotIn.value),
        )

        greetings.forEachIndexed { i, g ->
            GreetingBubble(
                flag = g.flag,
                text = g.text,
                tailAtStart = g.tailAtStart,
                modifier = Modifier
                    .align(g.align)
                    .offset(x = g.dx.dp, y = (g.dy + bubbleRise[i].value).dp)
                    .alpha(bubbleIn[i].value)
                    .scale(0.88f + 0.12f * bubbleIn[i].value),
            )
        }
    }
}

/**
 * Облачко реплики: плашка с флагом и приветствием плюс хвостик
 * снизу, направленный к маскоту. Сторона хвостика зависит от того,
 * слева облачко или справа.
 */
@Composable
private fun GreetingBubble(
    flag: String,
    text: String,
    tailAtStart: Boolean,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val bubble = MaterialTheme.colorScheme.surface

    Column(
        modifier = modifier,
        horizontalAlignment = if (tailAtStart) Alignment.Start else Alignment.End,
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(TutiRadius.lg))
                .background(bubble)
                .padding(horizontal = TutiSpace.md, vertical = TutiSpace.sm),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(flag, fontSize = 19.sp)
            Spacer(Modifier.width(TutiSpace.sm))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = c.jade.onSoft,
            )
        }
        Canvas(
            modifier = Modifier
                .padding(horizontal = TutiSpace.lg)
                .size(width = 14.dp, height = 9.dp),
        ) {
            val p = Path().apply {
                if (tailAtStart) {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(0f, size.height)
                } else {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                }
                close()
            }
            drawPath(p, bubble)
        }
    }
}
