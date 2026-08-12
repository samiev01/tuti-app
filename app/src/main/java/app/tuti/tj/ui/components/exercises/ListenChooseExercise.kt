package app.tuti.tj.ui.components.exercises

import androidx.compose.runtime.Composable
import app.tuti.tj.data.content.Exercise

@Composable
fun ListenChooseExercise(
    exercise: Exercise,
    selectedIndex: Int?,
    answeredCorrectly: Boolean?,
    onSelect: (Int) -> Unit,
) {
    MultipleChoiceExercise(
        exercise = exercise,
        selectedIndex = selectedIndex,
        answeredCorrectly = answeredCorrectly,
        onSelect = onSelect,
    )
}
