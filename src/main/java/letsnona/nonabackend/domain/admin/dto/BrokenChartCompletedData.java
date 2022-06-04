package letsnona.nonabackend.domain.admin.dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class BrokenChartCompletedData {
    private String x;
    private int y;

    public BrokenChartCompletedData(Date x, BigInteger y) {
        this.x = x.toString();
        this.y = y.intValue();
    }

    public BrokenChartCompletedData(BrokenChartTradingData brokenChartTradingData) {
        this.x = brokenChartTradingData.getX();
        this.y = brokenChartTradingData.getY();
    }
}
