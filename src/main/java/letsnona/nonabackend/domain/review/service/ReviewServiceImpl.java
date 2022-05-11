package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.review.dto.ReviewAddRequestDTO;
import letsnona.nonabackend.domain.review.dto.ReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewUpdateRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;

    @Override
    public Review saveReview(ReviewAddRequestDTO requestDTO) {
        Optional<Product> product = productRepository.findById(requestDTO.getProductId());

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .owner(memberService.getRequestUser())
                .product(product.get())
                .content(requestDTO.getContent())
                .grade(requestDTO.getGrade())
                .build();

        Review review = reviewDTO.toEntity();

        product.get().addReview(review);
        return reviewRepository.save(review);
    }

    @Override
    public TradeState updateReviewState(long reviewIndex, String tradeState) {
        Review byId = reviewRepository.findById(reviewIndex);
        TradeState state = TradeState.valueOf(tradeState.toUpperCase(Locale.ROOT));
        if (memberService.getRequestUser().equals(byId.getOwner()))
            byId.updateTradeState(state);
        return byId.getTradeState();
    }

    @Override
    public Review updateReview(long reviewIndex, ReviewUpdateRequestDTO dto) {
        Review byId = reviewRepository.findById(reviewIndex);
       if(isReviewOwner(byId) && byId.getTradeState()== TradeState.COMPLETED)
            byId.updateReview(dto);
        return byId;
    }

    @Override
    public Page<ProductReadResDTO> getProductReviews(long productId, Pageable pageable) {
        return null;
    }

    @Override
    public boolean isReviewOwner(Review review) {
//        return product.getOwner().equals(requestMember);
        return  memberService.getRequestUser().equals(review.getOwner());
    }

}
