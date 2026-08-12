package app.tuti.tj.audio

import android.media.MediaPlayer
import android.util.Log
import java.io.File

/**
 * Singleton MP3 player for OpenAI TTS audio.
 * Must be called from the main thread (same thread that created the MediaPlayer).
 * MediaPlayer.OnCompletionListener fires on the main thread, so completion callbacks
 * are always delivered on the main thread — safe to update Compose state directly.
 */
object TutiVoicePlayer {

    private var mediaPlayer: MediaPlayer? = null
    private var currentTempFile: File? = null

    /**
     * Plays [audioBytes] as MP3. Stops any currently playing audio first.
     * [onCompletion] is called on the main thread when playback ends (success or error).
     */
    fun play(audioBytes: ByteArray, onCompletion: (() -> Unit)? = null) {
        stop() // ensure previous playback + temp file are cleaned up
        try {
            val tmp = File.createTempFile("tuti_tts_", ".mp3").also {
                it.writeBytes(audioBytes)
                it.deleteOnExit()
            }
            currentTempFile = tmp

            mediaPlayer = MediaPlayer().apply {
                setDataSource(tmp.absolutePath)
                setOnCompletionListener {
                    cleanupTempFile()
                    onCompletion?.invoke()
                }
                setOnErrorListener { _, what, extra ->
                    Log.e("TutiVoice", "MediaPlayer error what=$what extra=$extra")
                    cleanupTempFile()
                    onCompletion?.invoke()
                    true
                }
                prepare()
                start()
            }
        } catch (e: Exception) {
            Log.e("TutiVoice", "Playback error", e)
            cleanupTempFile()
            onCompletion?.invoke()
        }
    }

    fun stop() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (_: Exception) {}
        cleanupTempFile()
    }

    fun isSpeaking(): Boolean = try {
        mediaPlayer?.isPlaying == true
    } catch (_: Exception) { false }

    private fun cleanupTempFile() {
        try { currentTempFile?.delete() } catch (_: Exception) {}
        currentTempFile = null
    }
}
