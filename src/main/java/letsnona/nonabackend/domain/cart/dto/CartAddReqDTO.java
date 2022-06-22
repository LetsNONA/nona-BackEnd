package letsnona.nonabackend.domain.cart.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CartAddReqDTO {
    private long id;
    private long productId;
    private String ownerName;

    public CartAddReqDTO(long id, long productId, String ownerName) {
        this.id = id;
        this.productId = productId;
        this.ownerName = ownerName;
    }

}

