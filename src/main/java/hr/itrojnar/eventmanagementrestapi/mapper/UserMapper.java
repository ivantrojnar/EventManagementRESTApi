package hr.itrojnar.eventmanagementrestapi.mapper;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.entities.UserEvent;
import hr.itrojnar.eventmanagementrestapi.model.TicketDTO;
import hr.itrojnar.eventmanagementrestapi.entities.Ticket;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.model.UserEventDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDto(final User user) {
        return UserDTO.withEvents(user.getId(), user.getUsername(), user.getPassword(), user.getUserType());
    }

    public User toEntity(final UserDTO userDTO) {
        return User.builder()
                .id(userDTO.id())
                .username(userDTO.username())
                .password(userDTO.password())
                .userType(userDTO.userType())
                .build();
    }

    private List<UserEventDTO> mapUserEventsToDTOs(List<UserEvent> userEvents) {
        return userEvents.stream()
                .map(this::mapUserEventToDTO)
                .collect(Collectors.toList());
    }

    private UserEventDTO mapUserEventToDTO(UserEvent userEvent) {
        return UserEventDTO.builder()
                .id(userEvent.getId())
                .attending(userEvent.isAttending())
                .tickets(mapTicketsToDTOs(userEvent.getTickets()))
                .build();
    }

    private List<TicketDTO> mapTicketsToDTOs(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::mapTicketToDTO)
                .collect(Collectors.toList());
    }

    private TicketDTO mapTicketToDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .build();
    }

    private List<UserEvent> mapUserEventDTOsToEntities(List<UserEventDTO> userEventDTOs) {
        return userEventDTOs.stream()
                .map(this::mapUserEventDTOToEntity)
                .collect(Collectors.toList());
    }

    private UserEvent mapUserEventDTOToEntity(UserEventDTO userEventDTO) {
        return UserEvent.builder()
                .id(userEventDTO.id())
                .attending(userEventDTO.attending())
                .tickets(mapTicketDTOsToEntities(userEventDTO.tickets()))
                .build();
    }

    private List<Ticket> mapTicketDTOsToEntities(List<TicketDTO> ticketDTOs) {
        return ticketDTOs.stream()
                .map(this::mapTicketDTOToEntity)
                .collect(Collectors.toList());
    }

    private Ticket mapTicketDTOToEntity(TicketDTO ticketDTO) {
        return Ticket.builder()
                .id(ticketDTO.id())
                .price(ticketDTO.price())
                .build();
    }
}
