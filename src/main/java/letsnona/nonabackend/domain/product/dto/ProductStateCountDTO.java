package letsnona.nonabackend.domain.product.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductStateCountDTO {
    BigInteger cnt ;

    public ProductStateCountDTO(BigInteger cnt) {
        this.cnt = cnt;
    }
}
