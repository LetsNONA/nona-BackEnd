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

        //given
        ReviewRequestDTO requestDTO = ReviewRequestDTO.builder()
                .postId(2L)
                .grade(1)
                .content("댓글 3")
                .build();
        //when
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
       // Review findReview = reviewRepository.findById(savedReview.getId());

        //then
        assertThat(savedReview.getId()).isNotNull();
        assertThat(savedReview.getContent()).isEqualTo(requestDTO.getContent());
        assertThat(savedReview.getGrade()).isEqualTo(requestDTO.getGrade());

    }
}