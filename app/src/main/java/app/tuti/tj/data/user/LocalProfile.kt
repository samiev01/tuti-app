package app.tuti.tj.data.user

import android.content.Context
import app.tuti.tj.data.repository.TutiRepository

// ════════════════════════════════════════════════════════════════
//  ПРОФИЛЬ В ЛОКАЛЬНОЙ БАЗЕ
//
//  Одно место, где ответы онбординга ложатся в Room и настройки.
//  Путей сюда два — конец онбординга и вход старым аккаунтом, у
//  которого прогресс в облаке не завёлся, — и разойтись они не
//  должны: курс, город и минуты обязаны получаться одинаковыми.
// ════════════════════════════════════════════════════════════════

suspend fun OnboardingProfile.applyLocally(
    context: Context,
    repository: TutiRepository,
) {
    val city = CityCatalog.byCode(cityCode)
    val courseId = courseIdFor(goal, language)

    runCatching {
        repository.saveOnboardingData(
            language = language.dbValue,
            level = level.dbValue,
            goal = goal.dbValue,
            dailyMinutes = dailyMinutes,
            courseId = courseId,
        )
        repository.initCourseProgress(courseId)
    }

    context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
        .edit()
        // Лидерборд группирует по таджикскому названию, код лежит
        // рядом — на него перейдут вместе с рейтингом.
        .putString("user_city", city.tajikName)
        .putString("user_city_code", city.code)
        .apply()
}
