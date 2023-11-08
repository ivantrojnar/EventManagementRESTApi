package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();
    Optional<User> findByUsernameAndPassword(String username, String password);
    void save(UserDTO newUser);

    UserDTO findUserDetails(String username, String password);
}
