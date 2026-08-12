package app.tuti.tj.ui.screens

import android.Manifest
import android.app.TimePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.TutiTipsManager
import app.tuti.tj.data.auth.GoogleAuthManager
import app.tuti.tj.data.local.entity.UserEntity
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.subscription.PlusManager
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.notifications.NotificationScheduler
import app.tuti.tj.notifications.TutiNotificationManager
import app.tuti.tj.ui.components.PlusAvatar
import app.tuti.tj.ui.components.PlusBadge
import app.tuti.tj.ui.components.rememberGoogleSignIn
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiGhostButton
import app.tuti.tj.ui.components.kit.TutiGradientCard
import app.tuti.tj.ui.components.kit.TutiIconButton
import app.tuti.tj.ui.components.kit.TutiListRow
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.components.kit.TutiSettingsGroup
import app.tuti.tj.ui.theme.ThemeManager
import app.tuti.tj.ui.theme.ThemeMode
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ════════════════════════════════════════════════════════════════
//  ПРОФИЛЬ
//
//  Сверху — «кто я и чего добился», ниже — настройки, собранные
//  в одинаковые группы. Раньше каждая настройка рисовала свою
//  карточку; теперь все они используют TutiSettingsGroup, поэтому
//  список читается как один блок, а не как набор плиток.
// ════════════════════════════════════════════════════════════════

private fun hasNotificationPermission(context: Context): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
    } else true

private data class Stat(val icon: String, val value: String, val label: String)

private fun formatXp(xp: Int): String =
    if (xp >= 1000) "${xp / 1000},${(xp % 1000).toString().padStart(3, '0')}" else xp.toString()

private fun memberSinceText(createdAt: Long): String {
    val fmt = SimpleDateFormat("MMMM yyyy", Locale.forLanguageTag("tg"))
    return "Аз моҳи ${fmt.format(Date(createdAt))} бо Tuti 🦜"
}

// ═══════════════════════════════════════════════════
//  ЭКРАН
// ═══════════════════════════════════════════════════

@Composable
fun ProfileScreen(
    repository: TutiRepository,
    onOpenAchievements: () -> Unit = {},
    onNavigateToPlus: () -> Unit = {},
) {
    val user by repository.getUserFlow().collectAsState(initial = null)
    val u = user ?: UserEntity()

    val language = if (u.selectedLanguage == "english") "english" else "russian"
    val learnedWords by repository.getTotalLearnedWords().collectAsState(initial = 0)
    val completedTopics by repository.getCompletedTopicsCount(language)
        .collectAsState(initial = 0)
    // Уроки курса тоже дарсҳо — раньше в счётчик попадали только свободные темы.
    val completedLessons by repository.getCompletedLessonsCountForLanguageCourse(language)
        .collectAsState(initial = 0)

    val liveStats = remember(u, learnedWords, completedTopics, completedLessons) {
        listOf(
            Stat("🔥", u.currentStreak.toString(), "Рӯзи серия"),
            Stat("💎", formatXp(u.totalXp), "очки"),
            Stat("📝", learnedWords.toString(), "Калимаҳо"),
            Stat("✅", (completedTopics + completedLessons).toString(), "Дарсҳо"),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        AvatarArea(
            name = u.name,
            createdAt = u.createdAt,
            onOpenAchievements = onOpenAchievements,
        )

        Column(
            modifier = Modifier.padding(horizontal = TutiSpace.screen),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.section),
        ) {
            StatsGrid(stats = liveStats)
            PlusStatusSection(onNavigateToPlus = onNavigateToPlus)
            ThemeSettingsSection()
            SoundSettingsSection()
            NotificationSettingsSection()
            HelpSection()
            Spacer(Modifier.height(TutiSpace.bottomNavGap))
        }
    }
}

// ═══════════════════════════════════════════════════
//  1 · ШАПКА С АВАТАРОМ
// ═══════════════════════════════════════════════════

