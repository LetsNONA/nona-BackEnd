package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.product.service.ProductService;
import letsnona.nonabackend.domain.review.dto.ReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewAddRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReviewRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;




    @Test
    @DisplayName("리뷰 save")
    @Transactional
    void saveReview() throws IOException {

        //given
        ReviewAddRequestDTO requestDTO = ReviewAddRequestDTO.builder()
                .productId(1L)
                .grade(4)
                .content("댓글 4")
                .build();
        //when
        Member member = memberRepository.findByUsername("testId");
        Optional<Product> byId = productRepository.findById(requestDTO.getProductId());

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .owner(member)
                .product(byId.get())
                .content(requestDTO.getContent())
                .grade(requestDTO.getGrade())
                .build();

        Review review = reviewDTO.toEntity();

        byId.get().addReview(review);

        Review savedReview = reviewRepository.save(review);
       // Review findReview = reviewRepository.findById(savedReview.getId());

        //then
        assertThat(savedReview.getId()).isNotNull();
        assertThat(savedReview.getContent()).isEqualTo(requestDTO.getContent());
        assertThat(savedReview.getGrade()).isEqualTo(requestDTO.getGrade());

    }
}