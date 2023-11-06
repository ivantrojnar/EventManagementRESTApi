package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    void save(UserDTO newUser);
}
