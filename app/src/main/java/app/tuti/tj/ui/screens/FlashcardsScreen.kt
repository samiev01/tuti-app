package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.TutiTipsManager
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.local.entity.LearnedWordEntity
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.ui.components.PaywallDialog
import app.tuti.tj.ui.components.SmartTutiTip
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiEmptyState
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiLoadingState
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiRingProgress
import app.tuti.tj.ui.components.kit.TutiSecondaryButton
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.TutiWordStyle
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.TutiStrings
import app.tuti.tj.ui.i18n.localizedName

// ════════════════════════════════════════════════════════════════
//  ФЛЕШ-КАРТЫ
//
//  Карточка — главный объект экрана, поэтому она занимает всё
//  свободное место и переворачивается настоящим 3D-поворотом.
//  Ответы «знаю / не знаю» окрашены в семантические цвета
//  системы, а не в произвольные зелёный и красный.
// ════════════════════════════════════════════════════════════════

private fun topicLabel(topicId: String, strings: TutiStrings): String {
    val info = ContentProvider.getTopicInfo(topicId)
    return if (info != null) "${info.emoji} ${info.localizedName(strings)}" else topicId
}

@Composable
fun FlashcardsScreen(
    repository: TutiRepository,
    onBack: () -> Unit,
    onGoToLessons: () -> Unit,
    onNavigateToPlus: () -> Unit = {},
) {
    val context = LocalContext.current
    var showPaywall by remember { mutableStateOf(false) }
    var deck by remember { mutableStateOf<List<LearnedWordEntity>>(emptyList()) }
    var loaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        deck = repository.getFlashcardDeck()
        loaded = true
    }

    if (showPaywall) {
        PaywallDialog(
            onGetPlus = { showPaywall = false; onNavigateToPlus() },
            onDismiss = { showPaywall = false; onBack() },
        )
    }

    if (!loaded) {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            TutiLoadingState(message = LocalTutiStrings.current.practice.preparingCards)
        }
        return
    }

    if (deck.isEmpty()) {
        EmptyDeckScreen(onBack = onBack, onGoToLessons = onGoToLessons)
        return
    }

    if (!FreeLimits.canDoFlashcards(context)) {
        showPaywall = true
        return
    }

    // Лимит списывается за пройденную колоду, а не за открытие экрана:
    // раньше попытка сгорала, даже если пользователь посмотрел одну
    // карточку и вышел. Повтор колоды в этом же заходе бесплатный —
    // флаг не даёт списать дважды.
    var limitCounted by remember { mutableStateOf(false) }

    FlashcardReview(
        initialDeck = deck,
        repository = repository,
        onBack = onBack,
        onDeckCompleted = {
            if (!limitCounted) {
                limitCounted = true
                runCatching { FreeLimits.incrementCount(context, "flashcards") }
            }
        },
    )
}

// ═══════════════════════════════════════════════════
//  ПУСТАЯ КОЛОДА
// ═══════════════════════════════════════════════════

@Composable
private fun EmptyDeckScreen(onBack: () -> Unit, onGoToLessons: () -> Unit) {
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
            title = s.learnFirstTitle,
            message = s.learnFirstMessage,
            mascotState = TutiState.THINKING,
        )
        Spacer(Modifier.height(TutiSpace.lg))
        TutiButton(
            text = s.toLessons,
            onClick = onGoToLessons,
            leadingEmoji = "📚",
        )
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(text = strings.common.back, onClick = onBack)
    }
}

// ═══════════════════════════════════════════════════
//  ПОВТОРЕНИЕ
// ═══════════════════════════════════════════════════

