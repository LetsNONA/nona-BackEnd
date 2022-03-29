package letsnona.nonabackend.domain.review.service;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.domain.post.service.PostService;
import letsnona.nonabackend.domain.review.dto.ReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    PostService postService;

    @Autowired
    PostRepository postRepository;




    @Test
    @DisplayName("리뷰 save")
    @Transactional
    void saveReview() throws IOException {

        /*ToDO
        *  - 테스트코드 getId 할시에 여러개로 돌아오는거 수정해야함*/

        ReviewRequestDTO requestDTO = ReviewRequestDTO.builder()
                .postId(1L)
                .grade(3)
                .content("정말맛있어요")
                .build();

        Member member = memberRepository.findByUsername("testId");
        Optional<Post> byId = postRepository.findById(requestDTO.getPostId());

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .owner(member)
                .post(byId.get())
                .content(requestDTO.getContent())
                .grade(requestDTO.getGrade())
                .build();

        Review review = reviewDTO.toEntity();

        byId.get().addReview(review);

        Review savedReview = reviewRepository.save(review);
        Review findReview = reviewRepository.findByPostId(byId.get().getId());
        assertThat(savedReview).isEqualTo(findReview);

    }
}