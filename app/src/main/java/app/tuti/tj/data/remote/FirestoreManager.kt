package app.tuti.tj.data.remote

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

data class CityScore(val city: String, val totalXp: Int, val userCount: Int)
data class UserScore(val name: String, val xp: Int, val streak: Int)
data class LeaderUserScore(
    val uid: String,
    val name: String,
    val city: String,
    val xp: Int,
    val streak: Int,
    val isPlusUser: Boolean = false,
)

object FirestoreManager {

    private const val TAG = "FirestoreManager"
    private val db by lazy { Firebase.firestore }

    /**
     * Запись в Firestore асинхронная: `set()`/`update()` возвращают Task
     * сразу, а отказ сервера (правила, отсутствующая база, нет сети)
     * приходит позже. Без этого листенера ошибка не попадала никуда —
     * приложение молчало, а в консоли не появлялось ни одного документа.
     */
    private fun Task<Void>.logResult(op: String) {
        addOnSuccessListener { Log.d(TAG, "$op ok") }
        addOnFailureListener { e ->
            val code = (e as? FirebaseFirestoreException)?.code
            Log.e(TAG, "$op FAILED${code?.let { " [$it]" } ?: ""}: ${e.message}", e)
        }
    }

    fun saveUserProfile(userId: String, name: String, city: String, xp: Int, isPlusUser: Boolean = false) {
        val user = hashMapOf<String, Any>(
            "name" to name,
            "city" to city,
            "xp" to xp,
            "lastActive" to FieldValue.serverTimestamp(),
        )
        if (isPlusUser) {
            user["isPlusUser"] = true
        }
        db.collection("users").document(userId)
            .set(user, SetOptions.merge())
            .logResult("saveUserProfile($userId)")
    }

    // set+merge вместо update: update падает с NOT_FOUND, если документа
    // пользователя ещё нет, и весь заработанный XP тихо терялся.
    fun addXp(userId: String, amount: Int) {
        db.collection("users").document(userId)
            .set(
                hashMapOf(
                    "xp" to FieldValue.increment(amount.toLong()),
                    "lastActive" to FieldValue.serverTimestamp(),
                ),
                SetOptions.merge(),
            )
            .logResult("addXp($userId, $amount)")
    }

    fun updateStreak(userId: String, streak: Int) {
        db.collection("users").document(userId)
            .set(hashMapOf("streak" to streak), SetOptions.merge())
            .logResult("updateStreak($userId, $streak)")
    }

    suspend fun getCityLeaderboard(): List<CityScore> = withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("users").get().await()
            val cityMap = mutableMapOf<String, CityScore>()

            for (doc in snapshot.documents) {
                val city = doc.getString("city") ?: continue
                val xp = doc.getLong("xp")?.toInt() ?: 0
                val current = cityMap.getOrPut(city) { CityScore(city, 0, 0) }
                cityMap[city] = current.copy(
                    totalXp = current.totalXp + xp,
                    userCount = current.userCount + 1,
                )
            }
            cityMap.values.sortedByDescending { it.totalXp }
        } catch (e: Exception) {
            Log.e(TAG, "getCityLeaderboard failed", e)
            emptyList()
        }
    }

    suspend fun getUserRankInCity(userId: String, city: String): Int =
        withContext(Dispatchers.IO) {
            try {
                val snapshot = db.collection("users")
                    .whereEqualTo("city", city)
                    .get().await()

                val users = snapshot.documents
                    .map { it.id to (it.getLong("xp")?.toInt() ?: 0) }
                    .sortedByDescending { it.second }

                users.indexOfFirst { it.first == userId } + 1
            } catch (e: Exception) {
                Log.e(TAG, "getUserRankInCity failed", e)
                0
            }
        }

    suspend fun getAllUsersSortedByXp(): List<LeaderUserScore> = withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("users")
                .orderBy("xp", Query.Direction.DESCENDING)
                .get().await()
            snapshot.documents.map { doc ->
                LeaderUserScore(
                    uid    = doc.id,
                    name   = doc.getString("name") ?: "???",
                    city   = doc.getString("city") ?: "",
                    xp     = doc.getLong("xp")?.toInt() ?: 0,
                    streak = doc.getLong("streak")?.toInt() ?: 0,
                    isPlusUser = doc.getBoolean("isPlusUser") ?: false,
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAllUsersSortedByXp failed", e)
            emptyList()
        }
    }

    suspend fun getTopUsersInCity(city: String, limit: Int = 10): List<UserScore> =
        withContext(Dispatchers.IO) {
            try {
                val snapshot = db.collection("users")
                    .whereEqualTo("city", city)
                    .orderBy("xp", Query.Direction.DESCENDING)
                    .limit(limit.toLong())
                    .get().await()

                snapshot.documents.map { doc ->
                    UserScore(
                        name = doc.getString("name") ?: "???",
                        xp = doc.getLong("xp")?.toInt() ?: 0,
                        streak = doc.getLong("streak")?.toInt() ?: 0,
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "getTopUsersInCity failed", e)
                emptyList()
            }
        }
}
