package com.example.watchappv3.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import com.example.watchappv3.presentation.ws.WatchWebSocketClient

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
fun AppNav() {
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MessagesScreen()
        }
    }
}
