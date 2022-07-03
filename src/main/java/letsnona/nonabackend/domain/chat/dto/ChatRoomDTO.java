package letsnona.nonabackend.domain.chat.dto;

import letsnona.nonabackend.domain.chat.entity.ChatRoom;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {

    private String roomUUID;
   private Member reqUser;
   private Member repsUser;

    public static ChatRoomDTO create(){
        //String participatesUser
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomUUID = UUID.randomUUID().toString();
     //   room.participatesUser = participatesUser;
        return room;
    }

        public ChatRoom toEntity(){
        return ChatRoom.builder()
                 .roomUUID(this.roomUUID)
                 .reqUser(this.reqUser)
                 .repsUser(this.repsUser)
                 .build();
        }

}