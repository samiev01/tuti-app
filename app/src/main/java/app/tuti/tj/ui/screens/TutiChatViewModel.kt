package app.tuti.tj.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tuti.tj.data.remote.TutiChatApi
import kotlinx.coroutines.launch
import java.util.UUID

data class UiChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val isError: Boolean = false,
)

class TutiChatViewModel : ViewModel() {
    var messages by mutableStateOf<List<UiChatMessage>>(emptyList())
        private set

    var inputText by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    private var lastFailedUserText: String? = null

    fun updateInput(text: String) {
        inputText = text
    }

    fun sendFromChip(text: String) {
        if (text.isBlank() || isLoading) return
        sendMessageInternal(text.trim())
    }

    fun sendCurrentInput() {
        val text = inputText.trim()
        if (text.isBlank() || isLoading) return
        sendMessageInternal(text)
    }

    fun retryAfterError(errorMessageId: String) {
        messages.find { it.id == errorMessageId && it.isError } ?: return
        val userText = lastFailedUserText ?: return
        messages = messages.filter { it.id != errorMessageId }
        lastFailedUserText = null
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            try {
                val result = callApi(userText)
                result.fold(
                    onSuccess = { reply ->
                        messages = messages + UiChatMessage(text = reply, isUser = false)
                    },
                    onFailure = {
                        appendNetworkError(userText)
                    },
                )
            } catch (e: Exception) {
                appendNetworkError(userText)
            } finally {
                isLoading = false
            }
        }
    }

    private fun sendMessageInternal(userText: String) {
        messages = messages + UiChatMessage(text = userText, isUser = true)
        inputText = ""
        isLoading = true
        viewModelScope.launch {
            try {
                val result = callApi(userText)
                result.fold(
                    onSuccess = { reply ->
                        messages = messages + UiChatMessage(text = reply, isUser = false)
                    },
                    onFailure = {
                        appendNetworkError(userText)
                    },
                )
            } catch (e: Exception) {
                appendNetworkError(userText)
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun callApi(userText: String): Result<String> {
        val priorForHistory = messages
            .dropLast(1)
            .filter { !it.isError }
            .takeLast(10)
            .map { m ->
                TutiChatApi.ChatMessage(
                    role = if (m.isUser) "user" else "assistant",
                    text = m.text,
                )
            }
        return try {
            TutiChatApi.sendMessage(
                message = userText,
                conversationHistory = priorForHistory,
                scenario = null,
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun appendNetworkError(userText: String) {
        lastFailedUserText = userText
        messages = messages + UiChatMessage(
            text = "Бубахшед, алоқа қатъ шуд. Боз кӯшиш кунед.",
            isUser = false,
            isError = true,
        )
    }
}
