package app.tuti.tj.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.WordItem
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSecondaryButton
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.TutiWordStyle
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.localizedName

// ════════════════════════════════════════════════════════════════
//  ИЗУЧЕНИЕ СЛОВ ТЕМЫ
//
//  Карточка раскрывается вниз, а не переворачивается: слово
//  остаётся на месте, под ним появляется перевод и пример.
//  Так связь «слово → значение» видна целиком.
// ════════════════════════════════════════════════════════════════

@Composable
fun WordLearnScreen(
    topicId: String,
    onStartQuiz: () -> Unit,
    onBack: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val s = strings.lessons
    val words = remember { ContentProvider.getWordsForTopic(topicId) }
    val topicInfo = remember { ContentProvider.getTopicInfo(topicId) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }

    val word = words.getOrNull(currentIndex) ?: return
    val progress = (currentIndex + 1f) / words.size
    val isLast = currentIndex == words.size - 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = TutiSpace.screen)
            .padding(top = TutiSpace.md, bottom = TutiSpace.sm),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TutiIconButton(emoji = "←", onClick = onBack, size = 40.dp)
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = topicInfo?.let { "${it.emoji} ${it.localizedName(strings)}" }
                        ?: s.wordsTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = strings.common.ofCount(currentIndex + 1, words.size),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            TutiPill(
                text = s.toQuiz,
                background = c.jade.soft,
                contentColor = c.jade.onSoft,
                onClick = onStartQuiz,
            )
        }

        Spacer(Modifier.height(TutiSpace.md))

        TutiProgressBar(progress = progress)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(TutiSpace.xl))

            TutiMascotVector(
                state = if (isFlipped) TutiState.CELEBRATE else TutiState.HAPPY,
                modifier = Modifier.size(72.dp),
            )

            Spacer(Modifier.height(TutiSpace.lg))

            WordCard(word = word, isFlipped = isFlipped, onFlip = { isFlipped = !isFlipped })

            Spacer(Modifier.height(TutiSpace.xxl))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            if (currentIndex > 0) {
                TutiSecondaryButton(
                    text = strings.common.previousArrow,
                    onClick = { currentIndex--; isFlipped = false },
                    modifier = Modifier.weight(1f),
                    fillWidth = false,
                )
            }
            TutiButton(
                text = if (isLast) s.startQuiz else strings.common.continueShort,
                onClick = {
                    if (isLast) onStartQuiz() else {
                        currentIndex++
                        isFlipped = false
                    }
                },
                trailingEmoji = if (isLast) "🎯" else "→",
                modifier = Modifier.weight(1f),
                fillWidth = false,
            )
        }

        Spacer(Modifier.height(TutiSpace.xs))
    }
}

@Composable
private fun WordCard(word: WordItem, isFlipped: Boolean, onFlip: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.xxl)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = if (isFlipped) 2.dp else 1.5.dp,
                color = if (isFlipped) c.jade.base.copy(alpha = 0.4f) else c.cardBorder,
                shape = shape,
            )
            .clickable { onFlip() }
            .padding(TutiSpace.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = word.word,
            style = TutiWordStyle,
            fontSize = 34.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.xs))

        Text(
            text = word.pronunciation,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        AnimatedVisibility(visible = isFlipped, enter = fadeIn() + expandVertically()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(TutiSpace.lg))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(c.divider),
                )
                Spacer(Modifier.height(TutiSpace.lg))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(TutiRadius.md))
                        .background(c.jade.soft)
                        .padding(horizontal = TutiSpace.xl, vertical = TutiSpace.md),
                ) {
                    Text(
                        text = word.translation,
                        style = MaterialTheme.typography.headlineSmall,
                        color = c.jade.onSoft,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(Modifier.height(TutiSpace.lg))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(TutiRadius.md))
                        .background(c.tileBg)
                        .padding(TutiSpace.lg),
                ) {
                    Text(
                        text = word.example,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(Modifier.height(TutiSpace.xs))
                    Text(
                        text = word.exampleTranslation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        if (!isFlipped) {
            Spacer(Modifier.height(TutiSpace.xxl))
            Text(
                text = LocalTutiStrings.current.lessons.tapForTranslation,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }
    }
}
