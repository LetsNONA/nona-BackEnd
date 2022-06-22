package letsnona.nonabackend.domain.cart.dto;

import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CartRespDTO {
    private long id;
    private Product product;
    private Member owner;

    public CartRespDTO(long id, Product product, Member owner) {
        this.id=id;
        this.product= product;
        this.owner=owner;
    }
    public CartRespDTO(Cart cart) {
       this.id=cart.getId();
       this.owner= cart.getOwner();
       this.product= cart.getProduct();
    }
}

