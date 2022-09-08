package letsnona.nonabackend.global.security.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class MemberRecommendProductDTO {
    private String age_group;
    private int product_id;
    private int cnt;

    public MemberRecommendProductDTO(String age_group,BigInteger product_id, BigInteger cnt) {
        this.age_group = age_group;
        this.product_id = product_id.intValue();
        this.cnt = cnt.intValue();
    }
}
