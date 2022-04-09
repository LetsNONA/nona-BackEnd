package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

/*    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @BeforeEach
    void setup(){
        memberRepository.deleteAll();
    }


    @Test
    @DisplayName("유저가입")
    void insertUser(){
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        //when
        memberRepository.save(member);

        //then
        Member getDbMember = memberRepository.findByUsername("testId");
        getDbMember.toString();
        assertThat(getDbMember).isEqualTo(member);

    }


}