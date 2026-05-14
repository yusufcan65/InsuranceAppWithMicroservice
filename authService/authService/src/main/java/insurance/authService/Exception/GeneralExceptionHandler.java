package insurance.authService.Exception;

import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<RestResponse<String>> usernameNotFoundException(UsernameNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<RestResponse<String>> tokenGenerationException(TokenGenerationException  exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
