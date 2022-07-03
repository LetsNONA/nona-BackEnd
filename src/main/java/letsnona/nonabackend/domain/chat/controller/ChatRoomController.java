package letsnona.nonabackend.domain.chat.controller;

import letsnona.nonabackend.domain.chat.dto.ChatRoomRespDTO;
import letsnona.nonabackend.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    /*TODO-
    *  채팅방 개설시 JWT 토큰 만들어서 반환 그 후
    * queryParam 으로 보낸 후 인증해야함*/
    private final ChatRoomService chatRoomService;
@GetMapping("/Room/uuid")
  public ChatRoomRespDTO requestConnect(@RequestParam String req, @RequestParam String resp, Pageable pageable){
      return chatRoomService.getChatRoomMessage(req,resp,pageable);
  }

}
