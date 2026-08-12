package app.tuti.tj.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.util.Log
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin

/**
 * Generates and plays all Tuti app sounds programmatically using PCM audio.
 * No sound files required — all waveforms are synthesised at runtime.
 */
object TutiSoundManager {

    private const val TAG = "TutiSound"
    private const val SAMPLE_RATE = 44100
    private const val MAX_AMP = 32767

    // Global volume multiplier — applied to every segment before synthesis
    private const val MASTER_VOLUME = 0.3f

    // Debounce: ignore play calls that arrive within this many ms of each other
    private const val DEBOUNCE_MS = 100L

    private var appContext: Context? = null
    private var isEnabled = true
    private var lastPlayMs = 0L

    // ── Waveform type ─────────────────────────────────────────────────────────

    enum class Wave { SINE, TRIANGLE, NOISE }

    /**
     * A single tone "segment": sweeps from [startHz] to [endHz] over [durationMs]
     * starting after [delayMs] within the overall sound.
     */
    data class Seg(
        val startHz: Float,
        val endHz: Float,
        val durationMs: Int,
        val delayMs: Int = 0,
        val volume: Float = 0.25f,
        val wave: Wave = Wave.SINE,
    )

    // ── Init / settings ───────────────────────────────────────────────────────

    fun init(context: Context) {
        try {
            appContext = context.applicationContext
            val prefs = context.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
            isEnabled = prefs.getBoolean("sounds_enabled", true)
        } catch (e: Exception) {
            Log.e(TAG, "init failed", e)
        }
    }

    fun setEnabled(enabled: Boolean) {
        isEnabled = enabled
        try {
            appContext?.getSharedPreferences("tuti_prefs", Context.MODE_PRIVATE)
                ?.edit()?.putBoolean("sounds_enabled", enabled)?.apply()
        } catch (e: Exception) {
            Log.e(TAG, "setEnabled failed", e)
        }
    }

    fun isEnabled(): Boolean = isEnabled

    // ── Public sound API ──────────────────────────────────────────────────────

    /** App open: cheerful three-note rising chirp */
    fun playTutiHello() = play(
        Seg(800f, 1200f, 100, volume = 0.12f),
        Seg(1000f, 1500f, 100, delayMs = 80, volume = 0.12f),
        Seg(1200f, 1800f, 120, delayMs = 170, volume = 0.12f),
    )

    /** Correct answer: bright rising C-E-G chord */
    fun playCorrectAnswer() = play(
        Seg(523f, 523f, 100),
        Seg(659f, 659f, 120, delayMs = 70),
        Seg(784f, 784f, 200, delayMs = 160, volume = 0.28f),
    )

    /** Wrong answer: soft descending two-note dip */
    fun playWrongAnswer() = play(
        Seg(380f, 260f, 200, volume = 0.22f),
        Seg(260f, 170f, 180, delayMs = 160, volume = 0.17f),
    )

    /** Button tap: quick pop */
    fun playButtonClick() = play(Seg(600f, 820f, 60, volume = 0.08f))

    /** Select option: light tick */
    fun playSelectOption() = play(Seg(520f, 720f, 70, volume = 0.16f))

    /** Flashcard flip: filtered noise whoosh */
    fun playCardFlip() = play(
        Seg(400f, 1400f, 70, volume = 0.14f, wave = Wave.NOISE),
        Seg(1400f, 400f, 80, delayMs = 50, volume = 0.10f, wave = Wave.NOISE),
    )

    /** XP coins: five ascending triangle tones */
    fun playXpEarned() {
        val notes = floatArrayOf(2000f, 2250f, 2500f, 2750f, 3000f)
        play(*notes.mapIndexed { i, f ->
            Seg(f, f, 80, delayMs = i * 60, volume = 0.20f, wave = Wave.TRIANGLE)
        }.toTypedArray())
    }

    /** Lesson begin: upbeat four-note scale */
    fun playLessonStart() {
        val notes = floatArrayOf(523f, 587f, 659f, 784f)
        play(*notes.mapIndexed { i, f ->
            Seg(f, f, 80, delayMs = i * 70)
        }.toTypedArray())
    }

    /** Lesson complete: four-note fanfare + shimmer tail */
    fun playLessonComplete() {
        val fanfare = floatArrayOf(523f, 659f, 784f, 1047f)
        val fanSegs = fanfare.mapIndexed { i, f -> Seg(f, f, 110, delayMs = i * 85, volume = 0.28f) }
        val shimmerStart = fanfare.size * 85 + 50
        val shimmer = listOf(
            Seg(880f, 1760f, 120, delayMs = shimmerStart, volume = 0.20f),
            Seg(1760f, 1320f, 140, delayMs = shimmerStart + 110, volume = 0.17f),
        )
        play(*(fanSegs + shimmer).toTypedArray())
    }

