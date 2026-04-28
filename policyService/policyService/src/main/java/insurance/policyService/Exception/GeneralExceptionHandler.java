package insurance.policyService.Exception;

import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<RestResponse<String>> serviceUnavailableException(PolicyNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(PolicyAlreadyPaidException.class)
    public ResponseEntity<RestResponse<String>>  policyAlreadyPaidException(PolicyAlreadyPaidException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.CONFLICT);
    }
}
