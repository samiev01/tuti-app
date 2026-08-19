package app.tuti.tj.data.user

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

// ════════════════════════════════════════════════════════════════
//  ВОЗРАСТНОЙ ПОРОГ
//
//  Дату рождения спрашивают до входа, поэтому uid ещё нет и
//  сохранить её некуда, кроме настроек. В Firestore она уезжает
//  позже, сразу после входа.
//
//  Решение запоминается: и «пустили», и «не пустили». Иначе отказ
//  обходился бы перезапуском приложения, а тем, кого пустили,
//  пришлось бы вводить дату каждый раз.
// ════════════════════════════════════════════════════════════════

enum class AgeGateVerdict {
    /** Дату ещё не спрашивали. */
    UNKNOWN,
    ALLOWED,
    BLOCKED,
}

object AgeGateManager {

    /** С какого возраста приложение доступно. */
    const val MIN_AGE = 13

    private const val PREFS = "tuti_prefs"
    private const val KEY_BIRTH_DATE = "birth_date"
    private const val KEY_VERDICT = "age_gate_verdict"

    /** ISO — так дату читает и человек в консоли, и любой парсер. */
    private val format: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    private val _verdict = MutableStateFlow(AgeGateVerdict.UNKNOWN)
    val verdict: StateFlow<AgeGateVerdict> = _verdict.asStateFlow()

    fun init(context: Context) {
        val saved = prefs(context).getString(KEY_VERDICT, null)
        _verdict.value = runCatching { AgeGateVerdict.valueOf(saved.orEmpty()) }
            .getOrDefault(AgeGateVerdict.UNKNOWN)
    }

    /**
     * Проверяет дату и запоминает решение. Возвращает вердикт,
     * чтобы экран сразу знал, что показывать дальше.
     */
    fun submit(context: Context, birthDate: LocalDate): AgeGateVerdict {
        val verdict = if (isOldEnough(birthDate)) {
            AgeGateVerdict.ALLOWED
        } else {
            AgeGateVerdict.BLOCKED
        }

        prefs(context).edit()
            // Дату храним в обоих случаях: при отказе она и есть
            // причина отказа, и переспрашивать её незачем.
            .putString(KEY_BIRTH_DATE, birthDate.format(format))
            .putString(KEY_VERDICT, verdict.name)
            .apply()

        _verdict.value = verdict
        return verdict
    }

    /** Дата в формате ISO или null, если её ещё не вводили. */
    fun birthDate(context: Context): String? =
        prefs(context).getString(KEY_BIRTH_DATE, null)

    fun isOldEnough(birthDate: LocalDate): Boolean =
        Period.between(birthDate, LocalDate.now()).years >= MIN_AGE

    /**
     * Собирает дату из трёх полей. null — такой даты не существует
     * (31 февраля, будущее, опечатка в годе).
     */
    fun parse(day: Int, month: Int, year: Int): LocalDate? {
        val date = runCatching { LocalDate.of(year, month, day) }.getOrNull() ?: return null
        if (date.isAfter(LocalDate.now())) return null
        if (year < EARLIEST_YEAR) return null
        return date
    }

    private const val EARLIEST_YEAR = 1900

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
}
