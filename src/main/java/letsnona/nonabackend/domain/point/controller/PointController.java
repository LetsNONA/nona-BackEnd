package letsnona.nonabackend.domain.point.controller;

import letsnona.nonabackend.domain.point.service.PointServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PointController {
    private  final PointServiceImpl pointService;

    @PostMapping("/user/api/point")
    public void increasePoint(@RequestParam String fee){
        pointService.IncreasePoint(fee);
    }
}
