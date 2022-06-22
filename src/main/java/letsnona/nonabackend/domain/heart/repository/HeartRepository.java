package letsnona.nonabackend.domain.heart.repository;


import letsnona.nonabackend.domain.heart.entity.Heart;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    public Page<Heart> findByOwner(Member owner, Pageable pageable);

    public Heart findById(long id);

}
