package com.example.watchappv3.presentation.ws

import android.R.attr.type
import com.example.watchappv3.presentation.model.MessageType
import com.example.watchappv3.presentation.ws.WebSocketState.addMessage
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

                    WebSocketState.addMessage("🕒 $time", MessageType.SYSTEM) // ✅ correct
                }
                "text" -> {
                    WebSocketState.addMessage(
                        text = "🗨 ${json.getString("value")}",
                        type = MessageType.RECEIVED
                    )
                }
            }
        } catch (e: Exception) {
            WebSocketState.addMessage(
                text = text,
                type = MessageType.RECEIVED
            )
        }
    }
}
