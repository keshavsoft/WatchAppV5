package com.example.watchappv3.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

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
