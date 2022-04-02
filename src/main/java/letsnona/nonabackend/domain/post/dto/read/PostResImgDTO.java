package letsnona.nonabackend.domain.post.dto.read;

import letsnona.nonabackend.domain.file.entity.PostImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostResImgDTO {
    private long id;
    private long post_id;
    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;

    public PostResImgDTO (PostImg postImg){
        this.id = postImg.getId();
        this.post_id = postImg.getPost().getId();
        this.originalImgSrc = postImg.getOriginalImgSrc();
        this.thumbImgSrc = postImg.getThumbImgSrc();
        this.originalName = postImg.getOriginalName();
    }
}