@Composable
private fun FlashcardReview(
    initialDeck: List<LearnedWordEntity>,
    repository: TutiRepository,
    onBack: () -> Unit,
    onDeckCompleted: () -> Unit,
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val s = strings.practice
    val workingDeck = remember { initialDeck.toMutableList() }
    var currentIndex by remember { mutableIntStateOf(0) }
    var isFlipped by remember { mutableStateOf(false) }
    var knownCount by remember { mutableIntStateOf(0) }
    var reviewedCount by remember { mutableIntStateOf(0) }
    var completed by remember { mutableStateOf(false) }

    val rotation = remember { Animatable(0f) }
    val slideX = remember { Animatable(0f) }
    val cardAlpha = remember { Animatable(1f) }

    val scope = rememberCoroutineScope()

    fun flipCard() {
        if (completed) return
        TutiSoundManager.playCardFlip()
        scope.launch {
            val target = if (rotation.value < 90f) 180f else 0f
            rotation.animateTo(target, tween(420, easing = FastOutSlowInEasing))
            isFlipped = target == 180f
        }
    }

    fun advanceCard(isKnown: Boolean) {
        val card = workingDeck.getOrNull(currentIndex) ?: return
        if (isKnown) TutiSoundManager.playCorrectAnswer() else TutiSoundManager.playWrongAnswer()
        scope.launch {
            if (isKnown) {
                repository.markWordKnown(card)
                knownCount++
            } else {
                repository.markWordUnknown(card)
                workingDeck.add(card)
            }
            reviewedCount++
            context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                .edit()
                .putString(
                    "last_study_date",
                    SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()),
                )
                .apply()
            runCatching {
                val uid = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
                if (uid != null) app.tuti.tj.data.remote.FirestoreManager.addXp(uid, 2)
            }

            // Известное уезжает влево, неизвестное — вправо:
            // направление само по себе подкрепляет выбор.
            val slideDir = if (isKnown) -1200f else 1200f
            launch { slideX.animateTo(slideDir, tween(280, easing = FastOutSlowInEasing)) }
            launch { cardAlpha.animateTo(0f, tween(250, easing = FastOutSlowInEasing)) }
            kotlinx.coroutines.delay(300)

            val next = currentIndex + 1
            if (next >= workingDeck.size) {
                completed = true
                scope.launch { runCatching { CloudSyncManager.saveProgress(context) } }
            } else {
                isFlipped = false
                rotation.snapTo(0f)
                slideX.snapTo(slideDir * -0.3f)
                cardAlpha.snapTo(0f)
                currentIndex = next
                launch { slideX.animateTo(0f, tween(280, easing = FastOutSlowInEasing)) }
                launch { cardAlpha.animateTo(1f, tween(280, easing = FastOutSlowInEasing)) }
            }
        }
    }

    LaunchedEffect(completed) {
        if (completed) onDeckCompleted()
    }

    if (completed) {
        CompletionScreen(
            totalReviewed = reviewedCount,
            knownCount = knownCount,
            onRestart = {
                workingDeck.clear()
                workingDeck.addAll(initialDeck)
                currentIndex = 0
                isFlipped = false
                knownCount = 0
                reviewedCount = 0
                completed = false
                scope.launch {
                    rotation.snapTo(0f)
                    slideX.snapTo(0f)
                    cardAlpha.snapTo(1f)
                }
            },
            onBack = onBack,
        )
        return
    }

    val card = workingDeck.getOrNull(currentIndex) ?: return
    val totalInDeck = workingDeck.size
    val progress = currentIndex.toFloat() / totalInDeck

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = TutiSpace.screen)
            .padding(top = TutiSpace.md, bottom = TutiSpace.lg),
    ) {
        SmartTutiTip(
            tipId = TutiTipsManager.TIP_FIRST_FLASHCARD,
            text = s.flashcardsHint,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TutiIconButton(emoji = "←", onClick = onBack, size = 40.dp)
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = s.flashcardsTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = strings.common.ofCount(currentIndex + 1, totalInDeck),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            TutiPill(
                text = "$knownCount",
                leadingEmoji = "✅",
                background = c.leaf.soft,
                contentColor = c.leaf.onSoft,
            )
        }

        Spacer(Modifier.height(TutiSpace.md))

        TutiProgressBar(progress = progress)

        Spacer(Modifier.height(TutiSpace.lg))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .graphicsLayer {
                    translationX = slideX.value
                    alpha = cardAlpha.value
                },
            contentAlignment = Alignment.Center,
        ) {
            FlashCard(
                word = card,
                rotation = rotation.value,
                isFlipped = isFlipped,
                onFlip = { flipCard() },
            )
        }

        Spacer(Modifier.height(TutiSpace.lg))

        if (isFlipped) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
            ) {
                TutiButton(
                    text = s.dontKnow,
                    onClick = { advanceCard(false) },
                    tone = TutiButtonTone.Coral,
                    leadingEmoji = "✕",
                    modifier = Modifier.weight(1f),
                    playSound = false,
                )
                TutiButton(
                    text = s.know,
                    onClick = { advanceCard(true) },
                    tone = TutiButtonTone.Leaf,
                    leadingEmoji = "✓",
                    modifier = Modifier.weight(1f),
                    playSound = false,
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(TutiRadius.lg))
                    .background(c.jade.soft)
                    .clickable { flipCard() }
                    .padding(vertical = TutiSpace.lg),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = s.tapForTranslation,
                    style = MaterialTheme.typography.labelLarge,
                    color = c.jade.onSoft,
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  КАРТОЧКА (3D-переворот)
// ═══════════════════════════════════════════════════

@Composable
private fun FlashCard(
    word: LearnedWordEntity,
    rotation: Float,
    isFlipped: Boolean,
    onFlip: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.xxl)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(2.dp, if (isFlipped) c.jade.base.copy(alpha = 0.4f) else c.cardBorder, shape)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clickable { onFlip() }
            .padding(TutiSpace.xxl),
        contentAlignment = Alignment.Center,
    ) {
        if (!isFlipped) {
            CardFront(word = word)
        } else {
            // Обратная сторона зеркалится поворотом контейнера,
            // поэтому её содержимое разворачиваем назад.
            Box(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
                CardBack(word = word)
            }
        }
    }
}

