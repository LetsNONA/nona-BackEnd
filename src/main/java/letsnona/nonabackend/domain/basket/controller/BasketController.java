package letsnona.nonabackend.domain.basket.controller;

import letsnona.nonabackend.domain.basket.service.BasketServiceImpl;
import letsnona.nonabackend.domain.cart.dto.CartAddReqDTO;
import letsnona.nonabackend.domain.cart.dto.CartRespDTO;
import letsnona.nonabackend.domain.cart.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@RestController
@RequiredArgsConstructor
public class BasketController {
    private final BasketServiceImpl basketService;

    @GetMapping("/user/api/basket")
    public Page<CartRespDTO> getAllCartList(Pageable pageable) {
        return basketService.getPageableCart(pageable);
    }

    @PostMapping("/user/api/basket")
    public CartRespDTO addProductForCart(@RequestBody CartAddReqDTO cartAddReqDTO) {
        return basketService.addCart(cartAddReqDTO);
    }

    /*TODO 모든걸 리팩토링하라*/
    @GetMapping("/user/api/basket/remove/index")
    public String removeProductForCart(@PathVariable long index) {

        return basketService.removeProductCart(index);
    }


}
