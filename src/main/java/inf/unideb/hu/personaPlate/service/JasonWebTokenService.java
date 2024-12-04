package inf.unideb.hu.personaPlate.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JasonWebTokenService {
    String extractUsernameFromToken(String token);

    String generateToken(UserDetails userDetails);

    boolean validateToken(String token, UserDetails userDetails);
}
