package letsnona.nonabackend.domain.admin.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BarChartDTO {
    private String id;
    private String label;
    private int value;
    private String color;

    @Builder
    public BarChartDTO(String id, String label, int value, String color) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.color = color;
    }
}
