package app.tuti.tj.data.auth

import kotlinx.coroutines.delay

// ════════════════════════════════════════════════════════════════
//  ЗАГЛУШКА ПРИВЯЗКИ АККАУНТА (этап 2A)
//
//  Настоящий linkWithCredential приходит на этапе 2B. Пока вместо
//  него — задержка и заранее выбранный исход: только так можно
//  посмотреть глазами на состояния, которые в жизни ловятся с
//  трудом (нет Play Services, конфликт аккаунтов).
//
//  Переключатель исхода живёт на самом экране и виден только в
//  отладочной сборке. В 2B этот файл уходит целиком.
// ════════════════════════════════════════════════════════════════

/** Сколько «думает» заглушка — чтобы состояние Loading было видно. */
private const val STUB_DELAY_MS = 2_000L

object AccountLinkStub {

    /** Что вернёт следующий вызов [link]. Меняется с экрана в debug-сборке. */
    var outcome: AccountLinkResult = AccountLinkResult.Success

    val outcomes: List<AccountLinkResult> = listOf(
        AccountLinkResult.Success,
        AccountLinkResult.Cancelled,
        AccountLinkResult.Failure(AuthErrorKind.NO_NETWORK),
        AccountLinkResult.Failure(AuthErrorKind.PLAY_SERVICES),
        AccountLinkResult.Failure(AuthErrorKind.ACCOUNT_CONFLICT),
        AccountLinkResult.Failure(AuthErrorKind.UNKNOWN),
    )

    suspend fun link(): AccountLinkResult {
        delay(STUB_DELAY_MS)
        return outcome
    }
}

/** Короткая подпись исхода для отладочного переключателя. */
val AccountLinkResult.debugLabel: String
    get() = when (this) {
        AccountLinkResult.Success -> "OK"
        AccountLinkResult.Cancelled -> "Cancel"
        is AccountLinkResult.Failure -> when (kind) {
            AuthErrorKind.NO_NETWORK -> "Net"
            AuthErrorKind.PLAY_SERVICES -> "Play"
            AuthErrorKind.ACCOUNT_CONFLICT -> "Conflict"
            AuthErrorKind.UNKNOWN -> "Unknown"
        }
    }
