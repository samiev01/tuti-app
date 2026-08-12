package app.tuti.tj.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.audio.rememberTtsPlayer
import app.tuti.tj.data.content.QuestionType
import app.tuti.tj.ui.components.exercises.ExerciseChoice
import app.tuti.tj.ui.components.exercises.choiceStateOf
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiEmptyState
import app.tuti.tj.ui.components.kit.TutiHearts
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSecondaryButton
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

private const val XP_PER_CORRECT = 10

// ════════════════════════════════════════════════════════════════
//  КВИЗ ПО СВОБОДНОЙ ТЕМЕ
//
//  Маскот здесь — участник: он реагирует на выбор ещё до
//  проверки (задумывается), радуется верному ответу и
//  сочувствует ошибке. Реплика подана в «облачке» с хвостиком,
//  направленным на него, поэтому вопрос читается как заданный
//  вслух.
// ════════════════════════════════════════════════════════════════

@Composable
fun LessonsScreen(
    viewModel: LessonsViewModel,
    onFinish: () -> Unit,
    onTryAgain: () -> Unit,
) {
    val uiState = viewModel.uiState
    val questions = uiState.questions
    val topicInfo = uiState.topicInfo

    if (questions.isEmpty()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            TutiEmptyState(
                title = "Саволҳо ёфт нашуданд",
                message = "Ин мавзӯъ ҳоло саволҳо надорад.",
                actionText = "← Бозгашт",
                onAction = onFinish,
            )
        }
        return
    }

    if (uiState.completed) {
        CompletionScreen(
            correctCount = uiState.correctCount,
            totalCount = questions.size,
            topicName = topicInfo?.name ?: "",
            topicEmoji = topicInfo?.emoji ?: "",
            onBackHome = onFinish,
            onRestart = onTryAgain,
        )
        return
    }

    val question = uiState.currentQuestion ?: return

    val tts = rememberTtsPlayer()
    val ttsText = remember(question) {
        if (question.type == QuestionType.LISTEN) question.hint.replace("🔊", "").trim() else null
    }

    LaunchedEffect(ttsText) {
        if (ttsText != null) tts.loadAndPlay(ttsText)
    }

    val mascotState = when {
        uiState.checked && uiState.isCorrect -> TutiState.CELEBRATE
        uiState.checked && !uiState.isCorrect -> TutiState.SAD
        uiState.hasSelection -> TutiState.THINKING
        else -> TutiState.HAPPY
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = TutiSpace.screen)
            .padding(top = TutiSpace.md, bottom = TutiSpace.sm),
    ) {
        ProgressHeader(
            current = uiState.currentQ + 1,
            total = questions.size,
            progress = uiState.progress,
            hearts = uiState.hearts,
        )

        Spacer(Modifier.height(TutiSpace.md))

        QuestionTypeBadge(question.type)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(TutiSpace.md))

            TutiMascotVector(state = mascotState, modifier = Modifier.size(72.dp))

            if (question.type == QuestionType.LISTEN) {
                Spacer(Modifier.height(TutiSpace.md))
                ListenButton(
                    isLoading = tts.isLoading,
                    onPlay = { tts.replay(fallbackText = ttsText ?: "") },
                )
            }

            Spacer(Modifier.height(TutiSpace.sm))

            SpeechBubble(prompt = question.prompt, hint = question.hint)

            Spacer(Modifier.height(TutiSpace.lg))

            AnswerGrid(
                options = question.options,
                selected = uiState.selected,
                checked = uiState.checked,
                correctIndex = question.correctIndex,
                onSelect = {
                    if (!uiState.checked) {
                        viewModel.selectAnswer(it)
                        TutiSoundManager.playSelectOption()
                    }
                },
            )

            Spacer(Modifier.height(TutiSpace.md))

            AnimatedVisibility(
                visible = uiState.checked,
                enter = fadeIn(tween(300)) + slideInVertically(tween(350)) { it / 3 },
            ) {
                FeedbackCard(
                    isCorrect = uiState.isCorrect,
                    correctAnswer = question.options[question.correctIndex],
                    explanation = question.explanation,
                )
            }

            Spacer(Modifier.height(TutiSpace.lg))
        }

        TutiButton(
            text = if (uiState.checked) "Давом додан" else "Санҷидан",
            onClick = {
                if (uiState.checked) {
                    viewModel.continueQuiz()
                } else {
                    val correct = uiState.isCorrect
                    viewModel.checkAnswer()
                    if (correct) TutiSoundManager.playCorrectAnswer()
                    else TutiSoundManager.playWrongAnswer()
                }
            },
            enabled = uiState.hasSelection || uiState.checked,
            tone = when {
                uiState.checked && uiState.isCorrect -> TutiButtonTone.Leaf
                uiState.checked -> TutiButtonTone.Mango
                else -> TutiButtonTone.Jade
            },
            trailingEmoji = if (uiState.checked) "→" else "✓",
            playSound = false,
        )

        Spacer(Modifier.height(TutiSpace.xs))
    }
}

