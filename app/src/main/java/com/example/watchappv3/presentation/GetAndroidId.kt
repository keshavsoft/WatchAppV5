package com.example.watchappv3.presentation

import android.content.Context
import android.provider.Settings

fun getAndroidId(context: Context): String {
    return Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}
