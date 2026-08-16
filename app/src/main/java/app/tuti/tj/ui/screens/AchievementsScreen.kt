package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.subscription.PlusManager
import app.tuti.tj.ui.achievements.Achievement
import app.tuti.tj.ui.achievements.achievementClaimedPrefKey
import app.tuti.tj.ui.achievements.buildAchievements
import app.tuti.tj.ui.achievements.isLockedState
import app.tuti.tj.ui.achievements.migrateChatMessagePrefs
import app.tuti.tj.ui.components.PlusAvatar
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiCelebrationDialog
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import app.tuti.tj.ui.i18n.DEFAULT_CITY
import app.tuti.tj.ui.i18n.LocalTutiStrings

private const val PREFS = "tuti_prefs"
private const val HIDDEN_ACHIEVEMENTS_TEASER = 3

// ════════════════════════════════════════════════════════════════
//  ДОСТИЖЕНИЯ
//
//  Три состояния карточки различаются формой, а не только цветом:
//  полученная — сплошная рамка и золотая награда, доступная —
//  обычная карточка с полосой прогресса, закрытая — пунктир и
//  приглушённая иконка. Так статус читается без чтения текста.
// ════════════════════════════════════════════════════════════════

@Composable
fun AchievementsScreen(
    repository: TutiRepository,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val strings = LocalTutiStrings.current
    val prefs = remember { context.getSharedPreferences(PREFS, Context.MODE_PRIVATE) }
    val scope = rememberCoroutineScope()

    migrateChatMessagePrefs(prefs)

    val user by repository.getUserFlow().collectAsState(initial = null)
    val lessonsDone by repository.getGlobalCompletedLessonsCount().collectAsState(initial = 0)
    val modulesDone by repository.getGlobalCompletedModulesCount().collectAsState(initial = 0)
    val wordsLearned by repository.getTotalLearnedWords().collectAsState(initial = 0)

    var languagesStarted by remember { mutableIntStateOf(0) }
    LaunchedEffect(lessonsDone, wordsLearned) {
        languagesStarted = runCatching { repository.computeLanguagesStartedCount() }.getOrDefault(0)
    }

    val streak = maxOf(user?.currentStreak ?: 0, prefs.getInt("current_streak", 0))
    val chatTotal = remember(lessonsDone, modulesDone, wordsLearned) {
        prefs.getInt("total_chat_messages", prefs.getInt("chat_count", 0))
    }
    val perfectLessons = remember(lessonsDone, modulesDone) {
        prefs.getInt("perfect_lessons", 0)
    }

    val achievements = remember(
        streak, lessonsDone, wordsLearned, chatTotal,
        perfectLessons, modulesDone, languagesStarted, strings,
    ) {
        buildAchievements(
            streak = streak,
            lessonsCompleted = lessonsDone,
            wordsLearned = wordsLearned,
            chatMessages = chatTotal,
            perfectLessons = perfectLessons,
            modulesCompleted = modulesDone,
            languagesStarted = languagesStarted,
            s = strings.achievements,
        )
    }

    val completedCount = achievements.count { it.isCompleted }
    val totalCount = achievements.size

    var globalRank by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        globalRank = runCatching {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@runCatching 0
            val all = FirestoreManager.getAllUsersSortedByXp()
            val idx = all.indexOfFirst { it.uid == uid }
            if (idx < 0) 0 else idx + 1
        }.getOrDefault(0)
    }

    var celebrationAchievement by remember { mutableStateOf<Achievement?>(null) }

    fun findNextCelebration(list: List<Achievement>): Achievement? =
        list.firstOrNull { a ->
            a.isCompleted && !prefs.getBoolean(achievementClaimedPrefKey(a.id), false)
        }

    LaunchedEffect(
        lessonsDone, modulesDone, wordsLearned,
        chatTotal, perfectLessons, streak, languagesStarted,
    ) {
        runCatching {
            delay(120)
            if (celebrationAchievement != null) return@runCatching
            celebrationAchievement = findNextCelebration(
                buildAchievements(
                    streak = streak,
                    lessonsCompleted = lessonsDone,
                    wordsLearned = wordsLearned,
                    chatMessages = chatTotal,
                    perfectLessons = perfectLessons,
                    modulesCompleted = modulesDone,
                    languagesStarted = languagesStarted,
                    s = strings.achievements,
                ),
            )
        }
    }

    val firebaseUser = remember { FirebaseAuth.getInstance().currentUser }
    val displayName = firebaseUser?.displayName?.takeIf { it.isNotBlank() }
        ?: user?.name ?: strings.common.user
    val photoUrl = firebaseUser?.photoUrl?.toString()
    // В prefs город лежит по-таджикски — показываем его на языке интерфейса.
    val userCity = strings.cities.name(
        remember { prefs.getString("user_city", DEFAULT_CITY) ?: DEFAULT_CITY },
    )
    val isPlusUser = remember { PlusManager.isPlusActive(context) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = TutiSpace.screen, vertical = TutiSpace.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TutiIconButton(emoji = "←", onClick = onBack, size = 40.dp)
                Spacer(Modifier.width(TutiSpace.md))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = strings.achievements.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = strings.achievements.unlockedCount(completedCount, totalCount),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = TutiSpace.screen)
                    .padding(bottom = TutiSpace.xxl),
            ) {
                UserStatsCard(
                    photoUrl = photoUrl,
                    name = displayName,
                    city = userCity,
                    isPlusUser = isPlusUser,
                    totalXp = user?.totalXp ?: 0,
                    streak = streak,
                    lessons = lessonsDone,
                    words = wordsLearned,
                    rank = globalRank,
                )

                Spacer(Modifier.height(TutiSpace.lg))

                OverallProgressCard(completed = completedCount, total = totalCount)

                Spacer(Modifier.height(TutiSpace.lg))

                achievements.forEachIndexed { index, ach ->
                    var visible by remember(ach.id) { mutableStateOf(false) }
                    LaunchedEffect(ach.id) {
                        delay(index * 50L)
                        visible = true
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(280)) + slideInVertically(tween(280)) { it / 3 },
                    ) {
                        AchievementCardItem(
                            achievement = ach,
                            locked = isLockedState(ach.progressFraction),
                        )
                    }
                    Spacer(Modifier.height(TutiSpace.sm))
                }

                Spacer(Modifier.height(TutiSpace.xs))
                HiddenAchievementsTeaser()
            }
        }

        celebrationAchievement?.let { ach ->
            TutiCelebrationDialog(
                title = strings.achievements.unlockedTitle,
                message = ach.title,
                onDismiss = { },
                primaryText = strings.achievements.unlockedButton,
                onPrimary = {
                    scope.launch {
                        runCatching {
                            prefs.edit()
                                .putBoolean(achievementClaimedPrefKey(ach.id), true).apply()
                            repository.addXp(ach.xpReward)
                            val uid = FirebaseAuth.getInstance().currentUser?.uid
                            if (uid != null) FirestoreManager.addXp(uid, ach.xpReward)
                        }
                        celebrationAchievement = null
                        val list = buildAchievements(
                            streak = streak,
                            lessonsCompleted = lessonsDone,
                            wordsLearned = wordsLearned,
                            chatMessages = chatTotal,
                            perfectLessons = perfectLessons,
                            modulesCompleted = modulesDone,
                            languagesStarted = languagesStarted,
                            s = strings.achievements,
                        )
                        celebrationAchievement = list.firstOrNull { a ->
                            a.isCompleted &&
                                !prefs.getBoolean(achievementClaimedPrefKey(a.id), false)
                        }
                    }
                },
                stats = listOf(
                    ach.icon to strings.achievements.badgeLabel,
                    "+${ach.xpReward}" to strings.common.points,
                ),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  КАРТОЧКА ПОЛЬЗОВАТЕЛЯ
// ═══════════════════════════════════════════════════

@Composable
private fun UserStatsCard(
    photoUrl: String?,
    name: String,
    city: String,
    isPlusUser: Boolean,
    totalXp: Int,
    streak: Int,
    lessons: Int,
    words: Int,
    rank: Int,
) {
    val c = MaterialTheme.tutiColors

    TutiCard(modifier = Modifier.fillMaxWidth(), contentPadding = TutiSpace.lg) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PlusAvatar(photoUrl = photoUrl, name = name, size = 56, isPlusUser = isPlusUser)
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = city,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            TutiPill(
                text = "$totalXp",
                leadingEmoji = "💎",
                background = c.grape.soft,
                contentColor = c.grape.onSoft,
            )
        }

        Spacer(Modifier.height(TutiSpace.lg))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            val common = LocalTutiStrings.current.common
            MiniStat("🔥", streak.toString(), common.streakLabel, Modifier.weight(1f))
            MiniStat("📚", lessons.toString(), common.lessonsLabel, Modifier.weight(1f))
            MiniStat("⭐", words.toString(), common.wordsLabel, Modifier.weight(1f))
            MiniStat("🏅", if (rank > 0) "#$rank" else "—", common.rankLabel, Modifier.weight(1f))
        }
    }
}

