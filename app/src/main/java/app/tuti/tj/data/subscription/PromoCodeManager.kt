package app.tuti.tj.data.subscription

import android.content.Context
import android.util.Log
import app.tuti.tj.data.sync.CloudSyncManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object PromoCodeManager {

    private const val TAG = "PromoCode"

    /** Промокоды лежат по Document ID: id документа и есть сам код. */
    const val COLLECTION = "TUTI-PROMOCODES"

    private const val DAYS_PER_MONTH = 30

    suspend fun redeemCode(context: Context, code: String): RedeemResult {
        return withContext(Dispatchers.IO) {
            val codeId = code.uppercase().trim()
            try {
                val db = Firebase.firestore
                val codeRef = db.collection(COLLECTION).document(codeId)
                val codeDoc = codeRef.get().await()

                if (!codeDoc.exists()) {
                    Log.w(TAG, "$codeId: документ не найден в $COLLECTION")
                    return@withContext RedeemResult.InvalidCode
                }

                // Поля нет — считаем код активным: старые документы его не имели.
                val isActive = codeDoc.getBoolean("active") ?: true
                if (!isActive) {
                    Log.w(TAG, "$codeId: active = false")
                    return@withContext RedeemResult.InvalidCode
                }

                if (codeDoc.getBoolean("used") == true) {
                    Log.w(TAG, "$codeId: уже использован")
                    return@withContext RedeemResult.AlreadyUsed
                }

                val durationDays = resolveDurationDays(codeDoc)
                val type = codeDoc.getString("type") ?: "monthly"

                val userId = Firebase.auth.currentUser?.uid ?: "anonymous"
                // Отмечаем код использованным до выдачи Plus: если записать
                // не удалось, подписку не выдаём — иначе код станет многоразовым.
                codeRef.update(
                    "used", true,
                    "usedBy", userId,
                    "usedAt", FieldValue.serverTimestamp(),
                ).await()

                PlusManager.activatePlus(context, durationDays)

                try { CloudSyncManager.saveProgress(context) } catch (_: Exception) {}

                Log.i(TAG, "$codeId: активирован на $durationDays дней")
                RedeemResult.Success(durationDays, type)
            } catch (e: Exception) {
                Log.e(TAG, "$codeId: ошибка активации", e)
                RedeemResult.Error
            }
        }
    }

    /**
     * Срок подписки: приоритет у [months], затем durationDays, иначе месяц.
     * Числа читаем терпимо к типу — в консоли Firestore поле легко завести
     * строкой вместо number.
     */
    private fun resolveDurationDays(doc: DocumentSnapshot): Int {
        val months = readNumber(doc, "months")
        if (months != null && months > 0) return months * DAYS_PER_MONTH
        val days = readNumber(doc, "durationDays")
        if (days != null && days > 0) return days
        return DAYS_PER_MONTH
    }

    private fun readNumber(doc: DocumentSnapshot, field: String): Int? =
        when (val raw = doc.get(field)) {
            is Number -> raw.toInt()
            is String -> raw.trim().toIntOrNull()
            else -> null
        }

    sealed class RedeemResult {
        data class Success(val days: Int, val type: String) : RedeemResult()
        data object InvalidCode : RedeemResult()
        data object AlreadyUsed : RedeemResult()
        data object Error : RedeemResult()
    }
}
