package app.tuti.tj.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.tuti.tj.ui.mascot.ART_HEIGHT
import app.tuti.tj.ui.mascot.ART_LEFT
import app.tuti.tj.ui.mascot.ART_TOP
import app.tuti.tj.ui.mascot.ART_WIDTH
import app.tuti.tj.ui.mascot.BodyBrush
import app.tuti.tj.ui.mascot.PIVOT_LEFT
import app.tuti.tj.ui.mascot.PIVOT_RIGHT
import app.tuti.tj.ui.mascot.TutiPaths
import app.tuti.tj.ui.mascot.TutiPalette
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class LivingMood { Normal, Happy, Excited, Curious }

/**
 * «Живой» Тӯтӣ: то же тело, хохолок и клюв, что и в
 * [app.tuti.tj.ui.mascot.TutiMascotVector], но с постоянной idle-анимацией.
 *
 * Сигнатура и набор анимаций сохранены от прежней версии: автосмена
 * настроения, двойное моргание, блуждающий взгляд, покачивание, пульсация
 * свечения. Изменилась только графика — вместо самодельного кружка рисуются
 * пути из design/mascot.
 *
 * Глаза здесь всегда открытые: блуждающий взгляд — главное, что делает этого
 * маскота живым, а с закрытыми дугами зрачков попросту нет.
 *
 * [waving] включает приветственный взмах крылом: короткая серия качков,
 * затем длинная пауза. Выключен по умолчанию — постоянно машущий маскот
 * рядом с контентом отвлекает, поэтому жест нужен только там, где Тӯтӣ
 * действительно здоровается (шапка главного экрана, приветствие).
 */
@Composable
fun LivingTutiMascot(
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    waving: Boolean = false,
) {
    val density = LocalDensity.current
    val paths = remember { TutiPaths() }

    // ── mood auto-cycle ───────────────────────────
    // Happy 70%, Excited 17%, Curious 13%
    var mood by remember { mutableStateOf(LivingMood.Happy) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000L + Random.nextLong(1000))
            val roll = Random.nextFloat()
            mood = when {
                roll < 0.70f -> LivingMood.Happy
                roll < 0.87f -> LivingMood.Excited
                else -> LivingMood.Curious
            }
        }
    }

    // ── blinking ──────────────────────────────────
    var blinkFactor by remember { mutableStateOf(1f) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L + Random.nextLong(2000))
            blinkFactor = 0.12f; delay(150)
            blinkFactor = 1f; delay(250)
            blinkFactor = 0.12f; delay(100)
            blinkFactor = 1f
        }
    }

    // ── pupil look direction ──────────────────────
    val pupilOffsetX = remember { Animatable(0f) }
    val pupilOffsetY = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(2500L + Random.nextLong(1500))
            val tx = Random.nextFloat() * 2f - 1f
            val ty = Random.nextFloat() * 0.6f - 0.3f
            launch { pupilOffsetX.animateTo(tx, tween(400, easing = EaseInOut)) }
            launch { pupilOffsetY.animateTo(ty, tween(400, easing = EaseInOut)) }
        }
    }

    // ── floating bob ──────────────────────────────
    val inf = rememberInfiniteTransition(label = "bob")
    val floatY by inf.animateFloat(
        initialValue = 0f, targetValue = -1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = EaseInOut), RepeatMode.Reverse),
        label = "floatY",
    )

    // ── glow pulse ────────────────────────────────
    val glowAlpha by inf.animateFloat(
        initialValue = 0.12f, targetValue = 0.22f,
        animationSpec = infiniteRepeatable(tween(2500, easing = EaseInOut), RepeatMode.Reverse),
        label = "glow",
    )

    // ── реакция на настроение ─────────────────────
    // Curious — наклон корпуса (заменяет ещё не нарисованный tuti_think).
    val tilt by animateFloatAsState(
        targetValue = if (mood == LivingMood.Curious) 7f else 0f,
        animationSpec = tween(500, easing = EaseInOut),
        label = "tilt",
    )
    // Excited — крылья вверх, плавным перетеканием между двумя путями.
    val wingsUp by animateFloatAsState(
        targetValue = if (mood == LivingMood.Excited) 1f else 0f,
        animationSpec = tween(320, easing = EaseInOut),
        label = "wingsUp",
    )
    val blushAlpha by animateFloatAsState(
        targetValue = when (mood) {
            LivingMood.Excited -> 0.95f
            LivingMood.Happy -> 0.85f
            else -> 0.7f
        },
        animationSpec = tween(400),
        label = "blush",
    )

    // ── приветственный взмах ──────────────────────
    // Крыло поднимается, качается ~1,5 с и опускается. Пауза между
    // взмахами длинная и случайная — жест читается как приветствие,
    // а не как бесконечный тик.
    var waveActive by remember { mutableStateOf(false) }
    LaunchedEffect(waving) {
        if (!waving) {
            waveActive = false
            return@LaunchedEffect
        }
        delay(500)
        while (true) {
            waveActive = true
            delay(1600)
            waveActive = false
            delay(5000L + Random.nextLong(3000))
        }
    }
    val waveAmount by animateFloatAsState(
        targetValue = if (waveActive) 1f else 0f,
        animationSpec = tween(260, easing = EaseInOut),
        label = "waveAmount",
    )
    val waveSwing by inf.animateFloat(
        initialValue = -7f, targetValue = 15f,
        animationSpec = infiniteRepeatable(tween(420, easing = EaseInOut), RepeatMode.Reverse),
        label = "waveSwing",
    )

    val floatDp = with(density) { (floatY * 8f).toDp() }

    Box(
        modifier = modifier.size(size + 24.dp, size + 32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(size + 24.dp, size + 32.dp)
                .offset(y = floatDp),
        ) {
            // Масштаб по реальным границам рисунка, чтобы персонаж занимал
            // всё отведённое место, а не половину.
            val s = minOf(this.size.width / ART_WIDTH, this.size.height / ART_HEIGHT)
            val originX = (this.size.width - ART_WIDTH * s) / 2f - ART_LEFT * s
            val originY = (this.size.height - ART_HEIGHT * s) / 2f - ART_TOP * s

            // Свечение рисуется в координатах холста, до перехода в viewBox.
            val bodyCenter = Offset(originX + 50f * s, originY + 57f * s)
            val glowR = 47.6f * s
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(TutiPalette.Teal.copy(alpha = glowAlpha), Color.Transparent),
                    center = bodyCenter,
                    radius = glowR,
                ),
                radius = glowR,
                center = bodyCenter,
            )

            withTransform({
                translate(originX, originY)
                scale(s, s, pivot = Offset.Zero)
            }) {
                drawLivingTuti(
                    paths = paths,
                    blink = blinkFactor,
                    lookX = pupilOffsetX.value,
                    lookY = pupilOffsetY.value,
                    floatY = floatY,
                    tilt = tilt,
                    wingsUp = wingsUp,
                    blushAlpha = blushAlpha,
                    wave = waveAmount,
                    waveAngle = waveSwing,
                )
            }
        }
    }
}

