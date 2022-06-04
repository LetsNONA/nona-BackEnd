package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.entity.enums.MemberState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
    List<Member> findByMemberState(MemberState memberState);

    List<Member> findAllBy();

    List<Member> findAllByUsernameAndMemberState(String username,MemberState memberState);
    int countAllBy();
}