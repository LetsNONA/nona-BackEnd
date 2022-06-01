package letsnona.nonabackend.global.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginSuccessDTO {
    private  String token;
    private String username;
    private String nickname;
}
