package letsnona.nonabackend.domain.review.dto;

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
