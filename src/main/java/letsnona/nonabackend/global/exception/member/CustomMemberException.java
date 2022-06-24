package letsnona.nonabackend.global.exception.member;

import letsnona.nonabackend.global.exception.member.CustomMemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomMemberException extends RuntimeException {
    private  final CustomMemberErrorCode customMemberErrorCode;
}
