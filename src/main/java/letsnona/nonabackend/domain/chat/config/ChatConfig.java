package letsnona.nonabackend.domain.chat.config;

import letsnona.nonabackend.domain.chat.handler.ChatPreHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class ChatConfig implements WebSocketMessageBrokerConfigurer {
    private final ChatPreHandler chatPreHandler;
   // 메세지 플로우를 모으기 위한 설정



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // CORS 설정 및 소켓을 지원하지 않으면 SockJs 이용
        registry.addEndpoint("/api/websocket").setAllowedOriginPatterns("*").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //Stomp 사용을 위한 Message Broker 설정
        registry.enableSimpleBroker("/queue", "/topic");
        //메세지를 받을떄 ,경로를 설정
        //queue : {1:1} topic {1:N}
        registry.setApplicationDestinationPrefixes("/app");
        //메세지를 보낼 떄 관련 경로 설정
        // -> broker
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(chatPreHandler);
    }
}
