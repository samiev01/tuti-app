package app.tuti.tj.ui.mascot

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Маскот Тӯтӣ. Исходники слоёв — SVG в design/mascot, viewBox 0 0 100 100.
 * Пути тела, хохолка и клюва общие для всех состояний: это то, что гарантирует
 * «во всех состояниях один и тот же персонаж». Менять их — только вместе с SVG.
 *
 * ── Характер (редизайн) ──────────────────────────────────────────
 * Тӯтӣ — не малыш и не talking head. Это уверенный, слегка
 * ироничный напарник: подбадривает, но не сюсюкает. Отсюда решения
 * в рисунке:
 *   · глаза меньше и посажены шире — взрослее, чем «кукольные»;
 *   · румянец приглушён до 55 % — намёк, а не «щёчки»;
 *   · хохолок и крылья работают как жест: подъём = энергия,
 *     опущенные = сочувствие, наклон = размышление;
 *   · корпус в собственных перьевых тонах, клюв в Mango — персонаж
 *     буквально собран из палитры интерфейса.
 */
enum class TutiState {
    /** Приветствие: машет крылом */
    HELLO,

    /** Радость: крылья вверх, глаза-дуги */
    HAPPY,

    /** Промах: брови домиком, открытые глаза */
    OOPS,

    /** Размышление: наклон головы, взгляд вбок */
    THINKING,

    /** Праздник: крылья вверх, звёзды вокруг */
    CELEBRATE,

    /** Сочувствие / пустой экран: крылья опущены, взгляд вниз */
    SAD,
}

internal object TutiPalette {
    // Перьевые тона маскота. Раньше они совпадали с брендовым Jade,
    // но бренд ушёл в чистый зелёный, а попугай остался бирюзовым:
    // это его природный цвет, а не акцент интерфейса.
    val Teal = Color(0xFF00C48C)
    val Green = Color(0xFF4BE07A)
    val Accent = Color(0xFF00A374)
    val Beak = Color(0xFFFF9F1C)
    val BeakDeep = Color(0xFFE07C00)
    val Cheek = Color(0xFFFF5C5C)
    val Ink = Color(0xFF0F1E2E)
    val White = Color(0xFFFFFFFF)
    val Star = Color(0xFFFFC24D)
}

// --- Общие пути: один источник правды для всех состояний ---
private const val D_CREST_LEFT = "M36.5 14.5c5 2.5 9 8 11 15-1 2.5-5.5 2.5-7.5 0-4-5.5-6.5-11.5-3.5-15z"
private const val D_CREST_MID = "M50 9c5 4.5 6.5 11 4 20-1.5 2.5-6.5 2.5-8 0-2.5-9-1-15.5 4-20z"
private const val D_CREST_RIGHT = "M63.5 14.5c-5 2.5-9 8-11 15 1 2.5 5.5 2.5 7.5 0 4-5.5 6.5-11.5 3.5-15z"
private const val D_BODY = "M50 27c14 0 26 13.5 27.5 30C79 74 66.5 88.5 50 88.5S21 74 22.5 57C24 40.5 36 27 50 27z"
private const val D_BEAK = "M50 56.5c4.5 1 7.5 3.5 7.5 6.5 0 4-4 7.5-7.5 8.5-3.5-1-7.5-4.5-7.5-8.5 0-3 3-5.5 7.5-6.5z"
private const val D_LID_LEFT = "M32 58a6 6 0 0 1 12 0h-2.2a3.8 3.8 0 0 0-7.6 0z"
private const val D_LID_RIGHT = "M56 58a6 6 0 0 1 12 0h-2.2a3.8 3.8 0 0 0-7.6 0z"
private const val D_BROW_LEFT = "M31.6 48.8c2.6-3.4 7.2-4 10.4-1.9l-.9 2c-2.7-1.7-6.3-1.2-8.4 1.5z"
private const val D_BROW_RIGHT = "M68.4 48.8c-2.6-3.4-7.2-4-10.4-1.9l.9 2c2.7-1.7 6.3-1.2 8.4 1.5z"

// Бровь «домиком» вверх — любопытство в THINKING, отличается от
// сочувственной брови в SAD только знаком наклона.
private const val D_BROW_UP_LEFT = "M31.6 47.4c3.2-2.6 7.6-2.4 10.4.6l-1.2 1.7c-2.3-2.3-5.9-2.5-8.4-.6z"
private const val D_BROW_UP_RIGHT = "M68.4 47.4c-3.2-2.6-7.6-2.4-10.4.6l1.2 1.7c2.3-2.3 5.9-2.5 8.4-.6z"

