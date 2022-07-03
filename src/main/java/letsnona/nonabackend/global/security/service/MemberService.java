package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.domain.product.dto.SellProductRatioDTO;
import letsnona.nonabackend.global.security.dto.*;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface MemberService {
    Member getRequestUser();

    int getPoint();
    int calculateAge(LocalDate birthday);
    public TotalNonaDataDTO getCountMemberAndTotalProductAndTodayProduct();
    public SellProductRatioDTO getSellProductRatio();

    Member JoinMember(Member member, List<MultipartFile> file);

    @Transactional
    ExchangeResponse exchangeMoney(ExchangeRequest exchangeMoney);

    String isDuplicateId(String username);
    String isDuplicateNickname(String nickname);

    MemberInfoUpdateResponse memberInfo();

    MemberInfoUpdateResponse memberInfoUpdate(MemberInfoUpdateRequest memberInfoUpdateRequest);



}
