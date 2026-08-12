package app.tuti.tj.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.notifications.NotificationScheduler
import app.tuti.tj.ui.components.GreetingOrbit
import app.tuti.tj.ui.components.LivingTutiMascot
import app.tuti.tj.ui.components.rememberGoogleSignIn
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.theme.LocalDarkTheme
import app.tuti.tj.ui.theme.TutiLogoFamily
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSize
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  ОНБОРДИНГ
//
//  Каждый шаг окрашен в собственное акцентное семейство палитры.
//  Это не декор: смена цвета — самый дешёвый способ показать,
//  что вопрос сменился, и удержать внимание на семи экранах
//  подряд. Цвета берутся из дизайн-системы, а фон собирается
//  из мягких заливок того же семейства, а не из отдельных
//  «пастельных» наборов.
// ════════════════════════════════════════════════════════════════

private enum class StepTone { Jade, Grape, Mango, Leaf, Sky, Amber, Teal }

private val stepTones = listOf(
    StepTone.Jade,   // 0 — знакомство
    StepTone.Grape,  // 1 — язык
    StepTone.Mango,  // 2 — уровень
    StepTone.Leaf,   // 3 — цель
    StepTone.Sky,    // 4 — время
    StepTone.Amber,  // 5 — город
    StepTone.Teal,   // 6 — готово
)

private data class OptionItem(val emoji: String, val label: String, val sublabel: String)

private val languageOptions = listOf(
    OptionItem("🇷🇺", "Русский язык", "Забони русӣ"),
    OptionItem("🇬🇧", "English", "Забони англисӣ"),
)
private val levelOptions = listOf(
    OptionItem("🌱", "Ибтидоӣ", "Ман навам"),
    OptionItem("📚", "Миёна", "Каме медонам"),
    OptionItem("🚀", "Пешрафта", "Хуб медонам"),
)
private val goalOptions = listOf(
    OptionItem("💼", "Барои кор", "Кор дар Русия/хориҷа"),
    OptionItem("🎓", "Барои таҳсил", "Донишгоҳ/мактаб"),
    OptionItem("✈️", "Барои сафар", "Сайёҳӣ ва муҳоҷират"),
    OptionItem("🧠", "Барои худам", "Рушди шахсӣ"),
)
private val timeOptions = listOf(
    OptionItem("☕", "5 дақиқа", "Оҳиста"),
    OptionItem("📖", "10 дақиқа", "Мӯътадил"),
    OptionItem("💪", "15 дақиқа", "Ҷиддӣ"),
    OptionItem("🔥", "20 дақиқа", "Максимум"),
)
private val cityOptions = listOf(
    OptionItem("🏛️", "Душанбе", "Пойтахт"),
    OptionItem("🏔️", "Хуҷанд", "Шимол"),
    OptionItem("☀️", "Бохтар", "Ҷануб"),
    OptionItem("🌿", "Кӯлоб", "Хатлон"),
    OptionItem("🏰", "Истаравшан", "Суғд"),
    OptionItem("🍑", "Конибодом", "Суғд"),
    OptionItem("📚", "Турсунзода", "НТМ"),
    OptionItem("🎨", "Пенҷикент", "Суғд"),
    OptionItem("🌾", "Ғафуров", "Суғд"),
    OptionItem("🏗️", "Ваҳдат", "НТМ"),
    OptionItem("🌄", "Исфара", "Суғд"),
    OptionItem("🔧", "Норак", "Хатлон"),
    OptionItem("🏭", "Ёвон", "Хатлон"),
    OptionItem("🏘️", "Дигар", "Шаҳри дигар"),
)

private val languageDbValues = listOf("russian", "english")
private val levelDbValues = listOf("beginner", "intermediate", "advanced")
private val goalDbValues = listOf("work", "study", "travel", "personal")
private val dailyMinutesValues = listOf(5, 10, 15, 20)
// Порядок и длина совпадают с cityOptions: выбор хранится индексом,
// поэтому новый город добавляется сразу в оба списка на одну позицию.
private val cityDbValues = listOf(
    "Душанбе", "Хуҷанд", "Бохтар", "Кӯлоб", "Истаравшан", "Конибодом",
    "Турсунзода", "Пенҷикент", "Ғафуров", "Ваҳдат", "Исфара", "Норак",
    "Ёвон", "Дигар",
)

private const val TOTAL_PAGES = 7

