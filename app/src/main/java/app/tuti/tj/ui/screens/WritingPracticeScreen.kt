package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.audio.rememberTtsPlayer
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiEmptyState
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiLoadingState
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiPracticeResult
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSecondaryButton
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ════════════════════════════════════════════════════════════════
//  МАШҚИ ИМЛО
//
//  Здесь пользователь чаще всего ошибается, поэтому обратная
//  связь построена мягко: подсказка показывает первую и
//  последнюю букву, попытки считаются явно, а неверный ответ
//  не закрывает задание — предлагает вернуться к нему.
// ════════════════════════════════════════════════════════════════

private const val MAX_ATTEMPTS = 3

/** Подсказка: «п _ _ _ т» — форма слова видна, само слово нет. */
private fun buildHint(word: String): String {
    if (word.length <= 2) return word
    return "${word.first()}${"_".repeat(word.length - 2)}${word.last()}"
}

@Composable
fun WritingPracticeScreen(
    viewModel: WritingPracticeViewModel,
    onBack: () -> Unit,
    onGoToLessons: () -> Unit,
) {
    val uiState = viewModel.uiState

    if (!uiState.loaded) {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            TutiLoadingState(message = "Машқро тайёр мекунем…")
        }
        return
    }

    if (!uiState.hasEnoughWords || uiState.questions.isEmpty()) {
        EmptyWritingScreen(
            wordsCount = uiState.wordsCount,
            onBack = onBack,
            onGoToLessons = onGoToLessons,
        )
        return
    }

    WritingQuiz(
        uiState = uiState,
        onTypedTextChange = viewModel::updateTypedText,
        onCheckAnswer = viewModel::checkAnswer,
        onAdvance = viewModel::advance,
        onRetryAfterWrong = viewModel::retryAfterWrong,
        onRestart = viewModel::restart,
        onBack = onBack,
    )
}

// ═══════════════════════════════════════════════════
//  ПУСТОЕ СОСТОЯНИЕ
// ═══════════════════════════════════════════════════

@Composable
private fun EmptyWritingScreen(
    wordsCount: Int,
    onBack: () -> Unit,
    onGoToLessons: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(TutiSpace.xxl),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TutiEmptyState(
            title = "Аввал калимаҳоро омӯзед!",
            message = if (wordsCount > 0)
                "Шумо $wordsCount калима доред. Ҳадди ақал $PRACTICE_MIN_WORDS_REQUIRED калима лозим аст."
            else
                "Пас аз омӯхтани калимаҳо онҳоро дар ин ҷо машқ карда метавонед.",
            mascotState = TutiState.THINKING,
        )
        Spacer(Modifier.height(TutiSpace.lg))
        TutiButton(
            text = "Ба дарсҳо рафтан",
            onClick = onGoToLessons,
            tone = TutiButtonTone.Mango,
            leadingEmoji = "📚",
        )
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(text = "← Бозгашт", onClick = onBack, tone = TutiButtonTone.Mango)
    }
}

// ═══════════════════════════════════════════════════
//  ЗАДАНИЕ
// ═══════════════════════════════════════════════════

