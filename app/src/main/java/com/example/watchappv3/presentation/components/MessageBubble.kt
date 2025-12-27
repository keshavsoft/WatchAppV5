package com.example.watchappv3.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
    val bgColor = when (type) {
        MessageType.SENT -> Color(0xFF1F1F1F)
        MessageType.RECEIVED -> Color(0xFF0D47A1)
        MessageType.SYSTEM -> Color.DarkGray
    }

    val fontSize = if (isTime) 11.sp else 12.sp

    val alignment = when (type) {
        MessageType.SENT -> Alignment.CenterEnd
        MessageType.RECEIVED -> Alignment.CenterStart
        MessageType.SYSTEM -> Alignment.Center
    }

    val isSent = type == MessageType.SENT

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isSent)
            End
        else
            Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isSent && type != MessageType.SYSTEM) {
            BubbleTail(color = bgColor, isSent = false)
        }

        Box(
            modifier = Modifier
                .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.75f)
                .background(bgColor, RoundedCornerShape(14.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = fontSize,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }

        if (isSent) {
            BubbleTail(color = bgColor, isSent = true)
        }
    }
}

@Composable
fun BubbleTail(
    color: Color,
    isSent: Boolean
) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .rotate(if (isSent) 45f else -45f)
            .background(color)
    )
}
