package app.tuti.tj.ui.components.exercises

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.tuti.tj.data.content.Exercise

/**
 * Выбор одного варианта — базовое задание урока.
 * Вся визуальная механика вынесена в [ExerciseChoiceGrid],
 * здесь остаётся только раскладка задания.
 */
@Composable
fun MultipleChoiceExercise(
    exercise: Exercise,
    selectedIndex: Int?,
    answeredCorrectly: Boolean?,
    onSelect: (Int) -> Unit,
) {
    val options = exercise.options
    if (options.isNullOrEmpty()) {
        Log.e("MultipleChoice", "options is null or empty for exercise ${exercise.id}")
        Text(
            text = "Хатогӣ: варианти ҷавоб нест",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExercisePrompt(prompt = exercise.prompt, hint = exercise.hint)

        ExerciseChoiceGrid(
            options = options,
            selectedIndex = selectedIndex,
            correctIndex = exercise.correctIndex?.coerceIn(0, options.size - 1),
            answeredCorrectly = answeredCorrectly,
            onSelect = onSelect,
        )
    }
}