@Composable
private fun AvatarArea(
    name: String,
    createdAt: Long,
    onOpenAchievements: () -> Unit = {},
) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val isPlusUser = remember { PlusManager.isPlusActive(context) }
    val daysRemaining = remember { PlusManager.getDaysRemaining(context) }

    var firebaseUser by remember { mutableStateOf<FirebaseUser?>(Firebase.auth.currentUser) }
    DisposableEffect(Unit) {
        val listener = FirebaseAuth.AuthStateListener { auth -> firebaseUser = auth.currentUser }
        Firebase.auth.addAuthStateListener(listener)
        onDispose { Firebase.auth.removeAuthStateListener(listener) }
    }

    // Вход отсюда ничего не восстанавливает: онбординг уже пройден,
    // достаточно привязать аккаунт — состояние обновит AuthStateListener.
    val signIn = rememberGoogleSignIn { }

    val fbUser = firebaseUser
    val displayName = fbUser?.displayName?.takeIf { it.isNotBlank() } ?: name
    val email = fbUser?.email
    val photoUrl = fbUser?.photoUrl?.toString()

    // Фон шапки меняется у Plus-пользователей: статус виден
    // ещё до того, как взгляд дойдёт до бейджа.
    val bgGradient = if (isPlusUser) {
        Brush.verticalGradient(listOf(c.mango.soft, MaterialTheme.colorScheme.background))
    } else {
        Brush.verticalGradient(listOf(c.jade.soft, MaterialTheme.colorScheme.background))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = TutiRadius.xxl, bottomEnd = TutiRadius.xxl))
            .background(bgGradient)
            .statusBarsPadding()
            .padding(top = TutiSpace.lg, bottom = TutiSpace.xl),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = TutiSpace.md),
        ) {
            TutiIconButton(
                emoji = "🏅",
                onClick = onOpenAchievements,
                size = 40.dp,
                tone = app.tuti.tj.ui.components.kit.TutiButtonTone.Mango,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PlusAvatar(
                photoUrl = photoUrl,
                name = displayName,
                size = 88,
                isPlusUser = isPlusUser,
            )

            Spacer(Modifier.height(TutiSpace.md))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = displayName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                if (isPlusUser) {
                    Spacer(Modifier.width(TutiSpace.sm))
                    TutiPill(
                        text = "Plus",
                        leadingEmoji = "⭐",
                        background = c.mango.base,
                        contentColor = Color.White,
                    )
                }
            }

            if (!email.isNullOrBlank()) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.height(2.dp))

            Text(
                text = memberSinceText(createdAt),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            if (isPlusUser) {
                Spacer(Modifier.height(TutiSpace.sm))
                PlusBadge(daysRemaining = daysRemaining)
            }

            Spacer(Modifier.height(TutiSpace.md))

            if (fbUser != null) {
                TutiGhostButton(
                    text = "Баромадан аз аккаунт",
                    onClick = { GoogleAuthManager(context).signOut() },
                    color = MaterialTheme.colorScheme.error,
                )
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(TutiRadius.pill))
                        .background(MaterialTheme.colorScheme.surface)
                        .border(1.5.dp, c.cardBorder, RoundedCornerShape(TutiRadius.pill))
                        .clickable(enabled = !signIn.isRunning) { signIn.launch() }
                        .padding(horizontal = TutiSpace.xl, vertical = TutiSpace.md),
                    contentAlignment = Alignment.Center,
                ) {
                    if (signIn.isRunning) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                            color = c.jade.base,
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "G",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                style = TextStyle(
                                    brush = Brush.linearGradient(
                                        listOf(
                                            Color(0xFF4285F4), Color(0xFF34A853),
                                            Color(0xFFFBBC05), Color(0xFFEA4335),
                                        ),
                                    ),
                                ),
                            )
                            Spacer(Modifier.width(TutiSpace.sm))
                            Text(
                                text = "Бо Google ворид шавед",
                                style = MaterialTheme.typography.labelLarge,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }
        }
    }

    Spacer(Modifier.height(TutiSpace.lg))
}

// ═══════════════════════════════════════════════════
//  2 · СТАТИСТИКА
// ═══════════════════════════════════════════════════

