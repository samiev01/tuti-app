package app.tuti.tj.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tuti.tj.audio.TutiSoundManager
import app.tuti.tj.data.subscription.PlusManager
import app.tuti.tj.data.subscription.PromoCodeManager
import app.tuti.tj.ui.components.kit.TutiButton
import app.tuti.tj.ui.components.kit.TutiButtonSize
import app.tuti.tj.ui.components.kit.TutiButtonTone
import app.tuti.tj.ui.components.kit.TutiCard
import app.tuti.tj.ui.components.kit.TutiIconTile
import app.tuti.tj.ui.components.kit.TutiPill
import app.tuti.tj.ui.mascot.TutiMascotVector
import app.tuti.tj.ui.mascot.TutiState
import app.tuti.tj.ui.theme.TutiRadius
import app.tuti.tj.ui.theme.TutiSpace
import app.tuti.tj.ui.theme.tutiColors
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  TUTI PLUS
//
//  Единственный экран, где допустимо «продавать». Отсюда:
//  золотая hero-шапка с маскотом, преимущества плитками (а не
//  списком мелким текстом), выделенный годовой тариф и понятная
//  инструкция «как купить» перед полем промокода — пользователь
//  не должен искать, что делать после нажатия.
// ════════════════════════════════════════════════════════════════

private data class Benefit(val emoji: String, val text: String)

private val benefits = listOf(
    Benefit("📚", "Дарсҳои бемаҳдуд дар як рӯз"),
    Benefit("🦜", "Суҳбати бемаҳдуд бо Tuti AI"),
    Benefit("🃏", "Корти калимаҳои бемаҳдуд"),
    Benefit("🎧", "Машқи шунавоии бемаҳдуд"),
    Benefit("📞", "Занги овозӣ бо Tuti"),
    Benefit("⭐", "Ҳамаи модулҳо кушода"),
    Benefit("🚫", "Бе реклама"),
)

