package app.tuti.tj.ui.screens

import app.tuti.tj.data.local.entity.LearnedWordEntity
import app.tuti.tj.data.repository.TutiRepository

const val PRACTICE_SESSION_SIZE = 10
const val PRACTICE_MIN_WORDS_REQUIRED = 4

data class PracticeDeckSnapshot<Q>(
    val deck: List<LearnedWordEntity>,
    val questions: List<Q>,
) {
    val wordsCount: Int get() = deck.size
    val hasEnoughWords: Boolean get() = deck.size >= PRACTICE_MIN_WORDS_REQUIRED
}

suspend fun <Q> loadPracticeDeckSnapshot(
    repository: TutiRepository,
    questionBuilder: (List<LearnedWordEntity>) -> List<Q>,
): PracticeDeckSnapshot<Q> {
    val deck = repository.getFlashcardDeck()
    return PracticeDeckSnapshot(
        deck = deck,
        questions = questionBuilder(deck),
    )
}

fun practiceProgress(currentIndex: Int, total: Int): Float =
    if (total <= 0) 0f else (currentIndex + 1).toFloat() / total
