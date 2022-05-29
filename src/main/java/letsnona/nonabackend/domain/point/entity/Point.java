package letsnona.nonabackend.domain.point.entity;

import letsnona.nonabackend.domain.point.enums.PointState;
import letsnona.nonabackend.domain.review.entity.Review;
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
public class Point extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Member owner;

    @ManyToOne
    private Review review;

    @Enumerated(value = EnumType.STRING)
    private PointState pointState;

    @Builder
    public Point(Member owner, Review review, PointState pointState) {
        this.owner = owner;
        this.review = review;
        this.pointState = pointState;
    }


}
