package letsnona.nonabackend.domain.chat.repository;

import letsnona.nonabackend.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

}

