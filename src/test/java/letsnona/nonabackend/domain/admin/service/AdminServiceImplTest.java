package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminServiceImpl adminService;
    @Test
    void getBarChatData() {
        List<BarChartDTO> barChatData = adminService.getBarChatData();
        System.out.println(barChatData);
    }
}