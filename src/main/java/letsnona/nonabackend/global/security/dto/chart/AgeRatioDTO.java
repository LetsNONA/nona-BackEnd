package letsnona.nonabackend.global.security.dto.chart;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class AgeRatioDTO {
    private String age_group;
    private int cnt;

    public AgeRatioDTO(String age_group, BigInteger cnt) {
        this.age_group = age_group;
        this.cnt = cnt.intValue();
    }
}
