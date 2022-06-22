package letsnona.nonabackend.global.security.controller;

import letsnona.nonabackend.domain.product.dto.SellProductRatioDTO;
import letsnona.nonabackend.global.security.dto.ExchangeRequest;
import letsnona.nonabackend.global.security.dto.ExchangeResponse;
import letsnona.nonabackend.global.security.dto.TotalNonaDataDTO;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    // 어드민이 접근 가능
    @GetMapping("admin/users")
    public List<Member> users() {
        return memberRepository.findAll();
    }

    @GetMapping("/user/api/sellRatio")
    public SellProductRatioDTO getSellRatio() {
        return memberService.getSellProductRatio();
    }

    @GetMapping("/user/api/point")
    public int getUserPoint() {
        return memberService.getPoint();
    }

    @GetMapping("/today")
    public TotalNonaDataDTO getTodayData() {
        return memberService.getCountMemberAndTotalProductAndTodayProduct();
    }

    @PostMapping("/join")
    public Member joinMember(
            @RequestPart(value = "key") Member member
            , @RequestPart(value = "file", required = false) List<MultipartFile> file) {
        return memberService.JoinMember(member, file);
    }

    @PostMapping("/user/exchange")
    public ResponseEntity<ExchangeResponse> exchangeMoney(@RequestBody ExchangeRequest exchangeMoney) {
        return new ResponseEntity<>(memberService.exchangeMoney(exchangeMoney), HttpStatus.OK);

    }


}











