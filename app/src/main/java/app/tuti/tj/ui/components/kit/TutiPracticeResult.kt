package app.tuti.tj.ui.components.kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.PracticeStrings
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ИТОГ ПРАКТИКИ
//
//  Один экран результата на все режимы (флеш-карты, аудирование,
//  письмо, квиз). Раньше каждый режим рисовал свой — из-за этого
//  «85 %» выглядели по-разному в четырёх местах. Теперь награда
//  за усилие всегда одна и та же: реакция маскота, кольцо
//  точности, две плитки и один явный выход.
// ════════════════════════════════════════════════════════════════

/** Заголовок подбирается по результату — тон обратной связи важнее числа. */
private fun titleFor(percent: Int, s: PracticeStrings): String = when {
    percent >= 90 -> s.resultPerfect
    percent >= 80 -> s.resultExcellent
    percent >= 60 -> s.resultGood
    percent >= 40 -> s.resultOk
    else -> s.resultRetry
}

private fun subtitleFor(percent: Int, s: PracticeStrings): String = when {
    percent >= 80 -> s.resultSubtitleGood
    percent >= 60 -> s.resultSubtitleOk
    else -> s.resultSubtitleRetry
}

@Composable
fun TutiPracticeResult(
    correct: Int,
    total: Int,
    onPrimary: () -> Unit,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier,
    primaryText: String = LocalTutiStrings.current.common.toHome,
    primaryEmoji: String = "🏠",
    restartText: String = LocalTutiStrings.current.common.restart,
    correctLabel: String = LocalTutiStrings.current.practice.correctAnswerLabel,
    wrongLabel: String = LocalTutiStrings.current.practice.wrongAnswerLabel,
    accentColor: Color? = null,
    xpEarned: Int? = null,
) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current
    val wrong = (total - correct).coerceAtLeast(0)
    val percent = if (total > 0) (correct * 100 / total) else 0
    val ring = accentColor ?: c.jade.base

    Column(
        modifier = modifier
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
                percent >= 70 -> TutiState.CELEBRATE
                percent >= 40 -> TutiState.HAPPY
                else -> TutiState.SAD
            },
            modifier = Modifier.size(104.dp),
        )

        Spacer(Modifier.height(TutiSpace.lg))

        Text(
            text = titleFor(percent, s.practice),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(TutiSpace.xs))
        Text(
            text = subtitleFor(percent, s.practice),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.xl))

        TutiRingProgress(
            progress = percent / 100f,
            size = 136.dp,
            stroke = 12.dp,
            color = ring,
            centerLabel = "$percent%",
        )

        if (xpEarned != null && xpEarned > 0) {
            Spacer(Modifier.height(TutiSpace.lg))
            TutiPill(
                text = s.common.xp(xpEarned),
                leadingEmoji = "💎",
                background = c.grape.soft,
                contentColor = c.grape.onSoft,
            )
        }

        Spacer(Modifier.height(TutiSpace.xl))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            ResultTile(
                emoji = "✅",
                value = "$correct",
                label = correctLabel,
                bg = c.correctBg,
                fg = c.correctText,
                modifier = Modifier.weight(1f),
            )
            ResultTile(
                emoji = "🔁",
                value = "$wrong",
                label = wrongLabel,
                bg = c.wrongBg,
                fg = c.wrongText,
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(Modifier.height(TutiSpace.xxl))

        TutiButton(text = primaryText, onClick = onPrimary, leadingEmoji = primaryEmoji)
        Spacer(Modifier.height(TutiSpace.sm))
        TutiSecondaryButton(text = restartText, onClick = onRestart, leadingEmoji = "🔄")
    }
}

@Composable
private fun ResultTile(
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
