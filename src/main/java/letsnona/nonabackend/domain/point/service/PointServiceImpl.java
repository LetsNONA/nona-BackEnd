package letsnona.nonabackend.domain.point.service;

import letsnona.nonabackend.domain.point.dto.PointRequestDTO;
import letsnona.nonabackend.domain.point.enums.PointState;
import letsnona.nonabackend.domain.point.repository.PointRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;


@RequiredArgsConstructor
@Service
public class PointServiceImpl {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PointRepository pointRepository;

    public void IncreasePoint(String fee) {

        Member byUsername = memberRepository.findByUsername(memberService.getRequestUser().getUsername());

        PointRequestDTO pointRequestDTO = new PointRequestDTO();
        pointRequestDTO.setOwner(byUsername);
        pointRequestDTO.setPointState(PointState.INCREASE);



        byUsername.increasePoint(Integer.parseInt(fee));
        pointRepository.save(pointRequestDTO.toEntity());

    }
}
