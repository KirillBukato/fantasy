package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.UserNotFoundException;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository
                .findAll();
    }

    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        return userRepository
                .save(user);
    }

    public User updateUser(Long id, User user) {
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

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
