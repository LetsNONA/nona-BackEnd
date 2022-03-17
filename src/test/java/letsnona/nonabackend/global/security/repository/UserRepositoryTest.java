package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

/*    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @BeforeEach
    void setup(){
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("유저가입")
    void insertUser(){
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = User.builder()
                .username("testId3")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        //when
        userRepository.save(user);

        //then

        assertThat(userRepository.findByUsername("testId3")).isEqualTo(user);

    }


}