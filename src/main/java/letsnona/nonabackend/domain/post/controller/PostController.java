package letsnona.nonabackend.domain.post.controller;

import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    /*
     * TODO
     *   -게시글 수정
     *   게시글 검색*/

    private final PostService postService;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping("user/api/post")
    void savePost(Authentication authentication
            , @RequestPart(value = "key") PostAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        PostAddResponseDTO responseDTO = postService.savePost(postDTO, file);
    }

    @DeleteMapping("user/api/post/{postIndex}")
    boolean deletePost(@PathVariable long postIndex) {
        return postService.deletePost(postIndex);
    }


    @GetMapping("user/api/post/{postIndex}")
    PostReadResDTO getPostDetail(@PathVariable long postIndex) {
        return postService.getPostDetails(postIndex);
    }

    @GetMapping("/posts/search")
    Page<PostReadResDTO> getSearchPost(@RequestParam("keyword") String keyword, Pageable pageable) {
        return postService.getSearchPost(keyword, pageable);
    }

    @GetMapping("/posts")
    Page<PostReadResDTO> getAllPosts(Pageable pageable) {
        return postService.getAllPost(pageable);
    }

}
