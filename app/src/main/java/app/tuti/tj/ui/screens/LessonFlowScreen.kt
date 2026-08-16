package app.tuti.tj.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.TutiTipsManager
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.Dialogue
import app.tuti.tj.data.content.DialogueLine
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.data.content.GrammarTip
import app.tuti.tj.data.content.Lesson
import app.tuti.tj.data.content.WordItem
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.ui.components.PaywallDialog
import app.tuti.tj.ui.components.SmartTutiTip
import app.tuti.tj.ui.components.TutiTip
import app.tuti.tj.ui.components.exercises.ExerciseHost
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiDialog
import app.tuti.tj.ui.components.kit.TutiDialogActions
import app.tuti.tj.ui.components.kit.TutiErrorState
import app.tuti.tj.ui.components.kit.TutiGhostButton
import app.tuti.tj.ui.components.kit.TutiHearts
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSecondaryButton
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.TutiWordStyle
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import app.tuti.tj.ui.i18n.LocalTutiStrings

private const val TAG = "LessonFlowScreen"

// ════════════════════════════════════════════════════════════════
//  ПОТОК УРОКА
//
//  Урок — единственное место, где интерфейс работает в режиме
//  «фокус»: нижняя навигация скрыта, на экране только шкала
//  прогресса, сердца и одно задание. Каждая фаза (диалог →
//  слова → грамматика → упражнения) въезжает сбоку, чтобы
//  чувствовалось движение вперёд.
// ════════════════════════════════════════════════════════════════

private enum class LessonPhase { DIALOGUE, WORDS, GRAMMAR, EXERCISES, COMPLETION, FAILED }

