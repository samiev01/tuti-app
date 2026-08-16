package app.tuti.tj.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.tuti.tj.analytics.TutiAnalytics
import app.tuti.tj.data.auth.AuthErrorKind
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.user.LearningGoal
import app.tuti.tj.data.user.LearningLanguage
import app.tuti.tj.data.user.ProficiencyLevel
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.findActivity
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.TutiStrings
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ФИНАЛЬНЫЙ ШАГ ОНБОРДИНГА
//
//  Кнопки «потом» здесь нет — и именно поэтому экран почти целиком
//  состоит из обработки отказов. Сначала человек видит, что он уже
//  вложил: язык, цель, уровень, город, время. Сохранять своё хочется
//  сильнее, чем абстрактный «прогресс».
//
//  Системная кнопка «назад» сворачивает приложение: пропускать этот
//  шаг нельзя, но и запирать человека в экране без выхода тоже.
//  Плюс ссылка «Кӯмак» в тех состояниях, где он сам ничего сделать
//  не может.
// ════════════════════════════════════════════════════════════════

/** TODO: заменить на настоящий канал поддержки. */
private const val SUPPORT_TELEGRAM_URL = "https://t.me/tutitj"

@Composable
fun FinalStepScreen(
    repository: TutiRepository,
    onDone: () -> Unit,
    viewModel: FinalStepViewModel = viewModel(),
) {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current
    val strings = LocalTutiStrings.current
    val s = strings.finalStep
    val state = viewModel.state
    val summary = viewModel.summary

    LaunchedEffect(Unit) {
        TutiAnalytics.finalStepShown()
        viewModel.load(context, repository)
    }

    LaunchedEffect(state) {
        if (state is FinalStepState.Success) onDone()
    }

    // Пропустить шаг нельзя, поэтому «назад» не уводит на главную,
    // а сворачивает приложение — как с домашнего экрана.
    BackHandler { context.findActivity().moveTaskToBack(true) }

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
                .padding(horizontal = TutiSpace.xl)
                .padding(bottom = TutiSpace.md),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CelebrationHeader()

                Text(
                    text = strings.onboarding.readyTitle,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(Modifier.height(TutiSpace.lg))

                if (summary != null) {
                    SummaryCard(summary = summary, strings = strings)
                    Spacer(Modifier.height(TutiSpace.lg))
                }

                Text(
                    text = s.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )

                if (state is FinalStepState.Error) {
                    Spacer(Modifier.height(TutiSpace.lg))
                    ErrorBanner(kind = state.kind)
                }

                if (state == FinalStepState.Restored) {
                    Spacer(Modifier.height(TutiSpace.lg))
                    RestoredBanner()
                }

                Spacer(Modifier.height(TutiSpace.lg))
            }

            val errorKind = (state as? FinalStepState.Error)?.kind
            val restored = state == FinalStepState.Restored

            TutiButton(
                text = when {
                    // Прогресс уже вернулся — дальше идти незачем спрашивать.
                    restored -> strings.common.continueShort
                    // Конфликт аккаунтов — не отказ, а другой путь:
                    // прогресс восстановится, идти надо дальше.
                    errorKind == AuthErrorKind.ACCOUNT_CONFLICT -> strings.common.continueShort
                    errorKind == null -> s.saveWithGoogle
                    else -> strings.common.retry
                },
                onClick = {
                    when {
                        restored || errorKind == AuthErrorKind.ACCOUNT_CONFLICT -> onDone()
                        else -> viewModel.signIn(context.findActivity(), context)
                    }
                },
                loading = state == FinalStepState.Loading,
                size = TutiButtonSize.Large,
                trailingEmoji = if (errorKind == null && !restored) "🔒" else null,
            )

            // Выхода «Баъдтар» нет — значит, должен остаться способ
            // дотянуться до нас. Иначе человек просто удалит приложение,
            // и мы никогда не узнаем, почему.
            if (errorKind == AuthErrorKind.PLAY_SERVICES || errorKind == AuthErrorKind.UNKNOWN) {
                Spacer(Modifier.height(TutiSpace.md))
                Text(
                    text = s.help,
                    style = MaterialTheme.typography.labelLarge,
                    color = c.jade.base,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(TutiRadius.sm))
                        .clickable { context.openSupportChat() }
                        .padding(vertical = TutiSpace.sm),
                )
            }
        }
    }
}

private fun android.content.Context.openSupportChat() {
    runCatching {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(SUPPORT_TELEGRAM_URL))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}

// ═══════════════════════════════════════════════════
//  ШАПКА
// ═══════════════════════════════════════════════════

@Composable
private fun CelebrationHeader() {
    val c = MaterialTheme.tutiColors
    val inf = rememberInfiniteTransition(label = "done")
    val bounce by inf.animateFloat(
        initialValue = 0f, targetValue = 14f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = FastOutSlowInEasing), RepeatMode.Reverse,
        ),
        label = "bounce",
    )

    Spacer(Modifier.height(TutiSpace.lg))

    Box(
        modifier = Modifier.fillMaxWidth().height(210.dp),
        contentAlignment = Alignment.Center,
    ) {
        FloatingDecor("🎉", -94, -50, 1600)
        FloatingDecor("⭐", 104, -40, 2000)
        FloatingDecor("✨", -62, 60, 1800)
        FloatingDecor("🎉", 84, 70, 2400)
        FloatingDecor("⭐", -112, 10, 2100)

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    Brush.radialGradient(
                        listOf(c.mango.base.copy(alpha = 0.18f), Color.Transparent),
                    ),
                ),
        )
        Box(modifier = Modifier.offset { IntOffset(0, -bounce.toInt()) }) {
            LivingTutiMascot(size = 132.dp)
        }
    }

    Spacer(Modifier.height(TutiSpace.md))
}

