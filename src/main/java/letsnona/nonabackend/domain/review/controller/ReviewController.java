package letsnona.nonabackend.domain.review.controller;

import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewUpdateRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewController {
    TradeState updateReviewState(@PathVariable long reviewIndex, @RequestParam String tradeState);

    Review requestTrade(long productIndex);

    Review updateReview(@PathVariable long reviewIndex, @RequestParam ReviewUpdateRequestDTO dto);

    Page<ProductReadResReviewDTO> getProductReviews(@PathVariable long productId, Pageable pageable);
}
