package letsnona.nonabackend.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> customMemberException(CustomException e) {
        return CustomExceptionResponse.toResponseEntity(e.getCustomErrorCode());
    }


}
