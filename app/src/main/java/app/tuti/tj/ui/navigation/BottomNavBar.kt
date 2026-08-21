package app.tuti.tj.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.tuti.tj.R
import app.tuti.tj.ui.components.OnboardingOverlay
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.i18n.NavStrings
import app.tuti.tj.ui.theme.NavHomeColor
import app.tuti.tj.ui.theme.NavLessonsColor
import app.tuti.tj.ui.theme.NavPracticeColor
import app.tuti.tj.ui.theme.NavProfileColor
import app.tuti.tj.ui.theme.NavRankingColor
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  НИЖНЯЯ НАВИГАЦИЯ
//
//  Пять разделов с фирменными цветными иконками Tuti. Цвет здесь
//  принадлежит вкладке, а не состоянию: иконки всегда в полную
//  силу, без прозрачности — приглушённые выглядели выцветшими.
//  Выбранную вкладку показывают округлая подложка её цвета, подъём
//  с лёгким увеличением и окрашенная уплотнённая подпись.
//
//  Иконки — растровые ресурсы в собственных цветах, поэтому
//  рисуются с `tint = Color.Unspecified`: любой tint убил бы
//  заливку.
//
//  Подложка занимает постоянное место [tabSlotWidth]×[tabSlotHeight]
//  и появляется только цветом — от этого высота панели не скачет
//  при переключении вкладок.
// ════════════════════════════════════════════════════════════════

/** Видимый размер иконки. */
private val navIconSize = 28.dp

/** Постоянное место под иконку: и подложка, и увеличенная иконка помещаются сюда. */
private val tabSlotWidth = 48.dp
private val tabSlotHeight = 36.dp

/** Минимальная область нажатия вкладки. */
private val tabMinTouch = 48.dp

/** Прозрачность подложки выбранной вкладки. */
private const val BACKDROP_ALPHA = 0.12f

enum class BottomNavItem(
    val route: String,
    @param:DrawableRes val iconRes: Int,
    val color: Color,
) {
    Home("home", R.drawable.nav_home, NavHomeColor),
    Lessons("lessons", R.drawable.nav_lessons, NavLessonsColor),
    Practice("practice", R.drawable.nav_practice, NavPracticeColor),
    Profile("profile", R.drawable.nav_profile, NavProfileColor),
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
                .padding(horizontal = 4.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Рейтинг стоит в центре, поэтому список собирается из двух
            // половин: порядок пунктов задан здесь, а не в enum.
            tabs.take(2).forEach { item ->
                NavTab(
                    iconRes = item.iconRes,
                    label = item.label(nav),
                    color = item.color,
                    selected = currentRoute == item.route,
                    modifier = Modifier.weight(1f),
                    onClick = { switchTo(item.route) },
                )
            }

            NavTab(
                iconRes = R.drawable.nav_ranking,
                label = nav.leaderboard,
                color = NavRankingColor,
                selected = currentRoute == LEADERBOARD_ROUTE,
                modifier = Modifier.weight(1f),
                onClick = { switchTo(LEADERBOARD_ROUTE) },
            )

            tabs.drop(2).forEach { item ->
                NavTab(
                    iconRes = item.iconRes,
                    label = item.label(nav),
                    color = item.color,
                    selected = currentRoute == item.route,
                    // Обучающая подсказка про практику подсвечивает эту
                    // вкладку, а панель живёт вне главного экрана —
                    // координаты кладём в общее хранилище.
                    modifier = Modifier
                        .weight(1f)
                        .then(
                            if (item == BottomNavItem.Practice) {
                                Modifier.onGloballyPositioned { coords ->
                                    val pos = coords.localToWindow(Offset.Zero)
                                    OnboardingOverlay.bounds["bottom_nav_practice"] = Rect(
                                        pos.x,
                                        pos.y,
                                        pos.x + coords.size.width,
                                        pos.y + coords.size.height,
                                    )
                                }
                            } else {
                                Modifier
                            }
                        ),
                    onClick = { switchTo(item.route) },
                )
            }
        }
    }
}

@Composable
private fun NavTab(
    @DrawableRes iconRes: Int,
    label: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Упругая, но короткая: вкладки переключают часто, и долгий
    // отскок начал бы мешать.
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.08f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "tabScale",
    )
    val offsetY by animateDpAsState(
        targetValue = if (selected) (-2).dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "tabOffset",
    )
    val backdrop by animateColorAsState(
        targetValue = if (selected) color.copy(alpha = BACKDROP_ALPHA) else Color.Transparent,
        animationSpec = TutiMotion.normal(),
        label = "tabBackdrop",
    )
    val labelColor by animateColorAsState(
        targetValue = if (selected) color else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = TutiMotion.normal(),
        label = "tabLabel",
    )
    val interaction = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .heightIn(min = tabMinTouch)
            .clip(RoundedCornerShape(TutiRadius.md))
            .clickable(interactionSource = interaction, indication = null) { onClick() }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.size(width = tabSlotWidth, height = tabSlotHeight),
            contentAlignment = Alignment.Center,
        ) {
            // Подложка лежит отдельным слоем и всегда занимает весь слот:
            // появляется она только цветом, поэтому размеры вкладки не
            // меняются и панель не дёргается.
            Box(
                modifier = Modifier
                    .size(width = tabSlotWidth, height = tabSlotHeight)
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .background(backdrop),
            )
            Icon(
                painter = painterResource(iconRes),
                contentDescription = label,
                // Иконки фирменные и цветные — tint уничтожил бы заливку.
                tint = Color.Unspecified,
                // Прозрачности нет ни в одном состоянии: приглушённые
                // иконки выглядели выцветшими. Выбранную вкладку
                // показывают подложка, цветная подпись и подъём.
                modifier = Modifier
                    .size(navIconSize)
                    .offset(y = offsetY)
                    .scale(scale),
            )
        }
        Spacer(Modifier.height(3.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = labelColor,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(tabSlotWidth + 16.dp),
        )
    }
}
