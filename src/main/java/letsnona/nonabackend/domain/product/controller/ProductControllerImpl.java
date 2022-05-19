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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Controller
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @PostMapping("/user/api/product")
    public ProductAddResponseDTO saveProduct(
            @RequestPart(value = "key") ProductAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        return productService.saveProduct(postDTO, file);
    }

    @Override
    @PutMapping("user/api/product")
    public ProductAddResponseDTO updateProduct(@RequestPart(value = "key") ProductAddRequestDTO postDTO) {
        return productService.updateProduct(postDTO);
    }

    @Override
    @DeleteMapping("user/api/product/{productIndex}")
    public String deleteProduct(@PathVariable long productIndex) {
        return productService.deleteProduct(productIndex);
    }

    @Override
    @GetMapping("user/api/product/{productIndex}")
    public ProductReadResDTO getProductDetail(@PathVariable long productIndex) {
        /*todo
         *  - 삭제 게시물 안뜨게해야함*/
        return productService.getProductDetails(productIndex);
    }

    @Override
    @GetMapping("/products/search")
    public Page<ProductReadResDTO> getSearchProduct(@RequestParam("keyword") String keyword, Pageable pageable) {
        return productService.getSearchProduct(keyword, pageable);
    }


    @GetMapping("/products/category")
    public Page<ProductReadResDTO> getProductByCategory(@RequestParam("keyword") String category, Pageable pageable) {
        return productService.getProductByCategory(category, pageable);
    }

    @Override
    @GetMapping("/products")
    public Page<ProductReadResDTO> getAllProducts( Pageable pageable) {
        return productService.getAllProduct(pageable);
    }

}