@Composable
fun LessonFlowScreen(
    lessonId: String,
    repository: TutiRepository,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    onNavigateToPlus: () -> Unit = {},
) {
    val context = LocalContext.current
    var showPaywall by remember { mutableStateOf(false) }
    var limitChecked by remember { mutableStateOf(false) }

    // Лимит только проверяется на входе, но не списывается: раньше
    // попытка сгорала в момент открытия урока, и пользователь, который
    // вышел на первом задании или случайно нажал не туда, терял её ни
    // за что. Списание перенесено на фактическое завершение урока.
    var limitCounted by remember { mutableStateOf(false) }

    if (!limitChecked) {
        if (!FreeLimits.canDoLesson(context)) showPaywall = true
        limitChecked = true
    }

    if (showPaywall) {
        PaywallDialog(
            onGetPlus = { showPaywall = false; onNavigateToPlus() },
            onDismiss = { showPaywall = false; onBack() },
        )
    }

    val lesson = remember { ContentProvider.getLesson(lessonId) }
    if (lesson == null) {
        Log.e(TAG, "Lesson NOT found for lessonId='$lessonId'")
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            TutiErrorState(
                title = LocalTutiStrings.current.lessonFlow.notFoundTitle,
                message = LocalTutiStrings.current.lessonFlow.notFoundMessage,
                retryText = LocalTutiStrings.current.common.back,
                onRetry = onBack,
            )
        }
        return
    }

    val hasDialogue = lesson.dialogue != null
    val hasGrammar = lesson.grammarTip != null
    val hasExercises = lesson.exercises.isNotEmpty()
    val initialPhase = when {
        hasDialogue -> LessonPhase.DIALOGUE
        lesson.newWords.isNotEmpty() -> LessonPhase.WORDS
        hasGrammar -> LessonPhase.GRAMMAR
        hasExercises -> LessonPhase.EXERCISES
        else -> LessonPhase.COMPLETION
    }

    var phase by remember { mutableStateOf(initialPhase) }
    var exerciseIndex by remember { mutableIntStateOf(0) }
    var hearts by remember { mutableIntStateOf(3) }
    var correctCount by remember { mutableIntStateOf(0) }
    var xpEarned by remember { mutableIntStateOf(0) }
    var showExitDialog by remember { mutableStateOf(false) }
    var showCorrectTip by remember { mutableStateOf(false) }
    var showWrongTip by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Единственное место списания лимита — переход в COMPLETION.
    // Через него проходят все способы дойти до конца, включая урок
    // без упражнений. Провал (FAILED) не списывает: урок не пройден,
    // и повтор не должен стоить ещё одной попытки.
    LaunchedEffect(phase) {
        if (phase == LessonPhase.COMPLETION && !limitCounted) {
            limitCounted = true
            runCatching { FreeLimits.incrementCount(context, "lessons") }
        }
    }

    val totalPhases = listOfNotNull(
        if (hasDialogue) "d" else null,
        if (lesson.newWords.isNotEmpty()) "w" else null,
        if (hasGrammar) "g" else null,
        "e",
    ).size
    val currentPhaseIdx = when (phase) {
        LessonPhase.DIALOGUE -> 0
        LessonPhase.WORDS -> if (hasDialogue) 1 else 0
        LessonPhase.GRAMMAR -> (if (hasDialogue) 1 else 0) +
            (if (lesson.newWords.isNotEmpty()) 1 else 0)
        LessonPhase.EXERCISES, LessonPhase.COMPLETION, LessonPhase.FAILED -> totalPhases - 1
    }
    val exerciseProgress = when {
        phase == LessonPhase.EXERCISES && lesson.exercises.isNotEmpty() ->
            exerciseIndex.toFloat() / lesson.exercises.size
        phase == LessonPhase.COMPLETION -> 1f
        else -> 0f
    }
    val overallProgress = (currentPhaseIdx.toFloat() + exerciseProgress) / totalPhases

    fun advancePhase() {
        phase = when (phase) {
            LessonPhase.DIALOGUE -> when {
                lesson.newWords.isNotEmpty() -> LessonPhase.WORDS
                hasGrammar -> LessonPhase.GRAMMAR
                hasExercises -> LessonPhase.EXERCISES
                else -> LessonPhase.COMPLETION
            }
            LessonPhase.WORDS -> when {
                hasGrammar -> LessonPhase.GRAMMAR
                hasExercises -> LessonPhase.EXERCISES
                else -> LessonPhase.COMPLETION
            }
            LessonPhase.GRAMMAR -> if (hasExercises) LessonPhase.EXERCISES
            else LessonPhase.COMPLETION
            else -> phase
        }
    }

    if (showExitDialog) {
        TutiDialog(
            onDismiss = { showExitDialog = false },
            title = LocalTutiStrings.current.lessonFlow.exitTitle,
            message = LocalTutiStrings.current.lessonFlow.exitMessage,
            mascotState = TutiState.SAD,
            accent = MaterialTheme.tutiColors.coral.base,
        ) {
            TutiDialogActions(
                primaryText = LocalTutiStrings.current.lessonFlow.exitStay,
                onPrimary = { showExitDialog = false },
                secondaryText = LocalTutiStrings.current.lessonFlow.exitConfirm,
                onSecondary = { showExitDialog = false; onBack() },
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        if (phase != LessonPhase.COMPLETION && phase != LessonPhase.FAILED) {
            LessonTopBar(
                progress = overallProgress,
                hearts = hearts,
                onBack = { showExitDialog = true },
            )
        }

        if (showCorrectTip) {
            TutiTip(
                text = LocalTutiStrings.current.lessonFlow.praise,
                onDismiss = { showCorrectTip = false },
            )
        }
        if (showWrongTip) {
            TutiTip(
                text = LocalTutiStrings.current.lessonFlow.encourage,
                onDismiss = { showWrongTip = false },
            )
        }

        AnimatedContent(
            targetState = phase,
            transitionSpec = {
                (slideInHorizontally { it / 3 } + fadeIn()) togetherWith
                    (slideOutHorizontally { -it / 3 } + fadeOut())
            },
            label = "phaseTransition",
            modifier = Modifier.weight(1f),
        ) { currentPhase ->
            when (currentPhase) {
                LessonPhase.DIALOGUE -> {
                    val dialogue = lesson.dialogue
                    if (dialogue != null) {
                        DialoguePhaseUI(dialogue = dialogue, onContinue = { advancePhase() })
                    } else {
                        advancePhase()
                    }
                }

                LessonPhase.WORDS -> WordsPhaseUI(
                    words = lesson.newWords,
                    onContinue = { advancePhase() },
                    onSkip = {
                        phase = if (hasExercises) LessonPhase.EXERCISES else LessonPhase.COMPLETION
                    },
                )

                LessonPhase.GRAMMAR -> {
                    val tip = lesson.grammarTip
                    if (tip != null) {
                        GrammarPhaseUI(tip = tip, onContinue = { advancePhase() })
                    } else {
                        advancePhase()
                    }
                }

                LessonPhase.EXERCISES -> {
                    if (lesson.exercises.isEmpty()) {
                        phase = LessonPhase.COMPLETION
                    } else {
                        val safeIdx = exerciseIndex.coerceIn(0, lesson.exercises.size - 1)
                        if (safeIdx != exerciseIndex) exerciseIndex = safeIdx
                        ExercisePhaseUI(
                            exercises = lesson.exercises,
                            exerciseIndex = safeIdx,
                            onAnswer = { isCorrect ->
                                try {
                                    if (isCorrect) {
                                        correctCount++
                                        xpEarned += 10
                                        if (TutiTipsManager.shouldShowTip(
                                                context,
                                                TutiTipsManager.TIP_FIRST_CORRECT,
                                            )
                                        ) {
                                            showCorrectTip = true
                                            TutiTipsManager.markTipShown(
                                                context,
                                                TutiTipsManager.TIP_FIRST_CORRECT,
                                            )
                                        }
                                    } else {
                                        hearts = (hearts - 1).coerceAtLeast(0)
                                        if (TutiTipsManager.shouldShowTip(
                                                context,
                                                TutiTipsManager.TIP_FIRST_WRONG,
                                            )
                                        ) {
                                            showWrongTip = true
                                            TutiTipsManager.markTipShown(
                                                context,
                                                TutiTipsManager.TIP_FIRST_WRONG,
                                            )
                                        }
                                    }

                                    if (hearts <= 0) {
                                        phase = LessonPhase.FAILED
                                    } else if (exerciseIndex + 1 >= lesson.exercises.size) {
                                        val totalEx = lesson.exercises.size.coerceAtLeast(1)
                                        val score = (correctCount * 100) / totalEx
                                        val stars = when {
                                            score >= 90 -> 3
                                            score >= 70 -> 2
                                            score >= 50 -> 1
                                            else -> 0
                                        }
                                        scope.launch {
                                            try {
                                                repository.saveLessonResult(
                                                    lessonId, stars, score, xpEarned,
                                                )
                                            } catch (e: Exception) {
                                                Log.e(TAG, "saveLessonResult FAILED", e)
                                            }
                                            try {
                                                lesson.newWords.forEach { w ->
                                                    repository.addLearnedWord(
                                                        word = w.word,
                                                        translation = w.translation,
                                                        language = "russian",
                                                        topicId = lessonId,
                                                        isCorrect = true,
                                                    )
                                                }
                                            } catch (e: Exception) {
                                                Log.e(TAG, "addLearnedWord FAILED", e)
                                            }
                                            try {
                                                val prefs = context.getSharedPreferences(
                                                    "tuti_prefs", Context.MODE_PRIVATE,
                                                )
                                                prefs.edit()
                                                    .putString(
                                                        "last_study_date",
                                                        SimpleDateFormat("yyyy-MM-dd", Locale.US)
                                                            .format(Date()),
                                                    )
                                                    .apply()
                                                val wrongAnswers =
                                                    lesson.exercises.size - correctCount
                                                if (wrongAnswers == 0 &&
                                                    lesson.exercises.isNotEmpty()
                                                ) {
                                                    val perfect =
                                                        prefs.getInt("perfect_lessons", 0) + 1
                                                    prefs.edit()
                                                        .putInt("perfect_lessons", perfect).apply()
                                                }
                                                val uid = com.google.firebase.auth.FirebaseAuth
                                                    .getInstance().currentUser?.uid
                                                if (uid != null && xpEarned > 0) {
                                                    app.tuti.tj.data.remote.FirestoreManager
                                                        .addXp(uid, xpEarned)
                                                }
                                            } catch (e: Exception) {
                                                Log.e(TAG, "XP/prefs save FAILED", e)
                                            }
                                            runCatching { CloudSyncManager.saveProgress(context) }
                                        }
                                        phase = LessonPhase.COMPLETION
                                    } else {
                                        exerciseIndex += 1
                                    }
                                } catch (e: Exception) {
                                    Log.e(TAG, "CRASH in onAnswer lambda!", e)
                                    phase = LessonPhase.COMPLETION
                                }
                            },
                        )
                    }
                }

                LessonPhase.COMPLETION -> CompletionPhaseUI(
                    lesson = lesson,
                    correctCount = correctCount,
                    totalExercises = lesson.exercises.size,
                    xpEarned = xpEarned,
                    onContinue = onFinish,
                )

                LessonPhase.FAILED -> FailedPhaseUI(
                    onRestart = {
                        phase = LessonPhase.EXERCISES
                        exerciseIndex = 0
                        hearts = 3
                        correctCount = 0
                        xpEarned = 0
                    },
                    onExit = onBack,
                )
            }
        }
    }
}

// ── Шапка урока ─────────────────────────────────

@Composable
private fun LessonTopBar(progress: Float, hearts: Int, onBack: () -> Unit) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TutiIconButton(
            emoji = "✕",
            onClick = onBack,
            size = 36.dp,
            tone = TutiButtonTone.Coral,
        )
        Spacer(Modifier.width(TutiSpace.md))
        TutiProgressBar(
            progress = progress,
            modifier = Modifier.weight(1f),
            height = TutiSize.progressThick,
            colors = c.progressGradient,
        )
        Spacer(Modifier.width(TutiSpace.md))
        TutiHearts(total = 3, remaining = hearts)
    }
}