    /** Star earned: sparkle arpeggio */
    fun playStarEarned() = play(
        Seg(880f, 1760f, 100, volume = 0.22f),
        Seg(1760f, 1320f, 90, delayMs = 80, volume = 0.20f),
        Seg(1320f, 1760f, 100, delayMs = 160, volume = 0.18f),
    )

    /** Heart lost: sad descending fall */
    fun playLoseHeart() = play(
        Seg(520f, 360f, 200, volume = 0.22f),
        Seg(360f, 200f, 200, delayMs = 170, volume = 0.17f),
    )

    /** Streak kept: rising fire sweep */
    fun playStreakFire() {
        val notes = floatArrayOf(440f, 494f, 523f, 587f, 659f, 784f)
        play(*notes.mapIndexed { i, f ->
            Seg(f, f, 80, delayMs = i * 60, volume = 0.22f)
        }.toTypedArray())
    }

    /** Progress bar fill: rising sweep */
    fun playProgressFill() = play(Seg(300f, 900f, 450, volume = 0.16f))

    // ── Core synthesis engine ─────────────────────────────────────────────────

    private fun play(vararg segments: Seg) {
        if (!isEnabled) return
        val now = System.currentTimeMillis()
        if (now - lastPlayMs < DEBOUNCE_MS) return
        lastPlayMs = now

        Thread {
            try {
                synthesiseAndPlay(segments)
            } catch (e: Exception) {
                Log.e(TAG, "Sound playback failed", e)
            }
        }.apply { isDaemon = true }.start()
    }

    private fun synthesiseAndPlay(segments: Array<out Seg>) {
        val totalMs = segments.maxOfOrNull { it.delayMs + it.durationMs } ?: return
        val totalSamples = (totalMs * SAMPLE_RATE / 1000).coerceAtLeast(1)

        val buffer = ShortArray(totalSamples)

        for (seg in segments) {
            val startSample = (seg.delayMs.toLong() * SAMPLE_RATE / 1000).toInt()
            val segSamples = (seg.durationMs.toLong() * SAMPLE_RATE / 1000).toInt().coerceAtLeast(1)

            // Fade-in: first 5 ms; fade-out: last 10 ms — prevents clicking
            val fadeIn = (SAMPLE_RATE * 0.005).toInt().coerceAtMost(segSamples / 4).coerceAtLeast(1)
            val fadeOut = (SAMPLE_RATE * 0.010).toInt().coerceAtMost(segSamples / 3).coerceAtLeast(1)
            val amp = (seg.volume * MASTER_VOLUME * MAX_AMP).toInt()

            var phase = 0.0

            for (i in 0 until segSamples) {
                val globalIdx = startSample + i
                if (globalIdx >= totalSamples) break

                val t = i.toFloat() / segSamples
                val freq = (seg.startHz + (seg.endHz - seg.startHz) * t).toDouble()
                val phaseInc = 2.0 * PI * freq / SAMPLE_RATE

                val raw = when (seg.wave) {
                    Wave.SINE -> sin(phase)
                    Wave.TRIANGLE -> {
                        val p = ((phase / PI) % 2.0 + 2.0) % 2.0
                        if (p < 1.0) (2.0 * p - 1.0) else (3.0 - 2.0 * p)
                    }
                    Wave.NOISE -> (Math.random() * 2.0 - 1.0)
                }

                val envelope = when {
                    i < fadeIn -> i.toFloat() / fadeIn
                    i > segSamples - fadeOut -> (segSamples - i).toFloat() / fadeOut
                    else -> 1f
                }

                val sample = (raw * amp * envelope).toInt()
                // Mix segments by addition with clamping
                val mixed = (buffer[globalIdx] + sample).coerceIn(-MAX_AMP, MAX_AMP)
                buffer[globalIdx] = mixed.toShort()

                phase += phaseInc
                // Keep phase in [0, 2π] to prevent floating-point drift
                while (phase > 2.0 * PI) phase -= 2.0 * PI
            }
        }

        val minBuf = AudioTrack.getMinBufferSize(
            SAMPLE_RATE,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
        )
        val bufBytes = (buffer.size * 2).coerceAtLeast(minBuf)

        val track = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build(),
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setSampleRate(SAMPLE_RATE)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build(),
            )
            .setBufferSizeInBytes(bufBytes)
            .setTransferMode(AudioTrack.MODE_STATIC)
            .build()

        track.write(buffer, 0, buffer.size)
        track.play()

        // Block this background thread until the buffer has finished playing
        Thread.sleep(totalMs.toLong() + 60)
        track.stop()
        track.release()
    }
}
