package letsnona.nonabackend.domain.post.dto.add;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAddResponseDTO {
    private long id;
    private Member owner;
    private String title;
    private String content;
    private Category category;
    private String tradePlace;
    private int price;
    private int hit;
    private String hashTag;
    private boolean flagCourierFee;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<PostImg> images = new ArrayList<>(); // 임시 이미지 아이디, join 필요

    public PostAddResponseDTO(Post post) {
        this.id = post.getId();
        this.owner = post.getOwner();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.tradePlace = post.getTradePlace();
        this.price = post.getPrice();
        this.hit = post.getHit();
        this.hashTag = post.getHashTag();
        this.flagCourierFee = post.isFlagCourierFee();
        this.images = post.getImages();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
