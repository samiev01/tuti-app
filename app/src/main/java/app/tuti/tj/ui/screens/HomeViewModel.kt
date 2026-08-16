package app.tuti.tj.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.Course
import app.tuti.tj.data.local.entity.LessonProgressEntity
import app.tuti.tj.data.local.entity.TopicProgressEntity
import app.tuti.tj.data.local.entity.UserEntity
import app.tuti.tj.data.repository.TutiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val user: UserEntity = UserEntity(),
    val language: String = "russian",
    val topics: List<TopicProgressEntity> = emptyList(),
    val streakDates: Set<String> = emptySet(),
    val course: Course? = null,
    val courseProgress: List<LessonProgressEntity> = emptyList(),
    /** Очки и серия только по выбранному языку, а не по всему аккаунту. */
    val languageXp: Int = 0,
    val languageStreak: Int = 0,
)

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val repository: TutiRepository,
) : ViewModel() {

    private val userFlow = repository.getUserFlow()
        .map { it ?: UserEntity() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserEntity(),
        )

    private val languageFlow = userFlow
        .map { if (it.selectedLanguage == "english") "english" else "russian" }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "russian",
        )

    private val topicsFlow = languageFlow
        .flatMapLatest { repository.getTopicProgress(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    private val streakDatesFlow = languageFlow
        .flatMapLatest { repository.getWeekStreaks(it) }
        .map { weekStreaks -> weekStreaks.map { it.date }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptySet(),
        )

    private val languageStatsFlow = languageFlow
        .flatMapLatest { repository.getLanguageStats(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    private val courseIdFlow = userFlow
        .map { it.courseId }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "",
        )

    private val courseFlow = courseIdFlow
        .map { courseId ->
            if (courseId.isNotBlank()) ContentProvider.getCourseById(courseId) else null
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    private val courseProgressFlow = courseIdFlow
        .flatMapLatest { courseId ->
            if (courseId.isBlank()) flowOf(emptyList())
            else repository.getAllLessonProgress(courseId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    private val courseBundleFlow = combine(courseFlow, courseProgressFlow) { course, courseProgress ->
        course to courseProgress
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null to emptyList(),
    )

    // combine типизирован до пяти потоков — недельные дни и статистику
    // языка сводим в одну пару заранее.
    private val statsBundleFlow = combine(streakDatesFlow, languageStatsFlow) { dates, stats ->
        dates to stats
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptySet<String>() to null,
    )

    val uiState: StateFlow<HomeUiState> = combine(
        userFlow,
        languageFlow,
        topicsFlow,
        statsBundleFlow,
        courseBundleFlow,
    ) { user, language, topics, statsBundle, courseBundle ->
        val (streakDates, stats) = statsBundle
        HomeUiState(
            user = user,
            language = language,
            topics = topics,
            streakDates = streakDates,
            course = courseBundle.first,
            courseProgress = courseBundle.second,
            languageXp = stats?.totalXp ?: 0,
            languageStreak = stats?.currentStreak ?: 0,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState(),
    )

    init {
        viewModelScope.launch {
            languageFlow.collect {
                repository.ensureTopicsExist(it)
                repository.ensureLanguageStatsExist(it)
            }
        }
        viewModelScope.launch {
            courseIdFlow.collect { courseId ->
                if (courseId.isNotBlank()) {
                    repository.initCourseProgress(courseId)
                    repository.backfillLearnedWordsFromCourses()
                }
            }
        }
    }

    fun setLanguage(language: String) {
        viewModelScope.launch {
            repository.setLanguage(language)
        }
    }
}

class HomeViewModelFactory(
    private val repository: TutiRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
