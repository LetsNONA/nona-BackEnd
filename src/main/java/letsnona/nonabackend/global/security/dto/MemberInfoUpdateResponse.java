package letsnona.nonabackend.global.security.dto;

import letsnona.nonabackend.global.security.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberInfoUpdateResponse {
    private String username;
    private String nickname;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;
    private String zipCode;

    public MemberInfoUpdateResponse(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickName();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.phoneNumber = member.getPhoneNumber();
        this.zipCode = member.getZipCode();
    }

}
