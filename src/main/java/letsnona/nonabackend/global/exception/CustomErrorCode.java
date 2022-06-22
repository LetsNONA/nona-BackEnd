package letsnona.nonabackend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    NOT_CHANGE_MONEY(HttpStatus.BAD_REQUEST, "환전할 포인트가 부족합니다.");



    private final HttpStatus httpStatus;
    private final String message;

}
