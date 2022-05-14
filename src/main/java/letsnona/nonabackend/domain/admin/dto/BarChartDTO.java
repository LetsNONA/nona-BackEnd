package letsnona.nonabackend.domain.admin.dto;


import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BarChartDTO {
    private String age_group;
    private int cnt;
    private static final String color = "hsl(217, 70%, 50%)";


    public BarChartDTO(AgeRatioDTO dto) {
        this.age_group = dto.getAge_group();
        this.cnt = dto.getCnt();
    }
}
