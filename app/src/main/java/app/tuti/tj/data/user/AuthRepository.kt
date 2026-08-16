package app.tuti.tj.data.user

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await

// ════════════════════════════════════════════════════════════════
//  ВХОД
//
//  Аккаунт появляется раньше, чем экран входа: при первом запуске
//  создаётся анонимный пользователь Firebase, и ответы онбординга
//  сохраняются уже под его uid. Google подключается позже к тому же
//  uid — данные при этом не теряются.
//
//  DI в проекте нет, поэтому репозиторий — object, как соседние
//  FirestoreManager и PlusManager. Состояния он не держит: uid
//  живёт в FirebaseAuth.
// ════════════════════════════════════════════════════════════════

object AuthRepository {

    private const val TAG = "AuthRepository"

    private val auth: FirebaseAuth get() = Firebase.auth

    /**
     * Два параллельных вызова [ensureSignedIn] (старт приложения и
     * сохранение профиля) успели бы оба увидеть пустой currentUser
     * и создать по анонимному аккаунту. Второй остался бы висеть
     * в консоли, а прогресс ушёл бы не туда.
     */
    private val mutex = Mutex()

    val currentUid: String? get() = auth.currentUser?.uid

    val isAnonymous: Boolean get() = auth.currentUser?.isAnonymous == true

    /** Пусто у анонимного аккаунта — имя приходит только вместе с Google. */
    val displayName: String get() = auth.currentUser?.displayName.orEmpty()

    /** Возвращает uid: существующий или новый анонимный. Требует сети при первом вызове. */
    suspend fun ensureSignedIn(): Result<String> = mutex.withLock {
        runCatching {
            auth.currentUser?.uid
                ?: auth.signInAnonymously().await().user!!.uid
        }.onFailure { e ->
            // Самый частый случай — нет сети на первом запуске.
            // Молча проглатывать нельзя: без uid онбординг никуда
            // не сохранится, и это должно быть видно в логе.
            Log.w(TAG, "ensureSignedIn failed: ${e.message}", e)
        }
    }
}
