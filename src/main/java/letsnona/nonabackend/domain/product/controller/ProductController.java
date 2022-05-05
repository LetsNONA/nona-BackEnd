package letsnona.nonabackend.domain.product.controller;

import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductController {

    ProductAddResponseDTO saveProduct(@RequestPart(value = "key") ProductAddRequestDTO postDTO
            , @RequestPart(value = "file") List<MultipartFile> file);

    ProductAddResponseDTO updateProduct(@RequestPart(value = "key") ProductAddRequestDTO postDTO);

    boolean deleteProduct(@PathVariable long postIndex);

    ProductReadResDTO getProductDetail(@PathVariable long postIndex);

    Page<ProductReadResDTO> getSearchProduct(@RequestParam("keyword") String keyword, Pageable pageable);

    Page<ProductReadResDTO> getAllProducts(Pageable pageable);

}
