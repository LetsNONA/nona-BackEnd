package letsnona.nonabackend.domain.chat.repository;

import letsnona.nonabackend.domain.chat.entity.ChatMessage;
import letsnona.nonabackend.domain.chat.msgState.MessageState;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    Page<ChatMessage> findByChatRoomRoomUUID(String uuid,Pageable pageable);
    List<ChatMessage> findByMessageStateAndReceiver(MessageState messageState, Member receiver);

}

