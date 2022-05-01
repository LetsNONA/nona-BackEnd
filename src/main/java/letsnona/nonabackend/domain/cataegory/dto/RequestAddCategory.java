package letsnona.nonabackend.domain.cataegory.dto;

import lombok.Data;

@Data
public class RequestAddCategory {

    private String categoryCode;
    private String categoryName;

    public RequestAddCategory(String categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
}
