package gmo.demowebsocket.config;

import gmo.demowebsocket.interceptor.HttpHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//Bật websocket server
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private HttpHandshakeInterceptor handshakeInterceptor;

    @Override
//    định nghĩa những endpoint mà client sẽ sử dụng để gọi và kết nối tới WebSocket
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        addEndpoint() thêm các endpoint
//        withSockJS() sử dụng các giải pháp thay thế khác như xhr-streaming, xhr-polling thay vì kết nối WebSocket mặc định
        registry.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        định nghĩa prefix cho các destination mà client sẽ gửi message tới
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

}