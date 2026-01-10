package com.example.watchappv5.presentation

import androidx.compose.runtime.*
import androidx.wear.compose.material.*

@Composable
fun SendMessageScreen() {

    // variables
    var phone by remember { mutableStateOf("8143779221") }
    var message by remember { mutableStateOf("Hello Watch") }
    var status by remember { mutableStateOf("Idle") }

    ScalingLazyColumn {
        item {
            Text("Send Message")
        }

        item {
            Text("Phone:")
        }

        item {
            Chip(
                label = { Text(phone) },
                onClick = {
                    // simulate change
                    phone = "9876543210"
                }
            )
        }

        item {
            Text("Message:")
        }

        item {
            Chip(
                label = { Text(message) },
                onClick = {
                    // simulate change
                    message = "Test message"
                }
            )
        }

        item {
            Chip(
                label = { Text("Send") },
                enabled = phone.isNotBlank() && message.isNotBlank(),
                onClick = {
                    // ✅ THIS IS WHAT YOU ASKED FOR
                    sendMessage(phone, message)
                    status = "Sent to $phone"
                }
            )
        }

        item {
            Text(status)
        }
    }
}

/**
 * Dummy send function (replace with WebSocket later)
 */
private fun sendMessage(phone: String, message: String) {
    // For now just log / verify flow
    println("SEND -> phone=$phone message=$message")
}
