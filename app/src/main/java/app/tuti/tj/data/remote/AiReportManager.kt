package app.tuti.tj.data.remote

import android.content.Context
import android.util.Log
import app.tuti.tj.data.user.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

// ════════════════════════════════════════════════════════════════
//  ЖАЛОБЫ НА ОТВЕТЫ ИИ
//
//  Репетитор иногда ошибается или отвечает не тем тоном, и увидеть
//  это можем только мы — из приложения. Поэтому жалоба уезжает
//  вместе с самим ответом: без текста разбирать её бессмысленно.
//
//  Версия приложения важна не меньше: один и тот же ответ на разных
//  сборках приходит из разных промптов.
// ════════════════════════════════════════════════════════════════

enum class AiReportReason {
    /** Ответ неверный по сути: неправильный перевод, выдуманное правило. */
    WRONG_ANSWER,

    /** Оскорбительное или неуместное содержание. */
    OFFENSIVE,

    OTHER,
}

object AiReportManager {

    private const val TAG = "AiReport"
    private const val COLLECTION = "ai_reports"

    /** Длинные ответы режем: для разбора хватает начала, а документ не резиновый. */
    private const val MAX_TEXT_LENGTH = 4000

    suspend fun submit(
        context: Context,
        messageText: String,
        reason: AiReportReason,
        comment: String,
    ): Result<Unit> = runCatching<Unit> {
        val report = mapOf(
            "userId" to AuthRepository.currentUid.orEmpty(),
            "messageText" to messageText.take(MAX_TEXT_LENGTH),
            "reason" to reason.name,
            "comment" to comment.trim(),
            "timestamp" to FieldValue.serverTimestamp(),
            "appVersion" to appVersion(context),
        )

        Firebase.firestore.collection(COLLECTION).add(report).await()

        Log.d(TAG, "report sent: ${reason.name}")
    }.onFailure { e ->
        Log.e(TAG, "report failed: ${e.message}", e)
    }

    private fun appVersion(context: Context): String = runCatching {
        context.packageManager.getPackageInfo(context.packageName, 0).versionName.orEmpty()
    }.getOrDefault("")
}