// ═══════════════════════════════════════════════════
//  ЭКРАН
// ═══════════════════════════════════════════════════

@Composable
fun OnboardingScreen(repository: TutiRepository, onComplete: () -> Unit) {
    val isDark = LocalDarkTheme.current
    val c = MaterialTheme.tutiColors
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var page by remember { mutableIntStateOf(0) }
    // Вход слился с приветствием: отдельного экрана логина больше нет.
    // Пройти дальше первого шага можно только через Google — вариант
    // «без аккаунта» убран, поэтому здесь нет ветки пропуска.
    var isRestoring by remember { mutableStateOf(false) }
    val alreadySignedIn = remember { FirebaseAuth.getInstance().currentUser != null }

    val signIn = rememberGoogleSignIn {
        scope.launch {
            // У вернувшегося пользователя прогресс лежит в облаке —
            // тогда настройку проходить заново не нужно.
            isRestoring = true
            val restored = runCatching { CloudSyncManager.restoreProgress(context) }
                .getOrDefault(false)
            isRestoring = false
            if (restored) {
                Toast.makeText(
                    context,
                    "☁️ Маълумоти шумо барқарор шуд!",
                    Toast.LENGTH_SHORT,
                ).show()
                onComplete()
            } else {
                page = 1
            }
        }
    }

    var langIdx by remember { mutableStateOf<Int?>(null) }
    var levelIdx by remember { mutableStateOf<Int?>(null) }
    var goalIdx by remember { mutableStateOf<Int?>(null) }
    var timeIdx by remember { mutableStateOf<Int?>(null) }
    var cityIdx by remember { mutableStateOf<Int?>(null) }

    // Акцент шага берётся из палитры дизайн-системы
    val accentPair = when (stepTones[page.coerceIn(stepTones.indices)]) {
        StepTone.Jade -> c.jade
        StepTone.Grape -> c.grape
        StepTone.Mango -> c.mango
        StepTone.Leaf -> c.leaf
        StepTone.Sky -> c.sky
        StepTone.Amber -> c.mango
        StepTone.Teal -> c.jade
    }

    val animAccent by animateColorAsState(accentPair.base, tween(TutiMotion.SLOW), label = "acc")
    val animAccentDeep by animateColorAsState(accentPair.deep, tween(TutiMotion.SLOW), label = "accD")

    // Фон — мягкая заливка того же семейства, растворяющаяся в
    // цвет приложения. Никаких отдельных «пастельных» палитр.
    val bgTop by animateColorAsState(
        if (isDark) accentPair.soft else accentPair.soft,
        tween(TutiMotion.SLOW), label = "bgTop",
    )
    val bgBottom = MaterialTheme.colorScheme.background

    val canAdvance = when (page) {
        0 -> true
        1 -> langIdx != null
        2 -> levelIdx != null
        3 -> goalIdx != null
        4 -> timeIdx != null
        5 -> cityIdx != null
        else -> true
    }

    // Подзаголовок шага «уровень» зависит от выбранного языка —
    // вопрос должен звучать про конкретный язык, а не абстрактно.
    val levelSubtitle = if (langIdx == 1) "Забони англисиро чӣ қадар медонед?"
    else "Забони русиро чӣ қадар медонед?"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(bgTop, bgBottom, bgBottom))),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = TutiSpace.xl)
                .padding(top = TutiSpace.md, bottom = TutiSpace.md),
        ) {
            TopRow(
                page = page,
                accent = animAccent,
                onBack = { if (page > 0) page-- },
                onSkip = { page = TOTAL_PAGES - 1 },
            )

            Spacer(Modifier.height(TutiSpace.md))

            ProgressDots(current = page, accent = animAccent)

            Spacer(Modifier.height(TutiSpace.md))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (page) {
                    0 -> WelcomePage()
                    1 -> SelectionPage(
                        title = "Кадом забон?",
                        subtitle = "Кадом забонро омӯхтан мехоҳед?",
                        options = languageOptions,
                        selected = langIdx,
                        accent = animAccent,
                        onSelect = { langIdx = it },
                    )
                    2 -> SelectionPage(
                        title = "Сатҳи шумо?",
                        subtitle = levelSubtitle,
                        options = levelOptions,
                        selected = levelIdx,
                        accent = animAccent,
                        onSelect = { levelIdx = it },
                    )
                    3 -> SelectionPage(
                        title = "Ҳадафи шумо?",
                        subtitle = "Барои чӣ забон меомӯзед?",
                        options = goalOptions,
                        selected = goalIdx,
                        accent = animAccent,
                        onSelect = { goalIdx = it },
                    )
                    4 -> SelectionPage(
                        title = "Вақти омӯзиш?",
                        subtitle = "Дар як рӯз чанд вақт омӯхтан мехоҳед?",
                        options = timeOptions,
                        selected = timeIdx,
                        accent = animAccent,
                        onSelect = { timeIdx = it },
                    )
                    5 -> SelectionPage(
                        title = "Шаҳри шумо?",
                        subtitle = "Барои рейтинги шаҳрҳо",
                        options = cityOptions,
                        selected = cityIdx,
                        accent = animAccent,
                        onSelect = { cityIdx = it },
                    )
                    6 -> CompletionPage()
                }
            }

            Spacer(Modifier.height(TutiSpace.md))

            // Первый шаг — единственный, где внизу не «Давом», а выбор:
            // войти через Google или продолжить без аккаунта.
            if (page == 0 && !alreadySignedIn) {
                AuthActions(
                    isLoading = signIn.isRunning || isRestoring,
                    onSignIn = signIn.launch,
                )
            } else {
                TutiButton(
                    text = if (page < TOTAL_PAGES - 1) "Давом" else "Оғоз кардан!",
                    onClick = {
                        if (page < TOTAL_PAGES - 1) {
                            page++
                        } else {
                            scope.launch {
                                try {
                                    val language =
                                        languageDbValues.getOrElse(langIdx ?: 0) { "russian" }
                                    val level =
                                        levelDbValues.getOrElse(levelIdx ?: 0) { "beginner" }
                                    val goal = goalDbValues.getOrElse(goalIdx ?: 0) { "personal" }
                                    val minutes = dailyMinutesValues.getOrElse(timeIdx ?: 0) { 5 }
                                    val city = cityDbValues.getOrElse(cityIdx ?: 0) { "Душанбе" }
                                    val langSuffix =
                                        if (language == "english") "english" else "russian"
                                    val courseId = "${goal}_$langSuffix"
                                    repository.saveOnboardingData(
                                        language, level, goal, minutes, courseId,
                                    )
                                    repository.initCourseProgress(courseId)
                                    context.getSharedPreferences(
                                        "tuti_prefs",
                                        android.content.Context.MODE_PRIVATE,
                                    ).edit().putString("user_city", city).apply()
                                    val fbUser = FirebaseAuth.getInstance().currentUser
                                    if (fbUser != null) {
                                        app.tuti.tj.data.remote.FirestoreManager.saveUserProfile(
                                            fbUser.uid, fbUser.displayName ?: "", city, 0,
                                        )
                                    }
                                    NotificationScheduler.scheduleDailyReminders(context)
                                    onComplete()
                                } catch (_: Exception) {
                                    onComplete()
                                }
                            }
                        }
                    },
                    enabled = canAdvance,
                    size = if (page == TOTAL_PAGES - 1) TutiButtonSize.Large
                    else TutiButtonSize.Medium,
                    trailingEmoji = if (page < TOTAL_PAGES - 1) "→" else "🚀",
                    gradient = listOf(animAccent, animAccentDeep),
                )
            }

            Spacer(Modifier.height(TutiSpace.xs))
        }
    }
}

