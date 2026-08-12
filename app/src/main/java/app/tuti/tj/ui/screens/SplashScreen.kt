package app.tuti.tj.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.theme.LocalDarkTheme
import app.tuti.tj.ui.theme.TutiLogoFamily
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  ЭКРАН ЗАПУСКА
//
//  Системный сплэш Android умеет показать только цвет фона и иконку
//  в круге, поэтому вся композиция — отдельный Compose-экран. Чтобы
//  стыка не было видно, фон системного сплэша (colors.xml) совпадает
//  с верхней точкой градиента здесь.
//
//  Экран держится не «столько, сколько красиво», а до готовности
//  данных: как только известен стартовый маршрут и прошёл минимум
//  [MIN_VISIBLE_MS], происходит переход. Минимум нужен, чтобы на
//  быстром устройстве заставка не мигала на один кадр.
//
//  Витрина языков живёт не здесь, а на приветственном экране
//  (первый шаг онбординга): сплэш должен уходить быстро, и
//  задерживать на нём взгляд нечем.
// ════════════════════════════════════════════════════════════════

/** Сколько экран показывается минимально, даже если данные уже готовы. */
private const val MIN_VISIBLE_MS = 1600L

/** Языки, которым приложение учит сейчас. */
private val splashLanguages = listOf(
    "🇷🇺" to "Русӣ",
    "🇬🇧" to "Англисӣ",
)

@Composable
fun SplashScreen(
    nextRoute: String?,
    onContinue: (String) -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val isDark = LocalDarkTheme.current

    // Переход происходит по двум условиям сразу: данные готовы и
    // минимальное время показа истекло.
    val route by rememberUpdatedState(nextRoute)
    LaunchedEffect(Unit) {
        val start = System.currentTimeMillis()
        while (route == null) delay(30)
        val elapsed = System.currentTimeMillis() - start
        if (elapsed < MIN_VISIBLE_MS) delay(MIN_VISIBLE_MS - elapsed)
        route?.let(onContinue)
    }

    // Каскад появления: кольца → маскот → логотип → подпись → языки.
    val steps = 5
    val alphas = remember { List(steps) { Animatable(0f) } }
    val rises = remember { List(steps) { Animatable(18f) } }
    LaunchedEffect(Unit) {
        repeat(steps) { i ->
            launch {
                delay(120L + i * 110L)
                launch { alphas[i].animateTo(1f, tween(460, easing = FastOutSlowInEasing)) }
                launch { rises[i].animateTo(0f, tween(460, easing = TutiMotion.overshoot)) }
            }
        }
    }

    val bg = if (isDark) {
        listOf(Color(0xFF0B141F), Color(0xFF0E1F1A))
    } else {
        listOf(Color(0xFFF4FDF9), Color(0xFFE3F7EC))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(bg)),
    ) {
        BackdropShapes(accent = c.jade.base, isDark = isDark)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = TutiSpace.xxl),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(1f))

            // ── маскот в кольцах ─────────────────
            Box(contentAlignment = Alignment.Center) {
                HaloRings(
                    accent = c.jade.base,
                    modifier = Modifier
                        .size(300.dp)
                        .alpha(alphas[0].value),
                )
                Sparkles(accent = c.jade.base, alpha = alphas[1].value)
                LivingTutiMascot(
                    size = 116.dp,
                    waving = true,
                    modifier = Modifier
                        .alpha(alphas[1].value)
                        .scale(0.9f + 0.1f * alphas[1].value),
                )
            }

            Spacer(Modifier.height(TutiSpace.sm))

            // ── логотип ──────────────────────────
            Text(
                text = "Tuti",
                style = TextStyle(
                    // У Baloo 2 высокий рост строчных и плотный набор:
                    // отрицательный трекинг слепил бы буквы.
                    fontFamily = TutiLogoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 62.sp,
                    letterSpacing = 0.sp,
                    brush = Brush.verticalGradient(listOf(c.jade.base, c.jade.deep)),
                ),
                modifier = Modifier
                    .alpha(alphas[2].value)
                    .offset(y = rises[2].value.dp),
            )

            Spacer(Modifier.height(TutiSpace.sm))

            // ── подпись ──────────────────────────
            Text(
                text = "Забонҳоро осон омӯзед",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(alphas[3].value)
                    .offset(y = rises[3].value.dp),
            )

            Spacer(Modifier.weight(1f))

            // ── языки ────────────────────────────
            Row(
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.xxl),
                modifier = Modifier
                    .alpha(alphas[4].value)
                    .offset(y = rises[4].value.dp),
            ) {
                splashLanguages.forEach { (flag, label) ->
                    LanguageBadge(flag = flag, label = label)
                }
            }

            Spacer(Modifier.height(TutiSpace.xxxl))
        }
    }
}

// ═══════════════════════════════════════════════════
//  ЯЗЫК
// ═══════════════════════════════════════════════════

