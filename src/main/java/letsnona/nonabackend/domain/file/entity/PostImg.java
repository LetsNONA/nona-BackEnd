package letsnona.nonabackend.domain.file.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostImg extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    private String originalImgSrc;
    private String thumbImgSrc;
    private String originalName;


    public void setProduct(Product product) {
        this.product = product;
    }

    @Builder
    public PostImg(long id, Product product, String originalImgSrc, String thumbImgSrc, String originalName) {
        this.id = id;
        this.product = product;
        this.originalImgSrc = originalImgSrc;
        this.thumbImgSrc = thumbImgSrc;
        this.originalName = originalName;
    }
}
