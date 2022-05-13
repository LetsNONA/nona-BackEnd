package letsnona.nonabackend.domain.admin.service;

import letsnona.nonabackend.domain.admin.dto.BarChartDTO;
import letsnona.nonabackend.global.security.dto.GenderDTO;
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
    public List<BarChartDTO> getBarChatData(){
        List<GenderDTO> genderDTOS = customMemberRepository.countMemberGender();
        return parseBarChart(genderDTOS);
    }



    @Override
    public List<BarChartDTO> parseBarChart(List<GenderDTO> dtoList) {
        /*Todo
        -어마무시하게 더러운 코드 제발 리펙토링!
         */
        List<BarChartDTO> barChartDTOList = new ArrayList<>();

        for (GenderDTO dto : dtoList
        ) {
            if (dto.getGender().charAt(0) == 'M') {
                BarChartDTO parse = BarChartDTO.builder()
                        .id("Male")
                        .label("Male")
                        .value(dto.getCnt())
                        .color("hsl(67, 70%, 50%)")
                        .build();
                barChartDTOList.add(parse);
            } else {
                if (dto.getGender().charAt(0) == 'F') {
                    BarChartDTO parse = BarChartDTO.builder()
                            .id("Female")
                            .label("Feale")
                            .value(dto.getCnt())
                            .color("hsl(213, 70%, 50%)")
                            .build();
                    barChartDTOList.add(parse);
                }
            }
        }
        return barChartDTOList;
    }
}