package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.UserNotFoundException;
import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.model.TeamIncome;
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
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<PlayerIncome> getUserPlayerIncomes(Long id) {
        return getUserById(id)
                .getPlayers()
                .stream()
                .flatMap(player -> player.getIncomes().stream())
                .toList();
    }

    public List<TeamIncome> getUserTeamIncomes(Long id) {
        return getUserById(id)
                .getTeams()
                .stream()
                .flatMap(team -> team.getIncomes().stream())
                .toList();
    }

    public Integer getUserEstimatedIncome(Long id) {
        Integer playerSum = getUserPlayerIncomes(id)
                .stream()
                .mapToInt(PlayerIncome::getValue)
                .reduce(0, Integer::sum);
        Integer teamSum = getUserTeamIncomes(id)
                .stream()
                .mapToInt(TeamIncome::getValue)
                .reduce(0, Integer::sum);
        return playerSum + teamSum;
    }
}
