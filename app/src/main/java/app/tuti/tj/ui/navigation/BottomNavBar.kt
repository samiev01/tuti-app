package app.tuti.tj.ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.NavStrings
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  НИЖНЯЯ НАВИГАЦИЯ
//
//  Вместо стандартной Material-панели — собственная: у каждой
//  вкладки свой акцентный цвет из палитры, а активный пункт
//  подсвечивается капсулой этого цвета. Так пользователь
//  запоминает разделы не только по подписи, но и по цвету,
//  а сама панель перестаёт быть «системной деталью».
//
//  В центре — рейтинг. Он выделен приподнятым золотым кругом, а не
//  такой же капсулой: это не рядовой раздел, а соревновательная
//  витрина, ради которой возвращаются. Композиция при этом
//  симметрична — по две вкладки с каждой стороны.
// ════════════════════════════════════════════════════════════════

enum class BottomNavItem(
    val route: String,
    val emoji: String,
) {
    Home("home", "🏠"),
    Lessons("lessons", "📚"),
    Practice("practice", "🎯"),
    Profile("profile", "👤"),
}

/** Подпись вкладки берётся из словаря: она зависит от языка интерфейса. */
private fun BottomNavItem.label(strings: NavStrings): String = when (this) {
    BottomNavItem.Home -> strings.home
    BottomNavItem.Lessons -> strings.lessons
    BottomNavItem.Practice -> strings.practice
    BottomNavItem.Profile -> strings.profile
}

/** Маршруты, на которых панель остаётся видимой. */
val bottomBarRoutes: List<String> =
    BottomNavItem.entries.map { it.route } + LEADERBOARD_ROUTE

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val c = MaterialTheme.tutiColors
    val nav = LocalTutiStrings.current.nav

    // Цвет вкладки берётся из акцентных семейств палитры
    val accents = listOf(c.jade, c.sky, c.mango, c.grape)
    val tabs = BottomNavItem.entries

    /** Переход между разделами: одна и та же семантика для всех пунктов. */
    fun switchTo(route: String) {
        if (currentRoute == route) return
        navController.navigate(route) {
            popUpTo(BottomNavItem.Home.route) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(c.cardBorder),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            // Центральный пункт выше остальных, поэтому ряд выравнивается
            // по нижнему краю — подписи всех пяти стоят на одной линии.
            verticalAlignment = Alignment.Bottom,
        ) {
            tabs.take(2).forEachIndexed { i, item ->
                NavTab(
                    emoji = item.emoji,
                    label = item.label(nav),
                    selected = currentRoute == item.route,
                    activeBg = accents[i].soft,
                    activeText = accents[i].onSoft,
                    modifier = Modifier.weight(1f),
                    onClick = { switchTo(item.route) },
                )
            }

            LeaderboardTab(
                selected = currentRoute == LEADERBOARD_ROUTE,
                modifier = Modifier.weight(1f),
                onClick = { switchTo(LEADERBOARD_ROUTE) },
            )

            tabs.drop(2).forEachIndexed { i, item ->
                NavTab(
                    emoji = item.emoji,
                    label = item.label(nav),
                    selected = currentRoute == item.route,
                    activeBg = accents[i + 2].soft,
                    activeText = accents[i + 2].onSoft,
                    modifier = Modifier.weight(1f),
                    onClick = { switchTo(item.route) },
                )
            }
        }
    }
}

@Composable
private fun NavTab(
    emoji: String,
    label: String,
    selected: Boolean,
    activeBg: Color,
    activeText: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val muted = MaterialTheme.colorScheme.onSurfaceVariant

    val capsuleWidth by animateDpAsState(
        targetValue = if (selected) 52.dp else 38.dp,
        animationSpec = TutiMotion.bouncy(),
        label = "navCapsule",
    )
    val emojiScale by animateFloatAsState(
        targetValue = if (selected) 1.12f else 1f,
        animationSpec = TutiMotion.bouncy(),
        label = "navEmoji",
    )
    val capsuleColor by animateColorAsState(
        targetValue = if (selected) activeBg else Color.Transparent,
        animationSpec = TutiMotion.normal(),
        label = "navCapsuleColor",
    )
    val labelColor by animateColorAsState(
        targetValue = if (selected) activeText else muted,
        animationSpec = TutiMotion.normal(),
        label = "navLabelColor",
    )

    val interaction = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.md))
            .clickable(interactionSource = interaction, indication = null) { onClick() }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .width(capsuleWidth)
                .height(30.dp)
                .clip(RoundedCornerShape(TutiRadius.pill))
                .background(capsuleColor),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = emoji,
                fontSize = 19.sp,
                modifier = Modifier.scale(emojiScale),
            )
        }
        Spacer(Modifier.height(3.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = labelColor,
        )
    }
}

/**
 * Рейтинг. Всегда залитый золотой круг, а не мягкая капсула, —
 * это якорь панели: он должен читаться как отдельная сущность
 * даже когда раздел не выбран. Выбранное состояние показывает
 * кольцо вокруг круга и цвет подписи.
 */
@Composable
private fun LeaderboardTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val muted = MaterialTheme.colorScheme.onSurfaceVariant

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.08f else 1f,
        animationSpec = TutiMotion.bouncy(),
        label = "leaderScale",
    )
    val ring by animateColorAsState(
        targetValue = if (selected) c.gold.copy(alpha = 0.45f) else Color.Transparent,
        animationSpec = TutiMotion.normal(),
        label = "leaderRing",
    )
    val labelColor by animateColorAsState(
        targetValue = if (selected) c.mango.onSoft else muted,
        animationSpec = TutiMotion.normal(),
        label = "leaderLabel",
    )

    val interaction = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.md))
            .clickable(interactionSource = interaction, indication = null) { onClick() }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .scale(scale)
                .border(3.dp, ring, CircleShape)
                .padding(3.dp)
                .clip(CircleShape)
                .background(Brush.linearGradient(listOf(c.gold, c.mango.deep))),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "🏆", fontSize = 19.sp)
        }
        Spacer(Modifier.height(3.dp))
        Text(
            text = LocalTutiStrings.current.nav.leaderboard,
            style = MaterialTheme.typography.labelSmall,
            color = labelColor,
        )
    }
}
