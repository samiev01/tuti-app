package app.tuti.tj.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.TutiTipsManager
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.delay

// ════════════════════════════════════════════════════════════════
//  ПОДСКАЗКА ОТ TUTI
//
//  Реплика наставника, а не системный тост: светлая карточка в
//  фирменном jade, живой маскот слева и закрытие справа.
//  Раньше подсказка была тёмной плашкой и выглядела как ошибка.
// ════════════════════════════════════════════════════════════════

@Composable
fun TutiTip(
    text: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
        runCatching { TutiSoundManager.playSelectOption() }
    }

    // Подсказка уходит сама: она помогает, но не требует действия.
    LaunchedEffect(Unit) {
        delay(8000)
        visible = false
        delay(300)
        onDismiss()
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it / 2 }) + fadeOut(),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.sm)
                .clip(RoundedCornerShape(TutiRadius.lg))
                .background(c.jade.soft)
                .border(1.dp, c.jade.base.copy(alpha = 0.3f), RoundedCornerShape(TutiRadius.lg))
                .padding(TutiSpace.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            LivingTutiMascot(size = 42.dp)

            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = c.jade.onSoft,
            )

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(TutiRadius.pill))
                    .background(c.jade.base.copy(alpha = 0.12f))
                    .clickable {
                        visible = false
                        onDismiss()
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "✕",
                    fontSize = 13.sp,
                    color = c.jade.onSoft,
                )
            }
        }
    }
}

/**
 * Показывает [TutiTip] только один раз — по идентификатору
 * подсказки, и сама помечает её показанной.
 */
@Composable
fun SmartTutiTip(
    tipId: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(TutiTipsManager.shouldShowTip(context, tipId)) }

    if (show) {
        TutiTip(
            text = text,
            onDismiss = {
                show = false
                TutiTipsManager.markTipShown(context, tipId)
            },
            modifier = modifier,
        )
    }
}
