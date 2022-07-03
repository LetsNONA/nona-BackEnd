package letsnona.nonabackend.domain.cataegory.controller;

import letsnona.nonabackend.domain.cataegory.dto.RequestAddCategory;
import letsnona.nonabackend.domain.cataegory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Override
    /*TODO - 어드민권한으로 수정 필요*/
    @PostMapping("/api/category")
    public void addCategory(@RequestBody RequestAddCategory requestAddCategory) {
        categoryService.addCategory(requestAddCategory);
    }
}
