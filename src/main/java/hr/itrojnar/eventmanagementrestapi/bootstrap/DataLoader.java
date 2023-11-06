package hr.itrojnar.eventmanagementrestapi.bootstrap;

import hr.itrojnar.eventmanagementrestapi.entities.UserType;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserService userService;

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
    }
}
