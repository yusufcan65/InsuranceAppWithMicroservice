package insurance.authService.Service.Impl;

import insurance.authService.Entity.AuthUser;
import insurance.authService.Service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String SECRET;


    private final ConcurrentHashMap<String, Date> invalidatedTokens = new ConcurrentHashMap<>();


  /*  public String generateToken(AuthUser user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }*/
    @Override
    public String generateToken(UserDetails userDetails) {


        Map<String, Object> claims = new HashMap<>(2);

        String role = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        claims.put("role", role);

        return createToken(claims, userDetails.getUsername());
    }
    private String createToken(Map<String, Object> claims, String userName){
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userName)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 20))  // 2 dakikalık geçerlilik süresi
                    .signWith(getSignKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
        catch (Exception e){
            throw new RuntimeException("TokenErrorMessage.TOKEN_GENERATE_ERROR");
        }

    }

   /* @Override
    public String generateToken(AuthUser user) {
        return null;
    }*/

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());

    }

    @Override
    public String extractUser(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    @Override
    public void invalidateToken(String token) {
        Date expirationDate = extractExpiration(token);
        invalidatedTokens.put(token, expirationDate);
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Date extractExpiration(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }



    public boolean isTokenValid(String token, UserDetails userDetails) {
        // Token'ın blacklist'te olup olmadığını kontrol et
        if (invalidatedTokens.containsKey(token)) {
            Date invalidationTime = invalidatedTokens.get(token);
            // Eğer token'ın expiration zamanı geçmişse, blacklist'ten kaldır
            if (invalidationTime.before(new Date())) {
                invalidatedTokens.remove(token);
            } else {
                return false; // Geçersiz token
            }
        }

        // Standart token doğrulama
        return validateToken(token, userDetails);
    }



}
