package hr.itrojnar.eventmanagementrestapi.schedulers;

import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    private final UserService userService;

    public UserScheduler(UserService userService) {
        this.userService = userService;
    }
    @Scheduled(fixedRate = 30000)
    public void printAllUsers() {
        List<UserDTO> allUsers = userService.findAll();

        System.out.println("===========================================");
        System.out.println("         All Users in the Database              ");
        System.out.println("===========================================");

        for (UserDTO user : allUsers) {
            System.out.println("Username: " + user.username() + ", Password: " + user.password());
        }

        System.out.println("===========================================");
    }
}
