package letsnona.nonabackend.domain.point.repository;


import letsnona.nonabackend.domain.point.entity.Point;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {

}
