package by.bsu.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.repository.PickRepository;
import org.springframework.stereotype.Service;

import by.bsu.fantasy.exceptions.LoginFailedException;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PickService pickService;

    public User createUser(String login, String passw, String role) {
        User newRecord = new User();
        newRecord.setUsername(login);
        newRecord.setPassword(passw);
        newRecord.setRole(role);
        newRecord.setPicks(new ArrayList<>());
        User user = userRepository.save(newRecord);
        pickService.createNewPick(user);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new LoginFailedException());
    }

    public List<User> getUsers() {
        return userRepository
                .findAll();
    }
}
