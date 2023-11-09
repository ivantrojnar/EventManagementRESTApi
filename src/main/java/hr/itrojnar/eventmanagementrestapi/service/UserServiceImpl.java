package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.Ticket;
import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.entities.UserEvent;
import hr.itrojnar.eventmanagementrestapi.entities.UserType;
import hr.itrojnar.eventmanagementrestapi.mapper.TicketMapper;
import hr.itrojnar.eventmanagementrestapi.mapper.UserEventMapper;
import hr.itrojnar.eventmanagementrestapi.mapper.UserMapper;
import hr.itrojnar.eventmanagementrestapi.model.TicketDTO;
import hr.itrojnar.eventmanagementrestapi.model.UserDTO;
import hr.itrojnar.eventmanagementrestapi.model.UserEventDTO;
import hr.itrojnar.eventmanagementrestapi.repository.TicketRepository;
import hr.itrojnar.eventmanagementrestapi.repository.UserEventRepository;
import hr.itrojnar.eventmanagementrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEventRepository userEventRepository;
    private final TicketRepository ticketRepository;
    private final UserMapper userMapper;
    private final UserEventMapper userEventMapper;
    private final TicketMapper ticketMapper;
    private final UserEventServiceImpl userEventServiceImpl;

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

    @Override
    public UserDTO findUserDetails(String username, String password) {
        Optional<User> userOptional = findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            List<UserEvent> userEvents = userEventServiceImpl.findByUser(user);

            List<UserEventDTO> userEventDTOs = userEvents.stream()
                    .map(userEvent -> {
                        List<Ticket> tickets = userEventServiceImpl.findByUserEvent(userEvent);
                        List<TicketDTO> ticketDTOs = tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());

                        return userEventMapper.toDto(userEvent, ticketDTOs);
                    })
                    .collect(Collectors.toList());

            return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getUserType(), userEventDTOs);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public void registerUser(String username, String password) {
        UserDTO newUser = UserDTO.builder().username(username).password(password).userType(UserType.USER).build();
        save(newUser);
    }
}
