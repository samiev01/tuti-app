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

private const val WRITING_MAX_ATTEMPTS = 3

data class WritingSessionQuestion(
    val audioText: String,
    val correctAnswer: String,
    val translation: String,
)

enum class WritingAnswerState { TYPING, CORRECT, WRONG, SHOW_ANSWER }

private fun buildWritingQuestions(deck: List<LearnedWordEntity>): List<WritingSessionQuestion> {
    if (deck.size < PRACTICE_MIN_WORDS_REQUIRED) return emptyList()
    return deck.shuffled().take(PRACTICE_SESSION_SIZE).map { w ->
        WritingSessionQuestion(
            audioText = w.word,
            correctAnswer = w.word,
            translation = w.translation,
        )
    }
}

private fun normalizeWritingAnswer(text: String): String =
    text.trim().lowercase().replace('ё', 'е')

private fun isWritingAnswerCorrect(typed: String, correct: String): Boolean =
    normalizeWritingAnswer(typed) == normalizeWritingAnswer(correct)

data class WritingPracticeUiState(
    val loaded: Boolean = false,
    val deck: List<LearnedWordEntity> = emptyList(),
    val questions: List<WritingSessionQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val correctCount: Int = 0,
    val typedText: String = "",
    val attempts: Int = 0,
    val answerState: WritingAnswerState = WritingAnswerState.TYPING,
    val showHint: Boolean = false,
    val completed: Boolean = false,
) {
    val currentQuestion: WritingSessionQuestion? = questions.getOrNull(currentIndex)
    val wordsCount: Int = deck.size
    val hasEnoughWords: Boolean = deck.size >= PRACTICE_MIN_WORDS_REQUIRED
    val progress: Float = practiceProgress(currentIndex, questions.size)
}

class WritingPracticeViewModel(
    private val repository: TutiRepository,
) : ViewModel() {

    var uiState by mutableStateOf(WritingPracticeUiState())
        private set

    init {
        viewModelScope.launch {
            val snapshot = loadPracticeDeckSnapshot(repository, ::buildWritingQuestions)
            uiState = uiState.copy(
                loaded = true,
                deck = snapshot.deck,
                questions = snapshot.questions,
            )
        }
    }

    fun updateTypedText(text: String) {
        if (uiState.answerState == WritingAnswerState.TYPING || uiState.answerState == WritingAnswerState.WRONG) {
            uiState = uiState.copy(typedText = text)
        }
    }

    fun checkAnswer(): Boolean? {
        val question = uiState.currentQuestion ?: return null
        val typed = uiState.typedText
        if (typed.isBlank()) return null

        return if (isWritingAnswerCorrect(typed, question.correctAnswer)) {
            uiState = uiState.copy(
                answerState = WritingAnswerState.CORRECT,
                correctCount = uiState.correctCount + 1,
            )
            true
        } else {
            val nextAttempts = uiState.attempts + 1
            val nextState = if (nextAttempts >= WRITING_MAX_ATTEMPTS) {
                WritingAnswerState.SHOW_ANSWER
            } else {
                WritingAnswerState.WRONG
            }
            uiState = uiState.copy(
                attempts = nextAttempts,
                answerState = nextState,
                showHint = nextAttempts >= 2 && nextState == WritingAnswerState.WRONG,
            )
            false
        }
    }

    fun retryAfterWrong() {
        uiState = uiState.copy(
            answerState = WritingAnswerState.TYPING,
            typedText = "",
        )
    }

    fun advance() {
        val next = uiState.currentIndex + 1
        uiState = if (next >= uiState.questions.size) {
            uiState.copy(completed = true)
        } else {
            uiState.copy(
                currentIndex = next,
                typedText = "",
                attempts = 0,
                answerState = WritingAnswerState.TYPING,
                showHint = false,
            )
        }
    }

    fun restart() {
        uiState = uiState.copy(
            currentIndex = 0,
            correctCount = 0,
            typedText = "",
            attempts = 0,
            answerState = WritingAnswerState.TYPING,
            showHint = false,
            completed = false,
        )
    }
}

class WritingPracticeViewModelFactory(
    private val repository: TutiRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WritingPracticeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WritingPracticeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
