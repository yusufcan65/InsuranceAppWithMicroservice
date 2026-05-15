package insurance.healthService.Exception;

public class ServiceUnavailableException extends RuntimeException{
    public ServiceUnavailableException( String message){
        super(message);
    }
}
