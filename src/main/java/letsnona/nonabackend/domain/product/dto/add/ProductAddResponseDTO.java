package letsnona.nonabackend.domain.product.dto.add;

import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAddResponseDTO {
    private long id;
    private Member owner;
    private String title;
    private String content;
    private Category category;
    private String tradePlace;
    private int price;
    private int hit;
    private String hashTag;
    private boolean flagCourierFee;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<PostImg> images = new ArrayList<>(); // 임시 이미지 아이디, join 필요

    public ProductAddResponseDTO(Product product) {
        this.id = product.getId();
        this.owner = product.getOwner();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.category = product.getCategory();
        this.tradePlace = product.getTradePlace();
        this.price = product.getPrice();
        this.hit = product.getHit();
        this.hashTag = product.getHashTag();
        this.flagCourierFee = product.isFlagCourierFee();
        this.images = product.getImages();
        this.createdDate = product.getCreatedDate();
        this.modifiedDate = product.getModifiedDate();
    }
}
