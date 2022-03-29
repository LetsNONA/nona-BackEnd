package letsnona.nonabackend.domain.review.controller;

import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
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
            , @RequestPart(value = "key") PostAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member user = principal.getUser();

    }

}