@Composable
private fun CardFront(word: LearnedWordEntity) {
    val c = MaterialTheme.tutiColors
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TutiPill(text = topicLabel(word.topicId, LocalTutiStrings.current))
        }

        Spacer(Modifier.height(TutiSpace.xl))

        Text(
            text = word.word,
            style = TutiWordStyle,
            fontSize = 38.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.md))

        // Пять точек — сколько раз слово уже вспомнили верно.
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            repeat(5) { i ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(TutiRadius.pill))
                        .background(if (i < word.correctCount) c.jade.base else c.progressTrack),
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.xxxl))

        Text(
            text = LocalTutiStrings.current.practice.tapForTranslation,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun CardBack(word: LearnedWordEntity) {
    val c = MaterialTheme.tutiColors
    val contentItem = remember(word.topicId, word.word) {
        ContentProvider.getWordsForTopic(word.topicId).find { it.word == word.word }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = word.translation,
            style = TutiWordStyle,
            color = c.jade.base,
            textAlign = TextAlign.Center,
        )

        if (contentItem != null) {
            Spacer(Modifier.height(TutiSpace.lg))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(1.dp)
                    .background(c.divider),
            )
            Spacer(Modifier.height(TutiSpace.lg))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(c.tileBg)
                    .padding(TutiSpace.md),
            ) {
                Text(
                    text = contentItem.example,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(TutiSpace.xs))
                Text(
                    text = contentItem.exampleTranslation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
    totalReviewed: Int,
    knownCount: Int,
    onRestart: () -> Unit,
    onBack: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val unknownCount = totalReviewed - knownCount
    val percent = if (totalReviewed > 0) (knownCount * 100 / totalReviewed) else 0

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
            state = if (percent >= 70) TutiState.CELEBRATE else TutiState.HAPPY,
            modifier = Modifier.size(96.dp),
        )

        Spacer(Modifier.height(TutiSpace.lg))

        Text(
            text = when {
                percent >= 80 -> strings.common.resultExcellent
                percent >= 60 -> strings.common.resultGood
                else -> strings.common.resultTryHarder
            },
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(TutiSpace.xl))

        TutiRingProgress(
            progress = percent / 100f,
            size = 132.dp,
            stroke = 12.dp,
            color = c.jade.base,
            centerLabel = "$percent%",
        )

        Spacer(Modifier.height(TutiSpace.xl))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            StatCard(
                emoji = "✅",
                value = "$knownCount",
                label = strings.practice.knownWords,
                bg = c.correctBg,
                fg = c.correctText,
                modifier = Modifier.weight(1f),
            )
            StatCard(
                emoji = "🔁",
                value = "$unknownCount",
                label = strings.practice.needRepeat,
                bg = c.wrongBg,
                fg = c.wrongText,
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(Modifier.height(TutiSpace.xxl))

        TutiButton(text = strings.common.toHome, onClick = onBack, leadingEmoji = "🏠")
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(text = strings.common.restart, onClick = onRestart, leadingEmoji = "🔄")
    }
}

@Composable
private fun StatCard(
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
            .padding(TutiSpace.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(emoji, fontSize = 24.sp)
        Spacer(Modifier.height(TutiSpace.xs))
        Text(
            text = value,
            style = MaterialTheme.typography.displaySmall,
            fontSize = 26.sp,
            color = fg,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = fg.copy(alpha = 0.85f),
            textAlign = TextAlign.Center,
        )
    }
}
