package by.bsu.fantasy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/all")
    private List<User> getAll() {
        return userService.getUSers();
    }
}
