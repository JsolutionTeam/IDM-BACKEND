package kr.co.jsol.common.config.websocket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap

@Component
class WebSocketHandler(
    private val CLIENTS: ConcurrentHashMap<String, WebSocketSession> = ConcurrentHashMap(),
) : TextWebSocketHandler() {

    override fun afterConnectionEstablished(session: WebSocketSession) {
        CLIENTS.put(session.id, session)
    }

    override fun afterConnectionClosed(
        session: WebSocketSession,
        status: CloseStatus,
    ) {
        CLIENTS.remove(session.id)
    }

    override fun handleTextMessage(
        session: WebSocketSession,
        message: TextMessage,
    ) {
        val requesterId = session.id

        log.info("requesterId = $requesterId, message = $message")

        CLIENTS.forEach { (id, client) ->
            // 다른 클라이언트에게 메시지 전송
            if (requesterId != id && client.isOpen) {
                client.sendMessage(message)
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}
