package hr.itrojnar.eventmanagementrestapi.repository;

import hr.itrojnar.eventmanagementrestapi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
