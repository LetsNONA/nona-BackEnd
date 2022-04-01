package letsnona.nonabackend.domain.post.dto.read;

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
}
