package app.tuti.tj.data.content

import app.tuti.tj.data.content.courses.personalRussianCourse
import app.tuti.tj.data.content.courses.studyRussianCourse
import app.tuti.tj.data.content.courses.travelRussianCourse
import app.tuti.tj.data.content.courses.personalEnglishCourse
import app.tuti.tj.data.content.courses.studyEnglishCourse
import app.tuti.tj.data.content.courses.travelEnglishCourse
import app.tuti.tj.data.content.courses.workEnglishCourse
import app.tuti.tj.data.content.courses.workRussianCourse

object ContentProvider {

    // ── legacy topic content (free topics) ─────────

    private val wordsByTopic = mapOf(
        "greetings" to greetingsWords,
        "numbers" to numbersWords,
        "food" to foodWords,
        "family" to familyWords,
        "city" to cityWords,
        "colors" to colorsWords,
        "time" to timeWords,
        "animals" to animalsWords,
        "clothes" to clothesWords,
        "body" to bodyWords,
        "nature" to natureWords,
        "professions" to professionsWords,
        "fruits" to fruitsWords,
        "emotions" to emotionsWords,
        "home" to homeWords,
        "en_greetings" to enGreetingsWords,
        "en_numbers" to enNumbersWords,
        "en_food" to enFoodWords,
        "en_family" to enFamilyWords,
        "en_city" to enCityWords,
        "en_colors" to enColorsWords,
        "en_time" to enTimeWords,
        "en_animals" to enAnimalsWords,
        "en_clothes" to enClothesWords,
        "en_body" to enBodyWords,
    )

    private val quizByTopic = mapOf(
        "greetings" to greetingsQuiz,
        "numbers" to numbersQuiz,
        "food" to foodQuiz,
        "family" to familyQuiz,
        "city" to cityQuiz,
        "colors" to colorsQuiz,
        "time" to timeQuiz,
        "animals" to animalsQuiz,
        "clothes" to clothesQuiz,
        "body" to bodyQuiz,
        "nature" to natureQuiz,
        "professions" to professionsQuiz,
        "fruits" to fruitsQuiz,
        "emotions" to emotionsQuiz,
        "home" to homeQuiz,
        "en_greetings" to enGreetingsQuiz,
        "en_numbers" to enNumbersQuiz,
        "en_food" to enFoodQuiz,
        "en_family" to enFamilyQuiz,
        "en_city" to enCityQuiz,
        "en_colors" to enColorsQuiz,
        "en_time" to enTimeQuiz,
        "en_animals" to enAnimalsQuiz,
        "en_clothes" to enClothesQuiz,
        "en_body" to enBodyQuiz,
    )

    fun getWordsForTopic(topicId: String): List<WordItem> =
        wordsByTopic[topicId] ?: emptyList()

    fun getQuestionsForTopic(topicId: String): List<QuizQuestion> =
        quizByTopic[topicId] ?: emptyList()

    fun getAllTopics(language: String = "russian"): List<TopicInfo> =
        FreeTopicsRegistry.topicInfos(language)

    fun getTopicInfo(topicId: String): TopicInfo? =
        FreeTopicsRegistry.definitionFor(topicId)?.toTopicInfo()

    // ── course content ─────────────────────────────

    private val allCourses: MutableList<Course> = mutableListOf(
        workRussianCourse,
        personalRussianCourse,
        studyRussianCourse,
        travelRussianCourse,
        workEnglishCourse,
        studyEnglishCourse,
        travelEnglishCourse,
        personalEnglishCourse,
    )

    private val coursesById: MutableMap<String, Course> =
        allCourses.associateBy { it.id }.toMutableMap()

    private val lessonsById: MutableMap<String, Lesson> =
        allCourses.flatMap { c -> c.modules.flatMap { m -> m.lessons } }
            .associateBy { it.id }.toMutableMap()

    fun getCourseForGoal(goal: String, language: String = "russian"): Course? =
        allCourses.find { it.goalType == goal && it.language == language }

    fun getCourseById(courseId: String): Course? =
        coursesById[courseId]

    fun getAllCourses(language: String = "russian"): List<Course> =
        allCourses.filter { it.language == language }

    fun getLesson(lessonId: String): Lesson? =
        lessonsById[lessonId]

    fun getExercisesForLesson(lessonId: String): List<Exercise> =
        lessonsById[lessonId]?.exercises ?: emptyList()

    /** Уроки курса в порядке прохождения (модуль за модулем). */
    fun getOrderedLessons(courseId: String): List<Lesson> =
        coursesById[courseId]?.orderedLessons ?: emptyList()

    /**
     * Первый непройденный урок курса по порядку контента.
     * Именно он открыт для прохождения — остальные ещё закрыты.
     */
    fun getNextLessonId(courseId: String, completedLessonIds: Set<String>): String? =
        getOrderedLessons(courseId).firstOrNull { it.id !in completedLessonIds }?.id

    /**
     * Открытые уроки курса: всё пройденное плюс первый непройденный.
     * Пройденные уроки могли попасть в БД вразнобой (восстановление из облака),
     * поэтому они остаются открытыми, но «фронт» всегда один и идёт по порядку.
     */
    fun getUnlockedLessonIds(courseId: String, completedLessonIds: Set<String>): Set<String> {
        val ordered = getOrderedLessons(courseId)
        if (ordered.isEmpty()) return emptySet()
        val unlocked = ordered.map { it.id }.filter { it in completedLessonIds }.toMutableSet()
        getNextLessonId(courseId, completedLessonIds)?.let { unlocked.add(it) }
        return unlocked
    }

    fun registerCourse(course: Course) {
        allCourses.add(course)
        coursesById[course.id] = course
        course.modules.flatMap { it.lessons }.forEach { lesson ->
            lessonsById[lesson.id] = lesson
        }
    }
}
