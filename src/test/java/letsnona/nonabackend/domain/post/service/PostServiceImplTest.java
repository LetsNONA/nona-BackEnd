package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(classes = NonaBackEndApplication.class)
@DataJpaTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class PostServiceImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;


    @Test
    @DisplayName("게시물 등록테스트")
        //@WithUserDetails("testId3")
    void addPost() {
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        Post post = Post.builder()
                .owner(member)
                .title("test_제목입니다")
                .content("test_내용입니다")
                .category("임시카테리고")
                .tradePlace("임시거래지역")
                .price(10000)
                .hashTag("임시해쉬태그")
                .imgid("임시이미지ID")
                .build();

        //when
        Member getMember = memberRepository.save(member);
        Post getPost = postRepository.save(post);

        //then
        assertThat(getMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(getMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(getPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(getPost.getOwner().getUsername()).isEqualTo(post.getOwner().getUsername());
    }
}