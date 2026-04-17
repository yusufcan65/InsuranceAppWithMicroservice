package insurance.userService.Service;


import java.util.List;

public interface JwtService {


    public String extractUsername(String token);

    public String extractRole(String token);


    public boolean isTokenValid(String token);


}