package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.file.service.FileServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class PostController {

    @Autowired
    FileServiceInterface postService;
   @PostMapping("user/post")
    void savePost(Authentication authentication, List<MultipartFile> file){
       postService.saveImage(file);
   }
}
