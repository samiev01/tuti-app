package app.tuti.tj.data.repository

import android.util.Log
import app.tuti.tj.data.content.ContentProvider
import app.tuti.tj.data.local.dao.CourseProgressDao

// ════════════════════════════════════════════════════════════════
//  ПОЧИНКА ПОРЯДКА ПРОХОЖДЕНИЯ
//
//  Старая логика открытия уроков опиралась на порядок строк в
//  lesson_progress (ORDER BY id), а после восстановления из облака
//  строки приходят вразнобой. Из-за этого открывались уроки из
//  дальних модулей, и пользователь мог пройти их «через голову»
//  предыдущих. Здесь мы приводим прогресс к виду, который курс
//  вообще допускает: пройденное — только сплошным префиксом.
// ════════════════════════════════════════════════════════════════

object CourseProgressRepair {

    private const val TAG = "CourseRepair"

    /**
     * Снимает отметку «пройден» с уроков, которые идут после первого
     * непройденного урока курса, и пересчитывает счётчики модулей.
     *
     * Идемпотентно: на здоровом прогрессе не делает ничего, поэтому
     * вызывается при каждой инициализации курса, а не один раз по флагу —
     * так чинится и прогресс, который приедет из облака позже.
     *
     * XP и звёзды пользователя не отзываются: сбрасывается только
     * отметка прохождения урока, накопленные очки остаются при нём.
     *
     * @return сколько уроков пришлось сбросить.
     */
    suspend fun repairLessonOrder(dao: CourseProgressDao, courseId: String): Int {
        val ordered = ContentProvider.getOrderedLessons(courseId)
        if (ordered.isEmpty()) return 0

        val rows = dao.getAllLessonProgressOnce(courseId)
        if (rows.isEmpty()) return 0

        val completedIds = rows.filter { it.completed }.map { it.lessonId }.toSet()
        val firstGap = ordered.indexOfFirst { it.id !in completedIds }
        // -1 — курс пройден целиком, дыр нет.
        if (firstGap < 0) return 0

        val stale = ordered.drop(firstGap + 1).filter { it.id in completedIds }
        if (stale.isEmpty()) return 0

        stale.forEach { dao.clearLessonCompletion(it.id) }
        Log.i(TAG, "reset ${stale.size} out-of-order lessons in $courseId")

        recountModules(dao, courseId)
        return stale.size
    }

    /** Счётчики модулей после сброса считаем заново — по фактическим строкам. */
    private suspend fun recountModules(dao: CourseProgressDao, courseId: String) {
        val course = ContentProvider.getCourseById(courseId) ?: return
        val rows = dao.getAllLessonProgressOnce(courseId)
        for (module in course.modules) {
            val completed = rows.count { it.moduleId == module.id && it.completed }
            dao.updateModuleProgress(module.id, completed)
        }
    }
}
