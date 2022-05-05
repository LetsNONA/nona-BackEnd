package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.review.dto.ReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Review  saveReview(Member user, ReviewRequestDTO requestDTO) {
        Optional<Product> post = productRepository.findById(requestDTO.getPostId());

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .owner(user)
                .product(post.get())
                .content(requestDTO.getContent())
                .grade(requestDTO.getGrade())
                .build();

        Review review = reviewDTO.toEntity();

        post.get().addReview(review);
        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }
}
