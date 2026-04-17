package insurance.userService.Service.Impl;

import insurance.userService.Service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        return extractUsername(token) != null;
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}