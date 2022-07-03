package letsnona.nonabackend.domain.chat.entity;

import letsnona.nonabackend.domain.chat.msgState.MessageState;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ChatRoom chatRoom;

    private String content;

    @ManyToOne
    private Member writer;

    @ManyToOne
    private  Member receiver;


    @Column(length = 15, columnDefinition = "varchar(15) default 'NOT_READ'")
    @Enumerated(value = EnumType.STRING)
    private MessageState messageState = MessageState.NOT_READ;


    public void updateMessageState(MessageState messageState){
        this.messageState = messageState;
    }
    @Builder
    public ChatMessage(ChatRoom chatRoom, String content, Member writer,Member receiver) {
        this.chatRoom = chatRoom;
        this.content = content;
        this.writer = writer;
        this.receiver = receiver;
    }
}
