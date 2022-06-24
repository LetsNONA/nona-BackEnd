package letsnona.nonabackend.domain.cataegory.service;

import letsnona.nonabackend.domain.cataegory.dto.RequestAddCategory;
import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory( RequestAddCategory requestAddCategory) {
        Category category = new Category(requestAddCategory);
        categoryRepository.save(category);
    }

    public boolean existCategory(String categoryCode){
        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(categoryCode);
        return byCategoryCode.isPresent();
    }
    public Category getCategory(String categoryCode){
        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(categoryCode);
        if(byCategoryCode.isPresent())
            return byCategoryCode.get();

    }

}
