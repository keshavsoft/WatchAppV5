package com.example.watchappv3.presentation.ws

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WebSocketState {

    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> = _messages

    private val _connected = MutableStateFlow(false)
    val connected: StateFlow<Boolean> = _connected

    fun append(msg: String) {
        _messages.value = _messages.value + msg
    }

    fun setConnected(value: Boolean) {
        _connected.value = value
    }

    fun reset() {
        _connected.value = false
        _messages.value = emptyList()
    }
}
