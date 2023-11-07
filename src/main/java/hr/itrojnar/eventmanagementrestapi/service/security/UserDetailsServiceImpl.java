package hr.itrojnar.eventmanagementrestapi.service.security;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.repository.UserRepository;
import hr.itrojnar.eventmanagementrestapi.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findAll();
        Optional<User> userOptional = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        return new UserDetailsImpl(user.getUsername(), encoder.encode(user.getPassword()), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}