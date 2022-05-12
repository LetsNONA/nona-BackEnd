package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemberServiceImpl  implements  MemberService{
   public Member getRequestUser(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
       return principal.getUser();
   }

    @Override
    public int calculateAge(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int bornYear = birthday.getYear();
        return (currentYear - bornYear) + 1;
    }
}
