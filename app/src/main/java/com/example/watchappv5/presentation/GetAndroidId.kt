package com.example.watchappv5.presentation

import android.content.Context
import android.provider.Settings

fun getAndroidId(context: Context): String {
    return Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}
