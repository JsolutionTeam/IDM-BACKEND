package kr.co.jsol.common.config.websocket

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val websocketHandler: WebSocketHandler,
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        log.info("WebSocketConfig registerWebSocketHandlers")
        registry.addHandler(websocketHandler, "/socket")
            .setAllowedOrigins("*")
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}
