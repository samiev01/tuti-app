package app.tuti.tj.ui.components

import androidx.compose.foundation.background
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