@Composable
private fun MiniStat(icon: String, value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TutiRadius.sm))
            .background(MaterialTheme.tutiColors.tileBg)
            .padding(vertical = TutiSpace.sm),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(icon, fontSize = 15.sp)
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 9.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ═══════════════════════════════════════════════════
//  ОБЩИЙ ПРОГРЕСС
// ═══════════════════════════════════════════════════

@Composable
private fun OverallProgressCard(completed: Int, total: Int) {
    val c = MaterialTheme.tutiColors
    val frac = if (total <= 0) 0f else completed.toFloat() / total.toFloat()

    TutiCard(modifier = Modifier.fillMaxWidth(), contentPadding = TutiSpace.lg) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = LocalTutiStrings.current.achievements.overallProgress,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            TutiPill(text = "$completed/$total")
        }
        Spacer(Modifier.height(TutiSpace.md))
        TutiProgressBar(
            progress = frac,
            height = TutiSize.progressThick,
            colors = listOf(c.mango.base, c.gold),
        )
    }
}

// ═══════════════════════════════════════════════════
//  КАРТОЧКА ДОСТИЖЕНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun AchievementCardItem(achievement: Achievement, locked: Boolean) {
    val c = MaterialTheme.tutiColors
    val completed = achievement.isCompleted && !locked

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = TutiSpace.lg,
        radius = TutiRadius.lg,
        background = if (completed) c.leaf.soft else MaterialTheme.colorScheme.surface,
        borderColor = when {
            completed -> c.correctBorder
            locked -> c.lockedBorder
            else -> c.cardBorder
        },
        dashed = locked,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = if (locked) Modifier.graphicsLayer { alpha = 0.45f } else Modifier,
            ) {
                TutiIconTile(
                    emoji = achievement.icon,
                    size = 52.dp,
                    background = if (completed) {
                        c.leaf.soft
                    } else {
                        c.tileBg
                    },
                    dimmed = locked,
                )
            }
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = achievement.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (locked) c.lockedContent
                        else MaterialTheme.colorScheme.onSurface,
                    )
                    if (completed) {
                        Spacer(Modifier.width(TutiSpace.xs))
                        Text("✅", fontSize = 14.sp)
                    }
                }
                Text(
                    text = achievement.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            when {
                completed -> TutiPill(
                    text = "+${achievement.xpReward}",
                    leadingEmoji = "💎",
                    background = c.mango.soft,
                    contentColor = c.mango.onSoft,
                )
                locked -> Text("🔒", fontSize = 18.sp)
            }
        }

        if (!completed && !locked) {
            Spacer(Modifier.height(TutiSpace.md))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                TutiProgressBar(
                    progress = achievement.progressFraction,
                    modifier = Modifier.weight(1f),
                    height = TutiSize.progressThin,
                    colors = listOf(c.mango.base, c.gold),
                )
                Spacer(Modifier.width(TutiSpace.sm))
                Text(
                    text = "${achievement.currentProgress}/${achievement.target}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  СКРЫТЫЕ ДОСТИЖЕНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun HiddenAchievementsTeaser() {
    val c = MaterialTheme.tutiColors

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = TutiSpace.xl,
        background = c.lockedBg,
        borderColor = c.lockedBorder,
        dashed = true,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(
                        Brush.linearGradient(
                            listOf(c.lockedBg, MaterialTheme.colorScheme.surface),
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text("🔒", fontSize = 24.sp)
            }
            Spacer(Modifier.height(TutiSpace.md))
            Text(
                text = LocalTutiStrings.current.achievements
                    .hiddenTeaser(HIDDEN_ACHIEVEMENTS_TEASER),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(TutiSpace.xs))
            Text(
                text = LocalTutiStrings.current.achievements.hiddenSubtitle,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
