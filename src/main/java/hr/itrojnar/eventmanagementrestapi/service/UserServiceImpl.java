package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.mapper.UserMapper;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        List<User> users = userRepository.findAll();

        Optional<User> user = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

        return user;
    }

    @Override
    public void save(UserDTO newUser) {
        final User userToSave = userMapper.toEntity(newUser);
        userRepository.save(userToSave);
    }
}
