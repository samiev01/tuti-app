package app.tuti.tj.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object TutiTtsApi {
    private const val TTS_URL = "https://tuti-api.vercel.app/api/tts"
    private const val MAX_CACHE = 20

    /** Simple FIFO in-memory cache so replaying a message costs zero network. */
    private val cache = LinkedHashMap<String, ByteArray>()

    suspend fun getAudio(text: String): ByteArray? {
        // Return cached bytes immediately if available
        cache[text]?.let { return it }

        return withContext(Dispatchers.IO) {
            try {
                val url = URL(TTS_URL)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                conn.connectTimeout = 15_000
                conn.readTimeout   = 30_000
                conn.doOutput = true

                val body = JSONObject().apply { put("text", text) }.toString()
                conn.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }

                if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                    val bytes = conn.inputStream.readBytes()
                    if (bytes.isNotEmpty()) {
                        // Evict oldest entry if cache is full
                        if (cache.size >= MAX_CACHE) cache.remove(cache.keys.first())
                        cache[text] = bytes
                        bytes
                    } else null
                } else {
                    Log.e("TutiTTS", "HTTP ${conn.responseCode}")
                    null
                }
            } catch (e: Exception) {
                Log.e("TutiTTS", "TTS request failed", e)
                null
            }
        }
    }
}
