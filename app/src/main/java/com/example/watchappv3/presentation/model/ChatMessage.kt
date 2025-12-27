package com.example.watchappv3.presentation.model

enum class MessageType {
    SENT,
    RECEIVED,
    SYSTEM
}

data class ChatMessage(
    val text: String,
    val type: MessageType
)
