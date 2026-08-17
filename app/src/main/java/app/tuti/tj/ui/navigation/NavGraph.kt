package app.tuti.tj.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.tuti.tj.TutiApplication
import app.tuti.tj.ui.screens.CourseScreen
import app.tuti.tj.ui.screens.FinalStepScreen
import app.tuti.tj.ui.screens.FlashcardsScreen
import app.tuti.tj.ui.screens.ListeningPracticeScreen
import app.tuti.tj.ui.screens.WritingPracticeScreen
import app.tuti.tj.ui.screens.HomeScreen
import app.tuti.tj.ui.screens.HomeViewModel
import app.tuti.tj.ui.screens.HomeViewModelFactory
import app.tuti.tj.ui.screens.LeaderboardScreen
import app.tuti.tj.ui.screens.LessonFlowScreen
import app.tuti.tj.ui.screens.LessonsListScreen
import app.tuti.tj.ui.screens.LessonsScreen
import app.tuti.tj.ui.screens.ListeningPracticeViewModel
import app.tuti.tj.ui.screens.ListeningPracticeViewModelFactory
import app.tuti.tj.ui.screens.LessonsViewModel
import app.tuti.tj.ui.screens.LessonsViewModelFactory
import app.tuti.tj.ui.screens.OnboardingScreen
import app.tuti.tj.ui.screens.PlusScreen
import app.tuti.tj.ui.screens.PracticeScreen
import app.tuti.tj.ui.screens.AchievementsScreen
import app.tuti.tj.ui.screens.ProfileScreen
import app.tuti.tj.ui.screens.SignInScreen
import app.tuti.tj.ui.screens.SplashScreen
import app.tuti.tj.ui.screens.TutiChatScreen
import app.tuti.tj.ui.screens.TutiChatViewModel
import app.tuti.tj.ui.screens.WordLearnScreen
import app.tuti.tj.ui.screens.WritingPracticeViewModel
import app.tuti.tj.ui.screens.WritingPracticeViewModelFactory
import kotlinx.coroutines.launch

const val SPLASH_ROUTE = "splash"

/** Вход стоит первым: без аккаунта ответы онбординга сохранять некуда. */
const val SIGN_IN_ROUTE = "sign_in"
const val ONBOARDING_ROUTE = "onboarding"

/** Финальный шаг онбординга: сводка выбора и переход на главную. */
const val FINAL_STEP_ROUTE = "final_step"
const val WORD_LEARN_ROUTE = "word_learn/{topicId}"
const val QUIZ_ROUTE = "quiz/{topicId}"
const val FLASHCARDS_ROUTE = "flashcards"
const val TUTI_CHAT_ROUTE = "tuti_chat"
const val LESSON_FLOW_ROUTE = "lesson_flow/{lessonId}"
const val LISTENING_ROUTE = "listening_practice"
const val WRITING_ROUTE = "writing_practice"
const val LEADERBOARD_ROUTE = "leaderboard"
const val ACHIEVEMENTS_ROUTE = "achievements"
const val PLUS_ROUTE = "plus"
const val COURSE_ROUTE = "course/{courseId}"

fun wordLearnRoute(topicId: String) = "word_learn/$topicId"
fun quizRoute(topicId: String) = "quiz/$topicId"
fun lessonFlowRoute(lessonId: String) = "lesson_flow/$lessonId"
fun courseRoute(courseId: String) = "course/$courseId"

