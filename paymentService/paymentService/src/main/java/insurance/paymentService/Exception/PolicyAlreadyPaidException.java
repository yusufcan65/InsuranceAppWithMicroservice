package insurance.paymentService.Exception;

public class PolicyAlreadyPaidException extends RuntimeException{
    public PolicyAlreadyPaidException(String message){
        super(message);
    }
}
