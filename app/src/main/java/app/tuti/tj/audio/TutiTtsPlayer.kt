package app.tuti.tj.audio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import app.tuti.tj.data.remote.TutiTtsApi
import kotlinx.coroutines.launch

/**
 * Shared state for a single TTS audio slot.
 *
 * Created once per logical audio unit (question, word, turn) via [rememberTtsPlayer].
 *
 * Usage:
 * ```
 * val tts = rememberTtsPlayer()
 *
 * LaunchedEffect(wordText) { tts.loadAndPlay(wordText) }
 *
 * AudioButton(isLoading = tts.isLoading, onClick = { tts.replay() })
 * ```
 */
class TtsPlayerState(
    private val onPlay: (suspend () -> Unit) -> Unit,
) {
    var isLoading: Boolean by mutableStateOf(false)
        private set

    var hasAudio: Boolean by mutableStateOf(false)
        private set

    private var cachedBytes: ByteArray? = null

    /** Fetch [text] via TTS API and play immediately. No-op if already loading. */
    fun loadAndPlay(text: String) {
        if (isLoading) return
        onPlay { fetchAndPlay(text, onCompletion = null) }
    }

    /** Play the last fetched audio again. If nothing cached, fetches [fallbackText]. */
    fun replay(fallbackText: String = "") {
        val bytes = cachedBytes
        if (bytes != null) {
            try { TutiVoicePlayer.play(bytes) } catch (_: Exception) {}
        } else if (fallbackText.isNotBlank()) {
            loadAndPlay(fallbackText)
        }
    }

    /** Like [loadAndPlay] but calls [onCompletion] when playback finishes. */
    fun loadAndPlayWithCallback(text: String, onCompletion: () -> Unit) {
        if (isLoading) return
        onPlay { fetchAndPlay(text, onCompletion) }
    }

    fun stop() = TutiVoicePlayer.stop()

    private suspend fun fetchAndPlay(text: String, onCompletion: (() -> Unit)?) {
        cachedBytes = null
        hasAudio = false
        isLoading = true
        try {
            val bytes = TutiTtsApi.getAudio(text)
            cachedBytes = bytes
            if (bytes != null) {
                hasAudio = true
                if (onCompletion != null) {
                    TutiVoicePlayer.play(bytes, onCompletion)
                } else {
                    TutiVoicePlayer.play(bytes)
                }
            }
        } catch (_: Exception) {
        } finally {
            isLoading = false
        }
    }
}

/**
 * Creates and remembers a [TtsPlayerState] tied to the current Composable's lifecycle.
 * Automatically stops playback when the composable leaves the composition.
 */
@Composable
fun rememberTtsPlayer(): TtsPlayerState {
    val scope = rememberCoroutineScope()
    val player = remember {
        TtsPlayerState(onPlay = { block -> scope.launch { block() } })
    }
    DisposableEffect(Unit) {
        onDispose { player.stop() }
    }
    return player
}
