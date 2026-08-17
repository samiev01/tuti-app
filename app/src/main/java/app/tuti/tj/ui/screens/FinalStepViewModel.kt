package app.tuti.tj.ui.screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tuti.tj.data.repository.TutiRepository
import app.tuti.tj.data.user.CityCatalog
import app.tuti.tj.data.user.LearningGoal
import app.tuti.tj.data.user.LearningLanguage
import app.tuti.tj.data.user.ProficiencyLevel
import app.tuti.tj.data.user.TutiCity
import kotlinx.coroutines.launch

// ════════════════════════════════════════════════════════════════
//  ФИНАЛЬНЫЙ ШАГ ОНБОРДИНГА
//
//  Вход остался позади — на этом экране человек только видит, что
//  он выбрал, и уходит на главную. Сводка нужна не для проверки:
//  так виднее, что за полминуты уже что-то собрано, и начинать
//  хочется сильнее.
// ════════════════════════════════════════════════════════════════

/**
 * Сводка ответов. Читается из Room и настроек, а не передаётся
 * с предыдущего экрана: онбординг мог быть прерван и продолжен
 * после перезапуска приложения.
 */
data class OnboardingSummary(
    val language: LearningLanguage,
    val goal: LearningGoal,
    val level: ProficiencyLevel,
    val dailyMinutes: Int,
    val city: TutiCity,
)

class FinalStepViewModel : ViewModel() {

    var summary by mutableStateOf<OnboardingSummary?>(null)
        private set

    private var loaded = false

    fun load(context: Context, repository: TutiRepository) {
        if (loaded) return
        loaded = true
        viewModelScope.launch {
            val user = runCatching { repository.getUserOnce() }.getOrNull() ?: return@launch
            val cityCode = context
                .getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                .getString("user_city_code", null)

            summary = OnboardingSummary(
                language = parse<LearningLanguage>(user.selectedLanguage)
                    ?: LearningLanguage.RUSSIAN,
                goal = parse<LearningGoal>(user.goal) ?: LearningGoal.PERSONAL,
                level = parse<ProficiencyLevel>(user.level) ?: ProficiencyLevel.BEGINNER,
                dailyMinutes = user.dailyGoalMinutes,
                city = CityCatalog.byCode(cityCode),
            )
        }
    }
}

/** В базе ответы лежат строками в нижнем регистре: `russian`, `work`. */
private inline fun <reified T : Enum<T>> parse(value: String): T? =
    runCatching { enumValueOf<T>(value.uppercase()) }.getOrNull()
