package com.example.watchappv5.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MessagesScreen(context: Context, navController: NavHostController) {
    MessagesUI(context, onVersion = {
        navController.navigate("version")
    })
}
