package letsnona.nonabackend.domain.product.dto.add;

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
public class ProductAddRequestDTO {

    private long id;
    private Member owner;
    private String title;
    private String content;
    private String category;
    private String tradePlace;
    private int price;
    private String hashTag;
    private boolean flagCourierFee;
    //private List<PostImgRequestDTO> images = new ArrayList<>(); // 임시 이미지 아이디, join 필요


    public Product toEntity(Category category){
        return Product.builder()
                .id(id)
                .owner(owner)
                .title(title)
                .content(content)
                .category(category)
                .price(price)
                .hashTag(hashTag)
                .flagCourierFee(flagCourierFee)
                .build();
    }
}
