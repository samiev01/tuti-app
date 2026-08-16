package app.tuti.tj.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  НЕТ СЕТИ НА ПЕРВОМ ЗАПУСКЕ
//
//  Анонимный вход требует сети: без него не будет ни uid, ни
//  документа профиля, и ответы онбординга сохранять просто некуда.
//
//  Поэтому здесь честный экран с кнопкой повтора, а не белый лист
//  и не вечный спиннер: человек должен понимать, чего ждёт
//  приложение и что от него самого требуется.
//
//  Показывается только новому пользователю. Тот, кто уже прошёл
//  онбординг, открывает приложение как обычно — весь его прогресс
//  лежит локально в Room и сети не требует.
// ════════════════════════════════════════════════════════════════

@Composable
fun NoConnectionScreen(
    isRetrying: Boolean,
    onRetry: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        c.mango.soft,
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
                .padding(horizontal = TutiSpace.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LivingTutiMascot(size = 116.dp)

            Spacer(Modifier.height(TutiSpace.lg))

            Text(text = "📡", fontSize = 34.sp)

            Spacer(Modifier.height(TutiSpace.md))

            Text(
                text = s.onboarding.offlineTitle,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(TutiSpace.sm))

            Text(
                text = s.onboarding.offlineMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = TutiSpace.md),
            )

            Spacer(Modifier.height(TutiSpace.xxl))

            TutiButton(
                text = s.common.retry,
                onClick = onRetry,
                loading = isRetrying,
                size = TutiButtonSize.Large,
                trailingEmoji = "🔄",
            )
        }
    }
}
