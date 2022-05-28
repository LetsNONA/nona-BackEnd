package letsnona.nonabackend.domain.admin.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PieChartDTO {
    private String id;
    private String label;
    private int value;
    private String color;

    @Builder
    public PieChartDTO(String id, String label, int value, String color) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.color = color;
    }
}
