package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.MemberRecommendProductDTO;
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

    @Override
    public List<MemberRecommendProductDTO> getRecommendProduct(int memberSeq) {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("SELECT  " +
                "    CASE" +
                "        WHEN m.age >= 10 AND m.age < 20 THEN '10대' " +
                "        WHEN m.age >= 20 AND m.age < 30 THEN '20대' " +
                "        WHEN m.age >= 30 AND m.age < 40 THEN '30대' " +
                "        WHEN m.age >= 40 AND m.age < 50 THEN '40대' " +
                "        WHEN m.age >= 60 AND m.age < 70 THEN '50대' " +
                "           END AS age_group, " +
                "         r.product_id, " +
                "         p.title as product_title, " +
                "        img.thumb_img_src as img_src, " +
                "         COUNT(*) AS '거래량' " +
                "FROM " +
                "    review r " +
                "        JOIN " +
                "    member m ON r.owner_id = m.id " +
                "      LEFT  JOIN " +
                "   product p ON r.product_id = p.id " +
                "      LEFT  JOIN " +
                "   post_img img ON r.product_id = img.id " +
                "GROUP BY age_group , r.product_id " +
                "HAVING age_group = (SELECT  " +
                "        CASE " +
                "                WHEN m.age >= 10 AND m.age < 20 THEN '10대' " +
                "                WHEN m.age >= 20 AND m.age < 30 THEN '20대' " +
                "                WHEN m.age >= 30 AND m.age < 40 THEN '30대' " +
                "                WHEN m.age >= 40 AND m.age < 50 THEN '40대' " +
                "                WHEN m.age >= 60 AND m.age < 70 THEN '50대' " +
                "            END AS age_group " +
                "    FROM " +
                "        member m " +
                "    WHERE " +
                "        id =  " + memberSeq +
                ") ORDER BY COUNT(*) DESC " +
                "LIMIT 3");

        return jpaResultMapper.list(nativeQuery, MemberRecommendProductDTO.class);
    }

}
