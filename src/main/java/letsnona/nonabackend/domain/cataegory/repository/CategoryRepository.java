package letsnona.nonabackend.domain.cataegory.repository;


import letsnona.nonabackend.domain.cataegory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryCode(String categoryCode);


}
