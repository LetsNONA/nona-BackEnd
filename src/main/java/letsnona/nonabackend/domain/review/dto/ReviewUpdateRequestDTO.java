package letsnona.nonabackend.domain.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ReviewUpdateRequestDTO {

    String grade;
    String content;

    public ReviewUpdateRequestDTO(String grade, String content) {
        this.grade = grade;
        this.content = content;
    }

}
