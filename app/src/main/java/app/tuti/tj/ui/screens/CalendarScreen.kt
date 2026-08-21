package app.tuti.tj.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiTopBar
import app.tuti.tj.ui.i18n.LocalTutiStrings
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.TutiTheme
import app.tuti.tj.ui.theme.tutiColors
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// ════════════════════════════════════════════════════════════════
//  КАЛЕНДАРЬ
//
//  Отдельный экран под то, как идут дни занятий. С главного он
//  открывается кнопкой в верхней строке: там неделя занимала
//  место над карточкой курса, а смотрят на неё редко.
//
//  Данные те же, что были на главном: даты занятий по выбранному
//  языку. У русского и английского свой счёт, они не суммируются.
// ════════════════════════════════════════════════════════════════

/** Размер галочки в кружке дня. */
private val dayCheckSize = 16.dp

@Composable
fun CalendarScreen(
    repository: TutiRepository,
    onBack: () -> Unit,
) {
    val s = LocalTutiStrings.current.home
    val user by repository.getUserFlow().collectAsState(initial = null)
    val language = if (user?.selectedLanguage == "english") "english" else "russian"

    // Поток пересоздаётся только при смене языка: без remember каждая
    // перерисовка начинала бы подписку заново.
    val weekFlow = remember(language) { repository.getWeekStreaks(language) }
    val weekStreaks by weekFlow.collectAsState(initial = emptyList())
    val streakDates = remember(weekStreaks) { weekStreaks.map { it.date }.toSet() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
    ) {
        TutiTopBar(title = s.calendarTitle, onBack = onBack)

        Column(
            modifier = Modifier.padding(horizontal = TutiSpace.screen),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.lg),
        ) {
            WeeklyStreakCard(streakDates = streakDates)
            Spacer(Modifier.height(TutiSpace.bottomNavGap))
        }
    }
}

/**
 * Неделя занятий.
 *
 * Блок фоновый, а не акцентный: нет пульсации на сегодняшнем дне,
 * нет насыщенной заливки у пройденных, кружки мелкие. Раньше он
 * жил на главном и там намеренно приглушался, чтобы не спорить с
 * карточкой курса; на своём экране оформление осталось прежним.
 */
@Composable
internal fun WeeklyStreakCard(streakDates: Set<String>) {
    val s = LocalTutiStrings.current.home
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.US) }
    val todayStr = remember { dateFormat.format(Calendar.getInstance().time) }
    val weekDays = remember {
        val cal = Calendar.getInstance()
        val today = cal.get(Calendar.DAY_OF_WEEK)
        val mondayOffset = if (today == Calendar.SUNDAY) -6 else Calendar.MONDAY - today
        cal.add(Calendar.DAY_OF_YEAR, mondayOffset)
        (0 until 7).map {
            val date = dateFormat.format(cal.time)
            cal.add(Calendar.DAY_OF_YEAR, 1)
            date
        }
    }
    val todayIndex = weekDays.indexOf(todayStr)
    val doneThisWeek = weekDays.count { it in streakDates }

    TutiCard(
        modifier = Modifier.fillMaxWidth(),
        radius = TutiRadius.xl,
        contentPadding = TutiSpace.lg,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = s.weeklyStreak,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = s.daysThisWeek(doneThisWeek),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
            )
        }

        Spacer(Modifier.height(TutiSpace.md))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            s.weekDays.forEachIndexed { i, label ->
                val dateStr = weekDays.getOrNull(i) ?: ""
                DayCircle(
                    label = label,
                    isCompleted = dateStr in streakDates,
                    isToday = i == todayIndex,
                )
            }
        }
    }
}

/**
 * Кружок дня.
 *
 * Сегодня отмечен тонким зелёным кольцом, пройденный день — мягкой
 * заливкой с галочкой, остальные — почти невидимой серой обводкой.
 */
@Composable
private fun DayCircle(label: String, isCompleted: Boolean, isToday: Boolean) {
    val c = MaterialTheme.tutiColors

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .background(
                    if (isCompleted) c.jade.soft else Color.Transparent,
                    CircleShape,
                )
                .border(
                    width = if (isToday && !isCompleted) 2.dp else 1.dp,
                    color = when {
                        isCompleted -> Color.Transparent
                        isToday -> c.jade.base
                        else -> c.cardBorder
                    },
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = null,
                    tint = c.jade.base,
                    modifier = Modifier.size(dayCheckSize),
                )
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isCompleted || isToday) MaterialTheme.colorScheme.onSurface
            else MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF7F9FC, widthDp = 360)
@Composable
private fun CalendarWeekPreview() {
    TutiTheme {
        Box(Modifier.padding(TutiSpace.screen)) {
            WeeklyStreakCard(streakDates = emptySet())
        }
    }
}
