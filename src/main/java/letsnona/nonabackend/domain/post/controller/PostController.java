package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.file.service.FileService;
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
    FileService postService;
   @PostMapping("user/post")
    void savePost(Authentication authentication, List<MultipartFile> file){
       postService.saveImage(file);
   }
}
