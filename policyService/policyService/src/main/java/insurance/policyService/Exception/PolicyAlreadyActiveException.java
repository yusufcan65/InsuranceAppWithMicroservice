package insurance.policyService.Exception;

public class PolicyAlreadyActiveException extends RuntimeException {
    public PolicyAlreadyActiveException(String message) {
        super(message);
    }
}
