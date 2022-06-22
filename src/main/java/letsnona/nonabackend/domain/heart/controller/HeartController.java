package letsnona.nonabackend.domain.heart.controller;

import letsnona.nonabackend.domain.heart.dto.HeartAddReqDTO;
import letsnona.nonabackend.domain.heart.dto.HeartRespDTO;
import letsnona.nonabackend.domain.heart.service.HeartServiceImpl;
import letsnona.nonabackend.domain.cart.dto.CartAddReqDTO;
import letsnona.nonabackend.domain.cart.dto.CartRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HeartController {
    private final HeartServiceImpl basketService;

    @GetMapping("/user/api/heart")
    public Page<HeartRespDTO> getAllCartList(Pageable pageable) {
        return basketService.getPageableHeart(pageable);
    }

    @PostMapping("/user/api/heart/{index}")
    public HeartRespDTO addProductForCart(@PathVariable long index) {
        return basketService.addHeart(index);
    }

    /*TODO 모든걸 리팩토링하라*/
    @GetMapping("/user/api/heart/remove/{index}")
    public String removeProductForCart(@PathVariable long index) {

        return basketService.removeProductHeart(index);
    }


}