@Composable
private fun LanguageBadge(flag: String, label: String) {
    val c = MaterialTheme.tutiColors
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, c.jade.base.copy(alpha = 0.18f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(flag, fontSize = 30.sp)
        }
        Spacer(Modifier.height(TutiSpace.sm))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = c.jade.base,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ДЕКОР
// ═══════════════════════════════════════════════════

/**
 * Концентрические кольца за маскотом. Медленно «дышат» —
 * это единственное движение фона, поэтому оно не спорит
 * с самим персонажем.
 */
@Composable
private fun HaloRings(accent: Color, modifier: Modifier = Modifier) {
    val inf = rememberInfiniteTransition(label = "halo")
    val breathe by inf.animateFloat(
        initialValue = 0.97f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            tween(3200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse,
        ),
        label = "haloBreathe",
    )

    Canvas(modifier = modifier.scale(breathe)) {
        val r = size.minDimension / 2f
        listOf(0.46f to 0.16f, 0.68f to 0.10f, 0.92f to 0.06f).forEach { (k, a) ->
            drawCircle(
                color = accent.copy(alpha = a),
                radius = r * k,
                style = Stroke(width = 1.2.dp.toPx()),
            )
        }
        drawCircle(
            brush = Brush.radialGradient(
                listOf(accent.copy(alpha = 0.10f), Color.Transparent),
                center = center,
                radius = r * 0.5f,
            ),
            radius = r * 0.5f,
        )
    }
}

/** Искры вокруг маскота — те же, что у него в состоянии CELEBRATE. */
@Composable
private fun Sparkles(accent: Color, alpha: Float) {
    val inf = rememberInfiniteTransition(label = "sparkle")
    val twinkle by inf.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1100, easing = FastOutSlowInEasing),
            RepeatMode.Reverse,
        ),
        label = "twinkle",
    )

    Canvas(modifier = Modifier.size(220.dp).alpha(alpha)) {
        val a = accent.copy(alpha = 0.55f)
        drawSparkle(Offset(size.width * 0.16f, size.height * 0.30f), 9f, a, twinkle)
        drawSparkle(Offset(size.width * 0.86f, size.height * 0.34f), 7f, a, 1.35f - twinkle)
        drawSparkle(Offset(size.width * 0.24f, size.height * 0.72f), 6f, a, twinkle * 0.8f)
    }
}

private fun DrawScope.drawSparkle(center: Offset, r: Float, color: Color, alpha: Float) {
    val a = alpha.coerceIn(0f, 1f)
    drawLine(
        color = color,
        start = Offset(center.x, center.y - r),
        end = Offset(center.x, center.y + r),
        strokeWidth = r * 0.38f,
        alpha = a,
    )
    drawLine(
        color = color,
        start = Offset(center.x - r, center.y),
        end = Offset(center.x + r, center.y),
        strokeWidth = r * 0.38f,
        alpha = a,
    )
}

/**
 * Фон: мягкие пятна по углам, холм у нижней кромки и листья.
 * Всё рисуется одним Canvas на весь экран — так фигуры можно
 * привязать к его пропорциям, а не к фиксированным отступам.
 */
@Composable
private fun BackdropShapes(accent: Color, isDark: Boolean) {
    val blobAlpha = if (isDark) 0.10f else 0.14f
    val hillAlpha = if (isDark) 0.12f else 0.18f
    val leafAlpha = if (isDark) 0.18f else 0.30f

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        drawCircle(
            brush = Brush.radialGradient(
                listOf(accent.copy(alpha = blobAlpha), Color.Transparent),
                center = Offset(w * 0.92f, h * 0.10f),
                radius = w * 0.42f,
            ),
            radius = w * 0.42f,
            center = Offset(w * 0.92f, h * 0.10f),
        )
        drawCircle(
            brush = Brush.radialGradient(
                listOf(accent.copy(alpha = blobAlpha * 0.8f), Color.Transparent),
                center = Offset(w * 0.06f, h * 0.30f),
                radius = w * 0.34f,
            ),
            radius = w * 0.34f,
            center = Offset(w * 0.06f, h * 0.30f),
        )

        val hill = Path().apply {
            moveTo(0f, h * 0.90f)
            cubicTo(w * 0.28f, h * 0.845f, w * 0.62f, h * 0.955f, w, h * 0.885f)
            lineTo(w, h); lineTo(0f, h); close()
        }
        drawPath(hill, accent.copy(alpha = hillAlpha * 0.55f))

        val hill2 = Path().apply {
            moveTo(0f, h * 0.945f)
            cubicTo(w * 0.34f, h * 0.90f, w * 0.70f, h * 0.99f, w, h * 0.935f)
            lineTo(w, h); lineTo(0f, h); close()
        }
        drawPath(hill2, accent.copy(alpha = hillAlpha))

        val leaf = accent.copy(alpha = leafAlpha)
        translate(left = w * 0.03f, top = h * 0.905f) { drawLeaf(58f, 96f, leaf, -18f) }
        translate(left = w * 0.15f, top = h * 0.935f) {
            drawLeaf(44f, 74f, leaf.copy(alpha = leafAlpha * 0.75f), 12f)
        }
        translate(left = w * 0.86f, top = h * 0.915f) { drawLeaf(52f, 88f, leaf, 22f) }
    }
}

/** Простой лист: два симметричных изгиба, сходящиеся в остриях. */
private fun DrawScope.drawLeaf(w: Float, h: Float, color: Color, rotationDeg: Float) {
    rotate(degrees = rotationDeg, pivot = Offset(w / 2f, h / 2f)) {
        val p = Path().apply {
            moveTo(w / 2f, 0f)
            cubicTo(w, h * 0.22f, w, h * 0.68f, w / 2f, h)
            cubicTo(0f, h * 0.68f, 0f, h * 0.22f, w / 2f, 0f)
            close()
        }
        drawPath(p, color)
        drawLine(
            color = color.copy(alpha = 0.5f),
            start = Offset(w / 2f, h * 0.08f),
            end = Offset(w / 2f, h * 0.92f),
            strokeWidth = 1.5f,
        )
    }
}
