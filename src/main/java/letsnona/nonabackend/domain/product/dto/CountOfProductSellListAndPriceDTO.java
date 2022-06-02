package letsnona.nonabackend.domain.product.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CountOfProductSellListAndPriceDTO {
    private BigInteger id;
    private BigInteger cnt ;
    private Integer price ;

    public CountOfProductSellListAndPriceDTO(BigInteger id, BigInteger cnt, Integer price) {
        this.id = id;
        this.cnt = cnt;
        this.price = price;
    }
}
