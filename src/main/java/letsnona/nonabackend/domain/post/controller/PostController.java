package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import letsnona.nonabackend.domain.post.dto.PostResponseDTO;
import letsnona.nonabackend.domain.post.service.PostService;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("user/api/post")
    void savePost(Authentication authentication
            , @RequestPart(value = "key") PostRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member user = principal.getUser();
        postDTO.setOwner(user);
        PostResponseDTO responseDTO = postService.savePost(postDTO, file);
    }
    @GetMapping("user/api/post/{postIndex}")
    void getPostDetail(@PathVariable("postIndex") Long postIndex){

    }
}
