package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
    @Transactional
    Member getRequestUser();
}
