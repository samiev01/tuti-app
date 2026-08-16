package app.tuti.tj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.ui.components.kit.TutiDialog
import app.tuti.tj.ui.components.kit.TutiDialogActions
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings

// ════════════════════════════════════════════════════════════════
//  ЛИМИТ ИСЧЕРПАН
//
//  Момент неприятный, поэтому тон подчёркнуто дружелюбный:
//  маскот сочувствует, а не давит; первым идёт бесплатный
//  выход («вернусь завтра»), Plus предлагается как ускорение,
//  а не как условие. Никакого таймера и красных цветов.
// ════════════════════════════════════════════════════════════════

@Composable
fun PaywallDialog(
    lessonsUsed: Int = 2,
    lessonsMax: Int = 2,
    onGetPlus: () -> Unit,
    onDismiss: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current.plus

    LaunchedEffect(Unit) {
        runCatching { TutiSoundManager.playLoseHeart() }
    }

    TutiDialog(
        onDismiss = onDismiss,
        title = s.paywallTitle,
        message = s.paywallMessage(lessonsUsed, lessonsMax),
        mascotState = TutiState.SAD,
        accent = c.mango.base,
    ) {
        Spacer(Modifier.height(TutiSpace.xl))

        // Что даёт Plus
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(TutiRadius.md))
                .background(c.mango.soft)
                .padding(TutiSpace.md),
        ) {
            Text(
                text = s.paywallBenefitsTitle,
                style = MaterialTheme.typography.titleSmall,
                color = c.mango.onSoft,
            )
            Spacer(Modifier.height(TutiSpace.sm))
            listOf(
                "📚" to s.paywallLessons,
                "🦜" to s.paywallChat,
                "🎧" to s.paywallListening,
                "📞" to s.paywallCall,
            ).forEach { (emoji, text) ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(emoji, fontSize = 14.sp)
                    Spacer(Modifier.width(TutiSpace.sm))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }

        Spacer(Modifier.height(TutiSpace.md))

        // Тарифы
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            PriceTile(
                label = s.monthly,
                price = "29",
                highlighted = false,
                modifier = Modifier.weight(1f),
            )
            PriceTile(
                label = s.yearly,
                price = "149",
                badge = "-57%",
                highlighted = true,
                modifier = Modifier.weight(1f),
            )
        }

        TutiDialogActions(
            primaryText = s.paywallPrimary,
            onPrimary = onGetPlus,
            secondaryText = s.paywallSecondary,
            onSecondary = onDismiss,
            tone = TutiButtonTone.Mango,
            gradient = c.plusGradient,
        )
    }
}

@Composable
private fun PriceTile(
    label: String,
    price: String,
    highlighted: Boolean,
    modifier: Modifier = Modifier,
    badge: String? = null,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.md)

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(if (highlighted) c.mango.soft else MaterialTheme.colorScheme.surface)
                .border(
                    width = if (highlighted) 2.dp else 1.dp,
                    color = if (highlighted) c.mango.base else c.cardBorder,
                    shape = shape,
                )
                .padding(vertical = TutiSpace.md),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (highlighted) c.mango.onSoft
                else MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = price,
                style = MaterialTheme.typography.displaySmall,
                fontSize = 22.sp,
                color = if (highlighted) c.mango.base else MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = LocalTutiStrings.current.plus.currency,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 9.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        if (badge != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(bottomStart = TutiRadius.sm, topEnd = TutiRadius.md))
                    .background(c.mango.base)
                    .padding(horizontal = 7.dp, vertical = 2.dp),
            ) {
                Text(
                    text = badge,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 9.sp,
                    color = Color.White,
                )
            }
        }
    }
}
