package letsnona.nonabackend.domain.cataegory.dto;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import lombok.Data;

@Data
public class PostReadResCategoryDTO {
    private long id;
    private String categoryCode;
    private String categoryName;

    public PostReadResCategoryDTO(Category category) {
        this.id = category.getId();
        this.categoryCode = category.getCategoryCode();
        this.categoryName = category.getCategoryName();
    }
}