/**
 * Граф всегда стартует с экрана запуска. [resolvedRoute] приходит из
 * MainActivity и до готовности данных равен null: заставка ждёт его и
 * только потом уводит на Home (вернувшийся пользователь) или в
 * онбординг (новый).
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    resolvedRoute: String?,
    modifier: Modifier = Modifier,
) {
    val app = LocalContext.current.applicationContext as TutiApplication
    val repo = app.repository
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = SPLASH_ROUTE,
        modifier = modifier,
    ) {
        composable(SPLASH_ROUTE) {
            SplashScreen(
                nextRoute = resolvedRoute,
                onContinue = { route ->
                    navController.navigate(route) {
                        popUpTo(SPLASH_ROUTE) { inclusive = true }
                    }
                },
            )
        }

        composable(SIGN_IN_ROUTE) {
            SignInScreen(
                repository = repo,
                onNeedsOnboarding = {
                    navController.navigate(ONBOARDING_ROUTE) {
                        popUpTo(SIGN_IN_ROUTE) { inclusive = true }
                    }
                },
                onRestored = {
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(SIGN_IN_ROUTE) { inclusive = true }
                    }
                },
            )
        }

        composable(ONBOARDING_ROUTE) {
            OnboardingScreen(
                repository = repo,
                onComplete = {
                    navController.navigate(FINAL_STEP_ROUTE) {
                        popUpTo(ONBOARDING_ROUTE) { inclusive = true }
                    }
                },
            )
        }

        composable(FINAL_STEP_ROUTE) {
            FinalStepScreen(
                repository = repo,
                onDone = {
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(FINAL_STEP_ROUTE) { inclusive = true }
                    }
                },
            )
        }

        composable(BottomNavItem.Home.route) {
            val user by repo.getUserFlow().collectAsState(initial = null)
            val courseId = user?.courseId ?: ""
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(repo),
            )

            HomeScreen(
                viewModel = homeViewModel,
                onTopicClick = { topicId ->
                    navController.navigate(wordLearnRoute(topicId))
                },
                onContinueCourse = {
                    if (courseId.isNotBlank()) {
                        scope.launch {
                            val next = repo.getNextUncompletedLesson(courseId)
                            if (next != null && next.lessonId.isNotBlank()) {
                                navController.navigate(lessonFlowRoute(next.lessonId))
                            }
                        }
                    }
                },
                onOpenCourse = { cId ->
                    if (cId.isNotBlank()) {
                        navController.navigate(courseRoute(cId))
                    }
                },
                onNavigateToPlus = { navController.navigate(PLUS_ROUTE) },
            )
        }

        composable(BottomNavItem.Lessons.route) {
            LessonsListScreen(
                repository = repo,
                onTopicClick = { topicId ->
                    navController.navigate(wordLearnRoute(topicId))
                },
                onLessonClick = { lessonId ->
                    if (lessonId.isNotBlank()) {
                        navController.navigate(lessonFlowRoute(lessonId))
                    }
                },
            )
        }

        composable(BottomNavItem.Practice.route) {
            PracticeScreen(
                onFlashcards    = { navController.navigate(FLASHCARDS_ROUTE) },
                onOpenChat      = { navController.navigate(TUTI_CHAT_ROUTE) },
                onOpenListening = { navController.navigate(LISTENING_ROUTE) },
                onOpenWriting   = { navController.navigate(WRITING_ROUTE) },
            )
        }

        composable(TUTI_CHAT_ROUTE) { entry ->
            val chatViewModel: TutiChatViewModel = viewModel(entry)
            TutiChatScreen(
                onBack = { navController.popBackStack() },
                viewModel = chatViewModel,
                onNavigateToPlus = { navController.navigate(PLUS_ROUTE) },
            )
        }

        composable(FLASHCARDS_ROUTE) {
            FlashcardsScreen(
                repository = repo,
                onBack = { navController.popBackStack() },
                onGoToLessons = {
                    navController.navigate(BottomNavItem.Lessons.route) {
                        popUpTo(FLASHCARDS_ROUTE) { inclusive = true }
                    }
                },
                onNavigateToPlus = { navController.navigate(PLUS_ROUTE) },
            )
        }
        composable(LISTENING_ROUTE) {
            val listeningViewModel: ListeningPracticeViewModel = viewModel(
                factory = ListeningPracticeViewModelFactory(repo),
            )
            ListeningPracticeScreen(
                viewModel = listeningViewModel,
                onBack = { navController.popBackStack() },
                onGoToLessons = {
                    navController.navigate(BottomNavItem.Lessons.route) {
                        popUpTo(LISTENING_ROUTE) { inclusive = true }
                    }
                },
                onNavigateToPlus = { navController.navigate(PLUS_ROUTE) },
            )
        }
        composable(WRITING_ROUTE) {
            val writingViewModel: WritingPracticeViewModel = viewModel(
                factory = WritingPracticeViewModelFactory(repo),
            )
            WritingPracticeScreen(
                viewModel = writingViewModel,
                onBack = { navController.popBackStack() },
                onGoToLessons = {
                    navController.navigate(BottomNavItem.Lessons.route) {
                        popUpTo(WRITING_ROUTE) { inclusive = true }
                    }
                },
            )
        }

        composable(BottomNavItem.Profile.route) {
            ProfileScreen(
                repository = repo,
                onOpenAchievements = { navController.navigate(ACHIEVEMENTS_ROUTE) },
                onNavigateToPlus = { navController.navigate(PLUS_ROUTE) },
            )
        }

        composable(ACHIEVEMENTS_ROUTE) {
            AchievementsScreen(
                repository = repo,
                onBack = { navController.popBackStack() },
            )
        }

        composable(LEADERBOARD_ROUTE) {
            LeaderboardScreen(repository = repo)
        }

        composable(PLUS_ROUTE) {
            PlusScreen(
                onBack = { navController.popBackStack() },
                onActivated = { navController.popBackStack() },
            )
        }

        composable(
            route = WORD_LEARN_ROUTE,
            arguments = listOf(navArgument("topicId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: "greetings"
            WordLearnScreen(
                topicId = topicId,
                onStartQuiz = {
                    navController.navigate(quizRoute(topicId)) {
                        popUpTo(wordLearnRoute(topicId)) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() },
            )
        }

        composable(
            route = QUIZ_ROUTE,
            arguments = listOf(navArgument("topicId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: "greetings"
            val lessonsViewModel: LessonsViewModel = viewModel(
                backStackEntry,
                factory = LessonsViewModelFactory(topicId, repo),
            )
            LessonsScreen(
                viewModel = lessonsViewModel,
                onFinish = {
                    navController.navigate(BottomNavItem.Home.route) {
                        popUpTo(BottomNavItem.Home.route) { inclusive = true }
                    }
                },
                onTryAgain = {
                    navController.navigate(quizRoute(topicId)) {
                        popUpTo(quizRoute(topicId)) { inclusive = true }
                    }
                },
            )
        }

        composable(
            route = LESSON_FLOW_ROUTE,
            arguments = listOf(navArgument("lessonId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: ""
            LessonFlowScreen(
                lessonId = lessonId,
                repository = repo,
                onFinish = {
                    if (!navController.popBackStack()) {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo(BottomNavItem.Home.route) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() },
                onNavigateToPlus = { navController.navigate(PLUS_ROUTE) },
            )
        }

        composable(
            route = COURSE_ROUTE,
            arguments = listOf(navArgument("courseId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            CourseScreen(
                courseId = courseId,
                repository = repo,
                onLessonClick = { lessonId ->
                    navController.navigate(lessonFlowRoute(lessonId))
                },
            )
        }
    }
}
