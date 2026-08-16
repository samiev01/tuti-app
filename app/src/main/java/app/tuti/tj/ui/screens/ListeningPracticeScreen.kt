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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.audio.rememberTtsPlayer
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.ui.components.PaywallDialog
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiEmptyState
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiLoadingState
import app.tuti.tj.ui.components.kit.TutiOptionButton
import app.tuti.tj.ui.components.kit.TutiOptionState
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiPracticeResult
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSecondaryButton
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import app.tuti.tj.ui.i18n.LocalTutiStrings

// ════════════════════════════════════════════════════════════════
//  МАШҚИ ШУНАВОӢ
//
//  Ведущий элемент — кнопка воспроизведения: крупная, с
//  расходящимися кольцами во время звучания. Всё остальное
//  подчинено ей, потому что задание начинается со слушания,
//  а не с чтения вариантов.
// ════════════════════════════════════════════════════════════════

@Composable
fun ListeningPracticeScreen(
    viewModel: ListeningPracticeViewModel,
    onBack: () -> Unit,
    onGoToLessons: () -> Unit,
    onNavigateToPlus: () -> Unit = {},
) {
    val context = LocalContext.current
    var showPaywall by remember { mutableStateOf(false) }
    val uiState = viewModel.uiState

    if (showPaywall) {
        PaywallDialog(
            onGetPlus = { showPaywall = false; onNavigateToPlus() },
            onDismiss = { showPaywall = false; onBack() },
        )
    }

    if (!uiState.loaded) {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            TutiLoadingState(message = LocalTutiStrings.current.practice.preparingPractice)
        }
        return
    }

    if (!uiState.hasEnoughWords || uiState.questions.isEmpty()) {
        EmptyListeningScreen(
            wordsCount = uiState.wordsCount,
            onBack = onBack,
            onGoToLessons = onGoToLessons,
        )
        return
    }

    if (!FreeLimits.canDoListening(context)) {
        showPaywall = true
        return
    }

    // Лимит списывается за пройденный набор вопросов, а не за открытие
    // экрана: раньше попытка сгорала, даже если пользователь послушал
    // одно слово и вышел. Повтор в этом же заходе бесплатный.
    var limitCounted by remember { mutableStateOf(false) }
    LaunchedEffect(uiState.completed) {
        if (uiState.completed && !limitCounted) {
            limitCounted = true
            runCatching { FreeLimits.incrementCount(context, "listening") }
        }
    }

    ListeningQuiz(
        uiState = uiState,
        onSelect = viewModel::selectOption,
        onAdvance = viewModel::advance,
        onRestart = viewModel::restart,
        onBack = onBack,
    )
}

// ═══════════════════════════════════════════════════
//  ПУСТОЕ СОСТОЯНИЕ
// ═══════════════════════════════════════════════════

