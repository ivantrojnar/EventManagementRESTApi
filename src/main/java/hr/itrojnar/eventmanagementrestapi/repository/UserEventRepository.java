package hr.itrojnar.eventmanagementrestapi.repository;

import hr.itrojnar.eventmanagementrestapi.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
