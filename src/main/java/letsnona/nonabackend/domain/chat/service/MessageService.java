package letsnona.nonabackend.domain.chat.service;

import letsnona.nonabackend.domain.chat.dto.ChatMessageDTO;

public interface MessageService {
    void sendMsg(ChatMessageDTO message);
}
