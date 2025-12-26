package com.example.watchappv3.presentation.ws

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

object MessageParser {

    fun handle(text: String) {
        try {
            val json = JSONObject(text)
            when (json.getString("type")) {
                "time" -> {
                    val millis = json.getLong("value")
                    val time = SimpleDateFormat(
                        "HH:mm:ss",
                        Locale.getDefault()
                    ).format(Date(millis))
                    WebSocketState.append("🕒 $time")
                }
                "text" -> {
                    WebSocketState.append("💬 ${json.getString("value")}")
                }
            }
        } catch (e: Exception) {
            WebSocketState.append(text)
        }
    }
}
