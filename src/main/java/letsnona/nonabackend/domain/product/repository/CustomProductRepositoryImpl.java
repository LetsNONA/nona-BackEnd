package letsnona.nonabackend.domain.product.repository;

import letsnona.nonabackend.domain.product.dto.CountOfProductSellListAndPriceDTO;
import letsnona.nonabackend.domain.product.dto.CreateDateProductCountDTO;
import letsnona.nonabackend.domain.product.dto.ProductStateCountDTO;
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

    public ProductStateCountDTO getCountProductStateOfProduct(String state,String username){
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select count(*) AS cnt from product p\n" +
                "inner join review r\n" +
                "on p.id = r.product_id\n" +
                "where r.trade_state = '"+state+"' AND p.owner_id = (select id from member where username = '"+username+"'); ");

        return jpaResultMapper.uniqueResult(nativeQuery, ProductStateCountDTO.class);

    }

    public List<CountOfProductSellListAndPriceDTO> getCountOfProductSellList(String username){
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select p.id As id ,count(p.id) As cnt ,p.price As price from product p\n" +
                "inner join review r\n" +
                "on  p.id =  r.product_id\n" +
                "where r.trade_state='COMPLETED' AND p.owner_id = (select id from member where username='"+username+"')\n" +
                "group by p.id;");
        return jpaResultMapper.list(nativeQuery, CountOfProductSellListAndPriceDTO.class);
    }
}
