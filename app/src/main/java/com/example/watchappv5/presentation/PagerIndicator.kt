package com.example.watchappv5.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(
                        if (index == currentPage) 6.dp else 4.dp
                    )
                    .background(
                        color = if (index == currentPage) Color.White else Color.Gray,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
        }
    }
}
