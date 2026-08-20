package app.tuti.tj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.remote.AiReportManager
import app.tuti.tj.data.remote.AiReportReason
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  ЖАЛОБА НА ОТВЕТ РЕПЕТИТОРА
//
//  Причина выбирается из трёх, комментарий необязателен: чем
//  длиннее форма, тем реже её заполняют, а нам хватает причины
//  рядом с текстом ответа.
//
//  Отправка ничего не блокирует и никуда не уводит — лист сам
//  показывает подтверждение и закрывается.
// ════════════════════════════════════════════════════════════════

/** Сколько держится подтверждение, прежде чем лист закроется. */
private const val CONFIRMATION_MS = 1_400L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiReportSheet(
    messageText: String,
    onDismiss: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current
    val s = LocalTutiStrings.current.chat
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    var reason by remember { mutableStateOf<AiReportReason?>(null) }
    var comment by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }
    var isSent by remember { mutableStateOf(false) }

    LaunchedEffect(isSent) {
        if (isSent) {
            delay(CONFIRMATION_MS)
            onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .navigationBarsPadding()
                .padding(horizontal = TutiSpace.xl)
                .padding(bottom = TutiSpace.xl),
        ) {
            if (isSent) {
                SentConfirmation()
                return@Column
            }

            Text(
                text = s.reportTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.height(TutiSpace.xs))

            Text(
                text = s.reportSubtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(Modifier.height(TutiSpace.lg))

            Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
                ReasonRow(
                    text = s.reasonWrongAnswer,
                    selected = reason == AiReportReason.WRONG_ANSWER,
                    onClick = { reason = AiReportReason.WRONG_ANSWER },
                )
                ReasonRow(
                    text = s.reasonOffensive,
                    selected = reason == AiReportReason.OFFENSIVE,
                    onClick = { reason = AiReportReason.OFFENSIVE },
                )
                ReasonRow(
                    text = s.reasonOther,
                    selected = reason == AiReportReason.OTHER,
                    onClick = { reason = AiReportReason.OTHER },
                )
            }

            Spacer(Modifier.height(TutiSpace.lg))

            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                placeholder = { Text(s.commentPlaceholder) },
                minLines = 2,
                shape = RoundedCornerShape(TutiRadius.md),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = c.jade.base,
                    unfocusedBorderColor = c.cardBorder,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(TutiSpace.lg))

            TutiButton(
                text = s.reportSend,
                onClick = {
                    val picked = reason ?: return@TutiButton
                    isSending = true
                    scope.launch {
                        // Отказ сервера ничего не меняет для человека:
                        // жалобу он уже написал, и разбираться с сетью
                        // ему нечем. Поэтому результат один — принято.
                        AiReportManager.submit(context, messageText, picked, comment)
                        isSending = false
                        isSent = true
                    }
                },
                enabled = reason != null && !isSending,
                loading = isSending,
                size = TutiButtonSize.Large,
            )
        }
    }
}

@Composable
private fun SentConfirmation() {
    val s = LocalTutiStrings.current.chat

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = TutiSpace.xxl),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = s.reportSentTitle,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(TutiSpace.xs))
        Text(
            text = s.reportSentMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ReasonRow(text: String, selected: Boolean, onClick: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.md)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(if (selected) c.jade.soft else MaterialTheme.colorScheme.surface)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) c.jade.base else c.cardBorder,
                shape = shape,
            )
            .clickable { onClick() }
            .padding(TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f),
        )
        if (selected) {
            Text("✓", fontSize = 16.sp, color = c.jade.base)
        }
    }
}
