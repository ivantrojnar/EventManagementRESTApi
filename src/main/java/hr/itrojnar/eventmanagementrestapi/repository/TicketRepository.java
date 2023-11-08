package hr.itrojnar.eventmanagementrestapi.repository;

import hr.itrojnar.eventmanagementrestapi.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}