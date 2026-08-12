package app.tuti.tj.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState

enum class MascotMood { Happy, Thinking, Excited, Sad, Cool, Wink }

/**
 * Публичная сигнатура намеренно не менялась — все существующие вызовы
 * работают как раньше. Изменилась только отрисовка: вместо кружка с
 * юникод-символами рисуется векторный Тӯтӣ из design/mascot.
 *
 * После редизайна маскота у каждого настроения есть собственное
 * состояние рисунка — маппинг стал один к одному (см. [toState]).
 */
@Composable
fun TutiMascot(
    modifier: Modifier = Modifier,
    size: Dp = 44.dp,
    mood: MascotMood = MascotMood.Happy,
) {
    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        TutiMascotVector(state = mood.toState(), modifier = Modifier.size(size))
    }
}

private fun MascotMood.toState(): TutiState = when (this) {
    MascotMood.Happy -> TutiState.HAPPY
    MascotMood.Excited -> TutiState.CELEBRATE
    MascotMood.Sad -> TutiState.SAD
    MascotMood.Thinking -> TutiState.THINKING
    MascotMood.Cool -> TutiState.HELLO
    MascotMood.Wink -> TutiState.HELLO
}
