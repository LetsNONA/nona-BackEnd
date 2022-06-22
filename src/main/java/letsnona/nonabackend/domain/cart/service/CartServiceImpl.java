package letsnona.nonabackend.domain.cart.service;


import letsnona.nonabackend.domain.cart.dto.CartAddReqDTO;
import letsnona.nonabackend.domain.cart.dto.CartRespDTO;
import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.domain.cart.repository.CartRepository;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberService memberService;


    public Page<CartRespDTO> getPageableCart(Pageable pageable) {

        Member requestUser = memberService.getRequestUser();
        Page<Cart> byOwner = cartRepository.findByOwner(requestUser, pageable);

        Page<CartRespDTO> cartReadResDTOS = getCartReadResDTOS(byOwner);
        return cartReadResDTOS;
    }

    public String removeProductCart(long index) {
        Member requestUser = memberService.getRequestUser();

        Cart cart = cartRepository.getById(index);

        if (cart.getOwner().getUsername() == requestUser.getUsername()) {
            cartRepository.delete(cart);
            return "삭제완료";
        }
        return "삭제실패";
    }

    public CartRespDTO addCart(CartAddReqDTO reqDTO) {
        Member byUsername = memberService.getRequestUser();
        Optional<Product> byId = productRepository.findById(reqDTO.getProductId());

        if (byId.isPresent()) {
            Cart cartEntity = Cart.builder()
                    .owner(byUsername)
                    .product(byId.get())
                    .build();


            return new CartRespDTO(cartRepository.save(cartEntity));

        }
        return new CartRespDTO();
    }

    public Page<CartRespDTO> getCartReadResDTOS(Page<Cart> cart) {
        /*
         *  Response :  Entity -> DTO
         * */
        return cart.map(CartRespDTO::new);
    }
}