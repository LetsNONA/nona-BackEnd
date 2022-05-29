package letsnona.nonabackend.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.review.dto.ReviewDTO;
import letsnona.nonabackend.domain.review.enums.TradeState;
import letsnona.nonabackend.global.entity.BaseTimeEntity;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Product product;

    private double grade;
    @Column(columnDefinition = "TEXT")
    private String content;


    @Enumerated(value = EnumType.STRING)
    private TradeState tradeState;

    private LocalDate tradeCompletedDate;

    public void setProduct(Product product) {
        this.product = product;
    }
    public void updateTradeState(TradeState tradeState) { this.tradeState = tradeState;}
    public void updateTradeCompletedDate() { this.tradeCompletedDate = LocalDate.now();}
    public void updateReview(ReviewDTO dto){
       this.grade = dto.getGrade();
        this.content = dto.getContent();
    }
}