@Composable
private fun WritingQuiz(
    uiState: WritingPracticeUiState,
    onTypedTextChange: (String) -> Unit,
    onCheckAnswer: () -> Boolean?,
    onAdvance: () -> Unit,
    onRetryAfterWrong: () -> Unit,
    onRestart: () -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val keyboardController = LocalSoftwareKeyboardController.current
    val total = uiState.questions.size

    val tts = rememberTtsPlayer()
    val question = uiState.currentQuestion

    LaunchedEffect(uiState.currentIndex) {
        val q = uiState.currentQuestion ?: return@LaunchedEffect
        tts.loadAndPlay(q.audioText)
    }

    if (uiState.completed) {
        TutiPracticeResult(
            correct = uiState.correctCount,
            total = total,
            onPrimary = onBack,
            onRestart = onRestart,
            correctLabel = "дуруст навишт",
            wrongLabel = "хато",
            accentColor = c.mango.base,
        )
        return
    }

    if (question == null) return

    val editable = uiState.answerState == WritingAnswerState.TYPING ||
        uiState.answerState == WritingAnswerState.WRONG

    /** Проверка ответа: одна логика для кнопки и клавиши Done. */
    fun submit() {
        if (uiState.typedText.isBlank() || !editable) return
        keyboardController?.hide()
        val isCorrect = onCheckAnswer() ?: return
        val xp = if (uiState.attempts == 0) 5 else 2
        if (isCorrect) {
            TutiSoundManager.playCorrectAnswer()
            context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                .edit()
                .putString(
                    "last_study_date",
                    SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()),
                )
                .apply()
            runCatching {
                val uid = FirebaseAuth.getInstance().currentUser?.uid
                if (uid != null) FirestoreManager.addXp(uid, xp)
            }
        } else {
            TutiSoundManager.playWrongAnswer()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(horizontal = TutiSpace.screen)
            .padding(top = TutiSpace.md, bottom = TutiSpace.lg),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TutiIconButton(
                emoji = "←",
                onClick = { tts.stop(); onBack() },
                size = 40.dp,
                tone = TutiButtonTone.Mango,
            )
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Навиштан",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "${uiState.currentIndex + 1} аз $total",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            TutiPill(
                text = "${uiState.correctCount}",
                leadingEmoji = "✅",
                background = c.leaf.soft,
                contentColor = c.leaf.onSoft,
            )
        }

        Spacer(Modifier.height(TutiSpace.md))

        TutiProgressBar(
            progress = uiState.progress,
            colors = listOf(c.mango.base, c.mango.base.copy(alpha = 0.7f)),
        )

        Spacer(Modifier.height(TutiSpace.xl))

        Text(
            text = "Гӯш кунед ва калимаро нависед",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(TutiSpace.lg))

        WritingAudioButton(
            isLoading = tts.isLoading,
            hasAudio = tts.hasAudio,
            onPlay = { tts.replay() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(Modifier.height(TutiSpace.xl))

        if (uiState.showHint) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(c.mango.soft)
                    .padding(TutiSpace.md),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text("💡", fontSize = 18.sp)
                Spacer(Modifier.width(TutiSpace.sm))
                Text(
                    text = buildHint(question.correctAnswer),
                    style = MaterialTheme.typography.titleLarge,
                    color = c.mango.onSoft,
                    letterSpacing = 3.sp,
                )
            }
            Spacer(Modifier.height(TutiSpace.md))
        }

        OutlinedTextField(
            value = uiState.typedText,
            onValueChange = { if (editable) onTypedTextChange(it) },
            modifier = Modifier.fillMaxWidth(),
            enabled = editable,
            placeholder = {
                Text(
                    text = "Калимаро нависед…",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            textStyle = MaterialTheme.typography.titleLarge,
            singleLine = true,
            shape = RoundedCornerShape(TutiRadius.md),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = c.mango.base,
                unfocusedBorderColor = c.cardBorder,
                cursorColor = c.mango.base,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = c.tileBg,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { submit() }),
        )

        Spacer(Modifier.height(TutiSpace.lg))

        when (uiState.answerState) {
            WritingAnswerState.TYPING -> {
                TutiButton(
                    text = "Санҷидан",
                    onClick = { submit() },
                    tone = TutiButtonTone.Mango,
                    trailingEmoji = "✓",
                    enabled = uiState.typedText.isNotBlank(),
                    playSound = false,
                )
            }

            WritingAnswerState.WRONG -> {
                FeedbackBanner(
                    emoji = "✕",
                    title = "Нодуруст! Боз кӯшиш кунед",
                    subtitle = "Кӯшиши ${uiState.attempts}/$MAX_ATTEMPTS",
                    bgColor = c.wrongBg,
                    textColor = c.wrongText,
                )
                Spacer(Modifier.height(TutiSpace.md))
                TutiButton(
                    text = "Боз кӯшиш",
                    onClick = onRetryAfterWrong,
                    tone = TutiButtonTone.Mango,
                    leadingEmoji = "🔄",
                )
            }

            WritingAnswerState.CORRECT -> {
                FeedbackBanner(
                    emoji = "✓",
                    title = "Офарин! Дуруст!",
                    subtitle = "«${question.correctAnswer}» — ${question.translation}",
                    bgColor = c.correctBg,
                    textColor = c.correctText,
                )
                Spacer(Modifier.height(TutiSpace.md))
                TutiButton(
                    text = if (uiState.currentIndex < total - 1) "Идома" else "Натиҷа",
                    onClick = onAdvance,
                    tone = TutiButtonTone.Leaf,
                    trailingEmoji = "→",
                )
            }

            WritingAnswerState.SHOW_ANSWER -> {
                FeedbackBanner(
                    emoji = "💡",
                    title = "Ҷавоби дуруст: ${question.correctAnswer}",
                    subtitle = "Тарҷума: ${question.translation}",
                    bgColor = c.wrongBg,
                    textColor = c.wrongText,
                )
                Spacer(Modifier.height(TutiSpace.md))
                TutiButton(
                    text = if (uiState.currentIndex < total - 1) "Идома" else "Натиҷа",
                    onClick = onAdvance,
                    tone = TutiButtonTone.Mango,
                    trailingEmoji = "→",
                )
            }
        }

        Spacer(Modifier.weight(1f))
    }
}

// ═══════════════════════════════════════════════════
//  ОБРАТНАЯ СВЯЗЬ
// ═══════════════════════════════════════════════════

@Composable
private fun FeedbackBanner(
    emoji: String,
    title: String,
    subtitle: String,
    bgColor: Color,
    textColor: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TutiRadius.lg))
            .background(bgColor)
            .padding(TutiSpace.lg),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(TutiRadius.sm))
                .background(textColor),
            contentAlignment = Alignment.Center,
        ) {
            Text(emoji, fontSize = 18.sp, color = Color.White)
        }
        Spacer(Modifier.width(TutiSpace.md))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = textColor,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = textColor.copy(alpha = 0.85f),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  КНОПКА ВОСПРОИЗВЕДЕНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun WritingAudioButton(
    isLoading: Boolean,
    hasAudio: Boolean,
    onPlay: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val inf = rememberInfiniteTransition(label = "pulse")
    val pulse by inf.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(tween(650), RepeatMode.Reverse),
        label = "pulse",
    )
    val scale = if (isLoading) pulse else 1f

    Box(
        modifier = modifier
            .size(88.dp)
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .clip(CircleShape)
            .background(Brush.radialGradient(listOf(c.mango.base, c.mango.deep)))
            .clickable(enabled = hasAudio && !isLoading) { onPlay() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = if (isLoading) "⏳" else "🔊",
            fontSize = 34.sp,
        )
    }
}
