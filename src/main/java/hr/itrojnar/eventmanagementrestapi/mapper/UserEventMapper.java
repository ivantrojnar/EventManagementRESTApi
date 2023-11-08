package hr.itrojnar.eventmanagementrestapi.mapper;

import hr.itrojnar.eventmanagementrestapi.entities.UserEvent;
import hr.itrojnar.eventmanagementrestapi.model.TicketDTO;
import hr.itrojnar.eventmanagementrestapi.model.UserEventDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEventMapper {

    public UserEventDTO toDto(UserEvent userEvent, List<TicketDTO> tickets) {
        return UserEventDTO.builder()
                .id(userEvent.getId())
                .name(userEvent.getEvent().getName())  // Assuming Event has a 'name' property
                .address(userEvent.getEvent().getAddress())  // Assuming Event has an 'address' property
                .attending(userEvent.isAttending())
                .picture(userEvent.getEvent().getPicture())  // Assuming Event has a 'picture' property
                .maxAttendees(userEvent.getEvent().getMaxAttendees())  // Assuming Event has a 'maxAttendees' property
                .numAttendees(userEvent.getEvent().getNumAttendees())  // Assuming Event has a 'numAttendees' property
                .description(userEvent.getEvent().getDescription())  // Assuming Event has a 'description' property
                .date(userEvent.getEvent().getDate())  // Assuming Event has a 'date' property
                .tickets(tickets)
                .build();
    }
}
