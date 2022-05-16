package letsnona.nonabackend.domain.review.dto;

import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import lombok.Data;

@Data
public class MyReviewRespDTO {
    private long id;
    private String owner;
    private String productImg;
    private double grade;
    private String content;
    private TradeState tradeState;

    public MyReviewRespDTO(Review review) {
        this.id = review.getId();
        this.owner = review.getOwner().getUsername();
        this.productImg = review.getProduct().getImages().get(0).getThumbImgSrc();
        this.grade = review.getGrade();
        this.content = review.getContent();
        this.tradeState = review.getTradeState();
    }
}
