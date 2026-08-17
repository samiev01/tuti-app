package app.tuti.tj.data.user

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

// ════════════════════════════════════════════════════════════════
//  ПРОФИЛЬ ПОЛЬЗОВАТЕЛЯ В FIRESTORE
//
//  Ответы онбординга уходят одной записью в самом конце, а не по
//  экрану за раз: онбординг занимает полминуты, а пять операций
//  вместо одной — это пятикратный счёт на каждого пользователя.
//
//  isPlus и plusUntil здесь не пишутся никогда: их выставляет
//  только сервер, и правила безопасности отклоняют такую запись
//  с клиента.
// ════════════════════════════════════════════════════════════════

object UserProfileRepository {

    private const val TAG = "UserProfileRepository"

    private val firestore: FirebaseFirestore get() = Firebase.firestore

    /**
     * Возвращающегося пользователя отличает документ профиля, а не
     * сам факт входа: аккаунт Google может быть тот же, а Tuti на
     * нём человек ещё не открывал.
     */
    suspend fun hasProfile(uid: String): Boolean =
        runCatching {
            firestore.collection("users").document(uid).get().await().exists()
        }.onFailure { e ->
            Log.w(TAG, "hasProfile failed: ${e.message}", e)
        }.getOrDefault(false)

    /**
     * Ответы онбординга из облака.
     *
     * Нужны там, где CloudSyncManager бессилен: он поднимает прогресс
     * из users/{uid}/sync, а тот документ появляется только после
     * первого урока. Человек, который прошёл онбординг и закрыл
     * приложение, иначе вернулся бы на главную без курса.
     *
     * null — полей онбординга в документе нет. Так выглядят профили,
     * заведённые до того, как их начали записывать: у таких людей
     * прогресс поднимется обычным путём, из sync.
     */
    suspend fun readOnboarding(uid: String): OnboardingProfile? = runCatching {
        val doc = firestore.collection("users").document(uid).get().await()
        val language = doc.enumField<LearningLanguage>("language") ?: return@runCatching null

        OnboardingProfile(
            language = language,
            goal = doc.enumField<LearningGoal>("goal") ?: LearningGoal.PERSONAL,
            level = doc.enumField<ProficiencyLevel>("level") ?: ProficiencyLevel.BEGINNER,
            dailyMinutes = doc.getLong("dailyMinutes")?.toInt() ?: 5,
            cityCode = doc.getString("cityCode") ?: CityCatalog.default.code,
        )
    }.onFailure { e ->
        Log.w(TAG, "readOnboarding failed: ${e.message}", e)
    }.getOrNull()

    /** В документе перечисления лежат строками: `RUSSIAN`, `WORK`. */
    private inline fun <reified T : Enum<T>> DocumentSnapshot.enumField(field: String): T? =
        getString(field)?.let { runCatching { enumValueOf<T>(it) }.getOrNull() }

    suspend fun saveOnboarding(profile: OnboardingProfile): Result<Unit> = runCatching<Unit> {
        val uid = AuthRepository.currentUid ?: error("not signed in")

        val data = mapOf(
            "language" to profile.language.name,
            "goal" to profile.goal.name,
            "level" to profile.level.name,
            "dailyMinutes" to profile.dailyMinutes,
            "cityCode" to profile.cityCode,
            "onboardingCompleted" to true,
            "createdAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp(),
        )

        firestore.collection("users").document(uid)
            .set(data, SetOptions.merge())
            .await()

        Log.d(TAG, "saveOnboarding ok for $uid")
    }.onFailure { e ->
        Log.e(TAG, "saveOnboarding failed: ${e.message}", e)
    }
}
