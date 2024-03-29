package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.domain.file.dto.MemberImgRequestDTO;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.product.dto.CountOfProductSellListAndPriceDTO;
import letsnona.nonabackend.domain.product.dto.CreateDateProductCountDTO;
import letsnona.nonabackend.domain.product.dto.ProductStateCountDTO;
import letsnona.nonabackend.domain.product.dto.SellProductRatioDTO;
import letsnona.nonabackend.domain.product.repository.CustomProductRepositoryImpl;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.global.exception.CustomErrorCode;
import letsnona.nonabackend.global.exception.CustomException;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.dto.*;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.entity.enums.MemberState;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    public final FileService fileService;
    public final CustomProductRepositoryImpl customProductRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public Member getRequestUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        return principal.getUser();
    }

    @Override
    public int getPoint() {
        return getRequestUser().getPoint();
    }

    @Override
    public int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int bornYear = birthday.getYear();
        return (currentYear - bornYear) + 1;
    }

    @Override
    public TotalNonaDataDTO getCountMemberAndTotalProductAndTodayProduct() {
        int total_member = memberRepository.countAllBy();
        CreateDateProductCountDTO createDateProductCount = customProductRepository.getCreateDateProductCount();
        int total_product = productRepository.countAllBy();

        TotalNonaDataDTO totalNonaDataDTO = new TotalNonaDataDTO();
        totalNonaDataDTO.setTotalMemberCount(total_member);
        totalNonaDataDTO.setTotalProductCount(total_product);
        totalNonaDataDTO.setTotalTodayProductCount(createDateProductCount.getCnt().intValue());

        return totalNonaDataDTO;
    }

    @Override
    public SellProductRatioDTO getSellProductRatio() {
        Member requestUser = getRequestUser();
        ProductStateCountDTO trading = customProductRepository.getCountProductStateOfProduct("TRADING", requestUser.getUsername());
        ProductStateCountDTO completed = customProductRepository.getCountProductStateOfProduct("COMPLETED", requestUser.getUsername());
        List<CountOfProductSellListAndPriceDTO> countOfProductSellList = customProductRepository.getCountOfProductSellList(requestUser.getUsername());

        int totalPrice = 0;
        SellProductRatioDTO sellProductRatioDTO = new SellProductRatioDTO();

        for (CountOfProductSellListAndPriceDTO list : countOfProductSellList
        ) {
            totalPrice += (list.getPrice() * list.getCnt().intValue());
        }

        sellProductRatioDTO.setTrading(trading);
        sellProductRatioDTO.setCompleted(completed);
        sellProductRatioDTO.setTotalPrice(totalPrice);
        sellProductRatioDTO.setSellList(countOfProductSellList);

        return sellProductRatioDTO;
    }

    @Override
    public Member JoinMember(Member member, List<MultipartFile> file) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");
        member.setMemberState(MemberState.LOCKED); /*TODO - 상태값 시연전에 꼭바꿔라*/
        member.updateAge(calculateAge(member.getBirthday()));
        //  memberRepository.save(member);
        MemberImgRequestDTO memberImgRequestDTO = fileService.saveMemberImg(file);

        member.setOriginalName(memberImgRequestDTO.getOriginalName());
        member.setOriginalImgSrc(memberImgRequestDTO.getOriginalImgSrc());
        member.setThumbImgSrc(memberImgRequestDTO.getThumbImgSrc());
        if (memberRepository.existsByUsernameOrNickName(member.getUsername(), member.getNickName())) {
            throw new CustomException(CustomErrorCode.DUPLICATE_ID_OR_NICKNAME);
        }

        return memberRepository.save(member);
    }

    @Override
    public ExchangeResponse exchangeMoney(ExchangeRequest exchangeMoney) {
        Member member = memberRepository.findByUsername(getRequestUser().getUsername());
        if (member.getPoint() < exchangeMoney.getExchangeMoney()) {
            throw new CustomException(CustomErrorCode.NOT_CHANGE_MONEY);
        }
        member.decreasePoint(exchangeMoney.getExchangeMoney());
        String response = "남아있는 포인트 : " + member.getPoint();
        return new ExchangeResponse(response);
    }

    @Override
    public String isDuplicateId(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new CustomException(CustomErrorCode.DUPLICATE_ID);
        }
        return "사용가능한 아이디입니다.";
    }

    @Override
    public String isDuplicateNickname(String nickname) {
        if (memberRepository.existsByNickName(nickname)) {
            throw new CustomException(CustomErrorCode.DUPLICATE_NICKNAME);
        }
        return "사용가능한 닉네임입니다.";
    }

    @Override
    public MemberInfoUpdateResponse memberInfo() {
        Member member = getRequestUser();
        System.out.println(member);
        return new MemberInfoUpdateResponse(member);
    }

    @Override
    public MemberInfoUpdateResponse memberInfoUpdate(MemberInfoUpdateRequest memberIUR) {
        Member member = memberRepository.findByUsername(getRequestUser().getUsername());
        if (!memberIUR.getPassword().equals(memberIUR.getPasswordConfirm())) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_PASSWORD);
        }
        member.memberInfoUpdate(memberIUR.getUsername(), memberIUR.getNickname(),
                bCryptPasswordEncoder.encode(memberIUR.getPassword()), memberIUR.getEmail(),
                memberIUR.getBirthday(), memberIUR.getPhoneNumber(), memberIUR.getZipCode());
        return new MemberInfoUpdateResponse(member);
    }
}
