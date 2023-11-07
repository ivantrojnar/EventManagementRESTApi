package hr.itrojnar.eventmanagementrestapi.service.security;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.repository.UserRepository;
import hr.itrojnar.eventmanagementrestapi.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*if (!username.equals("ivan")) {
            throw new UsernameNotFoundException("User not found...");
        }*/
        List<User> users = userRepository.findAll();
        Optional<User> userOptional = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Log username and encoded password
        System.out.println("Username: " + user.getUsername());
        System.out.println("Encoded Password: " + user.getPassword());

        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        return new UserDetailsImpl(user.getUsername(), encoder.encode(user.getPassword()), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}