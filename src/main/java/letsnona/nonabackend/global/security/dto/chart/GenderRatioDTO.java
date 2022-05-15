package letsnona.nonabackend.global.security.dto.chart;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class GenderRatioDTO {
    private String gender;
    private int cnt;

    public GenderRatioDTO(String gender, BigInteger cnt) {
        this.gender = gender;
        this.cnt = cnt.intValue();
    }
}
