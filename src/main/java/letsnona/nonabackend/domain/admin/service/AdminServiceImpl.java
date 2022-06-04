package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.BrokenChartDTO;
import letsnona.nonabackend.domain.admin.dto.BrokenChartData;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.domain.admin.repository.CustomReviewRepository;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.entity.enums.MemberState;
import letsnona.nonabackend.global.security.repository.CustomMemberRepository;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CustomMemberRepository customMemberRepository;
    private final MemberRepository memberRepository;
    private final CustomReviewRepository customReviewRepository;

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> findAllByUsernameAndMemberState(String username) {
        return memberRepository.findAllByUsernameAndMemberState(username, MemberState.SUCCESS);
    }

    @Override
    public Member findByUsername(String username) {
        return null;
    }

    @Override
    public List<Member> findByLockedMember() {
        return memberRepository.findByMemberState(MemberState.LOCKED);
    }


    @Override
    @Transactional
    public Member changeMemberState(String username) {
        Member byUsername = memberRepository.findByUsername(username);
        byUsername.updateMemberSate(MemberState.SUCCESS);
        return byUsername;
    }

    @Override
    public List<PieChartDTO> getPieChartData() {
        List<GenderRatioDTO> genderRatioDTOS = customMemberRepository.getGenderRatio();
        return parseRespPieChartDTO(genderRatioDTOS);
    }

    @Override
    public List<BarChartDTO> getBarChartData() {
        List<AgeRatioDTO> ageRatioDTOS = customMemberRepository.getAgeRatio();
        return parseRespBarChartDTO(ageRatioDTOS);
    }

    @Override
    public List<BrokenChartDTO> getBrokenChartData() {
        List<BrokenChartData> completed = customReviewRepository.getReviewBrokenChartData("COMPLETED");
        List<BrokenChartData> trading = customReviewRepository.getReviewBrokenChartData("TRADING");

        List<BrokenChartDTO> result = new ArrayList<>();
        BrokenChartDTO completedDTO = BrokenChartDTO.builder()
                .id("거래완료")
                .color("hsl(331, 70%, 50%)")
                .data(completed)
                .build();

        BrokenChartDTO tradingDTO = BrokenChartDTO.builder()
                .id("거래중")
                .color("hsl(219, 70%, 50%)")
                .data(trading)
                .build();

        result.add(completedDTO);
        result.add(tradingDTO);

        return result;
    }

    @Override
    public List<PieChartDTO> parseRespPieChartDTO(List<GenderRatioDTO> dtoList) {
        /*Todo
        - 어마무시하게 더러운 코드 제발 리펙토링!
         */
        List<PieChartDTO> pieChartDTOList = new ArrayList<>();

        for (GenderRatioDTO dto : dtoList
        ) {
            PieChartDTO parse = new PieChartDTO();
            if (isMale(dto))
                parse = initMaleRespPieChartDTO(dto);

            if (isFeMale(dto))
                parse = initFemaleRespPieChartDTO(dto);

            pieChartDTOList.add(parse);
        }
        return pieChartDTOList;
    }

    @Override
    public List<BarChartDTO> parseRespBarChartDTO(List<AgeRatioDTO> dtoList) {
        /*Todo
        - 어마무시하게 더러운 코드 제발 리펙토링!
         */
        List<BarChartDTO> pieChartDTOList = new ArrayList<>();

        for (AgeRatioDTO dto : dtoList
        ) {
            pieChartDTOList.add(new BarChartDTO(dto));
        }
        return pieChartDTOList;
    }

    private PieChartDTO initMaleRespPieChartDTO(GenderRatioDTO dto) {
        return PieChartDTO.builder()
                .id("Male")
                .label("Male")
                .value(dto.getCnt())
                .color("hsl(67, 70%, 50%)")
                .build();

    }

    private PieChartDTO initFemaleRespPieChartDTO(GenderRatioDTO dto) {
        return PieChartDTO.builder()
                .id("Female")
                .label("Female")
                .value(dto.getCnt())
                .color("hsl(213, 70%, 50%)")
                .build();

    }

    private boolean isMale(GenderRatioDTO dto) {
        return dto.getGender().charAt(0) == 'M';
    }

    private boolean isFeMale(GenderRatioDTO dto) {
        return dto.getGender().charAt(0) == 'F';
    }

}