@Composable
private fun FloatingDecor(emoji: String, xOff: Int, yOff: Int, durationMs: Int) {
    val inf = rememberInfiniteTransition(label = "decor$emoji")
    val dy by inf.animateFloat(
        initialValue = 0f, targetValue = 10f,
        animationSpec = infiniteRepeatable(
            tween(durationMs, easing = FastOutSlowInEasing), RepeatMode.Reverse,
        ),
        label = "decDy",
    )
    Text(
        text = emoji,
        fontSize = 22.sp,
        modifier = Modifier
            .offset(x = xOff.dp, y = (yOff + dy).dp)
            .alpha(0.55f),
    )
}

// ═══════════════════════════════════════════════════
//  СВОДКА ВЫБОРА
// ═══════════════════════════════════════════════════

@Composable
private fun SummaryCard(summary: OnboardingSummary, strings: TutiStrings) {
    val c = MaterialTheme.tutiColors
    val s = strings.finalStep
    val o = strings.onboarding
    val shape = RoundedCornerShape(TutiRadius.lg)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, c.cardBorder, shape)
            .padding(TutiSpace.md),
        verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
    ) {
        Text(
            text = s.summaryTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        SummaryRow(
            emoji = when (summary.language) {
                LearningLanguage.RUSSIAN -> "🇷🇺"
                LearningLanguage.ENGLISH -> "🇬🇧"
            },
            label = s.labelLanguage,
            value = when (summary.language) {
                LearningLanguage.RUSSIAN -> o.optionRussian
                LearningLanguage.ENGLISH -> o.optionEnglish
            },
        )
        SummaryRow(
            emoji = when (summary.goal) {
                LearningGoal.WORK -> "💼"
                LearningGoal.STUDY -> "🎓"
                LearningGoal.TRAVEL -> "✈️"
                LearningGoal.PERSONAL -> "🧠"
            },
            label = s.labelGoal,
            value = when (summary.goal) {
                LearningGoal.WORK -> o.goalWork
                LearningGoal.STUDY -> o.goalStudy
                LearningGoal.TRAVEL -> o.goalTravel
                LearningGoal.PERSONAL -> o.goalPersonal
            },
        )
        SummaryRow(
            emoji = when (summary.level) {
                ProficiencyLevel.BEGINNER -> "🌱"
                ProficiencyLevel.INTERMEDIATE -> "📚"
                ProficiencyLevel.ADVANCED -> "🚀"
            },
            label = s.labelLevel,
            value = when (summary.level) {
                ProficiencyLevel.BEGINNER -> o.levelBeginner
                ProficiencyLevel.INTERMEDIATE -> o.levelIntermediate
                ProficiencyLevel.ADVANCED -> o.levelAdvanced
            },
        )
        SummaryRow(
            emoji = summary.city.emoji,
            label = s.labelCity,
            value = strings.cities.name(summary.city.tajikName),
        )
        SummaryRow(
            emoji = "⏰",
            label = s.labelDailyTime,
            value = o.minutes(summary.dailyMinutes),
        )
    }
}

@Composable
private fun SummaryRow(emoji: String, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = emoji, fontSize = 16.sp)
        Spacer(Modifier.width(TutiSpace.sm))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ОШИБКА
// ═══════════════════════════════════════════════════

@Composable
private fun ErrorBanner(kind: AuthErrorKind) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current.finalStep
    val shape = RoundedCornerShape(TutiRadius.lg)

    // Конфликт аккаунтов ничего не сломал — это спокойное сообщение,
    // а не отказ, поэтому и цвет у него не тревожный.
    val calm = kind == AuthErrorKind.ACCOUNT_CONFLICT
    val tone = if (calm) c.jade else c.mango

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(tone.soft)
            .border(1.dp, tone.base.copy(alpha = 0.35f), shape)
            .padding(TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = if (calm) "ℹ️" else "⚠️", fontSize = 18.sp)
        Spacer(Modifier.width(TutiSpace.sm))
        Text(
            text = when (kind) {
                AuthErrorKind.NO_NETWORK -> s.errorNoNetwork
                AuthErrorKind.PLAY_SERVICES -> s.errorPlayServices
                AuthErrorKind.ACCOUNT_CONFLICT -> s.errorAccountConflict
                AuthErrorKind.UNKNOWN -> s.errorUnknown
            },
            style = MaterialTheme.typography.bodyMedium,
            color = tone.onSoft,
        )
    }
}

/** Вошли старым аккаунтом: прогресс вернулся, тревожиться не о чем. */
@Composable
private fun RestoredBanner() {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current.finalStep
    val shape = RoundedCornerShape(TutiRadius.lg)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(c.jade.soft)
            .border(1.dp, c.jade.base.copy(alpha = 0.35f), shape)
            .padding(TutiSpace.md),
    ) {
        Text(
            text = s.restoredTitle,
            style = MaterialTheme.typography.titleMedium,
            color = c.jade.onSoft,
        )
        Spacer(Modifier.height(TutiSpace.xs))
        Text(
            text = s.restoredMessage,
            style = MaterialTheme.typography.bodyMedium,
            color = c.jade.onSoft,
        )
    }
}
