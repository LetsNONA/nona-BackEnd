package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface MemberService {
    @Transactional
    Member getRequestUser();

    int calculateAge(LocalDate birthday);
}