// --- Крылья: единственное, что различается формой между состояниями ---
private const val D_WING_DOWN_LEFT = "M27 58c-7 4.5-11.5 13.5-10 23 1.5 3 5 2.5 7-.5 3.5-7.5 5-15.5 3-22.5z"
private const val D_WING_DOWN_RIGHT = "M73 58c7 4.5 11.5 13.5 10 23-1.5 3-5 2.5-7-.5-3.5-7.5-5-15.5-3-22.5z"
private const val D_WING_UP_LEFT = "M27 58c-7-4.5-13.5-13.5-13.5-23 .5-3 4.5-3 6.5 0 4 7.5 6.5 15.5 7 23z"
private const val D_WING_UP_RIGHT = "M73 58c7-4.5 13.5-13.5 13.5-23-.5-3-4.5-3-6.5 0-4 7.5-6.5 15.5-7 23z"

// Точки вращения крыльев — у корпуса, а не в центре фигуры,
// иначе взмах выглядит как отрывание крыла.
internal val PIVOT_LEFT = Offset(27f, 58f)
internal val PIVOT_RIGHT = Offset(73f, 58f)

internal class TutiPaths {
    val crestLeft = parse(D_CREST_LEFT)
    val crestMid = parse(D_CREST_MID)
    val crestRight = parse(D_CREST_RIGHT)
    val body = parse(D_BODY)
    val beak = parse(D_BEAK)
    val lidLeft = parse(D_LID_LEFT)
    val lidRight = parse(D_LID_RIGHT)
    val browLeft = parse(D_BROW_LEFT)
    val browRight = parse(D_BROW_RIGHT)
    val browUpLeft = parse(D_BROW_UP_LEFT)
    val browUpRight = parse(D_BROW_UP_RIGHT)
    val wingDownLeft = parse(D_WING_DOWN_LEFT)
    val wingDownRight = parse(D_WING_DOWN_RIGHT)
    val wingUpLeft = parse(D_WING_UP_LEFT)
    val wingUpRight = parse(D_WING_UP_RIGHT)

    private fun parse(d: String): Path = PathParser().parsePathString(d).toPath()
}

// Реальные границы рисунка внутри viewBox: от кончика левого поднятого крыла
// до правого и от макушки хохолка до тени. Масштабируем по ним, а не по
// 100x100 — иначе персонаж занимает чуть больше половины отведённого места.
internal const val ART_LEFT = 13.5f
internal const val ART_TOP = 9f
internal const val ART_WIDTH = 73f
internal const val ART_HEIGHT = 86f

internal val BodyBrush = Brush.linearGradient(
    colors = listOf(TutiPalette.Teal, TutiPalette.Green),
    start = Offset(22f, 27f),
    end = Offset(78f, 88.5f),
)

/** Клюв с собственным градиентом — иначе он «плоский» на объёмном теле. */
internal val BeakBrush = Brush.linearGradient(
    colors = listOf(TutiPalette.Beak, TutiPalette.BeakDeep),
    start = Offset(42f, 56f),
    end = Offset(58f, 72f),
)

/** Состояния с открытыми глазами (в остальных — счастливые дуги). */
private fun TutiState.hasOpenEyes(): Boolean =
    this == TutiState.OOPS || this == TutiState.THINKING || this == TutiState.SAD

/**
 * Маскот с переключением состояний. Моргание идёт само по таймеру,
 * взмах крыла — только в [TutiState.HELLO], лёгкое «дыхание» — всегда.
 */
@Composable
fun TutiMascotVector(state: TutiState, modifier: Modifier = Modifier) {
    AnimatedContent(
        targetState = state,
        transitionSpec = { fadeIn(tween(180)) togetherWith fadeOut(tween(180)) },
        label = "tuti_state",
    ) { target ->
        TutiMascotFrame(target, modifier)
    }
}

@Composable
private fun TutiMascotFrame(state: TutiState, modifier: Modifier) {
    val paths = remember { TutiPaths() }
    val transition = rememberInfiniteTransition(label = "tuti_idle")

    // Моргание: долгая пауза, затем быстрое смыкание. Цикл 5 с попадает
    // в требуемые 4-6 секунд.
    val blink by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 5000
                0f at 0
                0f at 4600
                1f at 4720
                1f at 4800
                0f at 4920
            },
        ),
        label = "blink",
    )

    val wave by transition.animateFloat(
        initialValue = -6f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(820, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "wave",
    )

    // Дыхание: корпус едва заметно расширяется. Держит персонажа
    // «живым» даже на статичных экранах, но не тянет внимание.
    val breathe by transition.animateFloat(
        initialValue = 0.99f,
        targetValue = 1.015f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "breathe",
    )

    // Мерцание звёзд в CELEBRATE
    val sparkle by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "sparkle",
    )

    Canvas(modifier) {
        drawTutiFitted(
            paths = paths,
            state = state,
            blink = blink,
            waveDegrees = if (state == TutiState.HELLO) wave else 0f,
            breathe = breathe,
            sparkle = sparkle,
        )
    }
}

