package letsnona.nonabackend.domain.file.dto;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostImgResponseDTO {
    private long id;
    private Product product;
    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;
  /*  private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;*/

    public PostImg toEntity() {
        PostImg postImg = PostImg.builder()
                .id(id)
                .product(product)
                .originalImgSrc(originalImgSrc)
                .thumbImgSrc(thumbImgSrc)
                .originalName(originalName)
                .build();

       postImg.setProduct(product);
        return postImg;
    }
}