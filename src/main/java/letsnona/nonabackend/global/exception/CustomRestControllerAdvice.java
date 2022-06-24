package letsnona.nonabackend.global.exception;

import letsnona.nonabackend.global.exception.member.CustomMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {


    @ExceptionHandler(CustomMemberException.class)
    public ResponseEntity<CustomExceptionResponse> customException(CustomMemberException e) {
        return CustomExceptionResponse.toResponseEntity(e.getCustomMemberErrorCode());
    }
}
