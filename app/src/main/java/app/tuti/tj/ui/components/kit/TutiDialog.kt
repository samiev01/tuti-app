package app.tuti.tj.ui.components.kit

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings

// ════════════════════════════════════════════════════════════════
//  МОДАЛЬНЫЕ ОКНА
//
//  Одна форма на все диалоги приложения: маскот сверху «в вырезе»
//  карточки, заголовок, текст, содержимое, кнопки внизу.
//  Появление — лёгкий перелёт по масштабу, чтобы окно
//  «выпрыгивало», а не подменяло экран рывком.
// ════════════════════════════════════════════════════════════════

@Composable
fun TutiDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    emoji: String? = null,
    mascotState: TutiState? = null,
    accent: Color? = null,
    dismissOnOutside: Boolean = true,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    val c = MaterialTheme.tutiColors
    val accentColor = accent ?: c.jade.base

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.88f,
        animationSpec = TutiMotion.pop(),
        label = "dialogScale",
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = dismissOnOutside,
        ),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = TutiSpace.xxl)
                .scale(scale),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (mascotState != null) 46.dp else 0.dp)
                    .clip(RoundedCornerShape(TutiRadius.xxl))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, c.cardBorder, RoundedCornerShape(TutiRadius.xxl))
                    .padding(TutiSpace.xxl),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (mascotState != null) Spacer(Modifier.height(38.dp))

                if (emoji != null && mascotState == null) {
                    Text(emoji, fontSize = 44.sp)
                    Spacer(Modifier.height(TutiSpace.md))
                }
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )
                }
                if (message != null) {
                    Spacer(Modifier.height(TutiSpace.sm))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }
                content()
            }

            // Маскот сидит в «вырезе» — визуально связывает окно с брендом
            if (mascotState != null) {
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(RoundedCornerShape(TutiRadius.pill))
                        .background(
                            Brush.verticalGradient(
                                listOf(accentColor.copy(alpha = 0.22f), MaterialTheme.colorScheme.surface),
                            ),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    TutiMascotVector(state = mascotState, modifier = Modifier.size(72.dp))
                }
            }
        }
    }
}

/** Кнопочная строка диалога: главное действие сверху, отказ — ниже. */
@Composable
fun TutiDialogActions(
    primaryText: String,
    onPrimary: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryText: String? = null,
    onSecondary: (() -> Unit)? = null,
    tone: TutiButtonTone = TutiButtonTone.Jade,
    gradient: List<Color>? = null,
    primaryEnabled: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = TutiSpace.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TutiButton(
            text = primaryText,
            onClick = onPrimary,
            tone = tone,
            gradient = gradient,
            enabled = primaryEnabled,
        )
        if (secondaryText != null && onSecondary != null) {
            Spacer(Modifier.height(TutiSpace.xs))
            TutiGhostButton(text = secondaryText, onClick = onSecondary)
        }
    }
}

/**
 * Праздничное окно завершения: результат урока, разблокированное
 * достижение. Отличается от обычного диалога цветной шапкой и
 * маскотом в состоянии CELEBRATE.
 */
@Composable
fun TutiCelebrationDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    primaryText: String,
    onPrimary: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryText: String? = null,
    onSecondary: (() -> Unit)? = null,
    stats: List<Pair<String, String>> = emptyList(),
) {
    val c = MaterialTheme.tutiColors
    TutiDialog(
        onDismiss = onDismiss,
        modifier = modifier,
        title = title,
        message = message,
        mascotState = TutiState.CELEBRATE,
        accent = c.mango.base,
        dismissOnOutside = false,
    ) {
        if (stats.isNotEmpty()) {
            Spacer(Modifier.height(TutiSpace.xl))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                stats.forEach { (value, label) ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(TutiRadius.md))
                            .background(c.tileBg)
                            .padding(vertical = TutiSpace.md),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = value,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
        TutiDialogActions(
            primaryText = primaryText,
            onPrimary = onPrimary,
            secondaryText = secondaryText,
            onSecondary = onSecondary,
            tone = TutiButtonTone.Mango,
            gradient = c.plusGradient,
        )
    }
}

/**
 * Нижняя панель обратной связи в уроке. Зелёная при верном
 * ответе, коралловая при ошибке — цвет считывается раньше текста.
 */
@Composable
fun TutiFeedbackBar(
    isCorrect: Boolean,
    title: String,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
    detail: String? = null,
    buttonText: String = "${LocalTutiStrings.current.common.continueShort} →",
) {
    val c = MaterialTheme.tutiColors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = TutiRadius.xxl, topEnd = TutiRadius.xxl))
            .background(if (isCorrect) c.correctBg else c.wrongBg)
            .padding(TutiSpace.xl),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .background(if (isCorrect) c.correctText else c.wrongText),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = if (isCorrect) "✓" else "✕",
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isCorrect) c.correctText else c.wrongText,
                )
                if (detail != null) {
                    Text(
                        text = detail,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isCorrect) c.correctText else c.wrongText,
                    )
                }
            }
        }
        Spacer(Modifier.height(TutiSpace.lg))
        TutiButton(
            text = buttonText,
            onClick = onContinue,
            tone = if (isCorrect) TutiButtonTone.Leaf else TutiButtonTone.Coral,
        )
    }
}
