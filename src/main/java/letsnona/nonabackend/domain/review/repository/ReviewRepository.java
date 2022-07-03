package letsnona.nonabackend.domain.review.repository;


import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findById(long id);

    Page<Review> findAll(Pageable pageable);
    Page<Review> findByProductId(long productId,Pageable pageable);
    Page<Review> findByOwner(Member requestUser,Pageable pageable);
}
