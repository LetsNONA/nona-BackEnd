package letsnona.nonabackend.domain.cataegory.service;

import letsnona.nonabackend.domain.cataegory.dto.RequestAddCategory;
import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.global.exception.CustomErrorCode;
import letsnona.nonabackend.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(RequestAddCategory requestAddCategory) {
        Category category = new Category(requestAddCategory);
        categoryRepository.save(category);
    }

    @Override
    public boolean existCategory(String categoryCode) {
        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(categoryCode);
        return byCategoryCode.isPresent(); //예외처리해야함
    }

    @Override
    public Category getCategory(String categoryCode) {
        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(categoryCode);
        /*TODO - 예외처리해야함*/
        return byCategoryCode.orElseThrow(() -> {
            throw new CustomException(CustomErrorCode.NOT_EXISTS_CATEGORY);
        });

    }

}
