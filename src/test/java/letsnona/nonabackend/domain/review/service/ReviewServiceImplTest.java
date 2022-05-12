package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewAddRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReviewServiceImplTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("reviewServiceAdd")
    void reviewServiceAdd() {
        //given
        ReviewAddRequestDTO requestDTO = ReviewAddRequestDTO.builder()
                .productId(1L)
                .build();
        //when
        Member user = memberRepository.findByUsername("admin");
        Review review = reviewService.requestTrade(requestDTO);

        //then
        assertThat(review.getContent()).isEqualTo(requestDTO.getContent());
        assertThat(review.getGrade()).isEqualTo(requestDTO.getGrade());
    }

    @Test
    @DisplayName("review paging")
    void reviewPaging(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Review> all = reviewRepository.findAll(pageable);
        Page<ProductReadResReviewDTO> productReadResDTOS = reviewService.getProductReadResDTOS(all);
    }
}