package letsnona.nonabackend.domain.post.entity;

import letsnona.nonabackend.global.security.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User owner;

    private String imgid; // 임시 이미지 아이디, join 필요
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;

    @Column(columnDefinition = "boolean default 0")
    private boolean flagCourierFee;

    @Column(columnDefinition = "boolean default 0")
    private boolean flagDelete;

    @Builder
    public Post(User owner, String imgid, String title, String content, String category, String tradePlace, int price, String hashTag) {
        this.owner = owner;
        this.imgid = imgid;
        this.title = title;
        this.content = content;
        this.category = category;
        this.tradePlace = tradePlace;
        this.price = price;
        this.hashTag = hashTag;
    }


}

    /*
        .user(user)
                .title("test_제목입니다")
                .content("test_내용입니다")
                .category("임시카테리고")
                .tradePlace("임시거래지역")
                .price(10000)
                .flagCourierFee(0)
                .hashTag("임시해쉬태그")
                .imgid("임시이미지ID")
                .flagDelete("0")
                .build();
    * */