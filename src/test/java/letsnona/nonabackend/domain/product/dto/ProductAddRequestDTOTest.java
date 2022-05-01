package letsnona.nonabackend.domain.product.dto;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
@DataJpaTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
*/
@SpringBootTest
class ProductAddRequestDTOTest {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeAll
    static void setup() {

    }

    @Test
    void dtoToEntityTest() {
        //given

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();
        ProductAddRequestDTO post = ProductAddRequestDTO.builder()
                .owner(member)
                .title("test_제목입니다")
                .content("test_내용입니다")
                .categoryCode("cg001")
                .tradePlace("임시거래지역")
                .price(10000)
                .hashTag("임시해쉬태그")
                .build();

        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(post.getCategoryCode());
        Product entity = post.toEntity(byCategoryCode.get());

        assertThat(post.getOwner()).isEqualTo(entity.getOwner());
        assertThat(post.getTitle()).isEqualTo(entity.getTitle());

    }
}
