package school.model;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JWTProvider {
    private final SecretKey JWT_ACCESS_SECRET;
    private final SecretKey JWT_REFRESH_SECRET;

    public JWTProvider(@Value("${jwt.secret.access}") String jwtAccessSecret, @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
        this.JWT_ACCESS_SECRET = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.JWT_REFRESH_SECRET = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessToken(@NonNull Student student) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(student.getLogin())
                .setExpiration(accessExpiration)
                .signWith(JWT_ACCESS_SECRET)
                .claim("roles", student.getRole())
                .claim("firstName", student.getSurname())
                .compact();
    }

    public String generateRefreshToken(@NonNull Student student) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(student.getLogin())
                .setExpiration(refreshExpiration)
                .signWith(JWT_REFRESH_SECRET)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, JWT_ACCESS_SECRET);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, JWT_REFRESH_SECRET);
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, JWT_ACCESS_SECRET);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, JWT_REFRESH_SECRET);
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
