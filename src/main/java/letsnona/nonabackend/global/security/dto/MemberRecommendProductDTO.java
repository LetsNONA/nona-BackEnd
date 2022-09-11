package letsnona.nonabackend.global.security.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
@ToString
public class MemberRecommendProductDTO {
    private String age_group;
    private int product_id;
    private String product_title;
    private String img_src;
    private int cnt;

    public MemberRecommendProductDTO(String age_group, BigInteger product_id, String product_title, String img_src, BigInteger cnt) {
        this.age_group = age_group;
        this.product_id = product_id.intValue();
        this.product_title = product_title;
        this.img_src = img_src;
        this.cnt = cnt.intValue();
    }
}
