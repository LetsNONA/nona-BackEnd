package letsnona.nonabackend.domain.admin.repository;

import letsnona.nonabackend.domain.admin.dto.BrokenChartData;

import java.util.List;

public interface CustomReviewRepository {
    List<BrokenChartData> getReviewBrokenChartData(String reviewState);
}
