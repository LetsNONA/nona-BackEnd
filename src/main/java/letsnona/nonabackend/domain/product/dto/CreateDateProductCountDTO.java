package letsnona.nonabackend.domain.product.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CreateDateProductCountDTO {
    BigInteger cnt ;

    public CreateDateProductCountDTO(BigInteger cnt) {
        this.cnt = cnt;
    }
}
