package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.global.security.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProductServiceImplTest {



    @Test
    @DisplayName("제품 저장")
    void saveProduct() {
        Member member =
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getProductReadResDTOS() {
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void getSearchProduct() {
    }
}