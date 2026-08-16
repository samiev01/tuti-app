package app.tuti.tj.data.user

import android.util.Log
import com.google.firebase.Firebase
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

    suspend fun saveOnboarding(profile: OnboardingProfile): Result<Unit> = runCatching<Unit> {
        val uid = AuthRepository.ensureSignedIn().getOrThrow()

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
