package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.MilitaryTech
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.data.remote.LeaderUserScore
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiEmptyState
import app.tuti.tj.ui.components.kit.TutiGradientCard
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiSkeletonCard
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.AvatarPalette
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import app.tuti.tj.ui.i18n.DEFAULT_CITY
import app.tuti.tj.ui.i18n.LocalTutiStrings

// ════════════════════════════════════════════════════════════════
//  РЕЙТИНГ
//
//  Соревновательный экран, но оформлен как остальные: очень
//  светлый фон, белые карточки с тонкой границей, тёмно-синий
//  текст. Раньше шапка с карточкой профиля лежала на брендовом
//  градиенте, и экран выпадал из системы.
//
//  Цвета медалей вынесены в палитру (gold/silver/bronze) — они
//  используются и здесь, и в достижениях.
// ════════════════════════════════════════════════════════════════

private val cityEmojiMap = mapOf(
    "Душанбе" to "🏛️",
    "Хуҷанд" to "🏔️",
    "Кӯлоб" to "🌿",
    "Бохтар" to "☀️",
    "Истаравшан" to "🏰",
    "Конибодом" to "🍑",
    "Пенҷикент" to "🎨",
    "Ғафуров" to "🌾",
    "Ваҳдат" to "🏗️",
    "Турсунзода" to "📚",
    "Ёвон" to "🏭",
)

private fun cityEmoji(city: String) = cityEmojiMap[city] ?: "📍"

// ═══════════════════════════════════════════════════
//  ЭКРАН
// ═══════════════════════════════════════════════════

/**
 * Рейтинг — раздел нижней панели, а не экран поверх остальных,
 * поэтому здесь нет стрелки «назад»: выход отсюда — переключение
 * вкладки, как и из любого другого раздела.
 */
@Composable
fun LeaderboardScreen(
    repository: TutiRepository,
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val prefs = remember { context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE) }
    val fbUser = remember { FirebaseAuth.getInstance().currentUser }
    val strings = LocalTutiStrings.current
    // Значение из prefs — таджикское: по нему сравниваются города
    // в рейтинге. Переводим только то, что уходит в разметку.
    val userCity = remember { prefs.getString("user_city", DEFAULT_CITY) ?: DEFAULT_CITY }

    val localUser by repository.getUserFlow().collectAsState(initial = null)
    val statsLanguage = localUser?.selectedLanguage ?: "russian"
    val learnedWords by repository.getTotalLearnedWords().collectAsState(initial = 0)
    val completedTopics by repository.getCompletedTopicsCount(statsLanguage)
        .collectAsState(initial = 0)
    // Считаем так же, как в профиле: свободные темы плюс уроки курса.
    val completedLessons by repository.getCompletedLessonsCountForLanguageCourse(statsLanguage)
        .collectAsState(initial = 0)

    var allUsers by remember { mutableStateOf<List<LeaderUserScore>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isOffline by remember { mutableStateOf(false) }
    var filterMyCity by remember { mutableStateOf(false) }
    var refreshKey by remember { mutableStateOf(0) }

    LaunchedEffect(refreshKey) {
        if (refreshKey > 0) isLoading = true
        try {
            allUsers = FirestoreManager.getAllUsersSortedByXp()
            isOffline = false
        } catch (_: Exception) {
            isOffline = true
        }
        isLoading = false
    }

    if (fbUser == null) {
        NotSignedIn()
        return
    }

    val currentUid = fbUser.uid
    val displayedUsers = if (filterMyCity) allUsers.filter { it.city == userCity } else allUsers
    val myGlobalRank = allUsers.indexOfFirst { it.uid == currentUid }
        .let { if (it < 0) 0 else it + 1 }
    val myEntry = allUsers.firstOrNull { it.uid == currentUid }
    val myXp = myEntry?.xp ?: (localUser?.totalXp ?: 0)
    val myStreak = myEntry?.streak ?: (localUser?.currentStreak ?: 0)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        item(key = "header") {
            Header(
                fbUser = fbUser,
                myGlobalRank = myGlobalRank,
                userCity = userCity,
                xp = myXp,
                streak = myStreak,
                lessons = completedTopics + completedLessons,
                words = learnedWords,
            )
        }

        if (isOffline && allUsers.isNotEmpty()) {
            item(key = "offline") { OfflineBanner() }
        }

        item(key = "filter") {
            FilterButtons(
                filterMyCity = filterMyCity,
                onAll = { filterMyCity = false },
                onMyCity = { filterMyCity = true },
            )
        }

        when {
            isLoading -> {
                item(key = "loading") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = TutiSpace.screen),
                        verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
                    ) {
                        repeat(6) { TutiSkeletonCard(height = 64.dp) }
                    }
                }
            }

            displayedUsers.isEmpty() -> {
                item(key = "empty") {
                    TutiEmptyState(
                        title = strings.leaderboard.emptyTitle,
                        message = if (filterMyCity) {
                            strings.leaderboard.emptyMessageCity
                        } else {
                            strings.leaderboard.emptyMessageGlobal
                        },
                        mascotState = TutiState.THINKING,
                    )
                }
            }

            else -> {
                if (displayedUsers.size >= 2) {
                    item(key = "podium") {
                        Podium(
                            users = displayedUsers.take(3),
                            currentUid = currentUid,
                        )
                    }
                }

                // Сколько не хватает до вершины — спокойной строкой
                // под подиумом. Когда-то это была яркая карточка и она
                // перетягивала внимание с самого рейтинга; теперь это
                // тихая подсказка того же веса, что подписи вокруг.
                val leaderXp = displayedUsers.firstOrNull()?.xp ?: 0
                val gapToFirst = leaderXp - myXp
                if (gapToFirst > 0) {
                    item(key = "gap") { PointsToFirst(points = gapToFirst) }
                }

                itemsIndexed(
                    items = displayedUsers.drop(3),
                    key = { _, u -> "row_${u.uid}" },
                ) { index, user ->
                    UserRow(
                        rank = index + 4,
                        user = user,
                        colorIndex = displayedUsers.indexOf(user),
                        isMe = user.uid == currentUid,
                        animIndex = index,
                    )
                }
                item(key = "tail") {
                    Spacer(Modifier.height(TutiSpace.xxxl))
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  ШАПКА С КАРТОЧКОЙ ПРОФИЛЯ
// ═══════════════════════════════════════════════════

@Composable
private fun Header(
    fbUser: FirebaseUser,
    myGlobalRank: Int,
    userCity: String,
    xp: Int,
    streak: Int,
    lessons: Int,
    words: Int,
) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = TutiSpace.screen)
            .padding(top = TutiSpace.sm, bottom = TutiSpace.md),
    ) {
        Text(
            text = strings.leaderboard.title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(Modifier.height(TutiSpace.md))

        ProfileCard(
            fbUser = fbUser,
            myGlobalRank = myGlobalRank,
            userCity = userCity,
            xp = xp,
            streak = streak,
            lessons = lessons,
            words = words,
        )
    }
}

