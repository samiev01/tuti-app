package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.TutiTipsManager
import app.tuti.tj.data.subscription.FreeLimits
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.PaywallDialog
import app.tuti.tj.ui.components.SmartTutiTip
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ════════════════════════════════════════════════════════════════
//  ЧАТ С AI-НАСТАВНИКОМ
//
//  Это разговор, а не форма. Отсюда решения: пузыри с «хвостом»
//  к своему автору, маскот рядом с каждым ответом Tuti, живой
//  индикатор набора и подсказки-затравки на пустом экране —
//  начать разговор должно быть проще, чем закрыть его.
// ════════════════════════════════════════════════════════════════

@Composable
fun TutiChatScreen(
    onBack: () -> Unit,
    viewModel: TutiChatViewModel = viewModel(),
    onNavigateToPlus: () -> Unit = {},
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    var showPaywall by remember { mutableStateOf(false) }
    val messages = viewModel.messages
    val inputText = viewModel.inputText
    val isLoading = viewModel.isLoading
    val listState = rememberLazyListState()
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(messages.size) {
        val last = messages.lastOrNull()
        if (last != null && !last.isUser && !last.isError) {
            runCatching { TutiSoundManager.playSelectOption() }
        }
    }

    LaunchedEffect(messages.size, isLoading) {
        delay(32)
        val lastIndex = when {
            messages.isEmpty() && !isLoading -> 0
            isLoading -> messages.size
            else -> (messages.size - 1).coerceAtLeast(0)
        }
        runCatching { listState.scrollToItem(lastIndex) }
    }

    val layoutItemCount = listState.layoutInfo.totalItemsCount
    LaunchedEffect(layoutItemCount) {
        if (layoutItemCount > 0) {
            runCatching { listState.scrollToItem(layoutItemCount - 1) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding(),
    ) {
        ChatTopBar(isLoading = isLoading, onBack = onBack)

        SmartTutiTip(
            tipId = TutiTipsManager.TIP_FIRST_CHAT,
            text = "Бо ман гап занед! Ба русӣ ё тоҷикӣ нависед — ман кӯмак мекунам! 🦜",
        )

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = TutiSpace.md,
                vertical = TutiSpace.md,
            ),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            if (messages.isEmpty() && !isLoading) {
                item(key = "welcome") {
                    WelcomeBlock(
                        onChip = { chip ->
                            runCatching { TutiSoundManager.playButtonClick() }
                            keyboard?.hide()
                            viewModel.sendFromChip(chip)
                        },
                    )
                }
            } else {
                items(items = messages, key = { it.id }) { msg ->
                    ChatMessageBubble(
                        message = msg,
                        onRetry = {
                            runCatching { TutiSoundManager.playButtonClick() }
                            viewModel.retryAfterError(msg.id)
                        },
                    )
                }
                if (isLoading) {
                    item(key = "typing") {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            TypingIndicatorBubble(background = c.messageBg)
                        }
                    }
                }
            }
        }

        ChatInputBar(
            inputText = inputText,
            isLoading = isLoading,
            onInputChange = viewModel::updateInput,
            onSend = {
                if (inputText.isBlank() || isLoading) return@ChatInputBar
                if (!FreeLimits.canSendChatMessage(context)) {
                    showPaywall = true
                    return@ChatInputBar
                }
                FreeLimits.incrementCount(context, "chat")
                runCatching { TutiSoundManager.playButtonClick() }
                keyboard?.hide()
                context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                    .edit()
                    .putString(
                        "last_study_date",
                        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()),
                    )
                    .apply()
                viewModel.sendCurrentInput()
                runCatching {
                    val uid = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
                    val chatPrefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                    val prev = chatPrefs.getInt(
                        "total_chat_messages",
                        chatPrefs.getInt("chat_count", 0),
                    )
                    chatPrefs.edit().putInt("total_chat_messages", prev + 1).apply()
                    if (uid != null) app.tuti.tj.data.remote.FirestoreManager.addXp(uid, 5)
                }
            },
        )

        if (showPaywall) {
            PaywallDialog(
                onGetPlus = { showPaywall = false; onNavigateToPlus() },
                onDismiss = { showPaywall = false },
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  ШАПКА
// ═══════════════════════════════════════════════════

@Composable
private fun ChatTopBar(isLoading: Boolean, onBack: () -> Unit) {
    val c = MaterialTheme.tutiColors

    // Пока Tuti «думает», маскот покачивается — статус виден
    // без чтения подписи.
    val thinkingOffset by animateFloatAsState(
        targetValue = if (isLoading) 3f else 0f,
        animationSpec = tween(400, easing = FastOutSlowInEasing),
        label = "thinkingOffset",
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .statusBarsPadding()
                .padding(horizontal = TutiSpace.md, vertical = TutiSpace.sm),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TutiIconButton(emoji = "←", onClick = onBack, size = 40.dp)
            Spacer(Modifier.width(TutiSpace.sm))
            // Без круглой подложки: маскот рисуется крупнее своего
            // параметра size, и в круге ему обрезало бы хохолок и крылья.
            LivingTutiMascot(
                size = 22.dp,
                modifier = Modifier.graphicsLayer { translationY = thinkingOffset },
            )
            Spacer(Modifier.width(TutiSpace.sm))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Муаллими Tuti",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(if (isLoading) c.mango.base else c.leaf.base),
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = if (isLoading) "менависад…" else "онлайн",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isLoading) c.mango.onSoft else c.leaf.onSoft,
                    )
                }
            }
            TutiPill(
                text = "AI",
                background = c.jade.soft,
                contentColor = c.jade.onSoft,
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(c.cardBorder),
        )
    }
}

