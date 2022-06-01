package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.point.dto.PointRequestDTO;
import letsnona.nonabackend.domain.point.entity.Point;
import letsnona.nonabackend.domain.point.enums.PointState;
import letsnona.nonabackend.domain.point.repository.PointRepository;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.review.dto.*;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final MemberRepository  memberRepository;
    private final PointRepository pointRepository;

    /*TODO
    *  -리팩토링 해야함...*/
    @Override
    public Review requestTrade(ReviewAddRequestDTO requestDTO) {
        Optional<Product> product = productRepository.findById(requestDTO.getProductId());

        int productFee = product.get().getPrice();
        Member requestUser = memberService.getRequestUser();

        if (requestUser.getPoint() < productFee)
            return new Review();

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .owner(requestUser)
                .product(product.get())
                .content(requestDTO.getContent())
                .grade(requestDTO.getGrade())
                .tradeState(requestDTO.getTradeState())
                .build();

        Review review = reviewDTO.toEntity();

        PointRequestDTO pointRequestDTO = new PointRequestDTO();
        pointRequestDTO.setOwner(requestUser);
        pointRequestDTO.setReview(review);
        pointRequestDTO.setPointState(PointState.DECREASE);

        Point point = pointRequestDTO.toEntity();
        requestUser.decreasePoint(productFee);
        product.get().addReview(review);

        memberRepository.save(requestUser);
        reviewRepository.save(review);
        pointRepository.save(point);
        return review;
    }

    @Override
    @Transactional
    public TradeState updateReviewState(long reviewIndex, String tradeState) {
        Review byId = reviewRepository.findById(reviewIndex);
        TradeState state = TradeState.valueOf(tradeState.toUpperCase(Locale.ROOT));
        if (memberService.getRequestUser().getUsername().equals(byId.getOwner().getUsername()))
            byId.updateTradeState(state);

        if(byId.getTradeState() == TradeState.COMPLETED) {
            Product product = byId.getProduct();
            product.getOwner().increasePoint(product.getPrice());
            byId.updateTradeCompletedDate();

            PointRequestDTO pointRequestDTO = new PointRequestDTO();
            pointRequestDTO.setOwner(product.getOwner());
            pointRequestDTO.setReview(byId);
            pointRequestDTO.setPointState(PointState.INCREASE);

            pointRepository.save(pointRequestDTO.toEntity());
        }

        if(byId.getTradeState() == TradeState.CANCEL) {
            Member owner = byId.getOwner();
            owner.increasePoint(byId.getProduct().getPrice());

            PointRequestDTO pointRequestDTO = new PointRequestDTO();
            pointRequestDTO.setOwner(owner);
            pointRequestDTO.setReview(byId);
            pointRequestDTO.setPointState(PointState.INCREASE);

            pointRepository.save(pointRequestDTO.toEntity());
        }
        return byId.getTradeState();
    }

    @Override
    public Review updateReview(long reviewIndex, ReviewUpdateRequestDTO dto) {
        Review byId = reviewRepository.findById(reviewIndex);
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .grade(Double.parseDouble(dto.getGrade()))
                .content(dto.getContent())
                .build();
        if (isReviewOwner(byId) && byId.getTradeState() == TradeState.COMPLETED)
            byId.updateReview(reviewDTO);
        return byId;
    }

    @Override
    public Page<ProductReadResReviewDTO> getProductReviews(long productId, Pageable pageable) {
        Page<Review> byProductId = reviewRepository.findByProductId(productId, pageable);
        return getProductReadResDTOS(byProductId);
    }

    @Override
    public Page<MyReviewRespDTO> getUserPurchase(Pageable pageable) {
        Member requestUser = memberService.getRequestUser();
        Page<Review> byProductId = reviewRepository.findByOwner(requestUser, pageable);
        return getMyReviewsDTOS(byProductId);
    }



    @Override
    public Page<ProductReadResReviewDTO> getProductReadResDTOS(Page<Review> review) {
        /*
         *  Response :  Entity -> DTO
         * */
        return review.map(ProductReadResReviewDTO::new);
    }

    @Override
    public Page<MyReviewRespDTO> getMyReviewsDTOS(Page<Review> review) {
        /*
         *  Response :  Entity -> DTO
         * */
        return review.map(MyReviewRespDTO::new);
    }


    @Override
    public boolean isReviewOwner(Review review) {
//        return product.getOwner().equals(requestMember);
        return memberService.getRequestUser().getUsername().equals(review.getOwner().getUsername());
    }

}
