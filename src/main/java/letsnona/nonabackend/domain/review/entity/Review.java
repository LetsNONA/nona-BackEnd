package letsnona.nonabackend.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Member owner;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Post post;

    private double grade;
    @Column(columnDefinition = "TEXT")
    private String content;


    public void setPost(Post post) {
        this.post = post;
    }
}
