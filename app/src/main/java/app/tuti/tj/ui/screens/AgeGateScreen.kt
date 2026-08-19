package app.tuti.tj.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import app.tuti.tj.data.user.AgeGateManager
import app.tuti.tj.data.user.AgeGateVerdict
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ВОЗРАСТНОЙ ПОРОГ — ЭКРАН ПЕРЕД ВХОДОМ
//
//  Спрашивается ровно одна вещь: дата рождения. Ни порога в
//  подписи, ни галочки «мне есть 13» — иначе вопрос превращается
//  в подсказку, какой ответ считается правильным, и отвечать на
//  него честно перестают.
//
//  Экран стоит до входа через Google: аккаунта ещё нет, поэтому
//  ответ до поры живёт в настройках устройства.
// ════════════════════════════════════════════════════════════════

@Composable
fun AgeGateScreen() {
    val verdict by AgeGateManager.verdict.collectAsState()

    if (verdict == AgeGateVerdict.BLOCKED) {
        AgeGateBlocked()
    } else {
        AgeGateForm()
    }
}

@Composable
private fun AgeGateForm() {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current
    val s = LocalTutiStrings.current.ageGate

    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val date = AgeGateManager.parse(
        day = day.toIntOrNull() ?: 0,
        month = month.toIntOrNull() ?: 0,
        year = year.toIntOrNull() ?: 0,
    )
    val filled = day.isNotBlank() && month.isNotBlank() && year.length == 4

    GateBackground {
        LivingTutiMascot(size = 96.dp)

        Spacer(Modifier.height(TutiSpace.lg))

        Text(
            text = s.title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.sm))

        Text(
            text = s.subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.xl))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            DateField(
                value = day,
                onValueChange = { day = it.take(2); showError = false },
                label = s.dayLabel,
                modifier = Modifier.weight(1f),
            )
            DateField(
                value = month,
                onValueChange = { month = it.take(2); showError = false },
                label = s.monthLabel,
                modifier = Modifier.weight(1f),
            )
            DateField(
                value = year,
                onValueChange = { year = it.take(4); showError = false },
                label = s.yearLabel,
                modifier = Modifier.weight(1.4f),
            )
        }

        if (showError) {
            Spacer(Modifier.height(TutiSpace.sm))
            Text(
                text = s.invalidDate,
                style = MaterialTheme.typography.bodySmall,
                color = c.coral.base,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(Modifier.height(TutiSpace.xl))

        TutiButton(
            text = LocalTutiStrings.current.common.continueShort,
            onClick = {
                // Порог здесь не называется и кнопка ведёт себя
                // одинаково: решение принимает AgeGateManager, а экран
                // сам переключится — он подписан на вердикт.
                if (date == null) {
                    showError = true
                } else {
                    AgeGateManager.submit(context, date)
                }
            },
            enabled = filled,
            size = TutiButtonSize.Large,
            trailingEmoji = "→",
        )
    }
}

/**
 * Отказ. Показывается и сразу после ответа, и при каждом следующем
 * запуске: решение запомнено, обойти его перезапуском нельзя.
 */
@Composable
private fun AgeGateBlocked() {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current
    val s = LocalTutiStrings.current.ageGate

    GateBackground {
        LivingTutiMascot(size = 96.dp)

        Spacer(Modifier.height(TutiSpace.lg))

        Text(
            text = s.blockedTitle,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.md))

        Text(
            text = s.blockedMessage,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.xl))

        Text(
            text = s.blockedContactHint,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.xs))

        Text(
            text = s.supportEmail,
            style = MaterialTheme.typography.titleMedium,
            color = c.jade.base,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(TutiRadius.sm))
                .clickable { context.openMail(s.supportEmail) }
                .padding(TutiSpace.sm),
        )
    }
}

/** Общий фон и раскладка: экран один, состояния у него два. */
@Composable
private fun GateBackground(content: @Composable ColumnScope.() -> Unit) {
    val c = MaterialTheme.tutiColors

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        c.jade.soft,
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background,
                    ),
                ),
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = TutiSpace.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = content,
        )
    }
}

@Composable
private fun DateField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors

    OutlinedTextField(
        value = value,
        onValueChange = { input -> onValueChange(input.filter { it.isDigit() }) },
        label = { Text(label) },
        singleLine = true,
        textStyle = TextStyle(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(TutiRadius.md),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = c.jade.base,
            unfocusedBorderColor = c.cardBorder,
        ),
        modifier = modifier,
    )
}

private fun Context.openMail(address: String) {
    runCatching {
        startActivity(
            Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$address"))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}
