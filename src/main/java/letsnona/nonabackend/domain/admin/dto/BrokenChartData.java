package letsnona.nonabackend.domain.admin.dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class BrokenChartData {
    private String x;
    private int y;

    public BrokenChartData(Date x, BigInteger y) {
        this.x = x.toString();
        this.y = y.intValue();
    }
}
