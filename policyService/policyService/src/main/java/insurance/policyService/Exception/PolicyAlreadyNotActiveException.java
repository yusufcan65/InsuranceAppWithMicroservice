package insurance.policyService.Exception;

public class PolicyAlreadyNotActiveException extends RuntimeException {
    public PolicyAlreadyNotActiveException(String message) {
        super(message);
    }
}
