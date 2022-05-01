package letsnona.nonabackend.domain.cataegory.controller;

import letsnona.nonabackend.domain.cataegory.dto.RequestAddCategory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoryController {
    @Transactional
    void addCategory(@RequestBody RequestAddCategory requestAddCategory);
}
