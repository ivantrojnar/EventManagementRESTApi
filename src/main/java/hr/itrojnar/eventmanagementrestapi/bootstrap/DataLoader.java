package hr.itrojnar.eventmanagementrestapi.bootstrap;

import hr.itrojnar.eventmanagementrestapi.entities.UserType;
import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.service.EventService;
import hr.itrojnar.eventmanagementrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final EventService eventService;
    private final ResourceLoader resourceLoader;

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

        Resource resource1 = resourceLoader.getResource("classpath:base64image.txt");
        String base64Image1 = new String(resource1.getInputStream().readAllBytes());
        Resource resource2 = resourceLoader.getResource("classpath:base64image2.txt");
        String base64Image2 = new String(resource2.getInputStream().readAllBytes());

        final EventDTO event1 = EventDTO.builder()
                .name("Event 1")
                .picture(base64Image1)
                .maxAttendees(50)
                .numAttendees(0)
                .address("Event Address 1")
                .description("Event Description 1")
                .date(LocalDateTime.now().plusDays(7))
                .price(BigDecimal.valueOf(20))
                .build();

        final EventDTO event2 = EventDTO.builder()
                .name("Event 2")
                .picture(base64Image2)
                .maxAttendees(30)
                .numAttendees(0)
                .address("Event Address 2")
                .description("Event Description 2")
                .date(LocalDateTime.now().plusDays(14))
                .price(BigDecimal.valueOf(25))
                .build();

        eventService.save(event1);
        eventService.save(event2);

        System.out.println("All events successfully loaded!");
    }
}
