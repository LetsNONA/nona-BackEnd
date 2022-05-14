package letsnona.nonabackend.domain.admin.controller;

import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
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
    public List<PieChartDTO> getGenderRatio() {
     return adminService.getPieChartData();
    }
}
