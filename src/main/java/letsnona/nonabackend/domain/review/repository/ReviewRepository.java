package letsnona.nonabackend.domain.review.repository;


import letsnona.nonabackend.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
Review findById(long id);
}
