package hr.itrojnar.eventmanagementrestapi.repository;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
