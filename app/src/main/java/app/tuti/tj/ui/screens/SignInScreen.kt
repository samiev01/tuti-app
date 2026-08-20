package app.tuti.tj.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.tuti.tj.R
import app.tuti.tj.analytics.TutiAnalytics
import app.tuti.tj.data.auth.AuthErrorKind
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.ui.components.GreetingOrbit
import app.tuti.tj.ui.components.findActivity
import app.tuti.tj.ui.components.openPrivacyPolicy
import app.tuti.tj.ui.components.openSupportChat
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.theme.TutiAccent
import app.tuti.tj.ui.theme.TutiLogoFamily
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ВХОД
//
//  Первый экран приложения. Анонимных аккаунтов больше нет: пока
//  человек не вошёл, сохранять его ответы всё равно некуда.
//
//  Ценность экрана — не в удачном сценарии, а в отказах. Каждый из
//  них получает понятный текст и действие, а в тех случаях, где
//  человек сам ничего сделать не может, — ещё и ссылку в поддержку.
//  Отмена выбора аккаунта ошибкой не считается.
// ════════════════════════════════════════════════════════════════

@Composable
fun SignInScreen(
    repository: TutiRepository,
    onNeedsOnboarding: () -> Unit,
    onRestored: () -> Unit,
    viewModel: SignInViewModel = viewModel(),
) {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current
    val strings = LocalTutiStrings.current
    val state = viewModel.state

    LaunchedEffect(Unit) { TutiAnalytics.signInShown() }

    LaunchedEffect(state) {
        when {
            state == SignInState.NeedsOnboarding -> onNeedsOnboarding()
            // Возвращать было нечего — задерживать человека сообщением
            // не за что, уходим на главную сразу.
            state == SignInState.Returning(restored = false) -> onRestored()
        }
    }

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
                .padding(top = TutiSpace.md, bottom = TutiSpace.md),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(TutiSpace.lg))

                // Логотип: это точка входа в приложение, здесь бренд
                // должен быть назван, а не только показан маскотом.
                Text(
                    text = "Tuti",
                    style = TextStyle(
                        fontFamily = TutiLogoFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 56.sp,
                        letterSpacing = 0.sp,
                        brush = Brush.verticalGradient(listOf(c.jade.base, c.jade.deep)),
                    ),
                )

                Spacer(Modifier.height(TutiSpace.xs))

                Text(
                    text = strings.onboarding.tagline,
                    style = MaterialTheme.typography.bodyLarge,
                    color = c.jade.onSoft,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.height(TutiSpace.lg))

                // Витрина языков: приветствия вокруг маскота. Языков
                // больше, чем курсов, — это заявка на будущий набор.
                GreetingOrbit(stageHeight = 300.dp, mascotSize = 118.dp)

                val errorKind = (state as? SignInState.Error)?.kind
                if (errorKind != null) {
                    Spacer(Modifier.height(TutiSpace.lg))
                    AuthBanner(
                        emoji = "⚠️",
                        title = null,
                        message = when (errorKind) {
                            AuthErrorKind.NO_NETWORK -> strings.auth.errorNoNetwork
                            AuthErrorKind.PLAY_SERVICES -> strings.auth.errorPlayServices
                            AuthErrorKind.UNKNOWN -> strings.auth.errorUnknown
                        },
                        tone = c.mango,
                    )
                }

                if (state == SignInState.Returning(restored = true)) {
                    Spacer(Modifier.height(TutiSpace.lg))
                    AuthBanner(
                        emoji = "🎉",
                        title = strings.auth.restoredTitle,
                        message = strings.auth.restoredMessage,
                        tone = c.jade,
                    )
                }

                Spacer(Modifier.height(TutiSpace.lg))
            }

            when {
                state == SignInState.Returning(restored = true) -> {
                    TutiButton(
                        text = strings.common.continueShort,
                        onClick = onRestored,
                        size = TutiButtonSize.Large,
                        trailingEmoji = "→",
                    )
                }

                else -> GoogleSignInButton(
                    isLoading = state == SignInState.Loading,
                    onClick = {
                        viewModel.signIn(context.findActivity(), context, repository)
                    },
                )
            }

            val errorKind = (state as? SignInState.Error)?.kind
            if (errorKind == AuthErrorKind.PLAY_SERVICES || errorKind == AuthErrorKind.UNKNOWN) {
                Spacer(Modifier.height(TutiSpace.sm))
                Text(
                    text = strings.auth.help,
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

            Spacer(Modifier.height(TutiSpace.xs))
        }
    }
}

/**
 * Одна плашка на все сообщения экрана: тон задаёт смысл. Тревожный
 * — отказ, спокойный — возвращение с восстановленным прогрессом.
 */
@Composable
private fun AuthBanner(
    emoji: String,
    title: String?,
    message: String,
    tone: TutiAccent,
) {
    val shape = RoundedCornerShape(TutiRadius.lg)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(tone.soft)
            .border(1.dp, tone.base.copy(alpha = 0.35f), shape)
            .padding(TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = emoji, fontSize = 18.sp)
        Spacer(Modifier.width(TutiSpace.sm))
        Column {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = tone.onSoft,
                )
                Spacer(Modifier.height(TutiSpace.xs))
            }
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = tone.onSoft,
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  КНОПКА GOOGLE
// ═══════════════════════════════════════════════════

/**
 * Правила Google требуют белую поверхность и фирменную букву,
 * поэтому здесь не общая [TutiButton] — но механика нажатия та же
 * «плита», что и у остальных кнопок приложения.
 */
@Composable
private fun GoogleSignInButton(
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val context = LocalContext.current
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shape = RoundedCornerShape(TutiRadius.lg)
    val sink = if (pressed && !isLoading) TutiSize.plate else 0.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(TutiSize.buttonLg + TutiSize.plate)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TutiSize.buttonLg + TutiSize.plate)
                    .clip(shape)
                    .background(c.cardBorder),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = sink)
                    .height(TutiSize.buttonLg)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.5.dp, c.cardBorder, shape)
                    .clickable(
                        interactionSource = interaction,
                        indication = null,
                        enabled = !isLoading,
                    ) { onClick() },
                contentAlignment = Alignment.Center,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.5.dp,
                        color = c.jade.base,
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Фирменный знак, а не буква: цвета заданы самим
                        // ресурсом и в тему не перекрашиваются.
                        Image(
                            painter = painterResource(R.drawable.ic_google_logo),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(Modifier.width(TutiSpace.md))
                        Text(
                            text = strings.onboarding.googleSignIn,
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(TutiSpace.md))

        Text(
            text = strings.onboarding.terms,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        // Сама политика — отдельной строкой и по-настоящему
        // нажимаемой. Внутри абзаца ссылку не разглядеть, а
        // согласие даётся именно здесь, до входа.
        Text(
            text = strings.onboarding.privacyPolicy,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 11.sp,
            color = c.jade.base,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(TutiRadius.sm))
                .clickable { context.openPrivacyPolicy() }
                .padding(horizontal = TutiSpace.sm, vertical = TutiSpace.xs),
        )
    }
}
