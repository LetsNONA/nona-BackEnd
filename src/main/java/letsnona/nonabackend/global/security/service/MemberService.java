package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.domain.product.dto.SellProductRatioDTO;
import letsnona.nonabackend.global.security.dto.TotalNonaDataDTO;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {
    @Transactional
    Member getRequestUser();

    @Transactional
    int getPoint();
    int calculateAge(LocalDate birthday);
    @Transactional
    public TotalNonaDataDTO getCountMemberAndTotalProductAndTodayProduct();
    @Transactional
    public SellProductRatioDTO getSellProductRatio();

    Member JoinMember(Member member, List<MultipartFile> file);
}
