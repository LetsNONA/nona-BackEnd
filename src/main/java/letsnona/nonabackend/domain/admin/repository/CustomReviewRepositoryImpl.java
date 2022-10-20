package letsnona.nonabackend.domain.admin.repository;

import letsnona.nonabackend.domain.admin.dto.BarChartCategoryReport;
import letsnona.nonabackend.domain.admin.dto.BrokenChartCompletedData;
import letsnona.nonabackend.domain.admin.dto.BrokenChartTradingData;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomReviewRepositoryImpl implements CustomReviewRepository {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<BrokenChartCompletedData> getReviewBrokenCompletedChartData(String reviewState) {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select trade_completed_date AS x,count(id) AS y from review\n" +
                "where trade_completed_date >=date_sub(DATE_FORMAT(now(), '%Y-%m-%d'), interval 7 Day)\n" +
                "\tAND trade_completed_date <=  DATE_FORMAT(now(), '%Y-%m-%d')\n" +
                "    AND trade_state='" + reviewState + "'\n" +
                "    group by trade_completed_date;");
        return jpaResultMapper.list(nativeQuery, BrokenChartCompletedData.class);
    }

    @Override
    public List<BrokenChartTradingData> getReviewBrokenChartTradingData() {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select left(created_date,10) AS x,count(id) AS y from review\n" +
                "where created_date >=date_sub(DATE_FORMAT(now(), '%Y-%m-%d'), interval 7 Day)\n" +
                "\tAND created_date <=  DATE_FORMAT(now(), '%Y-%m-%d')\n" +
                "    AND trade_state='TRADING'\n" +
                "    group by left(created_date,10);");
        return jpaResultMapper.list(nativeQuery, BrokenChartTradingData.class);
    }
    @Override
    public List<BarChartCategoryReport> getCompletedTransactForCateogry(String categoryCode, String startDay, String endDay) {
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("SELECT count(a.id) as 'cnt' ,a.trade_completed_date as 'date' FROM review a\n" +
                "   JOIN product b ON a.product_id = b.id\n" +
                "   JOIN category c ON b.category_id = c.id\n" +
                "WHERE trade_state = 'COMPLETED'" +
                "   AND c.category_code = '"+categoryCode+"'" +
                "    AND a.trade_completed_date between '" + startDay+ "' AND '"+endDay+"'" +
                "group by c.id,trade_completed_date");
        return jpaResultMapper.list(nativeQuery, BarChartCategoryReport.class);
    }

}