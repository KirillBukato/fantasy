package by.bsu.fantasy.service;

import java.util.ArrayList;
import java.util.HashSet;
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
    private final PickService pickService;

    public User createUser(String login, String passw, String name, String role, String token) {
        if (getUsers().stream().anyMatch(el -> el.getUsername().equals(login)))
        {
            return null;
        }

        User newRecord = new User();
        newRecord.setUsername(login);
        newRecord.setPassword(passw);
        newRecord.setRole(role);
        newRecord.setBlockedTokens(new HashSet<>());
        newRecord.setName(name);

        newRecord.setPicks(new ArrayList<>());

        User user = userRepository.save(newRecord);
        pickService.createNewPick(user);
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(LoginFailedException::new);
    }

    public List<User> getUsers() {
        return userRepository
                .findAll();
    }
}
