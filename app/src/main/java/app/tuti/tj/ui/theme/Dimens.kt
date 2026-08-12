package app.tuti.tj.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp

// ════════════════════════════════════════════════════════════════
//  TUTI DESIGN SYSTEM · РАЗМЕРЫ, ТЕНИ, ДВИЖЕНИЕ
// ════════════════════════════════════════════════════════════════

/**
 * Отступы кратны 4. Экраны дышат за счёт [screen] по бокам и
 * [section] между смысловыми блоками — плотность одинакова везде.
 */
object TutiSpace {
    val xxs = 2.dp
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 20.dp
    val xxl = 24.dp
    val xxxl = 32.dp

    /** Горизонтальные поля любого экрана */
    val screen = 16.dp
    /** Внутренние поля карточки */
    val card = 16.dp
    /** Вертикальный зазор между секциями */
    val section = 20.dp
    /**
     * Хвост прокручиваемого списка.
     *
     * Высоту нижней панели уже добавляет Scaffold через innerPadding,
     * поэтому здесь нужен только небольшой воздух под последней
     * карточкой. Раньше стояло 96 dp — панель отсчитывалась дважды,
     * и в конце списка зияла пустота.
     */
    val bottomNavGap = 24.dp
}

/**
 * Высоты интерактивных элементов. Минимум 48 dp — комфортное
 * попадание пальцем и на телефоне подростка, и у взрослого.
 */
object TutiSize {
    val buttonSm = 40.dp
    val buttonMd = 52.dp
    val buttonLg = 58.dp

    /** Толщина «плиты» под кнопкой — фирменный 3D-эффект нажатия */
    val plate = 4.dp

    val iconTileSm = 36.dp
    val iconTileMd = 48.dp
    val iconTileLg = 56.dp
    val iconTileXl = 64.dp

    val progressThin = 6.dp
    val progressThick = 12.dp

    val avatarSm = 40.dp
    val avatarMd = 56.dp
    val avatarLg = 88.dp

    val minTouch = 48.dp
}

/**
 * Тени мягкие и низкие: интерфейс остаётся «плоско-объёмным».
 * Глубину даёт не размытие, а фирменная нижняя грань кнопок.
 */
object TutiElevation {
    val flat = 0.dp
    val card = 2.dp
    val raised = 6.dp
    val floating = 12.dp
    val dialog = 24.dp
}

/**
 * Движение. Всё быстрое (200–350 мс), с одной кривой ускорения;
 * пружины — только там, где нужен «отклик игрушки»: нажатия,
 * появление наград, маскот.
 */
object TutiMotion {
    /** Стандартная кривая интерфейса */
    val standard = CubicBezierEasing(0.2f, 0f, 0f, 1f)
    /** Небольшой перелёт — для появления наград и маскота */
    val overshoot = CubicBezierEasing(0.34f, 1.56f, 0.64f, 1f)

    const val FAST = 180
    const val NORMAL = 280
    const val SLOW = 420
    /** Шаг задержки в каскадном появлении элементов */
    const val STAGGER = 60

    fun <T> fast() = tween<T>(FAST, easing = standard)
    fun <T> normal() = tween<T>(NORMAL, easing = standard)
    fun <T> slow() = tween<T>(SLOW, easing = standard)
    fun <T> pop() = tween<T>(NORMAL, easing = overshoot)

    fun <T> bouncy() = spring<T>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium,
    )

    fun <T> snappy() = spring<T>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMedium,
    )
}
