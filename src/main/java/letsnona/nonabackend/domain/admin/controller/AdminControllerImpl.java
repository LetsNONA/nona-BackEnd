package letsnona.nonabackend.domain.admin.controller;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.BrokenChartDTO;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.domain.admin.service.AdminService;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminControllerImpl {
    private  final AdminService adminService;

    @GetMapping("/api/point/increase")
    public void increasePoint(@RequestParam String fee,@RequestParam String targetName){
        adminService.IncreasePoint(fee,targetName);
    }

    @GetMapping("/api/allMember")
    public List<Member> getAllMember() {
        return adminService.findAllMember();
    }
    /*TODo-
        임시로 List 형 반환 한번 확인해봐야함*/
    @GetMapping("/api/searchMember")
    public List<Member> getFindByUsername(@RequestParam String username) {
        return adminService.findAllByUsernameAndMemberState(username);
    }
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

    @GetMapping("/api/reviewRatio")
    public List<BrokenChartDTO> getReviewRatio() {
        return adminService.getBrokenChartData();
    }
}
