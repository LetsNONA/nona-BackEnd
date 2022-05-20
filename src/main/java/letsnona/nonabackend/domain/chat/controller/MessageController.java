package letsnona.nonabackend.domain.chat.controller;

import letsnona.nonabackend.domain.chat.dto.ChatMessageDTO;
import letsnona.nonabackend.domain.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final MessageService messageService;

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDTO message) {
        message.setContent(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        simpMessageSendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomUUID(),message);
    }
    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
       messageService.sendMsg(message);
    }
}
