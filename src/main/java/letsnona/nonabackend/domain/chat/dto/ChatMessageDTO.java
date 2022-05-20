package letsnona.nonabackend.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {

    private String roomUUID;
    private String writer;
    private String content;

}