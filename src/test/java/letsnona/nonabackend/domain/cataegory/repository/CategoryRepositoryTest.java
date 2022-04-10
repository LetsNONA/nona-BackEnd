package letsnona.nonabackend.domain.cataegory.repository;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryRepositoryTest {
@Autowired
CategoryRepository categoryRepository;

    @Transactional
    @DisplayName("카테고리 추가")
    @Test
    void InsertCategory() {
        //given
        Category category = Category.builder()
                .categoryCode("cg001")
                .categoryName("과일").build();

        //when
        Category save = categoryRepository.save(category);
        //then
        assertThat(save.getId()).isNotNull();
    }

}