// ═══════════════════════════════════════════════════
//  ВЕРХНЯЯ СТРОКА
// ═══════════════════════════════════════════════════

@Composable
private fun TopRow(
    page: Int,
    accent: Color,
    onBack: () -> Unit,
    onSkip: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (page > 0) {
            Text(
                text = "← Бозгашт",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onBack() }
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            )
        } else {
            Spacer(Modifier.width(80.dp))
        }

        Spacer(Modifier.weight(1f))

        if (page in 1 until TOTAL_PAGES - 1) {
            Text(
                text = "Гузаштан →",
                style = MaterialTheme.typography.labelMedium,
                color = accent,
                modifier = Modifier
                    .clip(RoundedCornerShape(TutiRadius.sm))
                    .clickable { onSkip() }
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  ИНДИКАТОР ШАГОВ
// ═══════════════════════════════════════════════════

@Composable
private fun ProgressDots(current: Int, accent: Color) {
    val c = MaterialTheme.tutiColors
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(TOTAL_PAGES) { i ->
            val isActive = i == current
            val isPassed = i < current
            val w by animateDpAsState(
                if (isActive) 30.dp else 8.dp,
                tween(TutiMotion.NORMAL),
                label = "dotW$i",
            )
            val color by animateColorAsState(
                when {
                    isActive -> accent
                    isPassed -> accent.copy(alpha = 0.45f)
                    else -> c.progressTrack
                },
                tween(TutiMotion.NORMAL),
                label = "dotC$i",
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .height(8.dp)
                    .width(w)
                    .clip(RoundedCornerShape(TutiRadius.pill))
                    .background(color),
            )
        }
    }
}

// ═══════════════════════════════════════════════════
//  ШАГ 0 — ЗНАКОМСТВО
// ═══════════════════════════════════════════════════

@Composable
private fun WelcomePage() {
    val c = MaterialTheme.tutiColors

    Spacer(Modifier.height(TutiSpace.lg))

    // Логотип на первом шаге — это точка входа в приложение,
    // здесь бренд должен быть назван, а не только показан маскотом.
    Text(
        text = "Tuti",
        style = TextStyle(
            fontFamily = TutiLogoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp,
            letterSpacing = 0.sp,
            brush = Brush.verticalGradient(listOf(c.jade.base, c.jade.deep)),
        ),
    )

    Spacer(Modifier.height(TutiSpace.xs))

    Text(
        text = "Забонҳоро осон омӯзед",
        style = MaterialTheme.typography.bodyLarge,
        color = c.jade.onSoft,
        textAlign = TextAlign.Center,
    )

    Spacer(Modifier.height(TutiSpace.lg))

    // Витрина языков: четыре приветствия вокруг маскота. Языков
    // больше, чем курсов, — это заявка на будущий набор.
    GreetingOrbit(stageHeight = 300.dp, mascotSize = 118.dp)

    Spacer(Modifier.height(TutiSpace.lg))
}

// ═══════════════════════════════════════════════════
//  ВХОД НА ПЕРВОМ ШАГЕ
// ═══════════════════════════════════════════════════

/**
 * Вход через Google — единственный способ начать.
 *
 * Выход «без аккаунта» убран намеренно: без Firebase-профиля прогресс
 * живёт только на устройстве и теряется при переустановке, а рейтинг
 * и синхронизация не работают вовсе. Дешевле потребовать вход сразу,
 * чем объяснять потом, почему пропал результат.
 *
 * Правила Google требуют белую поверхность и фирменную букву, поэтому
 * здесь не общая [TutiButton] — но механика нажатия та же «плита»,
 * что и у остальных кнопок приложения.
 */
@Composable
private fun AuthActions(
    isLoading: Boolean,
    onSignIn: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shape = RoundedCornerShape(TutiRadius.lg)
    val sink = if (pressed && !isLoading) TutiSize.plate else 0.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(TutiSize.buttonLg + TutiSize.plate)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TutiSize.buttonLg + TutiSize.plate)
                    .clip(shape)
                    .background(c.cardBorder),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = sink)
                    .height(TutiSize.buttonLg)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.5.dp, c.cardBorder, shape)
                    .clickable(
                        interactionSource = interaction,
                        indication = null,
                        enabled = !isLoading,
                    ) { onSignIn() },
                contentAlignment = Alignment.Center,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.5.dp,
                        color = c.jade.base,
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "G",
                            fontSize = 22.sp,
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
                        Spacer(Modifier.width(TutiSpace.md))
                        Text(
                            text = "Бо Google ворид шавед",
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(TutiSpace.md))

        Text(
            text = "Бо ворид шудан шумо шартҳои истифода\nва сиёсати махфиятро қабул мекунед",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun FloatingDecor(emoji: String, xOff: Int, yOff: Int, durationMs: Int) {
    val inf = rememberInfiniteTransition(label = "decor$emoji")
    val dy by inf.animateFloat(
        initialValue = 0f, targetValue = 10f,
        animationSpec = infiniteRepeatable(
            tween(durationMs, easing = FastOutSlowInEasing), RepeatMode.Reverse,
        ),
        label = "decDy",
    )
    Text(
        text = emoji,
        fontSize = 22.sp,
        modifier = Modifier
            .offset(x = xOff.dp, y = (yOff + dy).dp)
            .alpha(0.55f),
    )
}

// ═══════════════════════════════════════════════════
//  ШАГИ 1–5 — ВЫБОР
// ═══════════════════════════════════════════════════

@Composable
private fun SelectionPage(
    title: String,
    subtitle: String,
    options: List<OptionItem>,
    selected: Int?,
    accent: Color,
    onSelect: (Int) -> Unit,
) {
    val inf = rememberInfiniteTransition(label = "sel")
    val floatY by inf.animateFloat(
        initialValue = 0f, targetValue = 7f,
        animationSpec = infiniteRepeatable(
            tween(2400, easing = FastOutSlowInEasing), RepeatMode.Reverse,
        ),
        label = "sFloatY",
    )

    Spacer(Modifier.height(TutiSpace.sm))

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.offset { IntOffset(0, -floatY.toInt()) }) {
            LivingTutiMascot(size = 92.dp)
        }
    }

    Spacer(Modifier.height(TutiSpace.md))

    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(Modifier.height(TutiSpace.xs))
    Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(horizontal = TutiSpace.lg),
    )

    Spacer(Modifier.height(TutiSpace.xl))

    Column(verticalArrangement = Arrangement.spacedBy(TutiSpace.sm)) {
        options.forEachIndexed { idx, opt ->
            OptionCard(
                option = opt,
                isSelected = selected == idx,
                accent = accent,
                onClick = { onSelect(idx) },
            )
        }
    }

    Spacer(Modifier.height(TutiSpace.lg))
}

