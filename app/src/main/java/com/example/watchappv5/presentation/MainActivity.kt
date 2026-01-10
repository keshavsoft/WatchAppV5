package com.example.watchappv5.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.watchappv5.presentation.ws.WatchWebSocketClient

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme

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
            MaterialTheme { Box(Modifier.background(Color.Black)) { PagerScreen() } }
        }
    }
}

@Composable
fun PagerScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val context = LocalContext.current

    Box {
        // pages
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> MessagesUI(context = context, onVersion = {})
                1 -> VersionScreen()
                2 -> SendMessageScreen()
            }
        }

        // dots indicator (top-center)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 6.dp)
        ) {
            PagerIndicator(
                pageCount = 3,
                currentPage = pagerState.currentPage
            )
        }
    }
}
