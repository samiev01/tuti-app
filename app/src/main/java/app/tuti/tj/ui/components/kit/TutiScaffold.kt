package app.tuti.tj.ui.components.kit

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.mascot.TutiMascotStatic
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  КАРКАС ЭКРАНА: шапки и служебные состояния
// ════════════════════════════════════════════════════════════════

/**
 * Стандартная шапка внутреннего экрана: кнопка «назад»,
 * заголовок с подписью, необязательный хвост.
 */
@Composable
fun TutiTopBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBack != null) {
            TutiIconButton(emoji = "←", onClick = onBack, size = 40.dp)
            Spacer(Modifier.width(TutiSpace.md))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        if (trailing != null) {
            Spacer(Modifier.width(TutiSpace.sm))
            trailing()
        }
    }
}

/**
 * Цветная hero-шапка для экранов с собственным «настроением»:
 * Plus, рейтинг, достижения. Скругляется снизу и подкладывается
 * под контент.
 */
@Composable
fun TutiHeroHeader(
    title: String,
    gradient: List<Color>,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    emoji: String? = null,
    onBack: (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = TutiRadius.xxl, bottomEnd = TutiRadius.xxl))
            .background(Brush.linearGradient(gradient)),
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(listOf(Color.White.copy(alpha = 0.15f), Color.Transparent)),
                ),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TutiSpace.screen)
                .padding(top = TutiSpace.md, bottom = TutiSpace.xxl),
        ) {
            if (onBack != null) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(TutiRadius.sm))
                        .background(Color.White.copy(alpha = 0.22f))
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Text("←", fontSize = 20.sp, color = Color.White)
                }
                Spacer(Modifier.height(TutiSpace.sm))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (emoji != null) {
                    Text(emoji, fontSize = 32.sp)
                    Spacer(Modifier.width(TutiSpace.md))
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White,
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f),
                        )
                    }
                }
            }
            if (content != null) {
                Spacer(Modifier.height(TutiSpace.lg))
                content()
            }
        }
    }
}

// ── Служебные состояния ─────────────────────────────────────────

/**
 * Пустое состояние. Всегда с маскотом и всегда с выходом —
 * пустой экран без действия читается как поломка.
 */
@Composable
fun TutiEmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    mascotState: TutiState = TutiState.THINKING,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.xxl, vertical = TutiSpace.xxxl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TutiMascotStatic(state = mascotState, modifier = Modifier.size(112.dp))
        Spacer(Modifier.height(TutiSpace.lg))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(TutiSpace.sm))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 300.dp),
        )
        if (actionText != null && onAction != null) {
            Spacer(Modifier.height(TutiSpace.xl))
            TutiButton(
                text = actionText,
                onClick = onAction,
                fillWidth = false,
            )
        }
    }
}

/** Состояние ошибки: тот же каркас, другой тон и маскот. */
@Composable
fun TutiErrorState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    retryText: String = "Аз нав кӯшиш кунед",
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.xxl, vertical = TutiSpace.xxxl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TutiMascotStatic(state = TutiState.SAD, modifier = Modifier.size(112.dp))
        Spacer(Modifier.height(TutiSpace.lg))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(TutiSpace.sm))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 300.dp),
        )
        if (onRetry != null) {
            Spacer(Modifier.height(TutiSpace.xl))
            TutiButton(
                text = retryText,
                onClick = onRetry,
                tone = TutiButtonTone.Coral,
                fillWidth = false,
                leadingEmoji = "↻",
            )
        }
    }
}

/** Полноэкранная загрузка с дышащим маскотом. */
@Composable
fun TutiLoadingState(
    modifier: Modifier = Modifier,
    message: String = "Лаҳзае сабр кунед…",
) {
    val inf = rememberInfiniteTransition(label = "loadingPulse")
    val pulse by inf.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.06f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Reverse),
        label = "pulse",
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TutiMascotStatic(
            state = TutiState.HAPPY,
            modifier = Modifier
                .size(96.dp)
                .scale(pulse),
        )
        Spacer(Modifier.height(TutiSpace.lg))
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(TutiSpace.lg))
        TutiProgressBar(
            progress = 1f,
            modifier = Modifier.width(140.dp),
            showShine = true,
        )
    }
}

/**
 * Скелет-заглушка на время загрузки списка. Мягко пульсирует,
 * повторяя форму будущей карточки.
 */
@Composable
fun TutiSkeletonCard(
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 76.dp,
) {
    val inf = rememberInfiniteTransition(label = "skeleton")
    val a by inf.animateFloat(
        initialValue = 0.45f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Reverse),
        label = "skelAlpha",
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(TutiRadius.xl))
            .background(MaterialTheme.tutiColors.progressTrack)
            .alpha(a),
    )
}
