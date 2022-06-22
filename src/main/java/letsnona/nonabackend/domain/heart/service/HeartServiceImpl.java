package letsnona.nonabackend.domain.heart.service;


import letsnona.nonabackend.domain.heart.dto.HeartAddReqDTO;
import letsnona.nonabackend.domain.heart.dto.HeartRespDTO;
import letsnona.nonabackend.domain.heart.entity.Heart;
import letsnona.nonabackend.domain.heart.repository.HeartRepository;
import letsnona.nonabackend.domain.cart.dto.CartAddReqDTO;
import letsnona.nonabackend.domain.cart.dto.CartRespDTO;
import letsnona.nonabackend.domain.cart.entity.Cart;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
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
public class HeartServiceImpl {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final MemberService memberService;


    public Page<HeartRespDTO> getPageableHeart(Pageable pageable) {

        Member requestUser = memberService.getRequestUser();
        Page<Heart> byOwner = heartRepository.findByOwner(requestUser, pageable);

        Page<HeartRespDTO> cartReadResDTOS = getHeartReadResDTOS(byOwner);
        return cartReadResDTOS;
    }

    public String removeProductHeart(long index) {
        Member requestUser = memberService.getRequestUser();

        Heart heart = heartRepository.findById(index);

        System.out.println("heart.getOwner().getUsername() = " + heart.getOwner().getUsername());
        System.out.println("requestUser = " + requestUser.getUsername());
        if (heart.getOwner().getUsername().equals(requestUser.getUsername())) {
            heartRepository.delete(heart);
            return "삭제완료";
        }
        return "삭제실패";
    }

    public HeartRespDTO addHeart(long index) {
        Member byUsername = memberService.getRequestUser();
        Optional<Product> byId = productRepository.findById(index);

        if (byId.isPresent()) {
            Heart heartEntity = Heart.builder()
                    .owner(byUsername)
                    .product(byId.get())
                    .build();


            return new HeartRespDTO(heartRepository.save(heartEntity));

        }
        return new HeartRespDTO();
    }

    public Page<HeartRespDTO> getHeartReadResDTOS(Page<Heart> heart) {
        /*
         *  Response :  Entity -> DTO
         * */
        return heart.map(HeartRespDTO::new);
    }
}