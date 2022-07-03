package letsnona.nonabackend.domain.chat.repository;

import letsnona.nonabackend.domain.chat.entity.ChatRoom;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    Optional<ChatRoom> findByReqUserAndRepsUser(Member req, Member reps);
    ChatRoom findByRoomUUID(String UUID);
}

