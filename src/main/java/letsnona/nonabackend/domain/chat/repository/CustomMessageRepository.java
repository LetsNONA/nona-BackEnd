package letsnona.nonabackend.domain.chat.repository;

import letsnona.nonabackend.domain.chat.dto.NotReadMessageDTO;
import letsnona.nonabackend.global.security.entity.Member;

import java.util.List;

public interface CustomMessageRepository {
    List<NotReadMessageDTO> getNotReadMessage(Member req);
}
