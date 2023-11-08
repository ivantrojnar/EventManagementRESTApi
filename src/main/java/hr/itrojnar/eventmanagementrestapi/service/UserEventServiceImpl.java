package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.Ticket;
import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.entities.UserEvent;
import hr.itrojnar.eventmanagementrestapi.repository.TicketRepository;
import hr.itrojnar.eventmanagementrestapi.repository.UserEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEventServiceImpl implements UserEventService {

    private final UserEventRepository userEventRepository;
    private final TicketRepository ticketRepository;

    @Override
    public List<UserEvent> findByUser(User user) {
        List<UserEvent> allUserEvents = userEventRepository.findAll();

        return allUserEvents.stream()
                .filter(userEvent -> userEvent.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public List<Ticket> findByUserEvent(UserEvent userEvent) {
        List<Ticket> allTickets = ticketRepository.findAll();

        return allTickets.stream()
                .filter(ticket -> ticket.getUserEvent().equals(userEvent))
                .collect(Collectors.toList());
    }

    public List<Ticket> findAllTicketsForUser(User user) {
        List<Ticket> allTickets = ticketRepository.findAll();
        return allTickets.stream()
                .filter(ticket -> ticket.getUserEvent().getUser().equals(user))
                .collect(Collectors.toList());
    }
}
