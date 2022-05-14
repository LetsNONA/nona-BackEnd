package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomMemberRepositoryImplTest {
    @Autowired
    CustomMemberRepository customMemberRepository;

    @Test
    void countMemberAge() {
        List<AgeRatioDTO> ageStatisticsDTOS = customMemberRepository.getAgeRatio();
        System.out.println("ageStatisticsDTOS = " + ageStatisticsDTOS);
    }
}