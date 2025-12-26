package com.example.watchappv3.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.watchappv3.presentation.ws.WatchWebSocketClient

@Composable
fun SendButtons() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WearButton("Hi") {
            WatchWebSocketClient.sendMessage("hi")
        }

        WearButton("Ping") {
            WatchWebSocketClient.sendMessage("ping")
        }

        WearButton("I am a Student") {
            WatchWebSocketClient.sendMessage("I am a Student")
        }
    }
}
