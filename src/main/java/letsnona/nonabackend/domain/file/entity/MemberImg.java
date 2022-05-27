package letsnona.nonabackend.domain.file.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemberImg extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERIMG_ID")
    private long id;

    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;


//    public void setMember(Member member) {
//        this.member = member;
//    }

    @Builder
    public MemberImg(long id, String originalImgSrc, String thumbImgSrc, String originalName) {
        this.id = id;
        this.originalImgSrc = originalImgSrc;
        this.thumbImgSrc = thumbImgSrc;
        this.originalName = originalName;
    }
}
