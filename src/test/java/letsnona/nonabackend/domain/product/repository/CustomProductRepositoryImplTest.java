package letsnona.nonabackend.domain.product.repository;

import letsnona.nonabackend.domain.product.dto.CreateDateProductCountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomProductRepositoryImplTest {

    @Autowired
    CustomProductRepositoryImpl customProductRepository;
    @Test
    void createDateCount(){
        CreateDateProductCountDTO createDateProductCount = customProductRepository.getCreateDateProductCount();
        System.out.println("createDateProductCount = " + createDateProductCount);
    }

}