// ── Диалог ──────────────────────────────────────

@Composable
private fun DialoguePhaseUI(dialogue: Dialogue, onContinue: () -> Unit) {
    val s = LocalTutiStrings.current.lessonFlow
    var visibleLines by remember { mutableIntStateOf(1) }
    val allShown = visibleLines >= dialogue.lines.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(TutiSpace.screen),
    ) {
        SmartTutiTip(
            tipId = TutiTipsManager.TIP_FIRST_DIALOGUE,
            text = s.dialogueHint,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TutiIconTile(emoji = "💬", background = MaterialTheme.tutiColors.sky.soft)
                Spacer(Modifier.width(TutiSpace.md))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = dialogue.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = s.dialogueTapForTranslation,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Spacer(Modifier.height(TutiSpace.xl))

            dialogue.lines.take(visibleLines).forEachIndexed { idx, line ->
                DialogueLineCard(line = line, isLeft = idx % 2 == 0)
                Spacer(Modifier.height(TutiSpace.md))
            }
        }

        Spacer(Modifier.height(TutiSpace.md))

        TutiButton(
            text = if (!allShown) {
                LocalTutiStrings.current.common.continueShort
            } else {
                s.newWordsButton
            },
            onClick = { if (!allShown) visibleLines++ else onContinue() },
            tone = if (allShown) TutiButtonTone.Jade else TutiButtonTone.Sky,
            trailingEmoji = "→",
        )
    }
}