@Composable
private fun EmptyListeningScreen(
    wordsCount: Int,
    onBack: () -> Unit,
    onGoToLessons: () -> Unit,
) {
    val strings = LocalTutiStrings.current
    val s = strings.practice
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
            title = s.notEnoughWordsTitle,
            message = if (wordsCount > 0) {
                s.notEnoughWordsMessage(wordsCount, PRACTICE_MIN_WORDS_REQUIRED)
            } else {
                s.learnFirstMessage
            },
            mascotState = TutiState.THINKING,
        )
        Spacer(Modifier.height(TutiSpace.lg))
        TutiButton(
            text = s.toLessons,
            onClick = onGoToLessons,
            tone = TutiButtonTone.Sky,
            leadingEmoji = "📚",
        )
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(
            text = strings.common.back,
            onClick = onBack,
            tone = TutiButtonTone.Sky,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ВОПРОС
// ═══════════════════════════════════════════════════

@Composable
private fun ListeningQuiz(
    uiState: ListeningPracticeUiState,
    onSelect: (Int) -> Boolean?,
    onAdvance: () -> Unit,
    onRestart: () -> Unit,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val s = strings.practice
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
            accentColor = c.sky.base,
            xpEarned = uiState.correctCount * 5,
        )
        return
    }

    if (question == null) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
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
                tone = TutiButtonTone.Sky,
            )
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = s.listeningTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = strings.common.ofCount(uiState.currentIndex + 1, total),
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
            colors = listOf(c.sky.base, c.sky.base.copy(alpha = 0.7f)),
        )

        Spacer(Modifier.height(TutiSpace.xxl))

        Text(
            text = s.listenAndChoose,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(TutiSpace.xl))

        AudioPlayButton(
            isLoading = tts.isLoading,
            hasAudio = tts.hasAudio,
            onPlay = { tts.replay() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(Modifier.height(TutiSpace.xxl))

        Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.md)) {
            question.options.forEachIndexed { index, option ->
                val state = when {
                    uiState.answerState == ListeningAnswerState.NONE -> TutiOptionState.Idle
                    index == question.correctIndex -> TutiOptionState.Correct
                    index == uiState.selectedOption &&
                        uiState.answerState == ListeningAnswerState.WRONG -> TutiOptionState.Wrong
                    else -> TutiOptionState.Idle
                }
                val dimmed = uiState.answerState != ListeningAnswerState.NONE &&
                    state == TutiOptionState.Idle

                Box(
                    modifier = if (dimmed) Modifier.graphicsLayer { alpha = 0.45f } else Modifier,
                ) {
                    TutiOptionButton(
                        text = option,
                        state = state,
                        enabled = uiState.answerState == ListeningAnswerState.NONE,
                        onClick = {
                            val isCorrect = onSelect(index) ?: return@TutiOptionButton
                            if (isCorrect) TutiSoundManager.playCorrectAnswer()
                            else TutiSoundManager.playWrongAnswer()
                            context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                                .edit()
                                .putString(
                                    "last_study_date",
                                    SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()),
                                )
                                .apply()
                            runCatching {
                                val uid = FirebaseAuth.getInstance().currentUser?.uid
                                if (uid != null && isCorrect) FirestoreManager.addXp(uid, 5)
                            }
                        },
                    )
                }
            }
        }

        Spacer(Modifier.weight(1f))

        if (uiState.answerState != ListeningAnswerState.NONE) {
            if (uiState.answerState == ListeningAnswerState.WRONG) {
                Text(
                    text = strings.common
                        .correctAnswer(question.options[question.correctIndex]),
                    style = MaterialTheme.typography.titleMedium,
                    color = c.correctText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(bottom = TutiSpace.md),
                )
            }
            TutiButton(
                text = if (uiState.currentIndex < total - 1) {
                    strings.common.next
                } else {
                    strings.common.result
                },
                onClick = onAdvance,
                tone = TutiButtonTone.Sky,
                trailingEmoji = "→",
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  КНОПКА ВОСПРОИЗВЕДЕНИЯ
//
//  Во время загрузки/звучания от кнопки расходятся два
//  кольца — визуальный эквивалент звука для тех, кто
//  слушает без наушников или в тишине.
// ═══════════════════════════════════════════════════

@Composable
private fun AudioPlayButton(
    isLoading: Boolean,
    hasAudio: Boolean,
    onPlay: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val inf = rememberInfiniteTransition(label = "audio")

    val ring1 by inf.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1600), RepeatMode.Restart),
        label = "ring1",
    )
    val ring2 by inf.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1600, delayMillis = 800), RepeatMode.Restart,
        ),
        label = "ring2",
    )
    val pulse by inf.animateFloat(
        initialValue = 1f, targetValue = 1.08f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label = "pulse",
    )

    Box(
        modifier = modifier.size(168.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            listOf(ring1, ring2).forEach { t ->
                Box(
                    modifier = Modifier
                        .size((96 + t * 68).dp)
                        .clip(CircleShape)
                        .background(c.sky.base.copy(alpha = (1f - t) * 0.22f)),
                )
            }
        }
        Box(
            modifier = Modifier
                .size(104.dp)
                .graphicsLayer {
                    val s = if (isLoading) pulse else 1f
                    scaleX = s
                    scaleY = s
                }
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        listOf(c.sky.base, c.sky.deep),
                    ),
                )
                .clickable(enabled = hasAudio && !isLoading) { onPlay() },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (isLoading) "⏳" else "🔊",
                fontSize = 40.sp,
            )
        }
    }
}
