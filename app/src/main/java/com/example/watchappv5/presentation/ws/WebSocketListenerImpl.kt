import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketListenerImpl(
    private val onConnected: () -> Unit,
    private val onDisconnected: () -> Unit,
    private val onMessage: (String) -> Unit
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        onConnected()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        onMessage(text)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        onDisconnected()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        onDisconnected()
    }
}
