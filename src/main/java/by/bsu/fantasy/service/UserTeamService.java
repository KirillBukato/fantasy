package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.*;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserTeamService {

    private final UserService userService;
    private final TeamService teamService;

    public UserTeamService(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    public User linkTeamToUser(Long userId, Long teamId) {
        User user = userService.getUserById(userId);
        Team team = teamService.getTeamById(teamId);

        if (user.getTeams().contains(team)) {
            throw new UserAlreadyPickedTeamException(user, team);
        }
        if (user.getTeams().size() >= 2) {
            throw new UserTeamPickLimitException(user, 2);
        }
        if (team.getPrice() > user.getBalance()) {
            throw new UserNotEnoughMoneyException(user, team.getPrice());
        }
        user.setBalance(user.getBalance() - team.getPrice());
        user.getTeams().add(team);
        return userService.updateUser(userId, user);
    }

}
