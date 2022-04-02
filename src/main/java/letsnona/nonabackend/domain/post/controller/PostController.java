package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.domain.post.service.PostService;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping("user/api/post")
    void savePost(Authentication authentication
            , @RequestPart(value = "key") PostAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member user = principal.getUser();
        postDTO.setOwner(user);
        PostAddResponseDTO responseDTO = postService.savePost(postDTO, file);
    }

    @GetMapping("/images")
    public ResponseEntity<byte[]> showImage(@RequestParam("img_path") String filePath) throws IOException {
        String decodeFilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
        System.out.println("decodeFilePath = " + decodeFilePath);
        InputStream imageStream = new FileInputStream(filePath);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }


    @GetMapping("/posts")
    Page<PostReadResDTO> getAllPosts(Pageable pageable) {
        Page<Post> all = postRepository.findAll(pageable);
        return postService.findAllPageToDTO(all);
    }
}
