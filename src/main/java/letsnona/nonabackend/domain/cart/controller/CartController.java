package letsnona.nonabackend.domain.cart.controller;

import letsnona.nonabackend.domain.cart.dto.CartAddReqDTO;
import letsnona.nonabackend.domain.cart.dto.CartRespDTO;
import letsnona.nonabackend.domain.cart.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;


    @GetMapping("/user/api/cart")
    public Page<CartRespDTO> getAllCartList(Pageable pageable) {
        return cartService.getPageableCart(pageable);
    }

    @PostMapping("/user/api/cart/{index}")
    public CartRespDTO addProductForCart(@PathVariable long index) {
        return cartService.addCart(index);
    }

    /*TODO 모든걸 리팩토링하라*/
    @GetMapping("/user/api/cart/remove/{index}")
    public String removeProductForCart(@PathVariable long index) {

        return cartService.removeProductCart(index);
    }


}
