package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResImgDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.product.entity.Product;
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
    ProductAddResponseDTO savePost(ProductAddRequestDTO productAddRequestDTO, List<MultipartFile> imgList);

    @Transactional
     Page<ProductReadResDTO> getPostReadResDTOS(Page<Product> postPage);

    @Transactional
    Page<ProductReadResDTO> getAllPost(Pageable pageable);

    @Transactional
    ProductAddResponseDTO updatePost(ProductAddRequestDTO postDTO);
    @Transactional
    Page<ProductReadResDTO> getSearchPost(String keyword, Pageable pageable);

    @Transactional
    ProductReadResDTO getPostDetails(long index);

    @Transactional
    ResponseEntity<byte[]> getRespIMG(String filePath) throws IOException;

    @Transactional
    boolean deletePost(@PathVariable long postIndex);

    List<ProductReadResReviewDTO> getReviewEntityToDTO(List<Review> reviewList);

    List<ProductReadResImgDTO> getImageEntityToDTO(List<PostImg> imgList);

    boolean isPostOwner(Product product, Member requestMember);

    @Transactional
    Member getRequestUser();
}
