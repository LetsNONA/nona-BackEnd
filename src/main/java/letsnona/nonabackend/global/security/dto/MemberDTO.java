package letsnona.nonabackend.global.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class MemberDTO {
    private long id;
    private String username;

    private String password;
    private String nickName;
    private String email;
    private String zipCode;
    private String phoneNumber;
    private String gender;
    private LocalDate birthday;
    private String roles;
    private String providerId;
    private String provider;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.roles = member.getRoles();
        this.providerId = member.getProviderId();
        this.provider = member.getProvider();
    }
}
