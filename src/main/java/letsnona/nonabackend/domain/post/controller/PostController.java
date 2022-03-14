package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.post.service.PostServiceInterface;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class PostController {

    @Autowired
    PostServiceInterface postService;
   @PostMapping("user/post")
    void savePost(Authentication authentication, MultipartFile[] file){
       postService.saveImage(file);
   }
}
