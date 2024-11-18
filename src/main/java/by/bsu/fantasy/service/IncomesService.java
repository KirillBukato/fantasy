package by.bsu.fantasy.service;

import by.bsu.fantasy.model.PlayerIncome;
import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomesService {

    public List<PlayerIncome> getUserPlayerIncomes(User user) {
        return user.getPlayers()
                .stream()
                .flatMap(player -> player.getIncomes().stream())
                .toList();
    }

    public List<TeamIncome> getUserTeamIncomes(User user) {
        return user.getTeams()
                .stream()
                .flatMap(team -> team.getIncomes().stream())
                .toList();
    }

    public Integer getUserEstimatedIncome(User user) {
        Integer playerSum = getUserPlayerIncomes(user)
                .stream()
                .mapToInt(PlayerIncome::getValue)
                .reduce(0, Integer::sum);
        Integer teamSum = getUserTeamIncomes(user)
                .stream()
                .mapToInt(TeamIncome::getValue)
                .reduce(0, Integer::sum);
        return playerSum + teamSum;
    }
}
