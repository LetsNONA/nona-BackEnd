package letsnona.nonabackend.domain.review.controller;

import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewAddRequestDTO;
import letsnona.nonabackend.domain.review.dto.ReviewUpdateRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import letsnona.nonabackend.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {
    private final ReviewService reviewService;
    /*TODO
    *  - updateReview 해야함*/

    @Override
    @PutMapping("/user/api/review/{reviewIndex}")
    public Review updateReview(@PathVariable long reviewIndex, @RequestParam ReviewUpdateRequestDTO dto) {
        return null;

    }

    @Override
    @PutMapping("/user/api/review/state/{reviewIndex}")
    public TradeState updateReviewState(@PathVariable long reviewIndex, @RequestParam String tradeState) {
        return reviewService.updateReviewState(reviewIndex, tradeState);
    }

    @Override
    @PostMapping("/user/api/review/{productIndex}")
    public Review requestTrade(@PathVariable long productIndex) {
        ReviewAddRequestDTO reviewAddRequestDTO = ReviewAddRequestDTO.builder()
                .productId(productIndex)
                .content("")
                .tradeState(TradeState.TRADING)
                .build();
        return reviewService.requestTrade(reviewAddRequestDTO);

    }

    @Override
    @GetMapping("/user/api/review/{productId}")
    public Page<ProductReadResReviewDTO> getProductReviews(@PathVariable long productId, Pageable pageable) {
        return reviewService.getProductReviews(productId, pageable);
    }

}
