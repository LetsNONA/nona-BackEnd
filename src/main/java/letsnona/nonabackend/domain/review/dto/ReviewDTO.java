package letsnona.nonabackend.domain.review.dto;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.nio.file.attribute.PosixFileAttributes;

@Builder
@Data
public class ReviewDTO {
    private long id;
    private Member owner;
    private Post post;
    private double grade;
    private String content;

    public Review toEntity() {
    return  Review.builder()
            .owner(this.owner)
            .post(this.post)
            .grade(this.grade)
            .content(this.content)
            .build();
    }
}
