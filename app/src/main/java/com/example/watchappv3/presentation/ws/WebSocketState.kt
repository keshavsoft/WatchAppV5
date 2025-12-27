package com.example.watchappv3.presentation.ws

import com.example.watchappv3.presentation.model.ChatMessage
import com.example.watchappv3.presentation.model.MessageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WebSocketState {
    private val _messages =
        MutableStateFlow<List<ChatMessage>>(emptyList())

    val messages: StateFlow<List<ChatMessage>> = _messages

    fun addMessage(text: String, type: MessageType) {
        _messages.value =
            _messages.value + ChatMessage(text, type)
    }

    private val _connected = MutableStateFlow(false)
    val connected: StateFlow<Boolean> = _connected


    fun setConnected(value: Boolean) {
        _connected.value = value
    }

    fun reset() {
        _connected.value = false
        _messages.value = emptyList()
    }
}
