package letsnona.nonabackend.domain.admin.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrokenChartDTO {
    private String id;
    private String color;
    private List<BrokenChartCompletedData> data;
}
