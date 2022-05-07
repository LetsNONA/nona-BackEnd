package letsnona.nonabackend.domain.chat.controller;

import letsnona.nonabackend.domain.chat.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/chat/message")
    public void enter(ChatMessageDTO message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        simpMessageSendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        simpMessageSendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
