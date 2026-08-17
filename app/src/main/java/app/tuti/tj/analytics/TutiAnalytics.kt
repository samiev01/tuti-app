package app.tuti.tj.analytics

import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

// ════════════════════════════════════════════════════════════════
//  АНАЛИТИКА
//
//  Событий немного и заводятся они не «на всякий случай». Первое,
//  ради чего понадобилась аналитика: на финальном шаге онбординга
//  нет кнопки «Баъдтар», и без счётчиков не видно, сколько людей
//  до него дошло и сколько прошло дальше. Цена решения должна быть
//  измеримой, иначе спорить о ней бессмысленно.
//
//  Одна точка входа: имена событий лежат здесь, а не разбросаны
//  строками по экранам.
// ════════════════════════════════════════════════════════════════

object TutiAnalytics {

    private const val TAG = "TutiAnalytics"

    private val firebase: FirebaseAnalytics get() = Firebase.analytics

    /** Экран входа показан. */
    fun signInShown() = log("signin_shown")

    /**
     * Вход состоялся. Разница между этим счётчиком и [signInShown] —
     * и есть цена того, что вход стоит первым, до онбординга. Без
     * неё решение потом нечем будет пересмотреть.
     */
    fun signInSuccess() = log("signin_success")

    /** Вход закончился ошибкой. [kind] — вид ошибки. */
    fun signInError(kind: String) =
        log("signin_error", Bundle().apply { putString("kind", kind) })

    private fun log(name: String, params: Bundle? = null) {
        // Дубль в Logcat — чтобы событие можно было проверить сразу
        // на устройстве, не дожидаясь, пока оно доедет до консоли.
        Log.d(TAG, "$name${params?.let { " $it" } ?: ""}")
        runCatching { firebase.logEvent(name, params) }
    }
}
