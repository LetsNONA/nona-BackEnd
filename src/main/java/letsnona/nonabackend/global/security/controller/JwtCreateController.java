package letsnona.nonabackend.global.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.jwt.JwtProperties;
import letsnona.nonabackend.global.security.provider.GoogleUser;
import letsnona.nonabackend.global.security.provider.OAuthUserInfo;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/oauth/jwt/google")
    public String jwtCreate(@RequestBody Map<String, Object> data) {
        System.out.println("jwtCreate 실행됨");
        System.out.println(data.get("profileObj"));
        OAuthUserInfo googleUser =
                new GoogleUser((Map<String, Object>) data.get("profileObj"));

        Member memberEntity =
                memberRepository.findByUsername(googleUser.getProvider() + "_" + googleUser.getProviderId());

        if (memberEntity == null) {
            Member memberRequest = Member.builder()
                    .username(googleUser.getProvider() + "_" + googleUser.getProviderId())
                    .password(bCryptPasswordEncoder.encode("겟인데어"))
                    .email(googleUser.getEmail())
                    .provider(googleUser.getProvider())
                    .providerId(googleUser.getProviderId())
                    .roles("ROLE_USER")
                    .build();

            memberEntity = memberRepository.save(memberRequest);
        }

        String jwtToken = JWT.create()
                .withSubject(memberEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", memberEntity.getId())
                .withClaim("username", memberEntity.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }

}
