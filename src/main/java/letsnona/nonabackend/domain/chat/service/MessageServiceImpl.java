package letsnona.nonabackend.domain.chat.service;

import letsnona.nonabackend.domain.chat.dto.ChatMessageDTO;
import letsnona.nonabackend.domain.chat.dto.NotReadMessageDTO;
import letsnona.nonabackend.domain.chat.entity.ChatMessage;
import letsnona.nonabackend.domain.chat.entity.ChatRoom;
import letsnona.nonabackend.domain.chat.repository.ChatMessageRepository;
import letsnona.nonabackend.domain.chat.repository.ChatRoomRepository;
import letsnona.nonabackend.domain.chat.repository.CustomMessageRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;
    private final CustomMessageRepository messageRepository;

    @Override
    public void sendMsg(ChatMessageDTO message) {
        ChatRoom byRoomUUID = chatRoomRepository.findByRoomUUID(message.getRoomUUID());
        Member writer = memberRepository.findByUsername(message.getWriter());
        Member receiver = memberRepository.findByUsername(message.getReceiver());
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(byRoomUUID)
                .content(message.getContent())
                .writer(writer)
                .receiver(receiver)
                .build();

        chatMessageRepository.save(chatMessage);

        simpMessageSendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomUUID(), message);
    }
    @Override
    public List<NotReadMessageDTO> getNotReadMessageCount(String username){
        Member byUsername = memberRepository.findByUsername(username);
        return messageRepository.getNotReadMessage(byUsername);
    }
}

