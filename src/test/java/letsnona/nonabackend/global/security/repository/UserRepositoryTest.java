package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("유저가입")
    void insertUser(){
        //given
        User user = User.builder()
                .username("testId")
                .password(bCryptPasswordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        //when
        userRepository.save(user);

        //then

        assertThat(userRepository.findByUsername("testId")).isEqualTo(user);

    }


}