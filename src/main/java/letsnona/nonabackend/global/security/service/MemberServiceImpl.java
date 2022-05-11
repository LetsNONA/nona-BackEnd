package letsnona.nonabackend.global.security.service;

import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl  implements  MemberService{
   public Member getRequestUser(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
       return principal.getUser();
   }
}
