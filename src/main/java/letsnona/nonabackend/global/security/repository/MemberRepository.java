package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

}