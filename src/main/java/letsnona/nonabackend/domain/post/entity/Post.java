package letsnona.nonabackend.domain.post.entity;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Member owner;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PostImg> images= new ArrayList<>() ; // 임시 이미지 아이디, join 필요

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private int hit;
    @Column(columnDefinition = "boolean default 0")
    private boolean flagCourierFee;

    @Column(columnDefinition = "boolean default 0")
    private boolean flagDelete;


    public void addImg(PostImg img) {
        img.setPost(this);
        this.getImages().add(img);
    }

    @Builder
    public Post(Member owner, String title, String content, String category, String tradePlace, int price, String hashTag) {
        this.owner = owner;
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