@Composable
private fun OptionCard(
    option: OptionItem,
    isSelected: Boolean,
    accent: Color,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.lg)

    val bg by animateColorAsState(
        if (isSelected) accent.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
        tween(TutiMotion.NORMAL), label = "cardBg",
    )
    val borderColor by animateColorAsState(
        if (isSelected) accent else c.cardBorder,
        tween(TutiMotion.NORMAL), label = "brdC",
    )
    val borderW by animateDpAsState(
        if (isSelected) 2.dp else 1.dp, tween(TutiMotion.NORMAL), label = "brdW",
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(bg)
            .border(borderW, borderColor, shape)
            .clickable { onClick() }
            .padding(TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TutiIconTile(
            emoji = option.emoji,
            size = TutiSize.iconTileMd,
            background = if (isSelected) accent.copy(alpha = 0.16f) else c.tileBg,
        )
        Spacer(Modifier.width(TutiSpace.md))
        Column(Modifier.weight(1f)) {
            Text(
                text = option.label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = option.sublabel,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(Modifier.width(TutiSpace.sm))
        SelectionCheck(selected = isSelected, color = accent)
    }
}

// ═══════════════════════════════════════════════════
//  ШАГ 6 — ГОТОВО
// ═══════════════════════════════════════════════════

@Composable
private fun CompletionPage() {
    val c = MaterialTheme.tutiColors
    val inf = rememberInfiniteTransition(label = "done")
    val bounce by inf.animateFloat(
        initialValue = 0f, targetValue = 14f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = FastOutSlowInEasing), RepeatMode.Reverse,
        ),
        label = "bounce",
    )

    Spacer(Modifier.height(TutiSpace.xxl))

    Box(
        modifier = Modifier.fillMaxWidth().height(230.dp),
        contentAlignment = Alignment.Center,
    ) {
        FloatingDecor("🎉", -94, -50, 1600)
        FloatingDecor("⭐", 104, -40, 2000)
        FloatingDecor("✨", -62, 60, 1800)
        FloatingDecor("🎉", 84, 70, 2400)
        FloatingDecor("⭐", -112, 10, 2100)

        Box(
            modifier = Modifier
                .size(220.dp)
                .background(
                    Brush.radialGradient(
                        listOf(c.mango.base.copy(alpha = 0.18f), Color.Transparent),
                    ),
                ),
        )
        Box(modifier = Modifier.offset { IntOffset(0, -bounce.toInt()) }) {
            LivingTutiMascot(size = 144.dp)
        }
    }

    Spacer(Modifier.height(TutiSpace.xl))

    Text(
        text = "Ҳама чиз тайёр! 🎉",
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(Modifier.height(TutiSpace.md))
    Text(
        text = "Tuti курси шуморо тайёр кард.\nБиёед оғоз кунем!",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(horizontal = TutiSpace.xxl),
    )
    Spacer(Modifier.height(TutiSpace.xxxl))
}
