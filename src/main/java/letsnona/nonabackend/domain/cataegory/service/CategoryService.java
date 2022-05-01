package letsnona.nonabackend.domain.cataegory.service;

import letsnona.nonabackend.domain.cataegory.dto.PostReadResCategoryDTO;
import letsnona.nonabackend.domain.cataegory.dto.RequestAddCategory;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryService {
    @Transactional
    void addCategory(RequestAddCategory readResCategoryDTO);
}
