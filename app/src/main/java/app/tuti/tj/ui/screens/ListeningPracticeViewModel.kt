package app.tuti.tj.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.tuti.tj.data.local.entity.LearnedWordEntity
import app.tuti.tj.data.repository.TutiRepository
import kotlinx.coroutines.launch

data class ListeningSessionQuestion(
    val audioText: String,
    val options: List<String>,
    val correctIndex: Int,
)

enum class ListeningAnswerState { NONE, CORRECT, WRONG }

private fun buildListeningQuestions(deck: List<LearnedWordEntity>): List<ListeningSessionQuestion> {
    if (deck.size < PRACTICE_MIN_WORDS_REQUIRED) return emptyList()
    val shuffled = deck.shuffled()
    val selected = shuffled.take(PRACTICE_SESSION_SIZE)
    return selected.map { target ->
        val distractors = (deck - target).shuffled().take(3).map { it.translation }
        val allOptions = (distractors + target.translation).shuffled()
        ListeningSessionQuestion(
            audioText = target.word,
            options = allOptions,
            correctIndex = allOptions.indexOf(target.translation),
        )
    }
}

data class ListeningPracticeUiState(
    val loaded: Boolean = false,
    val deck: List<LearnedWordEntity> = emptyList(),
    val questions: List<ListeningSessionQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val correctCount: Int = 0,
    val answerState: ListeningAnswerState = ListeningAnswerState.NONE,
    val selectedOption: Int = -1,
    val completed: Boolean = false,
) {
    val currentQuestion: ListeningSessionQuestion? = questions.getOrNull(currentIndex)
    val wordsCount: Int = deck.size
    val hasEnoughWords: Boolean = deck.size >= PRACTICE_MIN_WORDS_REQUIRED
    val progress: Float = practiceProgress(currentIndex, questions.size)
}

class ListeningPracticeViewModel(
    private val repository: TutiRepository,
) : ViewModel() {

    var uiState by mutableStateOf(ListeningPracticeUiState())
        private set

    init {
        viewModelScope.launch {
            val snapshot = loadPracticeDeckSnapshot(repository, ::buildListeningQuestions)
            uiState = uiState.copy(
                loaded = true,
                deck = snapshot.deck,
                questions = snapshot.questions,
            )
        }
    }

    fun selectOption(optionIndex: Int): Boolean? {
        val question = uiState.currentQuestion ?: return null
        if (uiState.answerState != ListeningAnswerState.NONE) return null
        val isCorrect = optionIndex == question.correctIndex
        uiState = uiState.copy(
            selectedOption = optionIndex,
            answerState = if (isCorrect) ListeningAnswerState.CORRECT else ListeningAnswerState.WRONG,
            correctCount = uiState.correctCount + if (isCorrect) 1 else 0,
        )
        return isCorrect
    }

    fun advance() {
        val next = uiState.currentIndex + 1
        uiState = if (next >= uiState.questions.size) {
            uiState.copy(completed = true)
        } else {
            uiState.copy(
                currentIndex = next,
                answerState = ListeningAnswerState.NONE,
                selectedOption = -1,
            )
        }
    }

    fun restart() {
        uiState = uiState.copy(
            currentIndex = 0,
            correctCount = 0,
            answerState = ListeningAnswerState.NONE,
            selectedOption = -1,
            completed = false,
        )
    }
}

class ListeningPracticeViewModelFactory(
    private val repository: TutiRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListeningPracticeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListeningPracticeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
