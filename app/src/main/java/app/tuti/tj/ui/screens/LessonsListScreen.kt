package app.tuti.tj.ui.screens

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiGradientCard
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.components.kit.TutiSkeletonCard
import app.tuti.tj.ui.mascot.TutiMascotStatic
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

// ════════════════════════════════════════════════════════════════
//  ВКЛАДКА «ДАРСҲО»
//
//  Если у пользователя выбран курс — показываем маршрут курса.
//  Иначе это витрина свободных тем: каждая карточка сообщает
//  объём (слова/вопросы), прогресс и одно действие.
// ════════════════════════════════════════════════════════════════

@Composable
fun LessonsListScreen(
    repository: TutiRepository,
    onTopicClick: (String) -> Unit,
    onLessonClick: (String) -> Unit = {},
) {
    val user by repository.getUserFlow().collectAsState(initial = null)

    // Загрузка: скелеты вместо спиннера — переход к контенту
    // ощущается плавнее, экран не «прыгает».
    if (user == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding()
                .padding(horizontal = TutiSpace.screen),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            Spacer(Modifier.height(TutiSpace.lg))
            TutiSkeletonCard(height = 92.dp)
            repeat(4) { TutiSkeletonCard(height = 128.dp) }
        }
        return
    }

    val u = user!!

    if (u.courseId.isNotBlank()) {
        CourseScreen(
            courseId = u.courseId,
            repository = repository,
            onLessonClick = onLessonClick,
        )
        return
    }

    val topics by repository.getTopicProgress(
        if (u.selectedLanguage == "english") "english" else "russian",
    ).collectAsState(initial = emptyList())

    val allTopicInfos = remember { ContentProvider.getAllTopics() }
    val c = MaterialTheme.tutiColors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(horizontal = TutiSpace.screen),
    ) {
        Spacer(Modifier.height(TutiSpace.lg))

        Row(verticalAlignment = Alignment.CenterVertically) {
            TutiMascotStatic(state = TutiState.HAPPY, modifier = Modifier.size(44.dp))
            Spacer(Modifier.width(TutiSpace.md))
            Column {
                Text(
                    text = "Дарсҳо",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Мавзӯъро интихоб кунед",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.section))

        val completedCount = topics.count { it.progressPercent >= 100 }
        val unlockedCount = topics.count { it.isUnlocked }
        val overall = if (allTopicInfos.isEmpty()) 0f
        else completedCount.toFloat() / allTopicInfos.size

        TutiGradientCard(
            gradient = c.streakGradient,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = TutiSpace.lg,
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Пешрафти шумо",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f),
                        )
                        Text(
                            text = "$completedCount аз ${allTopicInfos.size} мавзӯъ тамом шуд",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                        )
                    }
                    Text(
                        text = "🌟 $unlockedCount/${allTopicInfos.size}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                    )
                }
                Spacer(Modifier.height(TutiSpace.md))
                TutiProgressBar(
                    progress = overall,
                    height = TutiSize.progressThin,
                    colors = listOf(Color.White, Color.White),
                    trackColor = Color.White.copy(alpha = 0.25f),
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.lg))

        Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.md)) {
            allTopicInfos.forEach { info ->
                val dbTopic = topics.find { it.topicId == info.id }
                LessonTopicCard(
                    info = info,
                    dbTopic = dbTopic,
                    onClick = {
                        TutiSoundManager.playLessonStart()
                        onTopicClick(info.id)
                    },
                )
            }
        }

        Spacer(Modifier.height(TutiSpace.bottomNavGap))
    }
}

// ═══════════════════════════════════════════════════
//  КАРТОЧКА ТЕМЫ
// ═══════════════════════════════════════════════════

@Composable
private fun LessonTopicCard(
    info: app.tuti.tj.data.content.TopicInfo,
    dbTopic: TopicProgressEntity?,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val progress = (dbTopic?.progressPercent ?: 0) / 100f
    val isLocked = dbTopic?.isUnlocked == false || dbTopic == null
    val isDone = progress >= 1f

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = if (isLocked) null else onClick,
        contentPadding = TutiSpace.lg,
        background = when {
            isDone -> c.leaf.soft
            isLocked -> c.lockedBg
            else -> MaterialTheme.colorScheme.surface
        },
        borderColor = when {
            isDone -> c.correctBorder
            isLocked -> c.lockedBorder
            else -> c.cardBorder
        },
        dashed = isLocked,
    ) {
        Column(modifier = if (isLocked) Modifier.alpha(0.7f) else Modifier) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TutiIconTile(
                    emoji = info.emoji,
                    size = TutiSize.iconTileLg,
                    background = if (isDone) c.leaf.soft else c.jade.soft,
                    dimmed = isLocked,
                )
                Spacer(Modifier.width(TutiSpace.md))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = info.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isLocked) c.lockedContent
                        else MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = info.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Spacer(Modifier.width(TutiSpace.sm))
                when {
                    isLocked -> Text("🔒", fontSize = 20.sp)
                    isDone -> Text("⭐", fontSize = 20.sp)
                    else -> TutiPill(text = "${(progress * 100).toInt()}%")
                }
            }

            if (!isLocked) {
                Spacer(Modifier.height(TutiSpace.md))
                TutiProgressBar(
                    progress = progress,
                    colors = if (isDone) listOf(c.leaf.base, c.leaf.base.copy(alpha = 0.7f))
                    else c.progressGradient,
                )
            }

            Spacer(Modifier.height(TutiSpace.md))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TutiPill(
                    text = "${info.totalWords} калима",
                    leadingEmoji = "📝",
                    background = c.sky.soft,
                    contentColor = c.sky.onSoft,
                )
                TutiPill(
                    text = "${info.totalQuestions} савол",
                    leadingEmoji = "❓",
                    background = c.grape.soft,
                    contentColor = c.grape.onSoft,
                )

                if (!isLocked) {
                    Spacer(Modifier.weight(1f))
                    TutiPill(
                        text = when {
                            isDone -> "Аз нав 🔄"
                            progress == 0f -> "Оғоз →"
                            else -> "Давом →"
                        },
                        background = if (isDone) c.leaf.soft else c.jade.base,
                        contentColor = if (isDone) c.leaf.onSoft else Color.White,
                    )
                }
            }
        }
    }
}
