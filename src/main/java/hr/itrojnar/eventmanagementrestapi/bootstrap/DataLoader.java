package hr.itrojnar.eventmanagementrestapi.bootstrap;

import hr.itrojnar.eventmanagementrestapi.entities.UserType;
import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.service.EventService;
import hr.itrojnar.eventmanagementrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final EventService eventService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Users about to load...");

        final UserDTO admin = UserDTO.builder().username("admin").password("admin123").userType(UserType.ADMIN).build();
        final UserDTO user1 = UserDTO.builder().username("user1").password("pass1").userType(UserType.USER).build();
        final UserDTO user2 = UserDTO.builder().username("user2").password("pass2").userType(UserType.USER).build();

        userService.save(admin);
        userService.save(user1);
        userService.save(user2);

        System.out.println("All users successfully loaded!");

        System.out.println("Events about to load...");

        final EventDTO event1 = EventDTO.builder()
                .name("Event 1")
                .maxAttendees(50)
                .numAttendees(0)
                .address("Event Address 1")
                .description("Event Description 1")
                .date(LocalDate.now().plusDays(7))
                .price(BigDecimal.valueOf(20))
                .build();

        final EventDTO event2 = EventDTO.builder()
                .name("Event 2")
                .maxAttendees(30)
                .numAttendees(0)
                .address("Event Address 2")
                .description("Event Description 2")
                .date(LocalDate.now().plusDays(14))
                .price(BigDecimal.valueOf(25))
                .build();

        eventService.save(event1);
        eventService.save(event2);

        System.out.println("All events successfully loaded!");
    }
}
