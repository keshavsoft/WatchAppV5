package com.example.watchappv3.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.example.watchappv3.presentation.model.MessageType

@Composable
fun MessageBubble(
    text: String,
    type: MessageType,
    isTime: Boolean = false
) {
    val bgColor1 = if (isTime) Color(0xFF0D47A1) else Color(0xFF1F1F1F)
    val bgColor = when (type) {
        MessageType.SENT -> Color(0xFF1F1F1F)
        MessageType.RECEIVED -> Color(0xFF0D47A1)
        MessageType.SYSTEM -> Color.DarkGray
    }

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
