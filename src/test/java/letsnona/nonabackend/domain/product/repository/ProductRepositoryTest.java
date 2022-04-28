package letsnona.nonabackend.domain.product.repository;

import letsnona.nonabackend.domain.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findByTitleContaining() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> like = productRepository.findByTitleContaining(pageable, "test");
    }
}