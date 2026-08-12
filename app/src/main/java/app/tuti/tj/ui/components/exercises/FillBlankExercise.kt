package app.tuti.tj.ui.components.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

/**
 * Заполнение пропуска. Ключевая деталь — сама фраза: пропуск
 * подчёркнут и меняет цвет вместе с выбором, поэтому ответ
 * читается прямо в предложении, а не отдельно от него.
 */
@Composable
fun FillBlankExercise(
    exercise: Exercise,
    selectedIndex: Int?,
    answeredCorrectly: Boolean?,
    onSelect: (Int) -> Unit,
) {
    val options = exercise.options
    if (options.isNullOrEmpty()) return

    val c = MaterialTheme.tutiColors
    val hint = exercise.hint ?: ""
    val safeCorrectIndex = exercise.correctIndex?.coerceIn(0, options.size - 1)
    val selectedWord = selectedIndex?.let { options.getOrNull(it) }

    val blankColor by animateColorAsState(
        when {
            answeredCorrectly == true -> c.correctText
            answeredCorrectly == false -> c.wrongText
            selectedWord != null -> c.jade.base
            else -> MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = TutiMotion.fast(),
        label = "blankColor",
    )

    val sentenceText = buildAnnotatedString {
        val parts = hint.split("_____")
        append(parts.getOrElse(0) { "" })
        withStyle(
            SpanStyle(
                color = blankColor,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textDecoration = if (selectedWord == null) TextDecoration.Underline else null,
            ),
        ) {
            append(selectedWord ?: "        ")
        }
        if (parts.size > 1) append(parts[1])
    }

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(TutiRadius.lg))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, c.cardBorder, RoundedCornerShape(TutiRadius.lg))
                .padding(TutiSpace.xl),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = sentenceText,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(Modifier.height(TutiSpace.xl))

        ExerciseChoiceGrid(
            options = options,
            selectedIndex = selectedIndex,
            correctIndex = safeCorrectIndex,
            answeredCorrectly = answeredCorrectly,
            onSelect = onSelect,
        )
    }
}
