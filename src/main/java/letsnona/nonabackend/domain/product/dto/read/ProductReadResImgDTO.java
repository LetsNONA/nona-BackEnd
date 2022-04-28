package letsnona.nonabackend.domain.product.dto.read;

import letsnona.nonabackend.domain.file.entity.PostImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductReadResImgDTO {
    private long id;
    private long post_id;
    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;

    public ProductReadResImgDTO(PostImg postImg){
        this.id = postImg.getId();
        this.post_id = postImg.getProduct().getId();
        this.originalImgSrc = postImg.getOriginalImgSrc();
        this.thumbImgSrc = postImg.getThumbImgSrc();
        this.originalName = postImg.getOriginalName();
    }
}
