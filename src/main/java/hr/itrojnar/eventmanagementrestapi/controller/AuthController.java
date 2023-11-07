package hr.itrojnar.eventmanagementrestapi.controller;

import hr.itrojnar.eventmanagementrestapi.entities.RefreshToken;
import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.model.AuthRequest;
import hr.itrojnar.eventmanagementrestapi.model.AuthResponse;
import hr.itrojnar.eventmanagementrestapi.model.RefreshTokenRequest;
import hr.itrojnar.eventmanagementrestapi.repository.UserRepository;
import hr.itrojnar.eventmanagementrestapi.service.security.JwtGeneratorService;
import hr.itrojnar.eventmanagementrestapi.service.security.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private JwtGeneratorService jwtGeneratorService;

    private RefreshTokenService refreshTokenService;

    private UserRepository userRepository;

    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody final AuthRequest request) {
        /*if (!Objects.equals(request, new AuthRequest("ivan", "password"))) {
            throw new UsernameNotFoundException("User not found...");
        }*/

        System.out.println(request.getUsername());
        System.out.println(request.getPassword());


        List<User> users = userRepository.findAll();

        for (User user : users) {
            System.out.println("Password: " + user.getPassword());
            System.out.println("Username: " + user.getUsername());
            // Add more fields as needed

            System.out.println();  // Adding a newline for better readability between users
        }

        Optional<User> userOptional = users.stream()
                .filter(u -> u.getUsername().equals(request.getUsername()) && u.getPassword().equals(request.getPassword()))
                .findFirst();
        System.out.println(userOptional.isPresent());

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println("test3");

        System.out.println("Username: " + user.getUsername());
        System.out.println("Encoded Password: " + user.getPassword());

        final RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());
        return AuthResponse.builder()
                .accessToken(jwtGeneratorService.generateToken(request.getUsername()))
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @PostMapping("/refreshToken")
    public AuthResponse refreshToken(@RequestBody final RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(username -> {
                    String accessToken = jwtGeneratorService.generateToken(username);
                    return AuthResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(request.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh token doesn't exist."));

    }
}