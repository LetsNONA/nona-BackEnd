package letsnona.nonabackend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
public class CustomExceptionResponse {
    private final int status;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<CustomExceptionResponse> toResponseEntity(CustomErrorCode customErrorCode) {
        return ResponseEntity
                .status(customErrorCode.getHttpStatus())
                .body(CustomExceptionResponse.builder()
                        .status(customErrorCode.getHttpStatus().value())
                        .error(customErrorCode.getHttpStatus().name())
                        .code(customErrorCode.name())
                        .message(customErrorCode.getMessage())
                        .build());
    }
}
