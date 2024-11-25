package by.bsu.fantasy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.config.SecurityConfig;
import by.bsu.fantasy.model.AuthRequest;
import by.bsu.fantasy.model.AuthResponse;
import by.bsu.fantasy.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse registerUser(@RequestBody AuthRequest authRequest) {
        return authService.registerUser(authRequest.getLogin(), SecurityConfig.passwordEncoder().encode(authRequest.getPassword()), "basic_user");
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody AuthRequest authRequest) {
        return authService.loginUser(authRequest.getLogin(), authRequest.getPassword());
    }
}
