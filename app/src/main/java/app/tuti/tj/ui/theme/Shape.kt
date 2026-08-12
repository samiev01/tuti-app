package app.tuti.tj.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ════════════════════════════════════════════════════════════════
//  TUTI DESIGN SYSTEM · СКРУГЛЕНИЯ
//
//  Одна геометрическая логика на всё приложение. Скругления
//  заметные, но не «пузырьковые»: радиус растёт вместе с
//  размером элемента, поэтому крупные карточки не выглядят
//  надувными, а мелкие чипы — острыми.
// ════════════════════════════════════════════════════════════════

object TutiRadius {
    /** Мелкие индикаторы, полоски прогресса */
    val xs = 8.dp
    /** Чипы, бейджи, мелкие плашки */
    val sm = 12.dp
    /** Плитки иконок, поля ввода */
    val md = 16.dp
    /** Кнопки, вложенные карточки */
    val lg = 20.dp
    /** Основные карточки, секции */
    val xl = 24.dp
    /** Диалоги, шторки, hero-блоки */
    val xxl = 32.dp
    /** Полная капсула */
    val pill = 999.dp
}

val ShapeXs = RoundedCornerShape(TutiRadius.xs)
val ShapeSm = RoundedCornerShape(TutiRadius.sm)
val ShapeMd = RoundedCornerShape(TutiRadius.md)
val ShapeLg = RoundedCornerShape(TutiRadius.lg)
val ShapeXl = RoundedCornerShape(TutiRadius.xl)
val ShapeXxl = RoundedCornerShape(TutiRadius.xxl)
val ShapePill = RoundedCornerShape(TutiRadius.pill)

/** Шторка снизу / hero-шапка со скруглением только сверху. */
val ShapeSheetTop = RoundedCornerShape(topStart = TutiRadius.xxl, topEnd = TutiRadius.xxl)

/** Шапка экрана со скруглением только снизу. */
val ShapeHeaderBottom = RoundedCornerShape(bottomStart = TutiRadius.xxl, bottomEnd = TutiRadius.xxl)

val TutiShapes = Shapes(
    extraSmall = ShapeXs,
    small = ShapeSm,
    medium = ShapeMd,
    large = ShapeLg,
    extraLarge = ShapeXl,
)
