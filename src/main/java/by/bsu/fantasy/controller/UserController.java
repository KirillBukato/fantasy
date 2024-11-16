package by.bsu.fantasy.controller;

import by.bsu.fantasy.exceptions.UserNotFoundException;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository
                .findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository
                .save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userRepository
                .findById(id)
                .map(u -> {
                    u.setName(user.getName());
                    u.setBalance(user.getBalance());
                    u.setPoints(user.getPoints());
                    return userRepository.save(u);
                })
                .orElseGet(
                        () -> userRepository.save(user)
                );
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