/** Свой результат всегда перед глазами — первой карточкой экрана. */
@Composable
private fun ProfileCard(
    fbUser: FirebaseUser,
    myGlobalRank: Int,
    userCity: String,
    xp: Int,
    streak: Int,
    lessons: Int,
    words: Int,
) {
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val name = fbUser.displayName?.takeIf { it.isNotBlank() } ?: strings.common.user
    val photoUrl = fbUser.photoUrl?.toString()

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        radius = TutiRadius.xl,
        contentPadding = TutiSpace.lg,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Avatar(
                photoUrl = photoUrl,
                name = name,
                size = 56.dp,
                colorIndex = 0,
            )
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${cityEmoji(userCity)} ${strings.cities.name(userCity)}" +
                        if (myGlobalRank > 0) " · #$myGlobalRank" else "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(Modifier.width(TutiSpace.sm))
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$xp",
                    style = MaterialTheme.typography.headlineSmall,
                    color = c.jade.base,
                )
                Text(
                    text = strings.common.points,
                    style = MaterialTheme.typography.labelSmall,
                    color = c.jade.base,
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.md))
        Box(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(c.divider),
        )
        Spacer(Modifier.height(TutiSpace.md))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            StatItem(Icons.Outlined.LocalFireDepartment, strings.common.streakLabel, "$streak")
            StatSeparator()
            StatItem(Icons.AutoMirrored.Outlined.MenuBook, strings.common.lessonsLabel, "$lessons")
            StatSeparator()
            StatItem(Icons.Outlined.StarBorder, strings.common.wordsLabel, "$words")
            StatSeparator()
            StatItem(
                Icons.Outlined.MilitaryTech,
                strings.common.rankLabel,
                if (myGlobalRank > 0) "#$myGlobalRank" else "—",
            )
        }
    }
}

/** Показатель в карточке профиля: контурный значок, число, подпись. */
@Composable
private fun StatItem(icon: ImageVector, label: String, value: String) {
    val c = MaterialTheme.tutiColors
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = c.jade.base,
            modifier = Modifier.size(22.dp),
        )
        Spacer(Modifier.height(TutiSpace.xs))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun StatSeparator() {
    Box(
        Modifier
            .width(1.dp)
            .height(44.dp)
            .background(MaterialTheme.tutiColors.divider),
    )
}

// ═══════════════════════════════════════════════════
//  ФИЛЬТР
// ═══════════════════════════════════════════════════

