package letsnona.nonabackend.domain.chat.service;

import letsnona.nonabackend.domain.chat.dto.ChatRoomDTO;
import letsnona.nonabackend.domain.chat.entity.ChatRoom;
import letsnona.nonabackend.domain.chat.repository.ChatRoomRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Override
    public String getChatRoomUUID(String req, String reps) {
        /*TODO 임시방편 코드
        *  리팩토링 필수 */

        Member reqUser = memberRepository.findByUsername(req);
        Member repsUser = memberRepository.findByUsername(reps);

        Optional<ChatRoom> byReqUserAndRepsUser = chatRoomRepository.findByReqUserAndRepsUser(reqUser, repsUser);

        if (byReqUserAndRepsUser.isPresent())
            return byReqUserAndRepsUser.get().getRoomUUID();

        byReqUserAndRepsUser = chatRoomRepository.findByReqUserAndRepsUser(repsUser, reqUser);
        if (byReqUserAndRepsUser.isPresent())
            return byReqUserAndRepsUser.get().getRoomUUID();

        ChatRoomDTO chatRoomDTO = ChatRoomDTO.create();
        chatRoomDTO.setReqUser(reqUser);
        chatRoomDTO.setRepsUser(repsUser);

        ChatRoom save = chatRoomRepository.save(chatRoomDTO.toEntity());
        return save.getRoomUUID();
    }

}
