package letsnona.nonabackend.domain.admin.repository;

import letsnona.nonabackend.domain.admin.dto.BarChartCategoryReport;
import letsnona.nonabackend.domain.admin.dto.BrokenChartCompletedData;
import letsnona.nonabackend.domain.admin.dto.BrokenChartTradingData;

import java.util.List;

public interface CustomReviewRepository {
    List<BrokenChartCompletedData> getReviewBrokenCompletedChartData(String reviewState);

    List<BrokenChartTradingData> getReviewBrokenChartTradingData();
    public List<BarChartCategoryReport> getCompletedTransactForCateogry(String categoryCode, String startDay, String endDay);
}
