package app.tuti.tj.ui.components.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

/**
 * Сборка предложения из слов.
 *
 * Поле ответа и банк слов разделены: сверху — «строка», куда
 * слова кладутся, снизу — банк, откуда берутся. Использованное
 * слово не исчезает, а гаснет на своём месте: так не сбивается
 * расположение и легче найти слово обратно.
 */
@Composable
fun BuildSentenceExercise(
    exercise: Exercise,
    builtWords: List<String>,
    answeredCorrectly: Boolean?,
    onAddWord: (String) -> Unit,
    onRemoveWord: (Int) -> Unit,
) {
    val availableWords = exercise.words
    if (availableWords.isNullOrEmpty()) return
    val c = MaterialTheme.tutiColors

    val answerBorder by animateColorAsState(
        when (answeredCorrectly) {
            true -> c.correctText
            false -> c.wrongText
            null -> c.cardBorder
        },
        animationSpec = TutiMotion.fast(),
        label = "sentenceBorder",
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = exercise.prompt,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.lg))

        // Строка ответа
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 84.dp)
                .clip(RoundedCornerShape(TutiRadius.lg))
                .background(MaterialTheme.colorScheme.surface)
                .border(2.dp, answerBorder, RoundedCornerShape(TutiRadius.lg))
                .padding(TutiSpace.md),
            contentAlignment = Alignment.Center,
        ) {
            if (builtWords.isEmpty()) {
                Text(
                    text = "Калимаҳоро зер кунед…",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
            } else {
                WrapRow(modifier = Modifier.fillMaxWidth()) {
                    builtWords.forEachIndexed { idx, word ->
                        ExerciseWordChip(
                            text = word,
                            filled = true,
                            enabled = answeredCorrectly == null,
                            onClick = { onRemoveWord(idx) },
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(TutiSpace.xl))

        // Банк слов
        WrapRow(modifier = Modifier.fillMaxWidth()) {
            availableWords.forEach { word ->
                ExerciseWordChip(
                    text = word,
                    used = builtWords.contains(word),
                    enabled = answeredCorrectly == null,
                    onClick = { onAddWord(word) },
                )
            }
        }
    }
}
