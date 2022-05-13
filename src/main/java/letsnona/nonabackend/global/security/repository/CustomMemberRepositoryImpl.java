package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.GenderDTO;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<GenderDTO> countMemberGender() {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select gender, count(*) as cnt from member group by gender");
        return jpaResultMapper.list(nativeQuery, GenderDTO.class);
    }

}
