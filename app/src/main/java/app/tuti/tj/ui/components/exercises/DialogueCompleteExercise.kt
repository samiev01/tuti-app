package app.tuti.tj.ui.components.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings

/**
 * Достроить реплику в диалоге.
 *
 * Реплика подана как пузырь сообщения с аватаром — задание
 * читается как продолжение живого разговора, а не как тест.
 */
@Composable
fun DialogueCompleteExercise(
    exercise: Exercise,
    selectedIndex: Int?,
    answeredCorrectly: Boolean?,
    onSelect: (Int) -> Unit,
) {
    val options = exercise.options
    if (options.isNullOrEmpty()) return
    val c = MaterialTheme.tutiColors
    val safeCorrectIndex = exercise.correctIndex?.coerceIn(0, options.size - 1)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(TutiRadius.pill))
                    .background(c.sky.soft),
                contentAlignment = Alignment.Center,
            ) {
                Text("💬", fontSize = 16.sp)
            }
            Spacer(Modifier.width(TutiSpace.sm))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(
                        RoundedCornerShape(
                            TutiRadius.lg, TutiRadius.lg, TutiRadius.lg, 6.dp,
                        ),
                    )
                    .background(c.tileBg)
                    .border(
                        1.dp,
                        c.cardBorder,
                        RoundedCornerShape(TutiRadius.lg, TutiRadius.lg, TutiRadius.lg, 6.dp),
                    )
                    .padding(TutiSpace.lg),
            ) {
                Text(
                    text = exercise.prompt,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.xl))

        Text(
            text = LocalTutiStrings.current.practice.fillTheBlank,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(TutiSpace.md))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement
                .spacedBy(TutiSpace.sm),
        ) {
            options.forEachIndexed { idx, option ->
                ExerciseChoice(
                    text = option,
                    state = choiceStateOf(
                        isSelected = selectedIndex == idx,
                        isCorrect = safeCorrectIndex == idx,
                        answeredCorrectly = answeredCorrectly,
                    ),
                    enabled = answeredCorrectly == null,
                    onClick = { onSelect(idx) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
