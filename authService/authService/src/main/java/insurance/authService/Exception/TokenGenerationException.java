package insurance.authService.Exception;

public class TokenGenerationException extends RuntimeException{
    public TokenGenerationException(String message){
        super(message);
    }
}
