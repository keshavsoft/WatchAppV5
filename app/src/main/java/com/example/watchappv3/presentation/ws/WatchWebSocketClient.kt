package com.example.watchappv3.presentation.ws

import WebSocketListenerImpl
import android.util.Log
import com.example.watchappv3.presentation.model.ChatMessage
import com.example.watchappv3.presentation.model.MessageType
import kotlinx.coroutines.flow.StateFlow
import okhttp3.*

object WatchWebSocketClient {
    private const val TAG = "WatchWS"
    private const val WS_URL = "wss://keshavsoft.com/"

    private val client = OkHttpClient()
    private var socket: WebSocket? = null

    val messages: StateFlow<List<ChatMessage>> = WebSocketState.messages
    val connected: StateFlow<Boolean> = WebSocketState.connected

    fun connect() {
        Log.d("WatchWS", "connect() called")

        if (socket != null) return
        val request = Request.Builder().url(WS_URL).build()

        socket = client.newWebSocket(
            request,
            WebSocketListenerImpl(
                onConnected = {
                    Log.d("WatchWS", "onOpen → CONNECTED")
                    WebSocketState.setConnected(true)
                },
                onDisconnected = {
                    Log.d("WatchWS", "onClosed/onFailure → DISCONNECTED")
                    WebSocketState.setConnected(false)
                },
                onMessage = { MessageParser.handle(it) }
            )
        )
    }

    fun disconnect() {
        socket?.close(1000, "bye")
        socket = null
        WebSocketState.reset()
    }

    fun sendMessage1(message: String) {
        socket?.send(message) ?: Log.w(TAG, "sendMessage: socket not connected")
    }

    fun sendMessage(message: String) {
        if (socket?.send(message) == true) {
            WebSocketState.addMessage(
                text = message,
                type = MessageType.SENT
            )
        } else {
            Log.w(TAG, "sendMessage: socket not connected")
        }
    }

}