@Composable
private fun FilterButtons(
    filterMyCity: Boolean,
    onAll: () -> Unit,
    onMyCity: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.md),
        horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
    ) {
        val s = LocalTutiStrings.current.leaderboard
        FilterTab(s.filterAll, selected = !filterMyCity, onClick = onAll, modifier = Modifier.weight(1f))
        FilterTab(
            s.filterMyCity,
            selected = filterMyCity,
            onClick = onMyCity,
            modifier = Modifier.weight(1f),
        )
    }
}

/**
 * Переключатель охвата рейтинга.
 *
 * Выбранный — залитый фирменным зелёным, невыбранный — белый с
 * тонкой границей, как карточки экрана. Раньше невыбранный был
 * серой плашкой и читался как отключённый.
 */
@Composable
private fun FilterTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.lg)

    Box(
        modifier = modifier
            .clip(shape)
            .background(if (selected) c.jade.base else MaterialTheme.colorScheme.surface)
            .then(
                if (selected) Modifier else Modifier.border(1.dp, c.cardBorder, shape)
            )
            .clickable(onClick = onClick)
            .padding(vertical = TutiSpace.md),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ПОДИУМ
// ═══════════════════════════════════════════════════

/**
 * Тройка лидеров.
 *
 * Подиум лежит в такой же белой карточке, как остальные блоки, и
 * подписан заголовком: раньше он висел прямо на фоне и читался как
 * продолжение списка, а не как отдельная витрина.
 */
@Composable
private fun Podium(users: List<LeaderUserScore>, currentUid: String) {
    TutiCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.screen),
        radius = TutiRadius.xl,
        contentPadding = TutiSpace.lg,
    ) {
        Text(
            text = LocalTutiStrings.current.leaderboard.topThree,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(TutiSpace.lg))
        PodiumRow(users = users, currentUid = currentUid)
    }
}

@Composable
private fun PodiumRow(users: List<LeaderUserScore>, currentUid: String) {
    val c = MaterialTheme.tutiColors

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
    ) {
        users.getOrNull(1)?.let { u ->
            PodiumItem(
                rank = 2, user = u, colorIndex = 1,
                avatarSize = 46.dp, podHeight = 68.dp,
                podColor = c.silver,
                rimColor = if (u.isPlusUser) c.gold else c.silver,
                isMe = u.uid == currentUid,
                modifier = Modifier.weight(1f),
            )
        } ?: Spacer(Modifier.weight(1f))

        users.getOrNull(0)?.let { u ->
            PodiumItem(
                rank = 1, user = u, colorIndex = 0,
                avatarSize = 56.dp, podHeight = 92.dp,
                podColor = c.gold,
                rimColor = c.gold,
                isMe = u.uid == currentUid,
                modifier = Modifier.weight(1f),
            )
        } ?: Spacer(Modifier.weight(1f))

        users.getOrNull(2)?.let { u ->
            PodiumItem(
                rank = 3, user = u, colorIndex = 2,
                avatarSize = 46.dp, podHeight = 52.dp,
                podColor = c.bronze,
                rimColor = if (u.isPlusUser) c.gold else c.bronze,
                isMe = u.uid == currentUid,
                modifier = Modifier.weight(1f),
            )
        } ?: Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun PodiumItem(
    rank: Int,
    user: LeaderUserScore,
    colorIndex: Int,
    avatarSize: Dp,
    podHeight: Dp,
    podColor: Color,
    rimColor: Color,
    isMe: Boolean,
    modifier: Modifier = Modifier,
) {
    val c = MaterialTheme.tutiColors
    val medal = when (rank) {
        1 -> "🥇"
        2 -> "🥈"
        else -> "🥉"
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(medal, fontSize = if (rank == 1) 22.sp else 18.sp)
        Spacer(Modifier.height(TutiSpace.xs))

        Avatar(
            photoUrl = null,
            name = user.name,
            size = avatarSize,
            colorIndex = colorIndex,
            borderColor = rimColor,
            borderWidth = if (rank == 1) 3.dp else 2.dp,
        )
        Spacer(Modifier.height(TutiSpace.xs))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = user.name.split(" ").first(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (user.isPlusUser) {
                Text(
                    text = " ⭐",
                    fontSize = 10.sp,
                )
            }
        }
        if (user.city.isNotBlank()) {
            Text(
                text = "${cityEmoji(user.city)} ${LocalTutiStrings.current.cities.name(user.city)}",
                style = MaterialTheme.typography.labelSmall,
                fontSize = 9.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(Modifier.height(TutiSpace.xs))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TutiSpace.xs)
                .height(podHeight)
                .clip(RoundedCornerShape(topStart = TutiRadius.sm, topEnd = TutiRadius.sm))
                .background(
                    Brush.verticalGradient(listOf(podColor, podColor.copy(alpha = 0.72f))),
                )
                .then(
                    if (isMe) Modifier.border(
                        2.dp,
                        c.jade.base,
                        RoundedCornerShape(topStart = TutiRadius.sm, topEnd = TutiRadius.sm),
                    ) else Modifier
                ),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "#$rank",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.85f),
                )
                Text(
                    text = "${user.xp}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
                Text(
                    text = LocalTutiStrings.current.common.points,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 9.sp,
                    color = Color.White.copy(alpha = 0.8f),
                )
            }
        }
    }
}

