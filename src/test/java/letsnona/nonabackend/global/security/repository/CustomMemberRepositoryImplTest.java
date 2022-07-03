package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Disabled
class CustomMemberRepositoryImplTest {
    @Autowired
    CustomMemberRepository customMemberRepository;

    @Test

    void getGenderRatio() {
        List<GenderRatioDTO> genderRatio = customMemberRepository.getGenderRatio();
        System.out.println("genderRatio = " + genderRatio);
    }
    @Test

    void countMemberAge() {
        List<AgeRatioDTO> ageStatisticsDTOS = customMemberRepository.getAgeRatio();
        System.out.println("ageStatisticsDTOS = " + ageStatisticsDTOS);
    }
}