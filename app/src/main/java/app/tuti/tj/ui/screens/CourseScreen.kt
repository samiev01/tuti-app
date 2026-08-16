package app.tuti.tj.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.Course
import app.tuti.tj.data.content.Lesson
import app.tuti.tj.data.content.Module
import app.tuti.tj.data.content.orderedLessons
import app.tuti.tj.data.content.orderedModules
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiEmptyState
import app.tuti.tj.ui.components.kit.TutiGradientCard
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiProgressBar
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings

// ════════════════════════════════════════════════════════════════
//  ЭКРАН КУРСА
//
//  Курс подан как маршрут: узлы уроков соединены линией, которая
//  окрашивается по мере прохождения. Пользователь видит не список,
//  а путь — где он сейчас и сколько до конца модуля.
// ════════════════════════════════════════════════════════════════

@Composable
fun CourseScreen(
    courseId: String,
    repository: TutiRepository,
    onLessonClick: (String) -> Unit,
) {
    val course = remember(courseId) { ContentProvider.getCourseById(courseId) }
    val allProgress by repository.getAllLessonProgress(courseId)
        .collectAsState(initial = emptyList())

    LaunchedEffect(courseId) {
        if (courseId.isNotBlank()) repository.initCourseProgress(courseId)
    }

    if (course == null) {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            TutiEmptyState(
                title = LocalTutiStrings.current.lessons.courseNotFoundTitle,
                message = LocalTutiStrings.current.lessons.courseNotFoundMessage,
                mascotState = TutiState.THINKING,
                modifier = Modifier.align(Alignment.Center),
            )
        }
        return
    }

    val completedCount = allProgress.count { it.completed }
    val totalCount = allProgress.size.coerceAtLeast(1)
    val progressFraction = completedCount.toFloat() / totalCount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(horizontal = TutiSpace.screen),
    ) {
        Spacer(Modifier.height(TutiSpace.md))

        CourseHeader(course, completedCount, totalCount, progressFraction)

        Spacer(Modifier.height(TutiSpace.section))

        // Открытые уроки считаем один раз для всего курса: порядок задаёт
        // контент, а не порядок строк в БД — иначе модули открываются вразнобой.
        val completedIds = allProgress.filter { it.completed }.map { it.lessonId }.toSet()
        val unlockedIds = remember(courseId, completedIds) {
            ContentProvider.getUnlockedLessonIds(courseId, completedIds)
        }

        course.orderedModules.forEach { module ->
            ModuleSection(
                module = module,
                allProgress = allProgress,
                unlockedIds = unlockedIds,
                onLessonClick = onLessonClick,
            )
            Spacer(Modifier.height(TutiSpace.md))
        }

        Spacer(Modifier.height(TutiSpace.bottomNavGap))
    }
}

@Composable
private fun CourseHeader(course: Course, completed: Int, total: Int, progress: Float) {
    val c = MaterialTheme.tutiColors

    TutiGradientCard(
        gradient = c.courseCardGradient,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = TutiSpace.xl,
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(TutiSize.iconTileLg)
                        .clip(RoundedCornerShape(TutiRadius.md))
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(course.emoji, fontSize = 26.sp)
                }
                Spacer(Modifier.width(TutiSpace.md))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                    )
                    Text(
                        text = LocalTutiStrings.current.lessons
                            .percentDone((progress * 100).toInt()),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.85f),
                    )
                }
            }

            Spacer(Modifier.height(TutiSpace.md))

            Text(
                text = course.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f),
            )

            Spacer(Modifier.height(TutiSpace.lg))

            TutiProgressBar(
                progress = progress,
                height = TutiSize.progressThick,
                colors = listOf(Color.White, Color.White),
                trackColor = Color.White.copy(alpha = 0.25f),
            )

            Spacer(Modifier.height(TutiSpace.sm))

            Text(
                text = LocalTutiStrings.current.lessons.lessonsDone(completed, total),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.9f),
            )
        }
    }
}

