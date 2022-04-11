package letsnona.nonabackend.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NamedEntityGraph(name = "PostWithMemberAndCategory", attributeNodes = {
        @NamedAttributeNode("owner"),
        @NamedAttributeNode("category")
})
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Member owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PostImg> images = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String tradePlace;
    private int price;
    private String hashTag;
    private int hit;
    @Column(columnDefinition = "boolean default 0")
    private boolean flagCourierFee;

    @Column(columnDefinition = "boolean default 0")
    private boolean flagDelete;

    public void addReview(Review review) {
        review.setPost(this);
        this.getReviews().add(review);
    }

    public void addImg(PostImg img) {
        img.setPost(this);
        this.getImages().add(img);
    }

    public void deletePost(){
        this.flagDelete = true;
    }

    public void updatePost(PostAddRequestDTO dto){
         this.title = dto.getTitle();
        this.content = dto.getContent();
        this.tradePlace = dto.getTradePlace();
        this.price= dto.getPrice();
        this.hashTag= dto.getHashTag();
        this.flagCourierFee= dto.isFlagCourierFee();
    }
    public void updateCategory( Category byCategoryCode){
        this.category = byCategoryCode;
    }


    @Builder
    public Post(Member owner, String title, String content, Category category, String tradePlace, int price, String hashTag) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.tradePlace = tradePlace;
        this.price = price;
        this.hashTag = hashTag;
    }


}

