package letsnona.nonabackend.domain.chat.entity;

import letsnona.nonabackend.global.entity.BaseTimeEntity;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor

public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomUUID;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member reqUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member repsUser;

    @Builder
    public ChatRoom(String roomUUID, Member reqUser, Member repsUser) {
        this.roomUUID = roomUUID;
        this.reqUser = reqUser;
        this.repsUser = repsUser;
    }
//private String participatesUser;

}
