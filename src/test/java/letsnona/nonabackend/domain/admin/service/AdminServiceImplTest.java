package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.repository.CustomMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    CustomMemberRepository customMemberRepository;
    @Test
    void getBarChatData() {
        List<PieChartDTO> barChatData = adminService.getPieChartData();
        System.out.println(barChatData);
    }

    @Test
    void getAgeRatio(){
        List<AgeRatioDTO> ageRatio = customMemberRepository.getAgeRatio();
        System.out.println("ageRatio = " + ageRatio);
    }

    @Test
    void parseRespBarChartDTO(){
        List<BarChartDTO> barChartData = adminService.getBarChartData();
        System.out.println("barChartData = " + barChartData);
    }
}