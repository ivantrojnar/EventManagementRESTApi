package hr.itrojnar.eventmanagementrestapi.repository;

import hr.itrojnar.eventmanagementrestapi.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findRefreshTokenByToken(String token);
}
