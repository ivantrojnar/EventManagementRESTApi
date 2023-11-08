package hr.itrojnar.eventmanagementrestapi.controller;

import hr.itrojnar.eventmanagementrestapi.model.AuthRequest;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/details")
    public ResponseEntity<UserDTO> getUserDetails(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        UserDTO userDTO = userService.findUserDetails(username, password);

        return ResponseEntity.ok(userDTO);
    }
}
