package letsnona.nonabackend.global.security.entity;

import lombok.Getter;
import net.jcip.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name= "vw_gender")
@Getter
public class MemberGenderView   {
    @Id
    private String gender;

    private int cnt;
}
