package letsnona.nonabackend.domain.chat.service;

import letsnona.nonabackend.domain.chat.dto.ChatRoomRespDTO;
import org.springframework.data.domain.Pageable;

public interface ChatRoomService {
    String getChatRoomUUID(String req, String reps);
    ChatRoomRespDTO getChatRoomMessage(String req, String reps, Pageable pageable);
}
