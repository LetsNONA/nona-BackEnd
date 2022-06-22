package letsnona.nonabackend.domain.heart.dto;

import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.domain.heart.entity.Heart;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class HeartRespDTO {
    private long id;
    private Product product;
    private Member owner;

    public HeartRespDTO(long id, Product product, Member owner) {
        this.id=id;
        this.product= product;
        this.owner=owner;
    }
    public HeartRespDTO(Heart heart) {
       this.id=heart.getId();
       this.owner= heart.getOwner();
       this.product= heart.getProduct();
    }
}

