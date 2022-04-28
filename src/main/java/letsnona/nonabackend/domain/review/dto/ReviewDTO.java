package letsnona.nonabackend.domain.review.dto;

import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewDTO {
    private long id;
    private Member owner;
    private Product product;
    private double grade;
    private String content;

    public Review toEntity() {
    return  Review.builder()
            .owner(this.owner)
            .product(this.product)
            .grade(this.grade)
            .content(this.content)
            .build();
    }
}
