package letsnona.nonabackend.domain.product.repository;

import letsnona.nonabackend.domain.product.dto.CreateDateProductCountDTO;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CustomProductRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    public CreateDateProductCountDTO getCreateDateProductCount() {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        LocalDate localDate = LocalDate.now();
        Query nativeQuery = em.createNativeQuery("select count(*) AS cnt from product " +
                "where created_date like '%" + localDate + "%' AND product_state='SELL'");
        return jpaResultMapper.uniqueResult(nativeQuery, CreateDateProductCountDTO.class);
    }


}