/**
 * Реплики чередуются по сторонам, как в мессенджере: так видно,
 * что это два разных человека, без подписи ролей на каждой строке.
 */
@Composable
private fun DialogueLineCard(line: DialogueLine, isLeft: Boolean) {
    val c = MaterialTheme.tutiColors
    var showTranslation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isLeft) Alignment.Start else Alignment.End,
    ) {
        Text(
            text = line.speaker,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp, start = 6.dp, end = 6.dp),
        )
        val shape = if (isLeft) {
            RoundedCornerShape(TutiRadius.lg, TutiRadius.lg, TutiRadius.lg, 6.dp)
        } else {
            RoundedCornerShape(TutiRadius.lg, TutiRadius.lg, 6.dp, TutiRadius.lg)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(shape)
                .background(if (isLeft) c.tileBg else c.jade.soft)
                .clickable { showTranslation = !showTranslation }
                .padding(TutiSpace.lg),
        ) {
            Text(
                text = line.text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (showTranslation) {
                Spacer(Modifier.height(TutiSpace.xs))
                Text(
                    text = line.translation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = c.jade.onSoft,
                )
            } else {
                Spacer(Modifier.height(TutiSpace.xs))
                Text(
                    text = LocalTutiStrings.current.lessonFlow.translationArrow,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

// ── Новые слова ─────────────────────────────────

@Composable
private fun WordsPhaseUI(words: List<WordItem>, onContinue: () -> Unit, onSkip: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val s = strings.lessonFlow
    var currentIdx by remember { mutableIntStateOf(0) }
    val word = words.getOrNull(currentIdx)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(TutiSpace.screen),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmartTutiTip(
            tipId = TutiTipsManager.TIP_FIRST_WORDS,
            text = s.learnNewWords,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = s.newWords,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            TutiPill(text = "${currentIdx + 1}/${words.size}")
        }

        Spacer(Modifier.height(TutiSpace.md))

        // Точки-шаги: сколько слов уже посмотрели
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            words.indices.forEach { i ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(TutiRadius.pill))
                        .background(if (i <= currentIdx) c.jade.base else c.progressTrack),
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.xl))

        if (word != null) {
            TutiCard(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = TutiSpace.xxl,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = word.word,
                        style = TutiWordStyle,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = word.pronunciation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp),
                    )

                    Spacer(Modifier.height(TutiSpace.lg))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(TutiRadius.md))
                            .background(c.jade.soft)
                            .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.sm),
                    ) {
                        Text(
                            text = word.translation,
                            style = MaterialTheme.typography.headlineSmall,
                            color = c.jade.onSoft,
                        )
                    }

                    Spacer(Modifier.height(TutiSpace.xl))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(TutiRadius.md))
                            .background(c.tileBg)
                            .padding(TutiSpace.md),
                    ) {
                        Text(
                            text = word.example,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = word.exampleTranslation,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp),
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))

        TutiButton(
            text = if (currentIdx + 1 >= words.size) strings.common.continueShort else s.nextWord,
            onClick = { if (currentIdx + 1 >= words.size) onContinue() else currentIdx++ },
            trailingEmoji = "→",
        )
        Spacer(Modifier.height(TutiSpace.xs))
        TutiGhostButton(text = strings.common.skipArrow, onClick = onSkip)
    }
}

