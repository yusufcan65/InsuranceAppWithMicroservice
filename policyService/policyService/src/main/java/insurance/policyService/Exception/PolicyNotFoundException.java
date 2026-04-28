package insurance.policyService.Exception;

public class PolicyNotFoundException extends RuntimeException{
    public PolicyNotFoundException(String message){
        super(message);
    }
}
