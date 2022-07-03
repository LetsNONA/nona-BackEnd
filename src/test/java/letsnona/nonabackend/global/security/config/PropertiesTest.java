package letsnona.nonabackend.global.security.config;

import letsnona.nonabackend.global.security.jwt.JwtProperties;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Disabled
class PropertiesTest {

    @Value("${jwt.TOKEN_PREFIX}") String TOKEN_PREFIX;
    @Test
    @DisplayName("Properties 주입 테스트")
    void input() {

        assertThat(TOKEN_PREFIX).isNotEmpty();
    }


}