@Composable
private fun ModuleSection(
    module: Module,
    allProgress: List<LessonProgressEntity>,
    unlockedIds: Set<String>,
    onLessonClick: (String) -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val moduleLessons = allProgress.filter { it.moduleId == module.id }
    val moduleCompleted = moduleLessons.count { it.completed }
    val moduleDone = moduleLessons.isNotEmpty() && moduleCompleted == moduleLessons.size

    // Модуль, в котором идёт работа, раскрыт; завершённые свёрнуты —
    // список не заставляет прокручивать пройденное.
    var expanded by remember(module.id) { mutableStateOf(!moduleDone) }
    val chevronRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = TutiMotion.normal(),
        label = "chevron",
    )

    TutiCard(
        modifier = Modifier.fillMaxWidth().animateContentSize(),
        contentPadding = 0.dp,
        radius = TutiRadius.xl,
        borderColor = if (moduleDone) c.correctBorder else c.cardBorder,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(TutiSpace.lg),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TutiIconTile(
                emoji = module.emoji,
                background = if (moduleDone) c.leaf.soft else c.jade.soft,
            )
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = module.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = module.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Spacer(Modifier.width(TutiSpace.sm))
            TutiPill(
                text = "$moduleCompleted/${module.lessons.size}",
                background = if (moduleDone) c.leaf.soft else c.jade.soft,
                contentColor = if (moduleDone) c.leaf.onSoft else c.jade.onSoft,
            )
            Spacer(Modifier.width(TutiSpace.sm))
            Text(
                text = "▲",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.rotate(chevronRotation),
            )
        }

        if (expanded) {
            Column(
                Modifier.padding(
                    start = TutiSpace.lg,
                    end = TutiSpace.lg,
                    bottom = TutiSpace.lg,
                ),
            ) {
                val lessons = module.orderedLessons
                lessons.forEachIndexed { idx, lesson ->
                    val lessonProgress = allProgress.find { it.lessonId == lesson.id }
                    val isCompleted = lessonProgress?.completed == true
                    val isUnlocked = lesson.id in unlockedIds
                    val isCurrent = isUnlocked && !isCompleted

                    LessonNode(
                        lesson = lesson,
                        isCompleted = isCompleted,
                        isUnlocked = isUnlocked,
                        isCurrent = isCurrent,
                        stars = lessonProgress?.stars ?: 0,
                        isLast = idx == lessons.lastIndex,
                        onClick = {
                            if (isUnlocked) {
                                TutiSoundManager.playLessonStart()
                                onLessonClick(lesson.id)
                            }
                        },
                    )
                }
            }
        }
    }
}

/**
 * Узел маршрута. Текущий урок выделен кольцом и заливкой —
 * взгляд сразу находит «где я остановился».
 */
@Composable
private fun LessonNode(
    lesson: Lesson,
    isCompleted: Boolean,
    isUnlocked: Boolean,
    isCurrent: Boolean,
    stars: Int,
    isLast: Boolean,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TutiRadius.md))
            .then(if (isCurrent) Modifier.background(c.jade.soft.copy(alpha = 0.45f)) else Modifier)
            .clickable(enabled = isUnlocked) { onClick() }
            .padding(horizontal = TutiSpace.sm, vertical = TutiSpace.sm),
        verticalAlignment = Alignment.Top,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                if (isCurrent) {
                    Box(
                        Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(c.jade.base.copy(alpha = 0.18f)),
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isCompleted -> c.leaf.base
                                isCurrent -> c.jade.base
                                else -> c.lockedBg
                            },
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = when {
                            isCompleted -> "✓"
                            !isUnlocked -> "🔒"
                            else -> lesson.emoji
                        },
                        fontSize = if (isCompleted) 20.sp else 18.sp,
                        color = if (isCompleted) Color.White else Color.Unspecified,
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(22.dp)
                        .clip(RoundedCornerShape(TutiRadius.pill))
                        .background(if (isCompleted) c.leaf.base.copy(alpha = 0.45f) else c.progressTrack),
                )
            }
        }

        Spacer(Modifier.width(TutiSpace.md))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 8.dp)
                .then(if (isUnlocked) Modifier else Modifier.alpha(0.55f)),
        ) {
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = lesson.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (isCurrent) {
                Spacer(Modifier.height(TutiSpace.sm))
                TutiPill(
                    text = LocalTutiStrings.current.lessons.startCourse,
                    background = c.jade.base,
                    contentColor = Color.White,
                )
            }
        }

        if (isCompleted && stars > 0) {
            Row(
                modifier = Modifier.padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                repeat(3) { i ->
                    Text(
                        text = if (i < stars) "⭐" else "☆",
                        fontSize = 13.sp,
                        modifier = if (i < stars) Modifier else Modifier.alpha(0.3f),
                    )
                }
            }
        }
    }
}
