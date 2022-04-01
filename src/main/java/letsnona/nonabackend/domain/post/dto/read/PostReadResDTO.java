package letsnona.nonabackend.domain.post.dto.read;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class PostReadResDTO {
    private long id;
    private Member owner;
    private List<PostImg> images ;
    private List<Review> reviews ;
    private String title;
    private String content;
    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private int hit;
    private boolean flagCourierFee;

}
