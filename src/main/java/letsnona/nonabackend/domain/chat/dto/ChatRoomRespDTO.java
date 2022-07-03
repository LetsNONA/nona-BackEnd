package letsnona.nonabackend.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class ChatRoomRespDTO {
    private String roomUUID;
    private Page<ChatMessageDTO> messages;

    public ChatRoomRespDTO(String chatRoomUUID, Page<ChatMessageDTO> parseChatRoomEntityToDTO) {
        this.roomUUID = chatRoomUUID;
        this.messages = parseChatRoomEntityToDTO;
    }
}
