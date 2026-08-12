package app.tuti.tj.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.content.QuizQuestion
import app.tuti.tj.data.content.TopicInfo
import app.tuti.tj.data.content.WordItem
import app.tuti.tj.data.repository.TutiRepository
import kotlinx.coroutines.launch

private const val LESSONS_XP_PER_CORRECT = 10

data class LessonsUiState(
    val topicId: String,
    val topicInfo: TopicInfo?,
    val questions: List<QuizQuestion>,
    val currentQ: Int = 0,
    val selected: Int = -1,
    val checked: Boolean = false,
    val hearts: Int = 3,
    val correctCount: Int = 0,
    val completed: Boolean = false,
) {
    val currentQuestion: QuizQuestion? = questions.getOrNull(currentQ)
    val hasSelection: Boolean = selected >= 0
    val isCorrect: Boolean = selected >= 0 && currentQuestion?.correctIndex == selected
    val progress: Float = if (questions.isEmpty()) 0f else if (checked) {
        (currentQ + 1f) / questions.size
    } else {
        currentQ.toFloat() / questions.size
    }
}

class LessonsViewModel(
    private val topicId: String,
    private val repository: TutiRepository,
) : ViewModel() {

    private val topicLanguage = if (topicId.startsWith("en_")) "english" else "russian"
    private val words: List<WordItem> = ContentProvider.getWordsForTopic(topicId)
    private val answeredCorrectly = mutableListOf<Boolean>()
    private var resultsSaved = false

    var uiState by mutableStateOf(
        LessonsUiState(
            topicId = topicId,
            topicInfo = ContentProvider.getTopicInfo(topicId),
            questions = ContentProvider.getQuestionsForTopic(topicId),
        ),
    )
        private set

    fun selectAnswer(index: Int) {
        if (uiState.checked) return
        uiState = uiState.copy(selected = index)
    }

    fun checkAnswer() {
        if (uiState.checked || uiState.selected < 0) return
        val isCorrect = uiState.isCorrect
        answeredCorrectly.add(isCorrect)
        uiState = uiState.copy(
            checked = true,
            correctCount = uiState.correctCount + if (isCorrect) 1 else 0,
            hearts = uiState.hearts - if (isCorrect) 0 else 1,
        )
    }

    fun continueQuiz() {
        if (!uiState.checked) return
        if (uiState.currentQ < uiState.questions.lastIndex) {
            uiState = uiState.copy(
                currentQ = uiState.currentQ + 1,
                selected = -1,
                checked = false,
            )
            return
        }

        uiState = uiState.copy(completed = true)
        saveResultsIfNeeded()
    }

    private fun saveResultsIfNeeded() {
        if (resultsSaved || uiState.questions.isEmpty()) return
        resultsSaved = true

        val finalCorrect = uiState.correctCount
        val totalQ = uiState.questions.size
        val xpEarned = finalCorrect * LESSONS_XP_PER_CORRECT
        val answersSnapshot = answeredCorrectly.toList()

        viewModelScope.launch {
            try {
                repository.recordLessonComplete(
                    topicId = topicId,
                    language = topicLanguage,
                    correctAnswers = finalCorrect,
                    totalQuestions = totalQ,
                    xpEarned = xpEarned,
                )
                Log.d("LessonsViewModel", "recordLessonComplete OK for $topicId")
            } catch (e: Exception) {
                Log.e("LessonsViewModel", "recordLessonComplete FAILED", e)
            }
            try {
                words.forEachIndexed { i, w ->
                    val wasCorrect = answersSnapshot.getOrElse(i) { false }
                    repository.addLearnedWord(
                        word = w.word,
                        translation = w.translation,
                        language = topicLanguage,
                        topicId = topicId,
                        isCorrect = wasCorrect,
                    )
                }
                Log.d("LessonsViewModel", "All ${words.size} words saved for $topicId")
            } catch (e: Exception) {
                Log.e("LessonsViewModel", "addLearnedWord FAILED", e)
            }
        }
    }
}

class LessonsViewModelFactory(
    private val topicId: String,
    private val repository: TutiRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LessonsViewModel(topicId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