/** «До первого места столько-то» — подсказка под подиумом. */
@Composable
private fun PointsToFirst(points: Int) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.lg)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.md)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, c.cardBorder, shape)
            .padding(horizontal = TutiSpace.lg, vertical = TutiSpace.md),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Outlined.StarBorder,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp),
        )
        Spacer(Modifier.width(TutiSpace.sm))
        Text(
            text = LocalTutiStrings.current.leaderboard.pointsToFirst(points),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

// ═══════════════════════════════════════════════════
//  СТРОКА РЕЙТИНГА
// ═══════════════════════════════════════════════════

@Composable
private fun UserRow(
    rank: Int,
    user: LeaderUserScore,
    colorIndex: Int,
    isMe: Boolean,
    animIndex: Int,
) {
    val c = MaterialTheme.tutiColors

    // Строки въезжают каскадом — список «собирается», а не
    // появляется стеной.
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(animIndex.coerceAtMost(12) * 45L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { 60 },
            animationSpec = tween(280, easing = FastOutSlowInEasing),
        ) + fadeIn(tween(280)),
    ) {
        TutiCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.xs),
            radius = TutiRadius.lg,
            contentPadding = TutiSpace.md,
            background = if (isMe) c.jade.soft else MaterialTheme.colorScheme.surface,
            borderColor = when {
                isMe -> c.jade.base
                user.isPlusUser -> c.gold.copy(alpha = 0.4f)
                else -> c.cardBorder
            },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$rank",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.width(28.dp),
                    textAlign = TextAlign.Center,
                )

                Avatar(null, user.name, 40.dp, colorIndex % AvatarPalette.size)
                Spacer(Modifier.width(TutiSpace.md))

                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = user.name,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false),
                        )
                        if (user.isPlusUser) Text(" ⭐", fontSize = 11.sp)
                        if (isMe) {
                            Spacer(Modifier.width(TutiSpace.xs))
                            TutiPill(
                                text = LocalTutiStrings.current.leaderboard.you,
                                background = c.jade.base,
                                contentColor = Color.White,
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${cityEmoji(user.city)} ${LocalTutiStrings.current.cities.name(user.city)}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        if (user.streak > 0) {
                            Spacer(Modifier.width(TutiSpace.sm))
                            Text(
                                text = "🔥${user.streak}",
                                style = MaterialTheme.typography.labelSmall,
                                color = c.mango.base,
                            )
                        }
                    }
                }

                Spacer(Modifier.width(TutiSpace.sm))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${user.xp}",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (user.isPlusUser) c.gold else c.jade.base,
                    )
                    Text(
                        text = LocalTutiStrings.current.common.points,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  АВАТАР
// ═══════════════════════════════════════════════════

@Composable
private fun Avatar(
    photoUrl: String?,
    name: String,
    size: Dp,
    colorIndex: Int,
    borderColor: Color? = null,
    borderWidth: Dp = 2.dp,
) {
    val context = LocalContext.current
    var hasErr by remember(photoUrl) { mutableStateOf(false) }
    val bg = AvatarPalette[colorIndex % AvatarPalette.size]

    Box(
        modifier = Modifier
            .size(size)
            .then(
                if (borderColor != null) Modifier.border(borderWidth, borderColor, CircleShape)
                else Modifier
            )
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bg),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = name.take(1).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                fontSize = (size.value * 0.38f).sp,
                color = Color.White,
            )
        }
        if (photoUrl != null && !hasErr) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(photoUrl).crossfade(true).build(),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onError = { hasErr = true },
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  СЛУЖЕБНЫЕ СОСТОЯНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun OfflineBanner() {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.sm)
            .clip(RoundedCornerShape(TutiRadius.md))
            .background(c.mango.soft)
            .padding(horizontal = TutiSpace.md, vertical = TutiSpace.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("📵", fontSize = 14.sp)
        Spacer(Modifier.width(TutiSpace.sm))
        Text(
            text = LocalTutiStrings.current.leaderboard.offline,
            style = MaterialTheme.typography.bodySmall,
            color = c.mango.onSoft,
        )
    }
}

@Composable
private fun NotSignedIn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(TutiSpace.xxxl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TutiEmptyState(
            title = LocalTutiStrings.current.leaderboard.signInTitle,
            message = LocalTutiStrings.current.leaderboard.signInMessage,
            mascotState = TutiState.THINKING,
        )
    }
}
