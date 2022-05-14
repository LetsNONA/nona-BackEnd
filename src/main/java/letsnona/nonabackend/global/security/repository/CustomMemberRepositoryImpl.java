package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.chart.AgeRatioDTO;
import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
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
    public List<GenderRatioDTO> getGenderRatio() {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select gender, count(*) as cnt from member group by gender");
        return jpaResultMapper.list(nativeQuery, GenderRatioDTO.class);
    }

    @Override
    public List<AgeRatioDTO> getAgeRatio() {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("SELECT CASE" +
                " When age < 20 Then '10대'" +
                " WHEN age < 30 THEN '20대'" +
                " WHEN age < 40 THEN '30대'" +
                " WHEN age < 50 THEN '40대'" +
                " WHEN age < 60 THEN '50대'" +
                " WHEN age < 70 THEN '60대'" +
                " END AS age_group,count(*) cnt " +
                " FROM member Group by age_group");
        return jpaResultMapper.list(nativeQuery, AgeRatioDTO.class);
    }

}
