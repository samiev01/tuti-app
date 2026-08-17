package app.tuti.tj.ui.components.kit

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ПОВЕРХНОСТИ: карточки, плитки, чипы, заголовки секций
// ════════════════════════════════════════════════════════════════

/**
 * Ссылка на акцентное семейство палитры. Позволяет описывать
 * данные экрана (список режимов, разделов) без обращения к
 * MaterialTheme — цвет резолвится уже в композиции.
 */
enum class TutiAccentRef { Jade, Mango, Grape, Sky, Coral, Leaf }

@Composable
fun TutiAccentRef.resolve(): app.tuti.tj.ui.theme.TutiAccent {
    val c = MaterialTheme.tutiColors
    return when (this) {
        TutiAccentRef.Jade -> c.jade
        TutiAccentRef.Mango -> c.mango
        TutiAccentRef.Grape -> c.grape
        TutiAccentRef.Sky -> c.sky
        TutiAccentRef.Coral -> c.coral
        TutiAccentRef.Leaf -> c.leaf
    }
}

/** Пунктирная рамка — единый способ показать «ещё не открыто». */
fun Modifier.dashedOutline(color: Color, radius: Dp, width: Dp = 1.5.dp) = drawBehind {
    drawRoundRect(
        color = color,
        style = Stroke(
            width = width.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f)),
        ),
        cornerRadius = CornerRadius(radius.toPx()),
    )
}

/**
 * Базовая карточка приложения: светлая поверхность, тонкая
 * граница вместо тяжёлой тени. Если задан [onClick], карточка
 * слегка проваливается при нажатии.
 */
@Composable
fun TutiCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    radius: Dp = TutiRadius.xl,
    background: Color? = null,
    borderColor: Color? = null,
    contentPadding: Dp = TutiSpace.card,
    dashed: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(radius)
    val bg = background ?: MaterialTheme.colorScheme.surface
    val border = borderColor ?: MaterialTheme.tutiColors.cardBorder

    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val sink by animateDpAsState(
        targetValue = if (pressed && onClick != null) 2.dp else 0.dp,
        animationSpec = TutiMotion.fast(),
        label = "cardSink",
    )

    Column(
        modifier = modifier
            .padding(top = sink)
            .clip(shape)
            .background(bg)
            .then(
                if (dashed) Modifier.dashedOutline(border, radius)
                else Modifier.border(1.dp, border, shape)
            )
            .then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = interaction,
                    indication = null,
                ) {
                    runCatching { TutiSoundManager.playButtonClick() }
                    onClick()
                } else Modifier
            )
            .padding(contentPadding),
        content = content,
    )
}

/**
 * Крупная карточка с градиентной заливкой и мягкими бликами —
 * для hero-блоков: серия, курс, Plus, рейтинг.
 */
@Composable
fun TutiGradientCard(
    gradient: List<Color>,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    radius: Dp = TutiRadius.xl,
    contentPadding: Dp = TutiSpace.card,
    content: @Composable BoxScope.() -> Unit,
) {
    val shape = RoundedCornerShape(radius)
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val sink by animateDpAsState(
        targetValue = if (pressed && onClick != null) 2.dp else 0.dp,
        animationSpec = TutiMotion.fast(),
        label = "gradientCardSink",
    )

    Box(
        modifier = modifier
            .padding(top = sink)
            .clip(shape)
            .background(Brush.linearGradient(gradient))
            .then(
                if (onClick != null) Modifier.clickable(
                    interactionSource = interaction,
                    indication = null,
                ) {
                    runCatching { TutiSoundManager.playButtonClick() }
                    onClick()
                } else Modifier
            ),
    ) {
        // Блики — два мягких круга, придают объём без картинок.
        // Лежат в слое matchParentSize: он получает размер от карточки и не
        // участвует в её измерении, иначе круг в 160.dp задавал бы карточке
        // минимальную высоту и короткий контент тонул в пустоте.
        Box(modifier = Modifier.matchParentSize()) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.TopEnd)
                    .background(
                        Brush.radialGradient(
                            listOf(Color.White.copy(alpha = 0.14f), Color.Transparent),
                        ),
                    ),
            )
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.radialGradient(
                            listOf(Color.White.copy(alpha = 0.09f), Color.Transparent),
                        ),
                    ),
            )
        }
        Box(modifier = Modifier.padding(contentPadding), content = content)
    }
}

/**
 * Плитка с эмодзи. Единственный разрешённый способ показать
 * эмодзи как иконку — так пёстрый набор символов собирается
 * в одну систему.
 */
@Composable
fun TutiIconTile(
    emoji: String,
    modifier: Modifier = Modifier,
    size: Dp = TutiSize.iconTileMd,
    background: Color? = null,
    radius: Dp = TutiRadius.md,
    dimmed: Boolean = false,
) {
    val bg = background ?: MaterialTheme.tutiColors.tileBg
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(radius))
            .background(if (dimmed) MaterialTheme.tutiColors.lockedBg else bg),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = emoji,
            fontSize = (size.value * 0.46f).sp,
        )
    }
}

/** Компактная плашка-счётчик: XP, проценты, «5/10», уровень. */
@Composable
fun TutiPill(
    text: String,
    modifier: Modifier = Modifier,
    background: Color? = null,
    contentColor: Color? = null,
    leadingEmoji: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val bg = background ?: MaterialTheme.tutiColors.jade.soft
    val fg = contentColor ?: MaterialTheme.tutiColors.jade.onSoft
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(bg)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingEmoji != null) {
            Text(leadingEmoji, fontSize = 12.sp)
            Spacer(Modifier.width(4.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = fg,
        )
    }
}

/**
 * Заголовок секции. Слева название, справа — необязательный
 * счётчик и действие. Ставится перед каждым списком.
 */
@Composable
fun TutiSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    counter: String? = null,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (counter != null) {
            Spacer(Modifier.width(8.dp))
            TutiPill(text = counter)
        }
        Spacer(Modifier.weight(1f))
        if (actionText != null && onAction != null) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.tutiColors.jade.base,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onAction() }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}

/** Тонкий разделитель внутри карточек-списков настроек. */
@Composable
fun TutiDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.tutiColors.divider),
    )
}

/**
 * Строка списка внутри карточки: плитка, заголовок с подписью,
 * произвольный хвост. Используется в настройках, практике,
 * списках уроков.
 */
@Composable
fun TutiListRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    emoji: String? = null,
    tileBackground: Color? = null,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .clickable {
                        runCatching { TutiSoundManager.playButtonClick() }
                        onClick()
                    }
                else Modifier
            )
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (emoji != null) {
            TutiIconTile(emoji = emoji, background = tileBackground)
            Spacer(Modifier.width(TutiSpace.md))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        if (trailing != null) {
            Spacer(Modifier.width(TutiSpace.sm))
            trailing()
        }
    }
}

/**
 * Группа настроек: карточка со строками и подпись над ней.
 *
 * [title] можно не задавать. Подпись нужна там, где карточка сама
 * себя не объясняет — например, ряд плиток выбора темы. Там, где
 * строки названы полными фразами, заголовок повторял бы их
 * своими словами и только удлинял экран.
 */
@Composable
fun TutiSettingsGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.height(TutiSpace.md))
        }
        TutiCard(
            modifier = Modifier.fillMaxWidth(),
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(TutiSpace.xs),
                    content = content,
                )
            },
        )
    }
}
