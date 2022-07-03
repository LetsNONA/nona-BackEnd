package letsnona.nonabackend.domain.chat.service;

import letsnona.nonabackend.domain.chat.dto.ChatMessageDTO;
import letsnona.nonabackend.domain.chat.dto.ChatRoomDTO;
import letsnona.nonabackend.domain.chat.dto.ChatRoomRespDTO;
import letsnona.nonabackend.domain.chat.entity.ChatMessage;
import letsnona.nonabackend.domain.chat.entity.ChatRoom;
import letsnona.nonabackend.domain.chat.msgState.MessageState;
import letsnona.nonabackend.domain.chat.repository.ChatMessageRepository;
import letsnona.nonabackend.domain.chat.repository.ChatRoomRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatRoomRespDTO getChatRoomMessage(String req, String reps, Pageable pageable) {
        String chatRoomUUID = getChatRoomUUID(req, reps);

        Page<ChatMessage> byChatRoomRoomUUID = chatMessageRepository.findByChatRoomRoomUUID(chatRoomUUID, pageable);
        updateMessageState(req);
        return new ChatRoomRespDTO(chatRoomUUID, parseChatRoomEntityToDTO(byChatRoomRoomUUID));

    }

    public void updateMessageState(String req) {
        Member byUsername = memberRepository.findByUsername(req);

        List<ChatMessage> entityList = chatMessageRepository.findByMessageStateAndReceiver(MessageState.NOT_READ, byUsername);
        for (ChatMessage msg: entityList
        ) {
            if (msg.getReceiver().getUsername().equals(byUsername.getUsername())) {
                msg.updateMessageState(MessageState.READ);
                chatMessageRepository.save(msg);
            }
        }
    }
   /* public void updateMessageState(String req,Page<ChatMessage> entity) {
        Member byUsername = memberRepository.findByUsername(req);

        for (ChatMessage msg: entity
             ) {
            if (msg.getReceiver().getUsername().equals(byUsername.getUsername())) {
                msg.updateMessageState(MessageState.READ);
                chatMessageRepository.save(msg);
            }
        }
    }*/

    public Page<ChatMessageDTO> parseChatRoomEntityToDTO(Page<ChatMessage> entity) {
        return entity.map(ChatMessageDTO::new);
    }


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
