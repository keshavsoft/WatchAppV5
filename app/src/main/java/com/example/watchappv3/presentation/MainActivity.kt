package com.example.watchappv3.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.watchappv3.presentation.ws.WatchWebSocketClient

import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

class MainActivity : ComponentActivity() {
    override fun onStart() {
        super.onStart()
        WatchWebSocketClient.connect()
    }

    override fun onStop() {
        super.onStop()
        WatchWebSocketClient.disconnect()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNav()
        }
    }
}

@Composable
fun WatchHomeScreen() {
    MessagesUI()
}

@Composable
fun AppNav() {
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            WatchHomeScreen()
        }
    }
}

@Composable
fun WatchApp() {
    MessagesUI()
}

@Composable
fun MessagesUI() {
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
                MessageBubble(msg, isTime = msg.startsWith("🕒"))
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SendButtons()
            }
        }
    }
}

@Composable
fun MessageBubble(
    text: String,
    isTime: Boolean = false
) {
    val bgColor = if (isTime) Color(0xFF0D47A1) else Color(0xFF1F1F1F)
    val fontSize = if (isTime) 11.sp else 12.sp

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 6.dp)
            .background(bgColor, RoundedCornerShape(14.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = fontSize,
            textAlign = TextAlign.Center
        )
    }
}

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

@Composable
fun WearButton(
    text: String,
    onClick: () -> Unit
) {
    androidx.wear.compose.material.Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(vertical = 4.dp)
    ) {
        Text(text = text, fontSize = 12.sp)
    }
}
