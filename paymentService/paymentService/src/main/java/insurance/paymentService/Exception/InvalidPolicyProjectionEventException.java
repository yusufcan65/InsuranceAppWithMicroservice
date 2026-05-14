package insurance.paymentService.Exception;

public class InvalidPolicyProjectionEventException extends RuntimeException {
    public InvalidPolicyProjectionEventException(String message) {
        super(message);
    }
}
