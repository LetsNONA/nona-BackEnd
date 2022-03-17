package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.NonaBackEndApplication;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.global.security.entity.User;
import letsnona.nonabackend.global.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = NonaBackEndApplication.class)
@DataJpaTest
class PostServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    @WithUserDetails("testId")
    void addPost() {
        //given
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Post post = Post.builder()
                .owner(user)
                .title("test_제목입니다")
                .content("test_내용입니다")
                .category("임시카테리고")
                .tradePlace("임시거래지역")
                .price(10000)
                .hashTag("임시해쉬태그")
                .imgid("임시이미지ID")
                .build();

        //when
        postRepository.save(post);

        //then

        assertThat(postRepository.findByOwner(username)).isEqualTo(post);
    }
}