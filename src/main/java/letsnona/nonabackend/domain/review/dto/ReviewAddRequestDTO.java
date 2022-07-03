package letsnona.nonabackend.domain.review.dto;

import letsnona.nonabackend.domain.review.enums.TradeState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewAddRequestDTO {
   // private Member owner;
    private long productId;
    private double grade;
    private String content;
    private TradeState tradeState;

}
