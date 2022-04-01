package letsnona.nonabackend.domain.post.dto.read;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResReivewDTO {
    private long id;
    private String owner;
    private long post_id;
    private double grade;
    private String content;
}
