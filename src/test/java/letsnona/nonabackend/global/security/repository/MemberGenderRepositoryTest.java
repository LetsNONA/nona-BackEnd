package letsnona.nonabackend.global.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberGenderRepositoryTest {
@Autowired
MemberGenderRepository memberGenderRepository;
    @Test
    void findAll() {
        System.out.println("memberGenderRepository.findAll() = " + memberGenderRepository.findAll());
    }
}