// ═══════════════════════════════════════════════════
//  ТИП ВОПРОСА
// ═══════════════════════════════════════════════════

@Composable
private fun QuestionTypeBadge(type: QuestionType) {
    val c = MaterialTheme.tutiColors
    val (label, accent) = when (type) {
        QuestionType.TRANSLATE -> "Тарҷума" to c.jade
        QuestionType.CHOOSE_TRANSLATION -> "Интихоб" to c.leaf
        QuestionType.FILL_BLANK -> "Пур кунед" to c.mango
        QuestionType.MATCH_WORD -> "Мувофиқ" to c.sky
        QuestionType.LISTEN -> "Гӯш кунед" to c.grape
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TutiPill(
            text = label,
            background = accent.soft,
            contentColor = accent.onSoft,
            leadingEmoji = if (type == QuestionType.LISTEN) "🔊" else null,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ШАПКА ПРОГРЕССА
// ═══════════════════════════════════════════════════

@Composable
private fun ProgressHeader(current: Int, total: Int, progress: Float, hearts: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$current/$total",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.width(TutiSpace.md))
        TutiProgressBar(
            progress = progress,
            modifier = Modifier.weight(1f),
            height = TutiSize.progressThick,
        )
        Spacer(Modifier.width(TutiSpace.md))
        TutiHearts(total = 3, remaining = hearts)
    }
}

// ═══════════════════════════════════════════════════
//  ОБЛАЧКО РЕПЛИКИ
// ═══════════════════════════════════════════════════

@Composable
private fun SpeechBubble(prompt: String, hint: String) {
    val c = MaterialTheme.tutiColors
    val surfaceColor = MaterialTheme.colorScheme.surface
    val borderColor = c.cardBorder

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Хвостик облачка указывает на маскота — реплика
        // читается как сказанная им.
        Canvas(modifier = Modifier.size(22.dp, 11.dp)) {
            val fill = Path().apply {
                moveTo(size.width / 2, 0f)
                lineTo(0f, size.height)
                lineTo(size.width, size.height)
                close()
            }
            drawPath(fill, surfaceColor)

            val outline = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width / 2, 0f)
                lineTo(size.width, size.height)
            }
            drawPath(outline, borderColor, style = Stroke(width = 1.5.dp.toPx()))
        }

        TutiCard(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-1).dp),
            contentPadding = TutiSpace.xl,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = prompt,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(TutiSpace.sm))
                Text(
                    text = hint,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  ВАРИАНТЫ ОТВЕТА
// ═══════════════════════════════════════════════════

@Composable
private fun AnswerGrid(
    options: List<String>,
    selected: Int,
    checked: Boolean,
    correctIndex: Int,
    onSelect: (Int) -> Unit,
) {
    val answered: Boolean? = if (checked) selected == correctIndex else null

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
    ) {
        options.chunked(2).forEachIndexed { rowIdx, rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                rowOptions.forEachIndexed { colIdx, option ->
                    val idx = rowIdx * 2 + colIdx
                    ExerciseChoice(
                        text = option,
                        state = choiceStateOf(
                            isSelected = selected == idx,
                            isCorrect = correctIndex == idx,
                            answeredCorrectly = answered,
                        ),
                        enabled = !checked,
                        onClick = { onSelect(idx) },
                        modifier = Modifier.weight(1f),
                        minHeight = 64.dp,
                    )
                }
                if (rowOptions.size < 2) Spacer(Modifier.weight(1f))
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  ОБРАТНАЯ СВЯЗЬ
// ═══════════════════════════════════════════════════

@Composable
private fun FeedbackCard(isCorrect: Boolean, correctAnswer: String, explanation: String) {
    val c = MaterialTheme.tutiColors
    val bg = if (isCorrect) c.correctBg else c.wrongBg
    val fg = if (isCorrect) c.correctText else c.wrongText

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(bg)
            .padding(TutiSpace.lg),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(fg),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (isCorrect) "✓" else "✕",
                fontSize = 18.sp,
                color = Color.White,
            )
        }
        Spacer(Modifier.width(TutiSpace.md))
        Column {
            Text(
                text = if (isCorrect) "Офарин! Дуруст!" else "Нодуруст…",
                style = MaterialTheme.typography.titleMedium,
                color = fg,
            )
            Text(
                text = if (isCorrect) "+$XP_PER_CORRECT очки"
                else "Ҷавоби дуруст: $correctAnswer",
                style = MaterialTheme.typography.titleSmall,
                color = fg.copy(alpha = 0.85f),
            )
            if (explanation.isNotBlank()) {
                Spacer(Modifier.height(TutiSpace.xs))
                Text(
                    text = explanation,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  ИТОГ
// ═══════════════════════════════════════════════════

@Composable
private fun CompletionScreen(
    correctCount: Int,
    totalCount: Int,
    topicName: String,
    topicEmoji: String,
    onBackHome: () -> Unit,
    onRestart: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val stars = when {
        correctCount >= 9 -> 3
        correctCount >= 7 -> 2
        correctCount >= 5 -> 1
        else -> 0
    }
    val xpEarned = correctCount * XP_PER_CORRECT
    val percent = if (totalCount > 0) correctCount * 100 / totalCount else 0

    var starsShown by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        TutiSoundManager.playLessonComplete()
        kotlinx.coroutines.delay(500)
        repeat(stars) {
            kotlinx.coroutines.delay(350)
            starsShown += 1
            TutiSoundManager.playStarEarned()
        }
        kotlinx.coroutines.delay(300)
        TutiSoundManager.playXpEarned()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(TutiSpace.xxl),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TutiMascotVector(
            state = when {
                correctCount >= 8 -> TutiState.CELEBRATE
                correctCount >= 5 -> TutiState.HAPPY
                else -> TutiState.SAD
            },
            modifier = Modifier.size(104.dp),
        )

        Spacer(Modifier.height(TutiSpace.lg))

        Row(horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
            repeat(3) { i ->
                Text(text = if (i < starsShown) "⭐" else "☆", fontSize = 36.sp)
            }
        }

        Spacer(Modifier.height(TutiSpace.lg))

        Text(
            text = when {
                correctCount >= 8 -> "Аъло! 🎉"
                correctCount >= 5 -> "Хуб! 👍"
                else -> "Кӯшиш кунед! 💪"
            },
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(TutiSpace.sm))

        TutiPill(
            text = "$topicEmoji $topicName",
            background = c.jade.soft,
            contentColor = c.jade.onSoft,
        )

        Spacer(Modifier.height(TutiSpace.xl))

        TutiCard(modifier = Modifier.fillMaxWidth(), contentPadding = TutiSpace.xl) {
            Text(
                text = "Шумо $correctCount аз $totalCount дуруст ҷавоб додед!",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(TutiSpace.lg))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                StatPill("💎", "+$xpEarned", "очки", c.grape.soft, c.grape.onSoft, Modifier.weight(1f))
                StatPill("⭐", "$stars/3", "ситора", c.mango.soft, c.mango.onSoft, Modifier.weight(1f))
                StatPill("🎯", "$percent%", "дақиқӣ", c.jade.soft, c.jade.onSoft, Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(TutiSpace.xxl))

        TutiButton(text = "Ба асосӣ", onClick = onBackHome, leadingEmoji = "🏠")
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(text = "Аз нав", onClick = onRestart, leadingEmoji = "🔄")
    }
}

@Composable
private fun StatPill(
    emoji: String,
    value: String,
    label: String,
    bg: Color,
    fg: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .padding(vertical = TutiSpace.md),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(emoji, fontSize = 18.sp)
        Spacer(Modifier.height(TutiSpace.xs))
        Text(text = value, style = MaterialTheme.typography.titleLarge, color = fg)
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = fg.copy(alpha = 0.8f),
        )
    }
}

// ═══════════════════════════════════════════════════
//  КНОПКА ПРОСЛУШИВАНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun ListenButton(isLoading: Boolean, onPlay: () -> Unit) {
    val c = MaterialTheme.tutiColors
    Box(
        modifier = Modifier
            .size(68.dp)
            .clip(CircleShape)
            .background(
                if (isLoading) Brush.radialGradient(listOf(c.progressTrack, c.progressTrack))
                else Brush.radialGradient(listOf(c.grape.base, c.grape.deep)),
            )
            .clickable(enabled = !isLoading) { onPlay() },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = if (isLoading) "⏳" else "🔊", fontSize = 28.sp)
    }
}
