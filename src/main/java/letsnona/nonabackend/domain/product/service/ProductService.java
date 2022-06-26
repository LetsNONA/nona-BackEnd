package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResImgDTO;
import letsnona.nonabackend.domain.product.dto.update.ProductUpdateRequestDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ProductService {
    @Transactional
    ProductAddResponseDTO saveProduct(Member member,ProductAddRequestDTO productAddRequestDTO);

    @Transactional
    Page<ProductReadResDTO> getProductReadResDTOS(Page<Product> postPage);

    @Transactional
    Page<ProductReadResDTO> getAllProduct(Pageable pageable);

    @Transactional
    Page<ProductReadResDTO> getProductByCategory(String categoryCode, Pageable pageable);

    @Transactional
    ProductAddResponseDTO updateProduct(ProductUpdateRequestDTO postDTO);

    @Transactional
    Page<ProductReadResDTO> getSearchProduct(String keyword, Pageable pageable);

    @Transactional
    Page<ProductReadResDTO> getSearchForOwner(Pageable pageable);
    @Transactional
    ProductReadResDTO getProductDetails(long index);

    @Transactional
    ResponseEntity<byte[]> getRespIMG(String filePath) throws IOException;

    @Transactional
    String deleteProduct(@PathVariable long postIndex);

    List<ProductReadResReviewDTO> getReviewEntityToDTO(List<Review> reviewList);

    List<ProductReadResImgDTO> getImageEntityToDTO(List<PostImg> imgList);

    boolean isPostOwner(Product product, Member requestMember);


}
