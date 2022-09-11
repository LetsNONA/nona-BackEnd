package letsnona.nonabackend.domain.product.controller;

import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.product.dto.CreateDateProductCountDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.dto.update.ProductUpdateRequestDTO;
import letsnona.nonabackend.domain.product.enums.ProductState;
import letsnona.nonabackend.domain.product.repository.CustomProductRepositoryImpl;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.product.service.ProductService;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.dto.MemberRecommendProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CustomProductRepositoryImpl customProductRepository;
    private final CategoryRepository categoryRepository;


 /*   @GetMapping("/test")
    public void hello(@AuthenticationPrincipal PrincipalDetails member) {

        System.out.println("member = " + member);
        *//*TODo - Authentication*//*
    }*/

    @GetMapping("/cicd")
    public String cicdTest(){
        return "하고싶지않아~";
    }

    @PostMapping("/user/api/product")
    public ProductAddResponseDTO saveProduct(@AuthenticationPrincipal PrincipalDetails member,
                                             @RequestPart(value = "key") ProductAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file) {
        postDTO.setFile(file);
        return productService.saveProduct(member.getUser(), postDTO);
    }

    @GetMapping("/user/api/recommend_product")
  public List<MemberRecommendProductDTO> getRecommendProductList (@AuthenticationPrincipal PrincipalDetails member){
        return productService.getRecommendProductList(member.getUser());
    }


    @PutMapping("/user/api/product")
    public ProductAddResponseDTO updateProduct(@RequestBody ProductUpdateRequestDTO postDTO) {
        return productService.updateProduct(postDTO);
    }

    @DeleteMapping("user/api/product/{productIndex}")
    public String deleteProduct(@PathVariable long productIndex) {
        return productService.deleteProduct(productIndex);
    }

    @GetMapping("user/api/product/{productIndex}")
    public ProductReadResDTO getProductDetail(@PathVariable long productIndex) {
        /*todo
         *  - 삭제 게시물 안뜨게해야함*/
        return productService.getProductDetails(productIndex);
    }

    @GetMapping("/products/search")
    public Page<ProductReadResDTO> getSearchProduct(@RequestParam("keyword") String keyword, Pageable pageable) {
        return productService.getSearchProduct(keyword, pageable);
    }

    /*TODO
     *  -서비스에 넘겨야함*/
    @GetMapping("/products/count")
    public int getAllProductCount() {
        return productRepository.countProductByProductState(ProductState.SELL);
    }

    @GetMapping("/products/createDateCount")
    public CreateDateProductCountDTO getCreateDateProductCount() {
        return customProductRepository.getCreateDateProductCount();
    }

    @GetMapping("/user/api/products/sellList")
    public Page<ProductReadResDTO> getSearchForOwner(Pageable pageable) {
        return productService.getSearchForOwner(pageable);
    }


    @GetMapping("/products/category")
    public Page<ProductReadResDTO> getProductByCategory(@RequestParam("keyword") String category, Pageable pageable) {
        return productService.getProductByCategory(category, pageable);
    }

    @GetMapping("/products")
    public Page<ProductReadResDTO> getAllProducts(Pageable pageable) {
        return productService.getAllProduct(pageable);
    }

}
