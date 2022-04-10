package letsnona.nonabackend.global.security.dto;

import letsnona.nonabackend.global.security.entity.Member;
import lombok.Data;

@Data
public class PostReadResUserDTO {
    private long id;
    private String username;
    private String email;
    private String roles;
    private String providerId;
    private String provider;

    public PostReadResUserDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.roles = member.getRoles();
        this.providerId = member.getProviderId();
        this.provider = member.getProvider();
    }
}
