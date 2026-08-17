package app.tuti.tj

import android.content.Context
import android.os.Bundle
import android.view.animation.PathInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.tuti.tj.ui.navigation.BottomNavBar
import app.tuti.tj.ui.navigation.BottomNavItem
import app.tuti.tj.ui.navigation.bottomBarRoutes
import app.tuti.tj.ui.navigation.NavGraph
import app.tuti.tj.ui.navigation.ONBOARDING_ROUTE
import app.tuti.tj.ui.navigation.SIGN_IN_ROUTE
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.remote.FirestoreManager
import app.tuti.tj.data.sync.CloudSyncManager
import app.tuti.tj.data.user.AuthRepository
import app.tuti.tj.data.user.UserProfileRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import app.tuti.tj.ui.i18n.LanguageManager
import app.tuti.tj.ui.i18n.ProvideTutiStrings
import app.tuti.tj.ui.screens.LanguagePickScreen
import app.tuti.tj.ui.theme.ThemeManager
import app.tuti.tj.ui.theme.ThemeMode
import app.tuti.tj.ui.theme.TutiTheme

/**
 * Сколько ждём Firestore при выборе стартового экрана. Дольше держать
 * заставку нельзя: не ответили — считаем, что профиля нет, и худшее,
 * что случится, — человек ещё раз пройдёт онбординг.
 */
private const val PROFILE_CHECK_TIMEOUT_MS = 5_000L

class MainActivity : ComponentActivity() {

    /** null — стартовый экран ещё не определён, системный сплэш держится. */
    private var startDestination: String? by mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        // Обязательно до super.onCreate: иначе системный сплэш не перехватится.
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Системный сплэш больше не удерживается: ожидание данных взял на
        // себя Compose-экран запуска, а системный отдаёт кадр сразу. Их фоны
        // совпадают, поэтому подмена не видна.
        resolveStartDestination()

        // Фон сплэша брендовый, а первый экран — светлый или тёмный, поэтому
        // уход построен как кроссфейд: цвет не «моргает», а перетекает.
        // Лёгкое увеличение добавляет направление движению, но держится
        // в пределах 6 % — иначе на цветном фоне рывок слишком заметен.
        // Кривая та же, что у переходов внутри приложения (TutiMotion.standard).
        splashScreen.setOnExitAnimationListener { provider ->
            provider.view.animate()
                .alpha(0f)
                .scaleX(1.06f)
                .scaleY(1.06f)
                .setDuration(320L)
                .setInterpolator(PathInterpolator(0.2f, 0f, 0f, 1f))
                .withEndAction { provider.remove() }
                .start()
        }

        enableEdgeToEdge()
        setContent {
            val themeMode by ThemeManager.themeMode.collectAsState()
            val darkTheme = when (themeMode) {
                ThemeMode.DARK   -> true
                ThemeMode.LIGHT  -> false
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            TutiTheme(darkTheme = darkTheme) {
                // Строки раздаются так же, как тема: сменил язык в
                // профиле — всё дерево перерисовалось, Activity при
                // этом не пересоздаётся и навигация не сбрасывается.
                ProvideTutiStrings {
                    // Пока язык не выбран, приложения как бы нет: ни
                    // навигации, ни нижней панели — только вопрос.
                    // Иначе первый экран пришлось бы рисовать на
                    // языке, который за человека никто не выбирал.
                    val languageChosen by LanguageManager.isChosen.collectAsState()
                    if (!languageChosen) {
                        LanguagePickScreen()
                        return@ProvideTutiStrings
                    }

                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val showBottomBar = navBackStackEntry?.destination?.route in bottomBarRoutes

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        // Scaffold ничего не отступает сам: системные вставки
                        // обрабатывает каждый экран (statusBarsPadding там, где
                        // нужен отступ, и без него там, где шапка-градиент
                        // должна уходить под статус-бар). Раньше здесь стояли
                        // systemBars, и отступ сверху удваивался — контент
                        // висел заметно ниже, чем задумано.
                        // innerPadding при этом всё равно учитывает высоту
                        // нижней панели, а imePadding в экранах получает
                        // настоящий сдвиг клавиатуры.
                        contentWindowInsets = WindowInsets(0, 0, 0, 0),
                        bottomBar = { if (showBottomBar) BottomNavBar(navController) },
                    ) { innerPadding ->
                        NavGraph(
                            navController = navController,
                            resolvedRoute = startDestination,
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }

    /**
     * Раньше этим занимался Compose-сплэш: ждал 2.7 секунды, проверял БД,
     * синхронизировал Firestore и только потом навигировал.
     *
     * Теперь маршрут решает быстрая локальная проверка, а синхронизация с
     * Firestore ушла в фон — сеть больше не задерживает открытие приложения.
     */
    private fun resolveStartDestination() {
        val repo = (application as TutiApplication).repository
        lifecycleScope.launch {
            // Приветственный звук на сплэше убран: он срабатывал при каждом
            // запуске, в том числе когда приложение открывают в тишине.
            // Звуки остаются там, где они — реакция на действие пользователя.
            val uid = AuthRepository.currentUid

            startDestination = when {
                // Не вошёл — дальше экрана входа делать нечего.
                uid == null -> SIGN_IN_ROUTE

                // Локальный ответ самый быстрый и работает офлайн:
                // если онбординг здесь уже пройден, ходить за этим
                // в Firestore незачем.
                repo.isOnboardingCompleted() -> BottomNavItem.Home.route

                // Вошёл, а локально пусто. Развилка — наличие профиля
                // в облаке, а не факт входа: аккаунт может быть тот же,
                // а Tuti на нём человек ещё не открывал. Сеть здесь
                // ограничена таймаутом, иначе заставка висела бы вечно.
                hasCloudProfile(uid) -> {
                    runCatching { CloudSyncManager.restoreProgress(this@MainActivity) }
                    BottomNavItem.Home.route
                }

                else -> ONBOARDING_ROUTE
            }

            if (startDestination == BottomNavItem.Home.route) {
                launch { syncProfileToFirestore(repo) }
            }
        }
    }

    private suspend fun hasCloudProfile(uid: String): Boolean =
        withTimeoutOrNull(PROFILE_CHECK_TIMEOUT_MS) {
            UserProfileRepository.hasProfile(uid)
        } ?: false

    private suspend fun syncProfileToFirestore(repo: TutiRepository) {
        runCatching {
            val fbUser = FirebaseAuth.getInstance().currentUser ?: return@runCatching
            val prefs = getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            val city = prefs.getString("user_city", "Душанбе") ?: "Душанбе"
            val user = repo.getUserOnce()
            FirestoreManager.saveUserProfile(
                fbUser.uid,
                fbUser.displayName ?: user?.name.orEmpty(),
                city,
                user?.totalXp ?: 0,
            )
            FirestoreManager.updateStreak(fbUser.uid, user?.currentStreak ?: 0)
        }
    }
}
