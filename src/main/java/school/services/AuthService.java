package school.services;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import school.model.*;
import school.model.interfaces.StudentRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JWTProvider jwtProvider;

    public JWTResponse login(@NonNull JWTRequest authRequest) throws AuthException {
        final Student student = studentRepository.findByLogin(authRequest.getLogin());
        if (student.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(student);
            final String refreshToken = jwtProvider.generateRefreshToken(student);
            refreshStorage.put(student.getLogin(), refreshToken);
            return new JWTResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JWTResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Student student = studentRepository.findByLogin(login);
                final String accessToken = jwtProvider.generateAccessToken(student);
                return new JWTResponse(accessToken, null);
            }
        }
        return new JWTResponse(null, null);
    }

    public JWTResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Student student = studentRepository.findByLogin(login);
                final String accessToken = jwtProvider.generateAccessToken(student);
                final String newRefreshToken = jwtProvider.generateRefreshToken(student);
                refreshStorage.put(student.getLogin(), newRefreshToken);
                return new JWTResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JWTAuthentication getAuthInfo() {
        return (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
