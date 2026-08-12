package app.tuti.tj.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object TutiChatApi {
    private const val BASE_URL = "https://tuti-api.vercel.app/api/chat"

    data class ChatMessage(val role: String, val text: String) // "user" or "assistant"

    /**
     * Returns [Result.success] with assistant text on HTTP 200.
     * [Result.failure] for network errors, timeouts, or non-200 responses (retryable).
     */
    suspend fun sendMessage(
        message: String,
        conversationHistory: List<ChatMessage> = emptyList(),
        scenario: String? = null,
    ): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(BASE_URL)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                connection.connectTimeout = 15_000
                connection.readTimeout = 30_000
                connection.doOutput = true

                val jsonBody = JSONObject().apply {
                    put("message", message)
                    put("scenario", scenario ?: "")
                    put(
                        "conversationHistory",
                        JSONArray().apply {
                            conversationHistory.forEach { msg ->
                                put(
                                    JSONObject().apply {
                                        put("role", msg.role)
                                        put("text", msg.text)
                                    },
                                )
                            }
                        },
                    )
                }

                connection.outputStream.use { os ->
                    os.write(jsonBody.toString().toByteArray(Charsets.UTF_8))
                }

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().use { it.readText() }
                    val jsonResponse = JSONObject(response)
                    val text = jsonResponse.optString("response").trim()
                    if (text.isNotEmpty()) {
                        Result.success(text)
                    } else {
                        Result.failure(IllegalStateException("empty response"))
                    }
                } else {
                    Log.w("TutiChat", "HTTP ${connection.responseCode}")
                    connection.errorStream?.bufferedReader()?.use { Log.w("TutiChat", it.readText()) }
                    Result.failure(IllegalStateException("HTTP ${connection.responseCode}"))
                }
            } catch (e: Exception) {
                Log.e("TutiChat", "API error", e)
                Result.failure(e)
            }
        }
    }
}
