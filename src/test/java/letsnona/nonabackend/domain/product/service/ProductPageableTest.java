package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResImgDTO;
import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ProductPageableTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;


    @Test
    void setup() {
        productRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("Post 페이징")
    void postPageable() throws Exception {
        /*mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andDo(print());*/
        Pageable pageable = PageRequest.of(0, 10);

        Page<Product> all = productRepository.findAll(pageable);
        Page<ProductReadResDTO> dtoPage = all.map(new Function<Product, ProductReadResDTO>() {
            @Override
            public ProductReadResDTO apply(Product post) {

                List<ProductReadResImgDTO> imgDTOList = post.getImages().stream().map(ProductReadResImgDTO::new).collect(Collectors.toList());
                List<ProductReadResReviewDTO> reviewDTOList = post.getReviews().stream().map(ProductReadResReviewDTO::new).collect(Collectors.toList());

                return new ProductReadResDTO(post, imgDTOList, reviewDTOList);

            }
        });
        assertThat(all).isInstanceOf(Page.class);
        assertThat(dtoPage).isInstanceOf(Page.class);
        assertThat(dtoPage.getContent().get(0)).isInstanceOf(ProductReadResDTO.class);

    }
}