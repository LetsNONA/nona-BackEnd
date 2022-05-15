package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.domain.admin.dto.PieChartDTO;
import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import letsnona.nonabackend.global.security.repository.CustomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CustomMemberRepository customMemberRepository;

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