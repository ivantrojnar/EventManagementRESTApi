package hr.itrojnar.eventmanagementrestapi.schedulers;

import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.service.EventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventScheduler {

    private final EventService eventService;

    public EventScheduler(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(fixedRate = 30000)
    public void printAllEvents() {
        List<EventDTO> allEvents = eventService.findAll();

        System.out.println("============================================");
        System.out.println("         All Events in the Database              ");
        System.out.println("============================================");

        for (EventDTO event : allEvents) {
            System.out.println("Event ID: " + event.id() +
                    ", Name: " + event.name() +
                    ", Max Attendees: " + event.maxAttendees() +
                    ", Number of Attendees: " + event.numAttendees() +
                    ", Address: " + event.address() +
                    ", Description: " + event.description() +
                    ", Date: " + event.date() +
                    ", Price: " + event.price());
        }

        System.out.println("============================================");
    }
}
