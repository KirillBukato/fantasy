package by.bsu.fantasy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.model.AuthRequest;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody AuthRequest authRequest) {
        return authService.registerUser(authRequest.getLogin(), authRequest.getPassword(), "basic_user");
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody AuthRequest authRequest) {
        return authService.loginUser(authRequest.getLogin(), authRequest.getPassword());
    }
}