@Composable
fun PlusScreen(onBack: () -> Unit, onActivated: () -> Unit) {
    val c = MaterialTheme.tutiColors
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var promoCode by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var selectedPlan by remember { mutableStateOf(1) } // 0 — месяц, 1 — год

    val isPlus = remember { PlusManager.isPlusActive(context) }
    val daysRemaining = remember { PlusManager.getDaysRemaining(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
    ) {
        // ── hero-шапка ───────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = TutiRadius.xxl, bottomEnd = TutiRadius.xxl))
                .background(Brush.linearGradient(c.plusGradient)),
        ) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.TopEnd)
                    .background(
                        Brush.radialGradient(
                            listOf(Color.White.copy(alpha = 0.2f), Color.Transparent),
                        ),
                    ),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = TutiSpace.screen)
                    .padding(top = TutiSpace.md, bottom = TutiSpace.xxl),
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(TutiRadius.sm))
                        .background(Color.White.copy(alpha = 0.22f))
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center,
                ) {
                    Text("←", fontSize = 20.sp, color = Color.White)
                }

                Spacer(Modifier.height(TutiSpace.lg))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TutiMascotVector(
                        state = TutiState.CELEBRATE,
                        modifier = Modifier.size(88.dp),
                    )
                    Spacer(Modifier.height(TutiSpace.md))
                    Text(
                        text = "Tuti Plus ⭐",
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White,
                    )
                    Spacer(Modifier.height(TutiSpace.xs))
                    Text(
                        text = if (isPlus) "Фаъол · $daysRemaining рӯз боқӣ"
                        else "Ҳама имконият бе маҳдудият!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.92f),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(TutiSpace.screen),
            verticalArrangement = Arrangement.spacedBy(TutiSpace.section),
        ) {
            // ── преимущества ─────────────────────
            Column {
                Text(
                    text = "Бартариятҳои Plus",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(Modifier.height(TutiSpace.md))
                TutiCard(modifier = Modifier.fillMaxWidth(), contentPadding = TutiSpace.md) {
                    benefits.forEach { b ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = TutiSpace.sm),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TutiIconTile(
                                emoji = b.emoji,
                                size = 38.dp,
                                background = c.mango.soft,
                                radius = TutiRadius.sm,
                            )
                            Spacer(Modifier.width(TutiSpace.md))
                            Text(
                                text = b.text,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f),
                            )
                            Text("✓", fontSize = 16.sp, color = c.leaf.base)
                        }
                    }
                }
            }

            // ── тарифы ───────────────────────────
            Column {
                Text(
                    text = "Нархҳо",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(Modifier.height(TutiSpace.md))

                PlanCard(
                    title = "Моҳона",
                    subtitle = "30 рӯз",
                    price = "29",
                    selected = selectedPlan == 0,
                    accentBase = c.jade.base,
                    accentSoft = c.jade.soft,
                    onClick = { selectedPlan = 0 },
                )
                Spacer(Modifier.height(TutiSpace.sm))
                PlanCard(
                    title = "Солона",
                    subtitle = "365 рӯз",
                    price = "149",
                    badge = "Сарфа 57%",
                    selected = selectedPlan == 1,
                    accentBase = c.mango.base,
                    accentSoft = c.mango.soft,
                    onClick = { selectedPlan = 1 },
                )
            }

            // ── как купить ───────────────────────
            Column {
                Text(
                    text = "Чӣ тавр харидан",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(Modifier.height(TutiSpace.md))
                TutiCard(modifier = Modifier.fillMaxWidth(), contentPadding = TutiSpace.lg) {
                    val steps = listOf(
                        "Ба @tuti_support дар Telegram нависед",
                        "Маблағро гузаронед",
                        "Промокод мегиред",
                        "Дар поён ворид кунед",
                    )
                    steps.forEachIndexed { i, step ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = TutiSpace.sm),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(RoundedCornerShape(TutiRadius.pill))
                                    .background(c.jade.soft),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "${i + 1}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = c.jade.onSoft,
                                )
                            }
                            Spacer(Modifier.width(TutiSpace.md))
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }

            // ── промокод ─────────────────────────
            Column {
                Text(
                    text = "Промокод",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(Modifier.height(TutiSpace.md))

                OutlinedTextField(
                    value = promoCode,
                    onValueChange = { promoCode = it.uppercase() },
                    placeholder = {
                        Text(
                            text = "TUTI-XXXX-XXXX",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    textStyle = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(TutiRadius.md),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = c.mango.base,
                        unfocusedBorderColor = c.cardBorder,
                        cursorColor = c.mango.base,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = c.tileBg,
                    ),
                )

                Spacer(Modifier.height(TutiSpace.md))

                TutiButton(
                    text = "Фаъол кардан",
                    onClick = {
                        if (promoCode.isBlank()) return@TutiButton
                        isLoading = true
                        scope.launch {
                            val result = PromoCodeManager.redeemCode(context, promoCode)
                            isLoading = false
                            when (result) {
                                is PromoCodeManager.RedeemResult.Success -> {
                                    isSuccess = true
                                    resultMessage =
                                        "🎉 Табрик! Tuti Plus барои ${result.days} рӯз фаъол шуд!"
                                    TutiSoundManager.playLessonComplete()
                                    onActivated()
                                }
                                is PromoCodeManager.RedeemResult.InvalidCode -> {
                                    isSuccess = false
                                    resultMessage = "Промокод нодуруст аст"
                                }
                                is PromoCodeManager.RedeemResult.AlreadyUsed -> {
                                    isSuccess = false
                                    resultMessage = "Ин промокод аллакай истифода шудааст"
                                }
                                is PromoCodeManager.RedeemResult.Error -> {
                                    isSuccess = false
                                    resultMessage = "Хатогӣ рӯй дод. Боз кӯшиш кунед"
                                }
                            }
                        }
                    },
                    tone = TutiButtonTone.Mango,
                    gradient = c.plusGradient,
                    size = TutiButtonSize.Large,
                    enabled = promoCode.isNotBlank(),
                    loading = isLoading,
                    trailingEmoji = "⭐",
                )

                if (resultMessage.isNotBlank()) {
                    Spacer(Modifier.height(TutiSpace.md))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(TutiRadius.md))
                            .background(if (isSuccess) c.correctBg else c.wrongBg)
                            .padding(TutiSpace.lg),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(if (isSuccess) "✅" else "⚠️", fontSize = 18.sp)
                        Spacer(Modifier.width(TutiSpace.md))
                        Text(
                            text = resultMessage,
                            style = MaterialTheme.typography.titleSmall,
                            color = if (isSuccess) c.correctText else c.wrongText,
                        )
                    }
                }
            }

            Spacer(Modifier.height(TutiSpace.xl))
        }
    }
}

/**
 * Тариф. Выбранный отличается заливкой и толщиной рамки, а не
 * только галочкой — на маленьком экране это заметнее.
 */
@Composable
private fun PlanCard(
    title: String,
    subtitle: String,
    price: String,
    selected: Boolean,
    accentBase: Color,
    accentSoft: Color,
    onClick: () -> Unit,
    badge: String? = null,
) {
    val c = MaterialTheme.tutiColors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(TutiRadius.lg))
            .background(if (selected) accentSoft else MaterialTheme.colorScheme.surface)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) accentBase else c.cardBorder,
                shape = RoundedCornerShape(TutiRadius.lg),
            )
            .clickable { onClick() }
            .padding(TutiSpace.lg),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SelectionCheck(selected = selected, color = accentBase)
        Spacer(Modifier.width(TutiSpace.md))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                if (badge != null) {
                    Spacer(Modifier.width(TutiSpace.sm))
                    TutiPill(
                        text = badge,
                        background = accentBase,
                        contentColor = Color.White,
                    )
                }
            }
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = price,
                style = MaterialTheme.typography.displaySmall,
                fontSize = 24.sp,
                color = accentBase,
            )
            Text(
                text = "сомонӣ",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
