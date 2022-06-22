package letsnona.nonabackend.global.security.dto;

import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MemberInfoUpdateRequest {
    private String username;
    private String nickname;
    private String password;
    private String passwordConfirm;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;
    private String zipCode;

}
