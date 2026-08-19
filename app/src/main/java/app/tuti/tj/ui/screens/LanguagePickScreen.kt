package app.tuti.tj.ui.screens

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.i18n.AppLanguage
import app.tuti.tj.ui.i18n.LanguageManager
import app.tuti.tj.ui.i18n.stringsFor
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ВЫБОР ЯЗЫКА — САМЫЙ ПЕРВЫЙ ЭКРАН
//
//  Показывается ровно один раз: пока человек не ответил, никакой
//  язык не выбран за него. Системную локаль приложение не
//  спрашивает — в Таджикистане русскоязычная система стоит у
//  многих, кто хочет учиться на таджикском, и наоборот.
//
//  Единственный экран приложения, который не берёт строки из
//  LocalTutiStrings: подписывать его на одном языке нельзя —
//  ровно половина пользователей увидела бы вопрос на чужом.
//  Поэтому заголовок стоит на обоих сразу, а каждая карточка
//  подписана на своём.
// ════════════════════════════════════════════════════════════════

@Composable
fun LanguagePickScreen() {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current

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
                .padding(horizontal = TutiSpace.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LivingTutiMascot(size = 104.dp, waving = true)

            Spacer(Modifier.height(TutiSpace.lg))

            // Заголовок на обоих языках, сверху вниз. Второй — в
            // скобках: так видно, что это тот же вопрос, а не
            // отдельная строка, и взгляд не спотыкается о два
            // одинаково набранных заголовка подряд.
            AppLanguage.entries.forEachIndexed { index, language ->
                val title = stringsFor(language).onboarding.pickLanguageTitle
                Text(
                    text = if (index == 0) title else "($title)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(TutiSpace.xs))
            }

            Spacer(Modifier.height(TutiSpace.xl))

            Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
                AppLanguage.entries.forEach { language ->
                    LanguageCard(
                        language = language,
                        onClick = { LanguageManager.setLanguage(context, language) },
                    )
                }
            }

            Spacer(Modifier.height(TutiSpace.lg))

            // Обещание, что выбор не окончательный: без него люди
            // подолгу висят на этом экране, боясь ошибиться.
            AppLanguage.entries.forEach { language ->
                Text(
                    text = stringsFor(language).onboarding.pickLanguageHint,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun LanguageCard(language: AppLanguage, onClick: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.lg)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, c.cardBorder, shape)
            .clickable { onClick() }
            .padding(TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TutiIconTile(
            emoji = language.flag,
            size = TutiSize.iconTileMd,
            background = c.tileBg,
        )
        Spacer(Modifier.width(TutiSpace.md))
        Column(Modifier.weight(1f)) {
            Text(
                text = language.nativeName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = stringsFor(language).onboarding.pickLanguageAction,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Text(
            text = "→",
            style = MaterialTheme.typography.titleMedium,
            color = c.jade.base,
        )
    }
}