/**
 * Статичный Тӯтӣ — тот же рисунок, но без единой анимации и без таймеров.
 * Для иконок и мест, где живой маскот отвлекает.
 */
@Composable
fun TutiMascotStatic(state: TutiState, modifier: Modifier = Modifier) {
    val paths = remember { TutiPaths() }
    Canvas(modifier) {
        drawTutiFitted(paths, state, blink = 0f, waveDegrees = 0f, breathe = 1f, sparkle = 1f)
    }
}

/** Вписывает рисунок в холст по его реальным границам и рисует. */
private fun DrawScope.drawTutiFitted(
    paths: TutiPaths,
    state: TutiState,
    blink: Float,
    waveDegrees: Float,
    breathe: Float,
    sparkle: Float,
) {
    val s = minOf(size.width / ART_WIDTH, size.height / ART_HEIGHT)
    withTransform({
        translate(
            left = (size.width - ART_WIDTH * s) / 2f - ART_LEFT * s,
            top = (size.height - ART_HEIGHT * s) / 2f - ART_TOP * s,
        )
        scale(s, s, pivot = Offset.Zero)
    }) {
        drawTuti(paths, state, blink, waveDegrees, breathe, sparkle)
    }
}

/** Порядок отрисовки снизу вверх — как в SVG. */
private fun DrawScope.drawTuti(
    paths: TutiPaths,
    state: TutiState,
    blink: Float,
    waveDegrees: Float,
    breathe: Float,
    sparkle: Float,
) {
    val eyesOpen = state.hasOpenEyes()

    // Тень под персонажем. В такт дыханию слегка меняет ширину —
    // без этого «дыхание» читается как дрожание.
    val shadowScale = 2f - breathe
    drawOval(
        color = TutiPalette.Ink,
        topLeft = Offset(28f + (1f - shadowScale) * 22f, 89f),
        size = Size(44f * shadowScale, 6f),
        alpha = 0.09f,
    )

    // Звёзды праздника — за корпусом, чтобы не спорить с лицом
    if (state == TutiState.CELEBRATE) {
        drawSparkle(Offset(20f, 26f), 4.2f, TutiPalette.Star, sparkle)
        drawSparkle(Offset(80f, 32f), 3.4f, TutiPalette.Star, 1.35f - sparkle)
        drawSparkle(Offset(84f, 66f), 2.8f, TutiPalette.Star, sparkle * 0.85f)
        drawSparkle(Offset(16f, 60f), 3.0f, TutiPalette.Star, 1.2f - sparkle)
    }

    // Наклон головы в THINKING делаем наклоном всей фигуры вокруг
    // основания — так силуэт остаётся цельным.
    val tilt = if (state == TutiState.THINKING) -7f else 0f

    withTransform({
        rotate(tilt, pivot = Offset(50f, 86f))
        scale(breathe, breathe, pivot = Offset(50f, 86f))
    }) {
        // crest
        drawPath(paths.crestLeft, TutiPalette.Accent)
        drawPath(paths.crestMid, TutiPalette.Accent)
        drawPath(paths.crestRight, TutiPalette.Accent)

        // wings — вращение вокруг точки у корпуса
        val wingsUp = state == TutiState.HAPPY || state == TutiState.CELEBRATE
        val wingsDown = state == TutiState.SAD || state == TutiState.OOPS

        val leftWing = if (wingsUp) paths.wingUpLeft else paths.wingDownLeft
        val rightWing = when {
            wingsDown -> paths.wingDownRight
            else -> paths.wingUpRight
        }

        drawPath(leftWing, TutiPalette.Accent)
        withTransform({ rotate(waveDegrees, pivot = PIVOT_RIGHT) }) {
            drawPath(rightWing, TutiPalette.Accent)
        }

        // body
        drawPath(paths.body, BodyBrush)

        // highlight
        withTransform({ rotate(-35f, pivot = Offset(36f, 38f)) }) {
            drawOval(
                color = TutiPalette.White,
                topLeft = Offset(28.8f, 33.4f),
                size = Size(14.4f, 9.2f),
                alpha = 0.28f,
            )
        }

        // cheeks — приглушены: намёк на румянец, а не «щёчки малыша»
        drawOval(TutiPalette.Cheek, Offset(25.2f, 61.6f), Size(11.6f, 8.8f), alpha = 0.55f)
        drawOval(TutiPalette.Cheek, Offset(63.2f, 61.6f), Size(11.6f, 8.8f), alpha = 0.55f)

        // eyes: белки/зрачки/блики видны только когда глаза открыты и не в моргании
        val openAlpha = if (eyesOpen) 1f - blink else 0f
        if (openAlpha > 0f) {
            // Направление взгляда — часть эмоции: вбок при размышлении,
            // вниз при сочувствии.
            val gazeX = when (state) {
                TutiState.THINKING -> 1.6f
                TutiState.SAD -> 0f
                else -> 0f
            }
            val gazeY = when (state) {
                TutiState.SAD -> 1.4f
                TutiState.THINKING -> -0.6f
                else -> 0f
            }

            drawCircle(TutiPalette.White, radius = 6.5f, center = Offset(38f, 57f), alpha = openAlpha)
            drawCircle(TutiPalette.White, radius = 6.5f, center = Offset(62f, 57f), alpha = openAlpha)
            drawCircle(
                TutiPalette.Ink, radius = 4.1f,
                center = Offset(38f + gazeX, 57.5f + gazeY), alpha = openAlpha,
            )
            drawCircle(
                TutiPalette.Ink, radius = 4.1f,
                center = Offset(62f + gazeX, 57.5f + gazeY), alpha = openAlpha,
            )
            drawCircle(
                TutiPalette.White, radius = 1.35f,
                center = Offset(39.6f + gazeX, 55.6f + gazeY), alpha = openAlpha,
            )
            drawCircle(
                TutiPalette.White, radius = 1.35f,
                center = Offset(63.6f + gazeX, 55.6f + gazeY), alpha = openAlpha,
            )
        }

        // lids: в hello/happy/celebrate это постоянная «счастливая дуга»,
        // в остальных — само моргание
        val lidAlpha = if (eyesOpen) blink else 1f
        if (lidAlpha > 0f) {
            drawPath(paths.lidLeft, TutiPalette.Ink, alpha = lidAlpha)
            drawPath(paths.lidRight, TutiPalette.Ink, alpha = lidAlpha)
        }

        // brows — знак наклона отличает растерянность от любопытства
        when (state) {
            TutiState.OOPS, TutiState.SAD -> {
                drawPath(paths.browLeft, TutiPalette.Ink)
                drawPath(paths.browRight, TutiPalette.Ink)
            }
            TutiState.THINKING -> {
                drawPath(paths.browUpLeft, TutiPalette.Ink)
                drawPath(paths.browUpRight, TutiPalette.Ink)
            }
            else -> Unit
        }

        // beak
        drawPath(paths.beak, BeakBrush)
    }
}

