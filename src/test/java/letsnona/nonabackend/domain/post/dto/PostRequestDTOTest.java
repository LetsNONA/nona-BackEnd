package letsnona.nonabackend.domain.post.dto;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.global.security.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
class PostRequestDTOTest {
    @Autowired
    PostRepository repository;

    @Autowired
    EntityManager em;

    @BeforeAll
    static void setup() {

    }

    @Test
    void savePost() {
        //given

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();
        PostRequestDTO post = PostRequestDTO.builder()
                .owner(member)
                .title("test_제목입니다")
                .content("test_내용입니다")
                .category("임시카테리고")
                .tradePlace("임시거래지역")
                .price(10000)
                .hashTag("임시해쉬태그")
                .build();
        Post entity = post.toEntity();

        repository.save(entity);


    }
}
