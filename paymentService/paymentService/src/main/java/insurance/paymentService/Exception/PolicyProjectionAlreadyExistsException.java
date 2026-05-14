package insurance.paymentService.Exception;

public class PolicyProjectionAlreadyExistsException extends RuntimeException {
    public PolicyProjectionAlreadyExistsException(String message) {
        super(message);
    }
}
