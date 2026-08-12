package app.tuti.tj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.ui.theme.AvatarPalette
import app.tuti.tj.ui.theme.tutiColors
import coil.compose.AsyncImage
import kotlin.math.absoluteValue

/**
 * Аватар пользователя.
 *
 * Plus-статус показан не тенью, а золотым кольцом с зазором —
 * приём «медали»: кольцо не сливается с фото и одинаково
 * читается и на светлой, и на тёмной теме.
 */
@Composable
fun PlusAvatar(
    photoUrl: String?,
    name: String,
    size: Int = 40,
    isPlusUser: Boolean = false,
) {
    val c = MaterialTheme.tutiColors
    val ringColor = if (isPlusUser) c.gold else c.cardBorder
    val ringWidth = if (isPlusUser) 2.5.dp else 1.5.dp
    // Зазор между кольцом и фото — кольцо читается как награда,
    // а не как обводка изображения.
    val innerPadding = if (isPlusUser) 3.dp else 2.dp

    Box(
        modifier = Modifier
            .size(size.dp)
            .border(ringWidth, ringColor, CircleShape)
            .padding(innerPadding)
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        if (photoUrl != null) {
            AsyncImage(
                model = photoUrl,
                contentDescription = name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        } else {
            val colorIndex = name.hashCode().absoluteValue % AvatarPalette.size
            val base = AvatarPalette[colorIndex]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(listOf(base, base.copy(alpha = 0.78f))),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = name.firstOrNull()?.uppercase() ?: "?",
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = (size / 2.3).sp,
                    color = Color.White,
                )
            }
        }
    }
}
