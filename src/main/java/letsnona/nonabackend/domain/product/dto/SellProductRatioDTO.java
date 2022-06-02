package letsnona.nonabackend.domain.product.dto;


import lombok.Data;

import java.util.List;

@Data
public class SellProductRatioDTO {
    private ProductStateCountDTO trading;
    private ProductStateCountDTO completed;
    private int totalPrice;
    private List<CountOfProductSellListAndPriceDTO> sellList;
}
