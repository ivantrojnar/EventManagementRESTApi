package hr.itrojnar.eventmanagementrestapi.service.security;


import hr.itrojnar.eventmanagementrestapi.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);
}
