package letsnona.nonabackend.global.security.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Disabled
class MemberServiceImplTest {
@Autowired
MemberService memberService;
    @Test
    @Disabled
    void calculateAge() {
        //given
        String birthday = "1999-03-19";
        LocalDate birthDate = LocalDate.parse(birthday);
        //when
        int age = memberService.calculateAge(birthDate);

        //then
        assertThat(age).isEqualTo(24);
    }
}