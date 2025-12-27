package com.example.watchappv3.presentation

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.watchappv3.presentation.components.MessageBubble
import com.example.watchappv3.presentation.components.SendButtons
import com.example.watchappv3.presentation.ws.WatchWebSocketClient


@Composable
fun MessagesUI(context: Context) {
    val isConnected = WatchWebSocketClient
        .connected
        .collectAsState(initial = false)
        .value

    val messages = WatchWebSocketClient
        .messages
        .collectAsState(initial = emptyList())
        .value

    MaterialTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = if (isConnected) "🟢 Connected" else "🔴 Disconnected",
                    color = Color.White,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(vertical = 6.dp),
                    textAlign = TextAlign.Center
                )
            }

            items(messages) { msg ->
                MessageBubble(
                    text = msg.text,
                    type = msg.type
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SendButtons(context)
            }
        }
    }
}
