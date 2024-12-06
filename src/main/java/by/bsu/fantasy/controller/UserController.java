package by.bsu.fantasy.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.UserService;
import by.bsu.fantasy.util.JwtTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;

    @GetMapping("/users/all")
    private List<User> getAll() {
        return userService.getUSers();
    }

    @SuppressWarnings("null")
    @GetMapping("/users/fromtoken")
    private ResponseEntity<User> getUserFromToken(HttpServletRequest request) {
        HttpStatus flag = null;
        try {
            User user = jwtTokenRepository.getUserFromRequest(request, flag);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, null, flag);
        }
    }
}
