package letsnona.nonabackend.domain.chat.entity;

import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ChatRoom chatRoom;

    private String content;

    @ManyToOne
    private Member writer;

    @Builder
    public ChatMessage(ChatRoom chatRoom, String content, Member writer) {
        this.chatRoom = chatRoom;
        this.content = content;
        this.writer = writer;
    }
}