// ═══════════════════════════════════════════════════
//  ПУСТОЙ ЭКРАН — ПРИГЛАШЕНИЕ К РАЗГОВОРУ
// ═══════════════════════════════════════════════════

@Composable
private fun WelcomeBlock(onChip: (String) -> Unit) {
    val c = MaterialTheme.tutiColors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = TutiSpace.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LivingTutiMascot(size = 96.dp)
        Spacer(Modifier.height(TutiSpace.lg))
        Text(
            text = "Салом! Ман Tuti ҳастам 🦜",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(TutiSpace.sm))
        Text(
            text = "Бо ман гап занед! Ман кӯмак мекунам ва хатоҳоро ислоҳ мекунам.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = TutiSpace.lg),
        )
        Spacer(Modifier.height(TutiSpace.xl))

        // Затравки: три готовые реплики снимают страх «с чего начать»
        val chips = listOf(
            Triple("👋", "Привет, как дела?", c.jade),
            Triple("🏪", "Дар мағоза чӣ гӯям?", c.sky),
            Triple("📝", "Грамматикаро шарҳ деҳ", c.grape),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            modifier = Modifier.fillMaxWidth().padding(horizontal = TutiSpace.sm),
        ) {
            chips.forEach { (emoji, label, accent) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(TutiRadius.lg))
                        .background(accent.soft)
                        .border(1.dp, accent.base.copy(alpha = 0.25f), RoundedCornerShape(TutiRadius.lg))
                        .clickable { onChip("$emoji $label") }
                        .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.md),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(emoji, fontSize = 18.sp)
                    Spacer(Modifier.width(TutiSpace.md))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = accent.onSoft,
                        modifier = Modifier.weight(1f),
                    )
                    Text("→", fontSize = 16.sp, color = accent.base)
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  ПУЗЫРЬ СООБЩЕНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun ChatMessageBubble(message: UiChatMessage, onRetry: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val timeStr = remember(message.timestamp) {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp))
    }
    val maxBubbleWidth = (LocalConfiguration.current.screenWidthDp * 0.78f).dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom,
    ) {
        // Аватар только у Tuti: собственные сообщения и так
        // очевидны по стороне и цвету.
        if (!message.isUser) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(c.jade.soft),
                contentAlignment = Alignment.Center,
            ) {
                Text("🦜", fontSize = 15.sp)
            }
            Spacer(Modifier.width(TutiSpace.sm))
        }

        Column(horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start) {
            val shape = if (message.isUser) {
                RoundedCornerShape(TutiRadius.lg, TutiRadius.lg, 6.dp, TutiRadius.lg)
            } else {
                RoundedCornerShape(TutiRadius.lg, TutiRadius.lg, TutiRadius.lg, 6.dp)
            }
            val bg = when {
                message.isUser -> c.jade.base
                message.isError -> c.wrongBg
                else -> c.messageBg
            }
            val fg = when {
                message.isUser -> Color.White
                message.isError -> c.wrongText
                else -> MaterialTheme.colorScheme.onSurface
            }

            Box(
                modifier = Modifier
                    .widthIn(max = maxBubbleWidth)
                    .clip(shape)
                    .background(bg)
                    .then(
                        if (!message.isUser) Modifier.border(1.dp, c.cardBorder, shape)
                        else Modifier
                    )
                    .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.md),
            ) {
                Column {
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = fg,
                    )
                    if (message.isError) {
                        Spacer(Modifier.height(TutiSpace.sm))
                        TutiPill(
                            text = "Такрор",
                            leadingEmoji = "↻",
                            background = c.coral.base,
                            contentColor = Color.White,
                            onClick = onRetry,
                        )
                    }
                }
            }
            Text(
                text = timeStr,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            )
        }
    }
}

