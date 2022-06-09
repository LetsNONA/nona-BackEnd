package letsnona.nonabackend.domain.product.dto.update;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDTO {

    private long id;

    private String title;
    private String content;


    private String tradePlace;
    private int price;
    private String hashTag;
    private boolean flagCourierFee;


    public Product toEntity(Category category) {
        return Product.builder()
                .id(id)
                .title(title)
                .content(content)
                .tradePlace(tradePlace)
                .category(category)
                .price(price)
                .hashTag(hashTag)
                .flagCourierFee(flagCourierFee)
                .build();
    }
}
