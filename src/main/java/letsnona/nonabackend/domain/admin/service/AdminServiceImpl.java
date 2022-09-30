package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.*;
import letsnona.nonabackend.domain.admin.repository.CustomReviewRepository;
import letsnona.nonabackend.domain.point.dto.PointRequestDTO;
import letsnona.nonabackend.domain.point.enums.PointState;
import letsnona.nonabackend.domain.point.repository.PointRepository;
import letsnona.nonabackend.domain.review.dto.MyReviewRespDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.entity.enums.MemberState;
import letsnona.nonabackend.global.security.repository.CustomMemberRepository;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CustomMemberRepository customMemberRepository;
    private final MemberRepository memberRepository;
    private final CustomReviewRepository customReviewRepository;
    private final PointRepository pointRepository;

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
    public void IncreasePoint(String fee,String targetName) {

        Member byUsername = memberRepository.findByUsername(targetName);

        PointRequestDTO pointRequestDTO = new PointRequestDTO();
        pointRequestDTO.setOwner(byUsername);
        pointRequestDTO.setPointState(PointState.INCREASE);



        byUsername.increasePoint(Integer.parseInt(fee));
        pointRepository.save(pointRequestDTO.toEntity());

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
        List<BrokenChartCompletedData> completed = customReviewRepository.getReviewBrokenCompletedChartData("COMPLETED");
        List<BrokenChartTradingData> trading = customReviewRepository.getReviewBrokenChartTradingData();

        List<BrokenChartDTO> result = new ArrayList<>();
        BrokenChartDTO completedDTO = BrokenChartDTO.builder()
                .id("거래완료")
                .color("hsl(331, 70%, 50%)")
                .data(completed)
                .build();



        BrokenChartDTO tradingDTO = BrokenChartDTO.builder()
                .id("거래중")
                .color("hsl(219, 70%, 50%)")
                .data(getTradingDataToChardDTO(trading))
                .build();

        result.add(completedDTO);
        result.add(tradingDTO);

        return result;
    }

    public List<BrokenChartCompletedData> getTradingDataToChardDTO(List<BrokenChartTradingData> trading) {
        /*
         *  Response :  Entity -> DTO
         * */
        return trading.stream().map(BrokenChartCompletedData::new).collect(Collectors.toList());
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