@Composable
private fun TypingIndicatorBubble(background: Color) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.lg, TutiRadius.lg, TutiRadius.lg, 6.dp)

    Row(verticalAlignment = Alignment.Bottom) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(c.jade.soft),
            contentAlignment = Alignment.Center,
        ) {
            Text("🦜", fontSize = 15.sp)
        }
        Spacer(Modifier.width(TutiSpace.sm))
        Row(
            modifier = Modifier
                .clip(shape)
                .background(background)
                .border(1.dp, c.cardBorder, shape)
                .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.lg),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(3) { index ->
                val transition = rememberInfiniteTransition(label = "typing$index")
                val offsetY by transition.animateFloat(
                    initialValue = 0f,
                    targetValue = -6f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            400,
                            easing = FastOutSlowInEasing,
                            delayMillis = index * 120,
                        ),
                        repeatMode = RepeatMode.Reverse,
                    ),
                    label = "bounce",
                )
                Box(
                    modifier = Modifier
                        .graphicsLayer { translationY = offsetY * 4f }
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(c.jade.base.copy(alpha = 0.55f)),
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  ПОЛЕ ВВОДА
// ═══════════════════════════════════════════════════

@Composable
private fun ChatInputBar(
    inputText: String,
    isLoading: Boolean,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val canSend = inputText.isNotBlank() && !isLoading

    // Кнопка отправки пульсирует, только когда есть что отправить —
    // это подсказка, а не постоянный шум.
    val sendInfinite = rememberInfiniteTransition(label = "sendPulse")
    val sendPulse by sendInfinite.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "sendPulseScale",
    )
    val sendScale = if (canSend) sendPulse else 1f

    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(c.cardBorder),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .navigationBarsPadding()
                .padding(horizontal = TutiSpace.md, vertical = TutiSpace.sm),
            verticalAlignment = Alignment.Bottom,
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = onInputChange,
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(
                        text = "Ба русӣ ё тоҷикӣ нависед…",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                shape = RoundedCornerShape(TutiRadius.xl),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = c.jade.base,
                    unfocusedBorderColor = c.cardBorder,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = c.tileBg,
                ),
                maxLines = 4,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { onSend() }),
            )

            Spacer(Modifier.width(TutiSpace.sm))

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .scale(sendScale)
                    .clip(CircleShape)
                    .background(if (canSend) c.jade.base else c.progressTrack)
                    .clickable(enabled = canSend) { onSend() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "➤",
                    fontSize = 20.sp,
                    color = if (canSend) Color.White else c.lockedContent,
                )
            }
        }
    }
}
