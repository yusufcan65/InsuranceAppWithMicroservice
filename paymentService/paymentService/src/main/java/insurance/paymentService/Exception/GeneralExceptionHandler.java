package insurance.paymentService.Exception;

import insurance.insuranceCommon.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PolicyAlreadyPaidException.class)
    public ResponseEntity<RestResponse<String>> policyAlreadyPaidException(PolicyAlreadyPaidException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestResponse<String>> resourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}