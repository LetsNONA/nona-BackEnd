package letsnona.nonabackend.domain.file.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class PostImg extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;


    public void setPost(Post post) {
        this.post = post;
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
