package app.tuti.tj.ui.components.exercises

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.tuti.tj.ui.theme.TutiMotion
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors

/**
 * Сопоставление пар.
 *
 * Сопоставленная пара не исчезает, а гаснет и помечается
 * галочкой: остаётся видно, что уже связано, и список не
 * «схлопывается» под пальцем во время игры.
 */
@Composable
fun MatchPairsExercise(
    prompt: String,
    leftItems: List<String>,
    rightItems: List<String>,
    matchedLeftIndices: Set<Int>,
    matchedRightIndices: Set<Int>,
    selectedLeft: Int?,
    selectedRight: Int?,
    wrongPair: Pair<Int, Int>?,
    onSelectLeft: (Int) -> Unit,
    onSelectRight: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = prompt,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(TutiSpace.xl))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(TutiSpace.md),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                leftItems.forEachIndexed { idx, item ->
                    PairTile(
                        text = item,
                        matched = matchedLeftIndices.contains(idx),
                        selected = selectedLeft == idx,
                        wrong = wrongPair?.first == idx,
                        onClick = { onSelectLeft(idx) },
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(TutiSpace.sm),
            ) {
                rightItems.forEachIndexed { idx, item ->
                    PairTile(
                        text = item,
                        matched = matchedRightIndices.contains(idx),
                        selected = selectedRight == idx,
                        wrong = wrongPair?.second == idx,
                        onClick = { onSelectRight(idx) },
                    )
                }
            }
        }
    }
}

@Composable
private fun PairTile(
    text: String,
    matched: Boolean,
    selected: Boolean,
    wrong: Boolean,
    onClick: () -> Unit,
) {
    val c = MaterialTheme.tutiColors
    val shape = RoundedCornerShape(TutiRadius.md)

    val bg by animateColorAsState(
        when {
            matched -> c.correctBg
            wrong -> c.wrongBg
            selected -> c.jade.soft
            else -> MaterialTheme.colorScheme.surface
        },
        animationSpec = TutiMotion.fast(),
        label = "pairBg",
    )
    val border by animateColorAsState(
        when {
            matched -> c.correctText
            wrong -> c.wrongText
            selected -> c.jade.base
            else -> c.cardBorder
        },
        animationSpec = TutiMotion.fast(),
        label = "pairBorder",
    )
    val fg = when {
        matched -> c.correctText
        wrong -> c.wrongText
        selected -> c.jade.onSoft
        else -> MaterialTheme.colorScheme.onSurface
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 54.dp)
            .then(if (matched) Modifier.alpha(0.7f) else Modifier)
            .clip(shape)
            .background(bg)
            .border(if (selected || matched || wrong) 2.dp else 1.5.dp, border, shape)
            .clickable(enabled = !matched) { onClick() }
            .padding(horizontal = TutiSpace.md, vertical = TutiSpace.md),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (matched) {
            Text("✓", style = MaterialTheme.typography.titleMedium, color = c.correctText)
            Spacer(Modifier.width(TutiSpace.xs))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = fg,
            textAlign = TextAlign.Center,
        )
    }
}
