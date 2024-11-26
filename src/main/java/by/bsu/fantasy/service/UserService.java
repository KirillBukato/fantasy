package by.bsu.fantasy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createRecord(String login, String passw, String role) {
        User newRecord = new User();
        newRecord.setUsername(login);
        newRecord.setPassword(passw);
        newRecord.setRole(role);
        userRepository.save(newRecord);
    }

    public User getRecordByUsername(String id) {
        return userRepository
                .findByUsername(id)
                .orElseThrow(() -> new RuntimeException(id));
    }

    public List<User> getRecords() {
        return userRepository
                .findAll();
    }
}
