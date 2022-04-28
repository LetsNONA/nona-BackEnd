package letsnona.nonabackend.domain.review.controller;

import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ReviewController {
    void savePost(Authentication authentication
            , @RequestPart(value = "key") ProductAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member user = principal.getUser();

    }

}