/** Четырёхлучевая искра — форма читается лучше звезды на малом размере. */
private fun DrawScope.drawSparkle(center: Offset, r: Float, color: Color, alpha: Float) {
    val a = alpha.coerceIn(0f, 1f)
    drawLine(
        color = color,
        start = Offset(center.x, center.y - r),
        end = Offset(center.x, center.y + r),
        strokeWidth = r * 0.42f,
        alpha = a,
    )
    drawLine(
        color = color,
        start = Offset(center.x - r, center.y),
        end = Offset(center.x + r, center.y),
        strokeWidth = r * 0.42f,
        alpha = a,
    )
    drawCircle(color, radius = r * 0.32f, center = center, alpha = a)
}

/**
 * Кольцевой ореол вокруг маскота — используется на приветственных
 * экранах, чтобы фигура не «висела» на плоском фоне.
 */
internal fun DrawScope.drawTutiHalo(color: Color) {
    drawCircle(
        brush = Brush.radialGradient(
            listOf(color.copy(alpha = 0.18f), Color.Transparent),
            center = center,
            radius = size.minDimension * 0.55f,
        ),
        radius = size.minDimension * 0.55f,
    )
    drawCircle(
        color = color.copy(alpha = 0.14f),
        radius = size.minDimension * 0.44f,
        style = Stroke(width = size.minDimension * 0.012f),
    )
}

@Preview(showBackground = true)
@Composable
private fun TutiMascotPreview() {
    TutiMascotVector(TutiState.HELLO, Modifier.size(160.dp))
}
