package letsnona.nonabackend.domain.file.entity;

import letsnona.nonabackend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    private String originalImgSrc;
    private String thumbImgSrc;

    @Builder
    public PostImg(Post post, String originalImgSrc, String thumbImgSrc) {
        this.post = post;
        this.originalImgSrc = originalImgSrc;
        this.thumbImgSrc = thumbImgSrc;
    }
}
