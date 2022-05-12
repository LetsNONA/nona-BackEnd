package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.entity.MemberGenderView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberGenderRepository extends JpaRepository<MemberGenderView,String> {
    List<MemberGenderView> findAll();
}
