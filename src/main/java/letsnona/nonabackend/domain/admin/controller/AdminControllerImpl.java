package letsnona.nonabackend.domain.admin.controller;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminControllerImpl {
    private  final AdminService adminService;

    @GetMapping("/api/genderRatio")
    public List<BarChartDTO> getGenderRatio() {
     return adminService.getBarChatData();
    }
}
