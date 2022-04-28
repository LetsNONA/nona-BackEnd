package letsnona.nonabackend.domain.product.dto.read;

import letsnona.nonabackend.domain.review.entity.Review;
import lombok.Data;

@Data
public class ProductReadResReviewDTO {
    private long id;
    private String owner;
    private long post_id;
    private double grade;
    private String content;

    public ProductReadResReviewDTO(Review review) {
        this.id = review.getId();
        this.owner = review.getOwner().getUsername();
        this.post_id = review.getProduct().getId();
        this.grade = review.getGrade();
        this.content = review.getContent();
    }
}
