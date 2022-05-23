package letsnona.nonabackend.domain.chat.service;

import letsnona.nonabackend.domain.chat.dto.ChatMessageDTO;
import letsnona.nonabackend.domain.chat.dto.NotReadMessageDTO;

import java.util.List;

public interface MessageService {
    void sendMsg(ChatMessageDTO message);
    List<NotReadMessageDTO> getNotReadMessageCount(String username);
}
