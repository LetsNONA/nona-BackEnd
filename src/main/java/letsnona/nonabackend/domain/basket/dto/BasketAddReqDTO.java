package letsnona.nonabackend.domain.basket.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BasketAddReqDTO {
    private long id;
    private long productId;
    private String ownerName;

    public BasketAddReqDTO(long id, long productId, String ownerName) {
        this.id = id;
        this.productId = productId;
        this.ownerName = ownerName;
    }

}

