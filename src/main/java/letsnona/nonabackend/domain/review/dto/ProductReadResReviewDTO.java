package letsnona.nonabackend.domain.review.dto;

import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import lombok.Data;

@Data
public class ProductReadResReviewDTO {
    private long id;
    private String owner;
    private long product_id;
    private double grade;
    private String content;
    private TradeState tradeState;

    public ProductReadResReviewDTO(Review review) {
        this.id = review.getId();
        this.owner = review.getOwner().getUsername();
        this.product_id = review.getProduct().getId();
        this.grade = review.getGrade();
        this.content = review.getContent();
        this.tradeState = review.getTradeState();
    }
}
