package trafficService.trafficService.Exception;

import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<RestResponse<String>> serviceUnavailableException(ServiceUnavailableException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
