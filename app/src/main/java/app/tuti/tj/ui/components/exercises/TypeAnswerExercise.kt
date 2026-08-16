package app.tuti.tj.ui.components.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings

/**
 * Ввод ответа с клавиатуры.
 *
 * Рамка поля — единственный индикатор состояния, поэтому она
 * толстая и меняет цвет: при вводе на экране больше ничего не
 * происходит, и обратная связь должна быть однозначной.
 */
@Composable
fun TypeAnswerExercise(
    exercise: Exercise,
    typedText: String,
    answeredCorrectly: Boolean?,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val borderColor by animateColorAsState(
        when (answeredCorrectly) {
            true -> c.correctText
            false -> c.wrongText
            null -> c.cardBorder
        },
        animationSpec = TutiMotion.fast(),
        label = "inputBorder",
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExercisePrompt(prompt = exercise.prompt, hint = exercise.hint)

        OutlinedTextField(
            value = typedText,
            onValueChange = { if (answeredCorrectly == null) onTextChange(it) },
            placeholder = {
                Text(
                    text = LocalTutiStrings.current.practice.writeAnswerPlaceholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            textStyle = MaterialTheme.typography.headlineSmall,
            singleLine = true,
            enabled = answeredCorrectly == null,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onSubmit() }),
            shape = RoundedCornerShape(TutiRadius.lg),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                disabledBorderColor = borderColor,
                cursorColor = c.jade.base,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        if (answeredCorrectly == false) {
            Spacer(Modifier.height(TutiSpace.md))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(c.correctBg)
                    .padding(TutiSpace.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("💡", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.width(TutiSpace.sm))
                Text(
                    text = LocalTutiStrings.current.common.correctAnswer(exercise.correctAnswer),
                    style = MaterialTheme.typography.titleMedium,
                    color = c.correctText,
                )
            }
        }
    }
}

/**
 * Опечатка в один символ засчитывается как верный ответ:
 * задание проверяет знание слова, а не точность попадания
 * по клавишам.
 */
fun checkTypedAnswer(typed: String, correct: String): Boolean {
    val t = typed.trim().lowercase()
    val c = correct.trim().lowercase()
    if (t == c) return true
    if (t.length >= 2 && c.length >= 2 && levenshtein(t, c) <= 1) return true
    return false
}

private fun levenshtein(a: String, b: String): Int {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    for (i in 0..a.length) dp[i][0] = i
    for (j in 0..b.length) dp[0][j] = j
    for (i in 1..a.length) {
        for (j in 1..b.length) {
            val cost = if (a[i - 1] == b[j - 1]) 0 else 1
            dp[i][j] = minOf(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost)
        }
    }
    return dp[a.length][b.length]
}
