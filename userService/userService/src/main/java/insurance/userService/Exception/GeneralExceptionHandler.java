package insurance.userService.Exception;

import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestResponse<String>> userNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<RestResponse<Object>> serviceUnavailableException(ServiceUnavailableException exception){
        RestResponse<Object> response = RestResponse.error(null);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
