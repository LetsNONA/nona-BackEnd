package letsnona.nonabackend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    //member
    NOT_CHANGE_MONEY(HttpStatus.BAD_REQUEST, "환전할 포인트가 부족합니다."),
    DUPLICATE_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    DUPLICATE_ID_OR_NICKNAME(HttpStatus.CONFLICT,"아이디 또는 닉네임이 이미 존재합니다."),

    //category
    NOT_EXISTS_CATEGORY(HttpStatus.BAD_REQUEST,"카테고리가 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;

}
