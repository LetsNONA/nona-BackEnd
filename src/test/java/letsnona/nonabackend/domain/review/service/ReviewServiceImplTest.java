package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.review.dto.ReviewRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReviewServiceImplTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("reviewServiceAdd")
    void reviewServiceAdd() {
        //given
        ReviewRequestDTO requestDTO = ReviewRequestDTO.builder()
                .postId(1L)
                .grade(3)
                .content("정말맛있어요")
                .build();
        //when
        Member user = memberRepository.findByUsername("testId");
        Review review = reviewService.saveReview(user, requestDTO);

        //then
        assertThat(review.getContent()).isEqualTo(requestDTO.getContent());
        assertThat(review.getGrade()).isEqualTo(requestDTO.getGrade());
    }
}