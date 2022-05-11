package letsnona.nonabackend.domain.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewUpdateRequestDTO {
   // private Member owner;
    private long productId;
    private double grade;
    private String content;

}
