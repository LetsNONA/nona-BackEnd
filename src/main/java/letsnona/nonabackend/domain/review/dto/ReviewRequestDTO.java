package letsnona.nonabackend.domain.review.dto;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequestDTO {
   // private Member owner;
    private long  postId;
    private double grade;
    private String content;
}
