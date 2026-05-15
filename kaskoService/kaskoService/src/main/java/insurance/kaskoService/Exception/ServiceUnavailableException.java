package insurance.kaskoService.Exception;

public class ServiceUnavailableException extends RuntimeException{

    public ServiceUnavailableException(String message){
        super(message);
    }
}
