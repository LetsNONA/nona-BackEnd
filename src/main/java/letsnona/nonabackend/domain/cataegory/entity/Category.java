package letsnona.nonabackend.domain.cataegory.entity;

import letsnona.nonabackend.domain.cataegory.dto.RequestAddCategory;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryCode;
    private String categoryName;

    public Category(RequestAddCategory requestAddCategory) {
        this.categoryCode = requestAddCategory.getCategoryCode();
        this.categoryName = requestAddCategory.getCategoryName();
    }
}

