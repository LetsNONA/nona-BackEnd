package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import letsnona.nonabackend.domain.post.service.PostService;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("user/post")
    void savePost(Authentication authentication
            , @RequestPart(value = "key") PostRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member user = principal.getUser();
        postDTO.setOwner(user);
        postService.savePost(postDTO,file);
    }
}
