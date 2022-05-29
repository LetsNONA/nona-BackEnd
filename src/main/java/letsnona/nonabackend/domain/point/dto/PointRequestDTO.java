package letsnona.nonabackend.domain.point.dto;

import letsnona.nonabackend.domain.point.entity.Point;
import letsnona.nonabackend.domain.point.enums.PointState;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class PointRequestDTO {

    private long id;

    private Member owner;

    private Review review;

    private PointState pointState;

    public Point toEntity() {
        return  Point.builder()
                .owner(this.owner)
                .review(this.review)
                .pointState(this.pointState)
                .build();
    }

}
