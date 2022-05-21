package letsnona.nonabackend.domain.chat.dto;

import letsnona.nonabackend.domain.chat.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessageDTO {
    private long messageId;
    private String roomUUID;
    private String writer;
    private String content;

    public ChatMessageDTO(ChatMessage message){
        this.messageId = message.getId();
    this.roomUUID = message.getChatRoom().getRoomUUID();
    this.writer = message.getWriter().getUsername();
    this.content = message.getContent();
    }

}