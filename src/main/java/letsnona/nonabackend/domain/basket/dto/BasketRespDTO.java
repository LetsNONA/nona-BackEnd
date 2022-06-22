package letsnona.nonabackend.domain.basket.dto;

import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BasketRespDTO {
    private long id;
    private Product product;
    private Member owner;

    public BasketRespDTO(long id, Product product, Member owner) {
        this.id=id;
        this.product= product;
        this.owner=owner;
    }
    public BasketRespDTO(Cart cart) {
       this.id=cart.getId();
       this.owner= cart.getOwner();
       this.product= cart.getProduct();
    }
}

