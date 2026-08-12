package app.tuti.tj.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.components.kit.TutiGhostButton
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ОБУЧАЮЩИЕ ПОДСКАЗКИ ГЛАВНОГО ЭКРАНА
//
//  Затемнение с «прожектором» на нужном блоке плюс реплика
//  маскота. Логика позиционирования не менялась — обновлены
//  оформление пузыря, кнопки и индикатор шагов, чтобы первое
//  знакомство с приложением выглядело так же, как всё остальное.
// ════════════════════════════════════════════════════════════════

data class TooltipStep(
    val text: String,
    val targetKey: String,
    val mascotSide: String, // "left", "right", "center"
)

val onboardingSteps = listOf(
    TooltipStep(
        text = "Ин очкоҳои шумо ва пешрафт то ҳадафи навбатӣ! 💎",
        targetKey = "header_row",
        mascotSide = "right",
    ),
    TooltipStep(
        text = "Серияи ҳафтаина! Ҳар рӯз хонед ва серияро нигоҳ доред! 🔥",
        targetKey = "streak_card",
        mascotSide = "left",
    ),
    TooltipStep(
        text = "Курси асосии шумо! Барои оғоз ё давом пахш кунед! 👆",
        targetKey = "course_card",
        mascotSide = "right",
    ),
    TooltipStep(
        text = "Мавзӯъҳои ройгон! Бе пардохт калимаҳо омӯзед! 🎉",
        targetKey = "free_topics",
        mascotSide = "left",
    ),
    TooltipStep(
        text = "Дар бахши Машқ бо Tuti гап занед ва корти калимаҳо омӯзед! 🦜",
        targetKey = "bottom_nav_practice",
        mascotSide = "right",
    ),
    TooltipStep(
        text = "Офарин! Шумо тайёред! Биёед забон омӯзем! 🚀",
        targetKey = "done",
        mascotSide = "center",
    ),
)

// ═══════════════════════════════════════════════════
//  ОВЕРЛЕЙ
// ═══════════════════════════════════════════════════

