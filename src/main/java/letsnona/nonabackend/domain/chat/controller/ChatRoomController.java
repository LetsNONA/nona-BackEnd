package letsnona.nonabackend.domain.chat.controller;

import letsnona.nonabackend.domain.chat.dto.ChatRoomDTO;
import letsnona.nonabackend.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    /*TODO-
    *  채팅방 개설시 JWT 토큰 만들어서 반환 그 후
    * queryParam 으로 보낸 후 인증해야함*/
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms")
    public List<ChatRoomDTO> room(){
        return  chatRoomRepository.findAllRooms();
    }

    @PostMapping("/room")
    public ChatRoomDTO createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoomDTO(name);
    }

    @GetMapping("/room/{roomId}")
    public ChatRoomDTO roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