@Composable
private fun StatsGrid(stats: List<Stat>) {
    val c = MaterialTheme.tutiColors
    val accents = listOf(c.mango, c.grape, c.sky, c.leaf)

    Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
        for (row in 0..1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                for (col in 0..1) {
                    val idx = row * 2 + col
                    StatCard(
                        stat = stats[idx],
                        accentBg = accents[idx].soft,
                        accentFg = accents[idx].onSoft,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    stat: Stat,
    accentBg: Color,
    accentFg: Color,
    modifier: Modifier = Modifier,
) {
    TutiCard(
        modifier = modifier,
        radius = TutiRadius.lg,
        contentPadding = TutiSpace.lg,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .background(accentBg),
                contentAlignment = Alignment.Center,
            ) {
                Text(stat.icon, fontSize = 20.sp)
            }
            Spacer(Modifier.height(TutiSpace.sm))
            Text(
                text = stat.value,
                style = MaterialTheme.typography.displaySmall,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = stat.label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  3 · PLUS
//
//  Карточки рейтинга здесь больше нет: он переехал в центр нижней
//  панели и стал самостоятельным разделом. Дублировать вход в него
//  из профиля значило бы вести в один экран двумя разными путями.
// ═══════════════════════════════════════════════════

@Composable
private fun PlusStatusSection(onNavigateToPlus: () -> Unit) {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val isPlus = remember { PlusManager.isPlusActive(context) }
    val daysRemaining = remember { PlusManager.getDaysRemaining(context) }

    TutiGradientCard(
        gradient = c.plusGradient,
        modifier = Modifier.fillMaxWidth(),
        onClick = if (isPlus) null else onNavigateToPlus,
        radius = TutiRadius.lg,
        contentPadding = TutiSpace.lg,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("⭐", fontSize = 26.sp)
            Spacer(Modifier.width(TutiSpace.md))
            Column(Modifier.weight(1f)) {
                Text(
                    text = if (isPlus) "Tuti Plus фаъол" else "Tuti Plus гиред",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                )
                Text(
                    text = if (isPlus) "$daysRemaining рӯз боқӣ"
                    else "Дарсҳои бемаҳдуд ва бисёр аз ин зиёд!",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f),
                )
            }
            Text(if (isPlus) "✅" else "→", fontSize = 20.sp, color = Color.White)
        }
    }
}

// ═══════════════════════════════════════════════════
//  4 · ТЕМА
// ═══════════════════════════════════════════════════

@Composable
private fun ThemeSettingsSection() {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val themeMode by ThemeManager.themeMode.collectAsState()

    TutiSettingsGroup(title = "Мавзӯъ 🎨") {
        val options = listOf(
            ThemeMode.SYSTEM to ("📱" to "Системавӣ"),
            ThemeMode.LIGHT to ("☀️" to "Рӯшан"),
            ThemeMode.DARK to ("🌙" to "Торик"),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.sm),
        ) {
            options.forEach { (mode, labelPair) ->
                val (emoji, label) = labelPair
                val isSelected = themeMode == mode
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(TutiRadius.md))
                        .background(if (isSelected) c.jade.base else c.tileBg)
                        .clickable { ThemeManager.setThemeMode(context, mode) }
                        .padding(vertical = TutiSpace.md),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(emoji, fontSize = 18.sp)
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isSelected) Color.White
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════
//  5 · ЗВУК
// ═══════════════════════════════════════════════════

@Composable
private fun SoundSettingsSection() {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val scope = rememberCoroutineScope()
    var soundsOn by remember { mutableStateOf(TutiSoundManager.isEnabled()) }

    TutiSettingsGroup(title = "Садоҳо 🔊") {
        TutiListRow(
            title = "Садои барнома",
            subtitle = if (soundsOn) "Садоҳо фаъол ҳастанд" else "Садоҳо хомӯш ҳастанд",
            emoji = if (soundsOn) "🔊" else "🔇",
            tileBackground = c.sky.soft,
            trailing = {
                Switch(
                    checked = soundsOn,
                    onCheckedChange = { on ->
                        soundsOn = on
                        TutiSoundManager.setEnabled(on)
                        if (on) TutiSoundManager.playButtonClick()
                        scope.launch { runCatching { CloudSyncManager.saveProgress(context) } }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = c.jade.base,
                    ),
                )
            },
        )
    }
}

// ═══════════════════════════════════════════════════
//  6 · УВЕДОМЛЕНИЯ
// ═══════════════════════════════════════════════════

@Composable
private fun NotificationSettingsSection() {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors
    val prefs = remember { context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE) }
    var enabled by remember { mutableStateOf(prefs.getBoolean("reminders_enabled", true)) }
    var selectedHour by remember { mutableIntStateOf(prefs.getInt("reminder_hour", 19)) }
    var selectedMinute by remember { mutableIntStateOf(prefs.getInt("reminder_minute", 0)) }

    val hasPermission = remember { mutableStateOf(hasNotificationPermission(context)) }
    val pendingAction = remember { mutableStateOf<(() -> Unit)?>(null) }

    val permLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        hasPermission.value = granted
        if (granted) {
            pendingAction.value?.invoke()
            pendingAction.value = null
        } else {
            enabled = false
            prefs.edit().putBoolean("reminders_enabled", false).apply()
            Toast.makeText(
                context,
                "Барои ёдоварӣ иҷозати огоҳинома лозим аст",
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    fun scheduleAndConfirm() {
        NotificationScheduler.scheduleDailyReminders(context)
        val timeStr = String.format("%d:%02d", selectedHour, selectedMinute)
        Toast.makeText(
            context,
            "Ёдоварӣ барои соати $timeStr гузошта шуд 🔔",
            Toast.LENGTH_SHORT,
        ).show()
        val appContext = context.applicationContext
        Handler(Looper.getMainLooper()).postDelayed({
            TutiNotificationManager.showNotStudiedReminder(
                appContext, streak = 1, isLateReminder = false,
            )
        }, 2 * 60 * 1000L)
    }

    fun withPermission(action: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasPermission.value) {
            pendingAction.value = action
            permLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            action()
        }
    }

    TutiSettingsGroup(title = "Огоҳиномаҳо 🔔") {
        // Баннер разрешения показывается только когда оно нужно —
        // и сразу объясняет, зачем.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasPermission.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(TutiRadius.md))
                    .background(c.mango.soft)
                    .clickable { permLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) }
                    .padding(TutiSpace.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("🔔", fontSize = 20.sp)
                Spacer(Modifier.width(TutiSpace.md))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Иҷозати огоҳинома",
                        style = MaterialTheme.typography.titleSmall,
                        color = c.mango.onSoft,
                    )
                    Text(
                        text = "Барои ёдоварӣ иҷозат диҳед",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Text(
                    text = "Иҷозат →",
                    style = MaterialTheme.typography.labelMedium,
                    color = c.mango.base,
                )
            }
            Spacer(Modifier.height(TutiSpace.sm))
        }

        TutiListRow(
            title = "Ёдоварии ҳаррӯза",
            subtitle = "Tuti ба шумо дар бораи омӯзиш ёдоварӣ мекунад",
            emoji = "⏰",
            tileBackground = c.mango.soft,
            trailing = {
                Switch(
                    checked = enabled,
                    onCheckedChange = { on ->
                        enabled = on
                        prefs.edit().putBoolean("reminders_enabled", on).apply()
                        if (on) {
                            withPermission { scheduleAndConfirm() }
                        } else {
                            NotificationScheduler.cancelAll(context)
                            Toast.makeText(context, "Ёдоварӣ хомӯш шуд", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = c.jade.base,
                    ),
                )
            },
        )

        if (enabled) {
            TutiListRow(
                title = "Вақти ёдоварӣ",
                subtitle = "Ёдоварии бегоҳӣ",
                emoji = "🕐",
                tileBackground = c.grape.soft,
                trailing = {
                    TutiPill(
                        text = String.format("%d:%02d", selectedHour, selectedMinute),
                        background = c.jade.soft,
                        contentColor = c.jade.onSoft,
                        onClick = {
                            TimePickerDialog(
                                context,
                                { _, h, m ->
                                    selectedHour = h
                                    selectedMinute = m
                                    prefs.edit()
                                        .putInt("reminder_hour", h)
                                        .putInt("reminder_minute", m)
                                        .apply()
                                    NotificationScheduler.rescheduleEvening(context, h, m)
                                    Toast.makeText(
                                        context,
                                        "Ёдоварӣ барои соати ${String.format("%d:%02d", h, m)} " +
                                            "гузошта шуд 🔔",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                },
                                selectedHour,
                                selectedMinute,
                                true,
                            ).show()
                        },
                    )
                },
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  7 · ПОМОЩЬ
// ═══════════════════════════════════════════════════

@Composable
private fun HelpSection() {
    val context = LocalContext.current
    val c = MaterialTheme.tutiColors

    TutiSettingsGroup(title = "Кӯмак 💡") {
        TutiListRow(
            title = "Роҳнамои барнома",
            subtitle = "Ҳамаи маслиҳатҳои Tuti-ро аз нав нишон диҳед",
            emoji = "💡",
            tileBackground = c.leaf.soft,
            onClick = {
                TutiTipsManager.resetAllTips(context)
                context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean("tooltips_shown", false)
                    .apply()
                Toast.makeText(
                    context,
                    "Роҳнамоҳо аз нав нишон дода мешаванд 💡",
                    Toast.LENGTH_SHORT,
                ).show()
            },
            trailing = {
                Text(
                    text = "Барқарор →",
                    style = MaterialTheme.typography.labelMedium,
                    color = c.jade.base,
                )
            },
        )
    }
}
