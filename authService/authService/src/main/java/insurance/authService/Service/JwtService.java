package insurance.authService.Service;

import insurance.authService.Entity.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
    String extractUser(String token);
    void invalidateToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
