package app.tuti.tj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.tutiColors
import app.tuti.tj.ui.i18n.LocalTutiStrings

/**
 * Статус Plus. Единая капсула в mango-семействе — тот же цвет,
 * что у экрана подписки и у Plus-градиентов, поэтому статус
 * узнаётся в любом месте приложения.
 */
@Composable
fun PlusBadge(daysRemaining: Int = 0) {
    val c = MaterialTheme.tutiColors
    val s = LocalTutiStrings.current
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(c.mango.soft)
            .border(1.dp, c.mango.base.copy(alpha = 0.35f), RoundedCornerShape(TutiRadius.pill))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("⭐", fontSize = 11.sp)
        Spacer(Modifier.width(4.dp))
        Text(
            text = if (daysRemaining > 0) s.plus.plusDaysBadge(daysRemaining) else "Plus",
            style = MaterialTheme.typography.labelSmall,
            color = c.mango.onSoft,
        )
    }
}

/** Бесплатный тариф — нейтральная плашка, без негативной окраски. */
@Composable
fun FreeBadge() {
    val c = MaterialTheme.tutiColors
    Text(
        text = LocalTutiStrings.current.plus.freeBadge,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .clip(RoundedCornerShape(TutiRadius.pill))
            .background(c.tileBg)
            .padding(horizontal = 10.dp, vertical = 4.dp),
    )
}
