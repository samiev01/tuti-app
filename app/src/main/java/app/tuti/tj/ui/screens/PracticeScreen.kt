package app.tuti.tj.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.data.subscription.PlusManager
import app.tuti.tj.ui.components.kit.TutiAccentRef
import app.tuti.tj.ui.components.kit.resolve
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.mascot.TutiMascotStatic
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.PracticeStrings

// ════════════════════════════════════════════════════════════════
//  ЭКРАН ПРАКТИКИ
//
//  Четыре режима — четыре цвета из палитры. Карточка AI-наставника
//  выделена как основная: она больше, с маскотом вместо эмодзи и
//  собственной подписью-приглашением. Остальные три равнозначны.
//  На каждой карточке — остаток дневного лимита: пользователь
//  выбирает режим, уже зная, хватит ли попыток.
// ════════════════════════════════════════════════════════════════

private data class PracticeMode(
    val emoji: String,
    val title: String,
    val description: String,
    val accent: TutiAccentRef,
    val limitKey: String?,
    val limitMax: Int,
    val isFeatured: Boolean = false,
)

private fun practiceModes(s: PracticeStrings) = listOf(
    PracticeMode(
        emoji = "",
        title = s.chatTitle,
        description = s.chatDescription,
        accent = TutiAccentRef.Jade,
        limitKey = "chat",
        limitMax = FreeLimits.MAX_CHAT_MESSAGES_PER_DAY,
        isFeatured = true,
    ),
    PracticeMode(
        emoji = "🃏",
        title = s.flashcardsTitle,
        description = s.flashcardsDescription,
        accent = TutiAccentRef.Grape,
        limitKey = "flashcards",
        limitMax = FreeLimits.MAX_FLASHCARDS_PER_DAY,
    ),
    PracticeMode(
        emoji = "🎧",
        title = s.listeningTitle,
        description = s.listeningDescription,
        accent = TutiAccentRef.Sky,
        limitKey = "listening",
        limitMax = FreeLimits.MAX_LISTENING_PER_DAY,
    ),
    PracticeMode(
        emoji = "✍️",
        title = s.writingTitle,
        description = s.writingDescription,
        accent = TutiAccentRef.Mango,
        limitKey = null,
        limitMax = 0,
    ),
)

@Composable
fun PracticeScreen(
    onFlashcards: () -> Unit = {},
    onOpenChat: () -> Unit = {},
    onOpenListening: () -> Unit = {},
    onOpenWriting: () -> Unit = {},
) {
    val context = LocalContext.current
    val isPlus = remember { PlusManager.isPlusActive(context) }
    val s = LocalTutiStrings.current.practice
    val modes = remember(s) { practiceModes(s) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(horizontal = TutiSpace.screen),
    ) {
        Spacer(Modifier.height(TutiSpace.lg))
        Header()
        Spacer(Modifier.height(TutiSpace.section))

        Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.md)) {
            modes.forEachIndexed { index, mode ->
                val remaining = if (isPlus || mode.limitKey == null) null
                else FreeLimits.getRemainingCount(context, mode.limitKey, mode.limitMax)

                PracticeModeCard(
                    mode = mode,
                    remaining = remaining,
                    isPlus = isPlus,
                    onClick = when (index) {
                        0 -> onOpenChat
                        1 -> onFlashcards
                        2 -> onOpenListening
                        else -> onOpenWriting
                    },
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.bottomNavGap))
    }
}

@Composable
private fun Header() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TutiMascotStatic(state = TutiState.HAPPY, modifier = Modifier.size(44.dp))
            Spacer(Modifier.width(TutiSpace.md))
            Column {
                Text(
                    text = LocalTutiStrings.current.practice.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = LocalTutiStrings.current.practice.chooseMode,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun PracticeModeCard(
    mode: PracticeMode,
    remaining: Int?,
    isPlus: Boolean,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current.practice
    val accent = mode.accent.resolve()
    val exhausted = remaining != null && remaining == 0

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        contentPadding = TutiSpace.lg,
        background = accent.soft,
        borderColor = accent.base.copy(alpha = 0.22f),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(if (mode.isFeatured) TutiSize.iconTileXl else TutiSize.iconTileLg)
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center,
            ) {
                if (mode.isFeatured) {
                    // Статичный маскот: карточка — это кнопка,
                    // анимация здесь отвлекала бы от выбора режима.
                    TutiMascotStatic(
                        state = TutiState.HELLO,
                        modifier = Modifier.size(52.dp),
                    )
                } else {
                    Text(text = mode.emoji, fontSize = 28.sp)
                }
            }

            Spacer(Modifier.width(TutiSpace.lg))

            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = mode.title,
                        style = if (mode.isFeatured) MaterialTheme.typography.titleLarge
                        else MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    if (mode.isFeatured) {
                        Spacer(Modifier.width(TutiSpace.sm))
                        TutiPill(
                            text = "AI",
                            background = accent.base,
                            contentColor = Color.White,
                        )
                    }
                }
                Text(
                    text = mode.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (remaining != null) {
                    Spacer(Modifier.height(TutiSpace.sm))
                    TutiPill(
                        text = if (exhausted) {
                            s.limitReached
                        } else {
                            s.remaining(remaining, mode.limitMax)
                        },
                        leadingEmoji = if (exhausted) "⛔" else "⚡",
                        background = if (exhausted) c.coral.soft else MaterialTheme.colorScheme.surface,
                        contentColor = if (exhausted) c.coral.onSoft else accent.onSoft,
                    )
                } else if (isPlus && mode.limitKey != null) {
                    Spacer(Modifier.height(TutiSpace.sm))
                    TutiPill(
                        text = s.unlimited,
                        leadingEmoji = "♾️",
                        background = c.mango.soft,
                        contentColor = c.mango.onSoft,
                    )
                }
            }

            Spacer(Modifier.width(TutiSpace.sm))

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .background(accent.base),
                contentAlignment = Alignment.Center,
            ) {
                Text("→", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}
