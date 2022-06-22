package letsnona.nonabackend.domain.heart.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class HeartAddReqDTO {
    private long id;
    private long productId;
    private String ownerName;

    public HeartAddReqDTO(long id, long productId, String ownerName) {
        this.id = id;
        this.productId = productId;
        this.ownerName = ownerName;
    }

}

