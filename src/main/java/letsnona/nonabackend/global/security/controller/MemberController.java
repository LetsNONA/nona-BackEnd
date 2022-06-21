package letsnona.nonabackend.global.security.controller;

import letsnona.nonabackend.domain.product.dto.SellProductRatioDTO;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.dto.TotalNonaDataDTO;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor 
public class MemberController {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 모든 사람이 접근 가능


	
	// 어드민이 접근 가능
	@GetMapping("admin/users")
	public List<Member> users(){
		return memberRepository.findAll();
	}
	
@GetMapping("/user/api/sellRatio")
public SellProductRatioDTO getSellRatio (){
		return memberService.getSellProductRatio();
}

	@GetMapping("/today")
	public TotalNonaDataDTO getTodayData (){
		return memberService.getCountMemberAndTotalProductAndTodayProduct();
	}

	@PostMapping("/join")
	public Member joinMember(
			@RequestPart(value = "key") Member member
			, @RequestPart(value = "file",required = false) List<MultipartFile> file) {
		 return memberService.JoinMember(member, file);
	}


	
}











