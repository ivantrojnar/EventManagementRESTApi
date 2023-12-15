package hr.itrojnar.eventmanagementrestapi;

import hr.itrojnar.eventmanagementrestapi.repository.UserRepository;
import hr.itrojnar.eventmanagementrestapi.service.security.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;

@TestConfiguration
@Import(UserDetailsServiceAutoConfiguration.class)
public class SecurityTestConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    // Add other security-related beans if needed
}