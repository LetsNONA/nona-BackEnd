package letsnona.nonabackend.global.security.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class GenderDTO {
    private String gender;
    private int cnt;

    public GenderDTO(String gender, BigInteger cnt) {
        this.gender = gender;
        this.cnt = cnt.intValue();
    }
}
