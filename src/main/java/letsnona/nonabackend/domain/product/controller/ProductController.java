package letsnona.nonabackend.domain.product.controller;

import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    /*
     * TODO
     *   -게시글 수정
     *   게시글 검색*/

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping("user/api/post")
    ProductAddResponseDTO savePost(
            @RequestPart(value = "key") ProductAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        return productService.savePost(postDTO, file);
    }

    @PutMapping("user/api/post")
    ProductAddResponseDTO updatePost(@RequestPart(value = "key") ProductAddRequestDTO postDTO) {
        return productService.updatePost(postDTO);
    }

    @DeleteMapping("user/api/post/{postIndex}")
    boolean deletePost(@PathVariable long postIndex) {
        return productService.deletePost(postIndex);
    }


    @GetMapping("user/api/post/{postIndex}")
    ProductReadResDTO getPostDetail(@PathVariable long postIndex) {
        /*todo
         *  - 삭제 게시물 안뜨게해야함*/
        return productService.getPostDetails(postIndex);
    }

    @GetMapping("/posts/search")
    Page<ProductReadResDTO> getSearchPost(@RequestParam("keyword") String keyword, Pageable pageable) {
        return productService.getSearchPost(keyword, pageable);
    }

    @GetMapping("/posts")
    Page<ProductReadResDTO> getAllPosts(Pageable pageable) {
        return productService.getAllPost(pageable);
    }

}
