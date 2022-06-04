package letsnona.nonabackend.domain.admin.dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class BrokenChartTradingData {
    private String x;
    private int y;

    public BrokenChartTradingData(String x, BigInteger y) {
        this.x = x;
        this.y = y.intValue();
    }
}