/** Всё внутри — в координатах viewBox 0..100, порядок слоёв как в SVG. */
private fun DrawScope.drawLivingTuti(
    paths: TutiPaths,
    blink: Float,
    lookX: Float,
    lookY: Float,
    floatY: Float,
    tilt: Float,
    wingsUp: Float,
    blushAlpha: Float,
    wave: Float,
    waveAngle: Float,
) {
    // Тень живёт отдельно от наклона: она на земле, а не на птице.
    val shadowScale = 1f + floatY * 0.15f
    drawOval(
        color = TutiPalette.Ink,
        topLeft = Offset(50f - 22f * shadowScale, 89f),
        size = Size(44f * shadowScale, 6f),
        alpha = 0.07f,
    )

    withTransform({ rotate(tilt, pivot = Offset(50f, 57f)) }) {
        // crest — слегка ведёт за взглядом
        withTransform({ rotate(lookX * 4f, pivot = Offset(50f, 29f)) }) {
            drawPath(paths.crestLeft, TutiPalette.Accent)
            drawPath(paths.crestMid, TutiPalette.Accent)
            drawPath(paths.crestRight, TutiPalette.Accent)
        }

        // wings — перетекание между «вниз» и «вверх», вращение у корпуса.
        // Правое крыло дополнительно обслуживает взмах: приветствие
        // перекрывает «крылья вверх», иначе жест теряется.
        val rightUp = maxOf(wingsUp, wave)

        if (wingsUp < 1f) {
            drawPath(paths.wingDownLeft, TutiPalette.Accent, alpha = 1f - wingsUp)
        }
        if (rightUp < 1f) {
            drawPath(paths.wingDownRight, TutiPalette.Accent, alpha = 1f - rightUp)
        }
        if (wingsUp > 0f) {
            withTransform({ rotate(floatY * 4f, pivot = PIVOT_LEFT) }) {
                drawPath(paths.wingUpLeft, TutiPalette.Accent, alpha = wingsUp)
            }
        }
        if (rightUp > 0f) {
            val rightAngle = if (wave > 0f) waveAngle * wave else -floatY * 4f
            withTransform({ rotate(rightAngle, pivot = PIVOT_RIGHT) }) {
                drawPath(paths.wingUpRight, TutiPalette.Accent, alpha = rightUp)
            }
        }

        drawPath(paths.body, BodyBrush)

        withTransform({ rotate(-35f, pivot = Offset(36f, 38f)) }) {
            drawOval(TutiPalette.White, Offset(28.8f, 33.4f), Size(14.4f, 9.2f), alpha = 0.25f)
        }

        drawOval(TutiPalette.Cheek, Offset(25.2f, 61.6f), Size(11.6f, 8.8f), alpha = blushAlpha)
        drawOval(TutiPalette.Cheek, Offset(63.2f, 61.6f), Size(11.6f, 8.8f), alpha = blushAlpha)

        // Глаза: сжатие по вертикали = моргание, как в прежней версии.
        for (eyeX in listOf(38f, 62f)) {
            val eyeCenter = Offset(eyeX, 57f)
            withTransform({ scale(1f, blink, pivot = eyeCenter) }) {
                drawCircle(TutiPalette.White, radius = 6.5f, center = eyeCenter)
                if (blink > 0.3f) {
                    val px = eyeX + lookX * 2f
                    val py = 57.5f + lookY * 1.6f
                    drawCircle(TutiPalette.Ink, radius = 4.1f, center = Offset(px, py))
                    drawCircle(TutiPalette.White, radius = 1.35f, center = Offset(px + 1.6f, py - 1.9f))
                }
            }
        }

        drawPath(paths.beak, TutiPalette.Beak)
    }
}
