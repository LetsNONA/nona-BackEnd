package letsnona.nonabackend.global.exception;

import letsnona.nonabackend.global.exception.member.CustomMemberErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class CustomExceptionResponse {
    private final int status;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<CustomExceptionResponse> toResponseEntity(CustomMemberErrorCode customMemberErrorCode) {
        return ResponseEntity
                .status(customMemberErrorCode.getHttpStatus())
                .body(CustomExceptionResponse.builder()
                        .status(customMemberErrorCode.getHttpStatus().value())
                        .error(customMemberErrorCode.getHttpStatus().name())
                        .code(customMemberErrorCode.name())
                        .message(customMemberErrorCode.getMessage())
                        .build());
    }
}
