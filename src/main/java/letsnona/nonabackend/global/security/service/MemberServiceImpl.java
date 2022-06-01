package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.domain.file.dto.MemberImgRequestDTO;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.entity.enums.MemberState;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    public final FileService fileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public Member getRequestUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        return principal.getUser();
    }

    @Override
    public int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int bornYear = birthday.getYear();
        return (currentYear - bornYear) + 1;
    }

    @Override
    public Member JoinMember(Member member, List<MultipartFile> file) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");
        member.setMemberState(MemberState.LOCKED);
        member.updateAge(calculateAge(member.getBirthday()));
        //  memberRepository.save(member);
        MemberImgRequestDTO memberImgRequestDTO = fileService.saveMemberImg(file);

        member.setOriginalName(memberImgRequestDTO.getOriginalName());
        member.setOriginalImgSrc(memberImgRequestDTO.getOriginalImgSrc());
        member.setThumbImgSrc(memberImgRequestDTO.getThumbImgSrc());

         return memberRepository.save(member);
    }
}
