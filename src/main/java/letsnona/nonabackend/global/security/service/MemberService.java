package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {
    @Transactional
    Member getRequestUser();

    int calculateAge(LocalDate birthday);

    void JoinMember(Member member, List<MultipartFile> file);
}
