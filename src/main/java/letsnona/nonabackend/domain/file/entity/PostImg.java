package letsnona.nonabackend.domain.file.entity;

import letsnona.nonabackend.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class PostImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;


    public void setPost(Post post) {
        this.post = post;
        post.getImages().add(this);
    }

    @Builder
    public PostImg(long id, Post post, String originalImgSrc, String thumbImgSrc,String originalName) {
        this.id = id;
        this.post = post;
        this.originalImgSrc = originalImgSrc;
        this.thumbImgSrc = thumbImgSrc;
        this.originalName = originalName;
    }
}
