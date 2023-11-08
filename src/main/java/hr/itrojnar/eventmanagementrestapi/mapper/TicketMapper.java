package hr.itrojnar.eventmanagementrestapi.mapper;

import hr.itrojnar.eventmanagementrestapi.entities.Ticket;
import hr.itrojnar.eventmanagementrestapi.model.TicketDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDTO toDto(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .build();
    }
}