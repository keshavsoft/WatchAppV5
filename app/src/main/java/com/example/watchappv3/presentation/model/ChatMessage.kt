package com.example.watchappv5.presentation.model

enum class MessageType {
    SENT,
    RECEIVED,
    SYSTEM
}

data class ChatMessage(
    val text: String,
    val type: MessageType
)
