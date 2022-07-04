package letsnona.nonabackend.global.security.auth;

import letsnona.nonabackend.global.exception.CustomErrorCode;
import letsnona.nonabackend.global.exception.CustomException;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if(member == null)
            throw new CustomException(CustomErrorCode.NOT_EXISTS_ID);
        // session.setAttribute("loginUser", user);
        return new PrincipalDetails(member);
    }
}
