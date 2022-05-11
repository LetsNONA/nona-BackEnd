package letsnona.nonabackend.domain.review.controller;

import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.review.dto.ReviewAddRequestDTO;
import letsnona.nonabackend.domain.review.dto.ReviewUpdateRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import letsnona.nonabackend.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {
    private ReviewService reviewService;

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
                .build();
        return reviewService.saveReview(reviewAddRequestDTO);

    }

    @Override
    @GetMapping("/user/api/review/{productId}")
    public Page<ProductReadResDTO> getProductReviews(@PathVariable long productId,Pageable pageable) {
        return reviewService.getProductReviews(productId,pageable);
    }

}