// ── Грамматика ──────────────────────────────────

@Composable
private fun GrammarPhaseUI(tip: GrammarTip, onContinue: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current.lessonFlow

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(TutiSpace.screen),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmartTutiTip(
            tipId = TutiTipsManager.TIP_FIRST_GRAMMAR,
            text = s.grammarHint,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TutiIconTile(emoji = "💡", background = c.grape.soft)
                Spacer(Modifier.width(TutiSpace.md))
                Text(
                    text = s.grammarTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Spacer(Modifier.height(TutiSpace.xl))

            TutiCard(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = TutiSpace.xl,
            ) {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = c.grape.base,
                )
                Spacer(Modifier.height(TutiSpace.md))
                Text(
                    text = tip.explanation,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(TutiSpace.lg))

                // Примеры — отдельными плашками: правило читается,
                // примеры сканируются.
                tip.examples.forEach { example ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = TutiSpace.sm)
                            .clip(RoundedCornerShape(TutiRadius.sm))
                            .background(c.grape.soft)
                            .padding(TutiSpace.md),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("→", color = c.grape.base, fontSize = 14.sp)
                        Spacer(Modifier.width(TutiSpace.sm))
                        Text(
                            text = example,
                            style = MaterialTheme.typography.titleMedium,
                            color = c.grape.onSoft,
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(TutiSpace.lg))

        TutiButton(
            text = s.grammarUnderstood,
            onClick = onContinue,
            tone = TutiButtonTone.Grape,
            trailingEmoji = "✓",
        )
    }
}

// ── Упражнения ──────────────────────────────────

@Composable
private fun ExercisePhaseUI(
    exercises: List<Exercise>,
    exerciseIndex: Int,
    onAnswer: (Boolean) -> Unit,
) {
    val exercise = exercises.getOrNull(exerciseIndex)
    if (exercise == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = LocalTutiStrings.current.lessonFlow.noExerciseTitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        return
    }

    key(exerciseIndex) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(TutiSpace.screen),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (exerciseIndex == 0) {
                SmartTutiTip(
                    tipId = TutiTipsManager.TIP_FIRST_EXERCISE,
                    text = LocalTutiStrings.current.lessonFlow.exerciseHint,
                )
            }

            TutiPill(
                text = LocalTutiStrings.current.lessonFlow
                    .exerciseCounter(exerciseIndex + 1, exercises.size),
            )

            Spacer(Modifier.height(TutiSpace.lg))

            ExerciseHost(exercise = exercise, onAnswer = onAnswer)
        }
    }
}

