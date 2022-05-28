package letsnona.nonabackend.domain.admin.controller;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.domain.admin.service.AdminService;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/api/lockedUser")
    public List<Member> getLockedUser() {
        return adminService.findByLockedMember();
    }
    @PutMapping("/api/lockedUser")
    public Member changeLockedUser(@RequestParam String username) {
        return adminService.changeMemberState(username);
    }
    @GetMapping("/api/ageRatio")
    public List<BarChartDTO> getAgeRatio() {
        return adminService.getBarChartData();
    }
}
