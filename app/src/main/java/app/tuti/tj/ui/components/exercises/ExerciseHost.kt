package app.tuti.tj.ui.components.exercises

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.data.content.ExerciseType
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

private const val TAG = "ExerciseHost"

// ════════════════════════════════════════════════════════════════
//  ХОСТ УПРАЖНЕНИЙ
//
//  Единая рамка для всех типов заданий: содержимое → обратная
//  связь → одна кнопка внизу. Кнопка меняет и цвет, и текст в
//  зависимости от результата, поэтому «проверить» и «дальше» не
//  выглядят одинаково и не нажимаются по инерции.
// ════════════════════════════════════════════════════════════════

@Composable
fun ExerciseHost(
    exercise: Exercise,
    onAnswer: (isCorrect: Boolean) -> Unit,
) {
    var selectedIndex by remember(exercise.id) { mutableStateOf<Int?>(null) }
    var answeredCorrectly by remember(exercise.id) { mutableStateOf<Boolean?>(null) }
    var typedText by remember(exercise.id) { mutableStateOf("") }
    val builtWords = remember(exercise.id) { mutableStateListOf<String>() }

    var selectedLeft by remember(exercise.id) { mutableIntStateOf(-1) }
    var selectedRight by remember(exercise.id) { mutableIntStateOf(-1) }
    val matchedLeftIndices = remember(exercise.id) { mutableStateListOf<Int>() }
    val matchedRightIndices = remember(exercise.id) { mutableStateListOf<Int>() }
    var wrongPair by remember(exercise.id) { mutableStateOf<Pair<Int, Int>?>(null) }
    var matchPairsComplete by remember(exercise.id) { mutableStateOf(false) }

    val pairs = exercise.pairs ?: emptyList()
    val shuffledRight = remember(exercise.id) {
        if (pairs.isNotEmpty()) pairs.map { it.second }.shuffled() else emptyList()
    }

    fun checkMatchPair(leftIdx: Int, rightIdx: Int) {
        if (leftIdx !in pairs.indices || rightIdx !in shuffledRight.indices) return
        val rightItem = shuffledRight[rightIdx]
        val correctRight = pairs[leftIdx].second
        if (rightItem == correctRight) {
            matchedLeftIndices.add(leftIdx)
            matchedRightIndices.add(rightIdx)
            selectedLeft = -1
            selectedRight = -1
            wrongPair = null
            TutiSoundManager.playSelectOption()
            if (matchedLeftIndices.size == pairs.size) {
                matchPairsComplete = true
                answeredCorrectly = true
            }
        } else {
            wrongPair = leftIdx to rightIdx
            selectedLeft = -1
            selectedRight = -1
        }
    }

    LaunchedEffect(wrongPair) {
        if (wrongPair != null) {
            kotlinx.coroutines.delay(600)
            wrongPair = null
        }
    }

    val hasInput = when (exercise.type) {
        ExerciseType.MULTIPLE_CHOICE, ExerciseType.TRANSLATE_SENTENCE,
        ExerciseType.LISTEN_CHOOSE, ExerciseType.FILL_BLANK,
        ExerciseType.DIALOGUE_COMPLETE -> selectedIndex != null
        ExerciseType.BUILD_SENTENCE -> builtWords.isNotEmpty()
        ExerciseType.TYPE_ANSWER -> typedText.isNotBlank()
        ExerciseType.MATCH_PAIRS -> matchPairsComplete
    }

    // Данные проверяются до отрисовки: сбой контента не должен
    // ронять весь урок.
    val dataValid = when (exercise.type) {
        ExerciseType.MULTIPLE_CHOICE, ExerciseType.TRANSLATE_SENTENCE,
        ExerciseType.LISTEN_CHOOSE, ExerciseType.FILL_BLANK,
        ExerciseType.DIALOGUE_COMPLETE -> !exercise.options.isNullOrEmpty()
        ExerciseType.BUILD_SENTENCE -> !exercise.words.isNullOrEmpty()
        ExerciseType.MATCH_PAIRS -> !exercise.pairs.isNullOrEmpty()
        ExerciseType.TYPE_ANSWER -> true
    }

    if (!dataValid) {
        Log.e(TAG, "Invalid exercise data for ${exercise.id} type=${exercise.type}")
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Ин машқ дастрас нест",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(TutiSpace.md))
            TutiButton(text = "Давом", onClick = { onAnswer(true) }, trailingEmoji = "→")
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (exercise.type) {
            ExerciseType.MULTIPLE_CHOICE ->
                MultipleChoiceExercise(exercise, selectedIndex, answeredCorrectly) {
                    TutiSoundManager.playSelectOption(); selectedIndex = it
                }
            ExerciseType.TRANSLATE_SENTENCE ->
                TranslateSentenceExercise(exercise, selectedIndex, answeredCorrectly) {
                    TutiSoundManager.playSelectOption(); selectedIndex = it
                }
            ExerciseType.LISTEN_CHOOSE ->
                ListenChooseExercise(exercise, selectedIndex, answeredCorrectly) {
                    TutiSoundManager.playSelectOption(); selectedIndex = it
                }
            ExerciseType.FILL_BLANK ->
                FillBlankExercise(exercise, selectedIndex, answeredCorrectly) {
                    TutiSoundManager.playSelectOption(); selectedIndex = it
                }
            ExerciseType.DIALOGUE_COMPLETE ->
                DialogueCompleteExercise(exercise, selectedIndex, answeredCorrectly) {
                    TutiSoundManager.playSelectOption(); selectedIndex = it
                }
            ExerciseType.BUILD_SENTENCE -> BuildSentenceExercise(
                exercise, builtWords, answeredCorrectly,
                onAddWord = { TutiSoundManager.playSelectOption(); builtWords.add(it) },
                onRemoveWord = { idx -> if (idx in builtWords.indices) builtWords.removeAt(idx) },
            )
            ExerciseType.TYPE_ANSWER -> TypeAnswerExercise(
                exercise, typedText, answeredCorrectly,
                onTextChange = { typedText = it },
                onSubmit = {},
            )
            ExerciseType.MATCH_PAIRS -> MatchPairsExercise(
                prompt = exercise.prompt,
                leftItems = pairs.map { it.first },
                rightItems = shuffledRight,
                matchedLeftIndices = matchedLeftIndices.toSet(),
                matchedRightIndices = matchedRightIndices.toSet(),
                selectedLeft = if (selectedLeft >= 0) selectedLeft else null,
                selectedRight = if (selectedRight >= 0) selectedRight else null,
                wrongPair = wrongPair,
                onSelectLeft = { idx ->
                    selectedLeft = idx
                    if (selectedRight >= 0) checkMatchPair(idx, selectedRight)
                },
                onSelectRight = { idx ->
                    selectedRight = idx
                    if (selectedLeft >= 0) checkMatchPair(selectedLeft, idx)
                },
            )
        }

        Spacer(Modifier.height(TutiSpace.lg))

        AnimatedVisibility(
            visible = answeredCorrectly != null,
            enter = fadeIn() + expandVertically(),
        ) {
            Column {
                FeedbackCard(
                    isCorrect = answeredCorrectly == true,
                    explanation = exercise.explanation,
                )
                Spacer(Modifier.height(TutiSpace.lg))
            }
        }

        if (exercise.type == ExerciseType.MATCH_PAIRS) {
            if (matchPairsComplete) {
                TutiButton(
                    text = "Давом додан",
                    onClick = {
                        TutiSoundManager.playCorrectAnswer()
                        runCatching { onAnswer(true) }
                            .onFailure { Log.e(TAG, "onAnswer crash", it) }
                    },
                    tone = TutiButtonTone.Leaf,
                    trailingEmoji = "→",
                    playSound = false,
                )
            }
        } else {
            TutiButton(
                text = if (answeredCorrectly != null) "Давом додан" else "Санҷидан",
                onClick = {
                    try {
                        if (answeredCorrectly != null) {
                            TutiSoundManager.playButtonClick()
                            onAnswer(answeredCorrectly == true)
                        } else {
                            val correct: Boolean = when (exercise.type) {
                                ExerciseType.BUILD_SENTENCE ->
                                    builtWords.joinToString(" ")
                                        .equals(exercise.correctAnswer, ignoreCase = true)
                                ExerciseType.TYPE_ANSWER ->
                                    checkTypedAnswer(typedText, exercise.correctAnswer)
                                else -> {
                                    val ci = exercise.correctIndex
                                    val si = selectedIndex
                                    ci != null && si != null && si == ci
                                }
                            }
                            if (correct) TutiSoundManager.playCorrectAnswer()
                            else TutiSoundManager.playWrongAnswer()
                            answeredCorrectly = correct
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "CRASH in button onClick! Forcing advance.", e)
                        runCatching { onAnswer(false) }
                    }
                },
                enabled = hasInput,
                tone = when (answeredCorrectly) {
                    true -> TutiButtonTone.Leaf
                    false -> TutiButtonTone.Coral
                    null -> TutiButtonTone.Jade
                },
                trailingEmoji = if (answeredCorrectly != null) "→" else "✓",
                playSound = false,
            )
        }
    }
}

/**
 * Обратная связь после ответа. Пояснение показывается всегда —
 * и при верном ответе тоже: так закрепляется правило, а не
 * только факт попадания.
 */
@Composable
private fun FeedbackCard(isCorrect: Boolean, explanation: String) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TutiRadius.lg))
            .background(if (isCorrect) c.correctBg else c.wrongBg)
            .padding(TutiSpace.lg),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(TutiRadius.sm))
                .background(if (isCorrect) c.correctText else c.wrongText),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (isCorrect) "✓" else "✕",
                fontSize = 18.sp,
                color = Color.White,
            )
        }
        Column {
            Text(
                text = if (isCorrect) "Офарин! Дуруст!" else "Нодуруст…",
                style = MaterialTheme.typography.titleMedium,
                color = if (isCorrect) c.correctText else c.wrongText,
            )
            if (explanation.isNotBlank()) {
                Text(
                    text = explanation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
        }
    }
}
