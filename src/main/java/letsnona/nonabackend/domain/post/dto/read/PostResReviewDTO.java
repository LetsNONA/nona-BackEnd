package letsnona.nonabackend.domain.post.dto.read;

import letsnona.nonabackend.domain.review.entity.Review;
import lombok.Data;

@Data
public class PostResReviewDTO {
    private long id;
    private String owner;
    private long post_id;
    private double grade;
    private String content;

    public PostResReviewDTO(Review review) {
        this.id = review.getId();
        this.owner = review.getOwner().getUsername();
        this.post_id = review.getPost().getId();
        this.grade = review.getGrade();
        this.content = review.getContent();
    }
}
