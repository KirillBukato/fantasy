package by.bsu.fantasy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.bsu.fantasy.exceptions.LoginFailedException;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(String login, String passw, String role, String token) {
        User newRecord = new User();
        newRecord.setUsername(login);
        newRecord.setPassword(passw);
        newRecord.setRole(role);
        newRecord.setToken(token);
        userRepository.save(newRecord);
    }

    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new LoginFailedException());
    }

    public List<User> getUSers() {
        return userRepository
                .findAll();
    }
}