// ── Завершение ──────────────────────────────────

@Composable
private fun CompletionPhaseUI(
    lesson: Lesson,
    correctCount: Int,
    totalExercises: Int,
    xpEarned: Int,
    onContinue: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val score = if (totalExercises > 0) (correctCount * 100) / totalExercises else 0
    val stars = when {
        score >= 90 -> 3
        score >= 70 -> 2
        score >= 50 -> 1
        else -> 0
    }

    // Звёзды «зажигаются» по одной со звуком — награда
    // растягивается во времени, а не выдаётся мгновенно.
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
            .padding(TutiSpace.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmartTutiTip(
            tipId = TutiTipsManager.TIP_FIRST_COMPLETE,
            text = strings.lessonFlow.congratsFirstLesson,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(TutiSpace.xl))

            TutiMascotVector(
                state = if (stars >= 2) TutiState.CELEBRATE else TutiState.HAPPY,
                modifier = Modifier.size(112.dp),
            )

            Spacer(Modifier.height(TutiSpace.lg))

            Row(horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
                repeat(3) { i ->
                    Text(
                        text = if (i < starsShown) "⭐" else "☆",
                        fontSize = 40.sp,
                    )
                }
            }

            Spacer(Modifier.height(TutiSpace.lg))

            Text(
                text = if (stars >= 2) {
                    strings.common.resultExcellent
                } else {
                    strings.common.resultGood
                },
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = "${lesson.emoji} ${lesson.title}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = TutiSpace.xxl),
                textAlign = TextAlign.Center,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                StatItem(
                    emoji = "✅",
                    value = "$correctCount/$totalExercises",
                    label = strings.lessonFlow.correctLabel,
                    bg = c.correctBg,
                    fg = c.correctText,
                    modifier = Modifier.weight(1f),
                )
                StatItem(
                    emoji = "💎",
                    value = "+$xpEarned",
                    label = strings.common.points,
                    bg = c.grape.soft,
                    fg = c.grape.onSoft,
                    modifier = Modifier.weight(1f),
                )
                StatItem(
                    emoji = "📝",
                    value = "${lesson.newWords.size}",
                    label = strings.lessonFlow.wordLabel,
                    bg = c.sky.soft,
                    fg = c.sky.onSoft,
                    modifier = Modifier.weight(1f),
                )
            }
        }

        TutiButton(
            text = strings.common.continueLong,
            onClick = onContinue,
            trailingEmoji = "→",
        )
    }
}

@Composable
private fun StatItem(
    emoji: String,
    value: String,
    label: String,
    bg: Color,
    fg: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.lg))
            .background(bg)
            .padding(vertical = TutiSpace.md),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(emoji, fontSize = 20.sp)
        Spacer(Modifier.height(TutiSpace.xs))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = fg,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = fg.copy(alpha = 0.8f),
        )
    }
}

// ── Провал ──────────────────────────────────────

@Composable
private fun FailedPhaseUI(onRestart: () -> Unit, onExit: () -> Unit) {
    val strings = LocalTutiStrings.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(TutiSpace.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TutiMascotVector(state = TutiState.SAD, modifier = Modifier.size(112.dp))

        Spacer(Modifier.height(TutiSpace.lg))

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(3) { Text("🖤", fontSize = 24.sp) }
        }

        Spacer(Modifier.height(TutiSpace.lg))

        Text(
            text = strings.lessonFlow.heartsOverTitle,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Text(
            text = strings.lessonFlow.heartsOverMessage,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = TutiSpace.sm, bottom = TutiSpace.xxl),
        )

        TutiButton(text = strings.common.restart, onClick = onRestart, leadingEmoji = "🔄")
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(text = strings.common.exit, onClick = onExit)
    }
}
