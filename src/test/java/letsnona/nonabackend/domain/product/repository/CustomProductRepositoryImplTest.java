package letsnona.nonabackend.domain.product.repository;

import letsnona.nonabackend.domain.product.dto.CountOfProductSellListAndPriceDTO;
import letsnona.nonabackend.domain.product.dto.CreateDateProductCountDTO;
import letsnona.nonabackend.domain.product.dto.ProductStateCountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomProductRepositoryImplTest {

    @Autowired
    CustomProductRepositoryImpl customProductRepository;
    @Test
    void createDateCount(){
        CreateDateProductCountDTO createDateProductCount = customProductRepository.getCreateDateProductCount();
        System.out.println("createDateProductCount = " + createDateProductCount);
    }


    @Test
    void CountOfProductSellListDTO(){
        ProductStateCountDTO countProductStateOfProduct = customProductRepository.getCountProductStateOfProduct("TRADING", "admin");
        ProductStateCountDTO countProductStateOfProduct1 = customProductRepository.getCountProductStateOfProduct("COMPLETED", "admin");
        List<CountOfProductSellListAndPriceDTO> admin = customProductRepository.getCountOfProductSellList("admin");


    }

}