@Composable
fun OnboardingTooltips(
    targetBounds: Map<String, Rect>,
    onComplete: () -> Unit,
    onStepChanged: (Int) -> Unit = {},
) {
    var currentStep by remember { mutableIntStateOf(0) }
    val step = onboardingSteps[currentStep]
    val isLast = currentStep == onboardingSteps.size - 1

    LaunchedEffect(currentStep, targetBounds.keys.toSet()) {
        val s = onboardingSteps[currentStep]
        val needsTarget = s.targetKey != "done" && s.targetKey != "bottom_nav_practice"
        if (needsTarget && s.targetKey !in targetBounds) {
            if (currentStep < onboardingSteps.size - 1) currentStep++ else onComplete()
        }
    }

    LaunchedEffect(currentStep) { onStepChanged(currentStep) }

    val density = LocalDensity.current
    val cutoutPadPx = with(density) { 8.dp.toPx() }
    val gapPx = with(density) { 6.dp.toPx() }
    val paddingHorizPx = with(density) { 20.dp.toPx() }
    val scrimColor = MaterialTheme.tutiColors.scrim

    var overlayPos by remember { mutableStateOf(Offset.Zero) }
    var overlaySize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(100f)
            .onGloballyPositioned {
                overlayPos = it.localToWindow(Offset.Zero)
                overlaySize = it.size
            },
    ) {
        if (overlaySize == IntSize.Zero) return@Box

        val targetWindowRect = targetBounds[step.targetKey]
        val localRect = targetWindowRect?.let {
            Rect(
                it.left - overlayPos.x,
                it.top - overlayPos.y,
                it.right - overlayPos.x,
                it.bottom - overlayPos.y,
            )
        }
        val showCutout = localRect != null &&
            step.targetKey != "done" &&
            step.targetKey != "bottom_nav_practice"

        // ── Затемнение с вырезом-прожектором ──
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen },
        ) {
            drawRect(scrimColor)
            if (showCutout) {
                val r = localRect ?: return@Canvas
                // Вырез повторяет скругление карточек системы,
                // поэтому «прожектор» садится точно по блоку.
                drawRoundRect(
                    color = Color.Transparent,
                    topLeft = Offset(r.left - cutoutPadPx, r.top - cutoutPadPx),
                    size = Size(r.width + cutoutPadPx * 2, r.height + cutoutPadPx * 2),
                    cornerRadius = CornerRadius(with(density) { TutiRadius.xl.toPx() }),
                    blendMode = BlendMode.Clear,
                )
            }
        }

        // ── Перехват нажатий вне подсказки ──
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {},
        )

        val advance: () -> Unit = {
            if (currentStep < onboardingSteps.size - 1) currentStep++ else onComplete()
        }

        when {
            step.targetKey == "done" -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TooltipBubble(
                        step = step,
                        isLast = true,
                        currentStep = currentStep,
                        totalSteps = onboardingSteps.size,
                        onSkip = onComplete,
                        onNext = onComplete,
                        showArrow = false,
                        arrowPointsUp = false,
                        arrowCenterXPx = 0f,
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }
            }

            step.targetKey == "bottom_nav_practice" -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    TooltipBubble(
                        step = step,
                        isLast = false,
                        currentStep = currentStep,
                        totalSteps = onboardingSteps.size,
                        onSkip = onComplete,
                        onNext = advance,
                        showArrow = true,
                        arrowPointsUp = false,
                        arrowCenterXPx = overlaySize.width / 2f - paddingHorizPx,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 12.dp),
                    )
                }
            }

            localRect != null -> {
                val tooltipBelow = localRect.center.y < overlaySize.height / 2f
                val arrowCenterXInContent = localRect.center.x - paddingHorizPx

                var tooltipHeight by remember(currentStep) { mutableStateOf(250f) }
                val tooltipYPx = if (tooltipBelow) {
                    localRect.bottom + cutoutPadPx + gapPx
                } else {
                    localRect.top - cutoutPadPx - gapPx - tooltipHeight
                }

                TooltipBubble(
                    step = step,
                    isLast = isLast,
                    currentStep = currentStep,
                    totalSteps = onboardingSteps.size,
                    onSkip = onComplete,
                    onNext = advance,
                    showArrow = true,
                    arrowPointsUp = tooltipBelow,
                    arrowCenterXPx = arrowCenterXInContent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .offset { IntOffset(0, tooltipYPx.toInt().coerceAtLeast(0)) }
                        .onSizeChanged { tooltipHeight = it.height.toFloat() },
                )
            }
        }

        // ── Счётчик шагов ──
        Text(
            text = "${currentStep + 1}/${onboardingSteps.size}",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(TutiSpace.lg)
                .clip(RoundedCornerShape(TutiRadius.pill))
                .background(Color.White.copy(alpha = 0.18f))
                .padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ПУЗЫРЬ ПОДСКАЗКИ
// ═══════════════════════════════════════════════════

@Composable
private fun TooltipBubble(
    step: TooltipStep,
    isLast: Boolean,
    currentStep: Int,
    totalSteps: Int,
    onSkip: () -> Unit,
    onNext: () -> Unit,
    showArrow: Boolean,
    arrowPointsUp: Boolean,
    arrowCenterXPx: Float,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors

    Column(modifier = modifier) {
        if (showArrow && arrowPointsUp) {
            ArrowTriangle(pointingUp = true, centerXPx = arrowCenterXPx)
        }

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            if (step.mascotSide == "left" || step.mascotSide == "center") {
                LivingTutiMascot(size = if (isLast) 84.dp else 58.dp)
            }

            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .clip(RoundedCornerShape(TutiRadius.lg))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(TutiSpace.lg),
            ) {
                Text(
                    text = step.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(TutiSpace.md))

                // Индикатор шагов — та же форма, что в онбординге
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    repeat(totalSteps) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .size(
                                    width = if (index == currentStep) 20.dp else 6.dp,
                                    height = 6.dp,
                                )
                                .clip(RoundedCornerShape(TutiRadius.pill))
                                .background(
                                    when {
                                        index == currentStep -> c.jade.base
                                        index < currentStep -> c.jade.base.copy(alpha = 0.4f)
                                        else -> c.progressTrack
                                    },
                                ),
                        )
                    }
                }

                Spacer(Modifier.height(TutiSpace.md))

                if (!isLast) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TutiGhostButton(text = "Гузаштан", onClick = onSkip)
                        TutiButton(
                            text = "Давом",
                            onClick = onNext,
                            size = TutiButtonSize.Small,
                            trailingEmoji = "→",
                            fillWidth = false,
                        )
                    }
                } else {
                    TutiButton(
                        text = "Оғоз кардан!",
                        onClick = onNext,
                        leadingEmoji = "🚀",
                    )
                }
            }

            if (step.mascotSide == "right") {
                LivingTutiMascot(size = 58.dp)
            }
        }

        if (showArrow && !arrowPointsUp) {
            ArrowTriangle(pointingUp = false, centerXPx = arrowCenterXPx)
        }
    }
}

// ═══════════════════════════════════════════════════
//  ХВОСТИК-УКАЗАТЕЛЬ
// ═══════════════════════════════════════════════════

@Composable
private fun ArrowTriangle(pointingUp: Boolean, centerXPx: Float) {
    val density = LocalDensity.current
    val surface = MaterialTheme.colorScheme.surface
    val arrowW = with(density) { 14.dp.toPx() }
    val arrowH = with(density) { 8.dp.toPx() }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
    ) {
        val cx = centerXPx.coerceIn(arrowW / 2, size.width - arrowW / 2)
        val path = Path().apply {
            if (pointingUp) {
                moveTo(cx, 0f)
                lineTo(cx - arrowW / 2, arrowH)
                lineTo(cx + arrowW / 2, arrowH)
            } else {
                moveTo(cx - arrowW / 2, 0f)
                lineTo(cx + arrowW / 2, 0f)
                lineTo(cx, arrowH)
            }
            close()
        }
        drawPath(path, surface)
    }
}
