package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.*;
import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.Team;
import org.springframework.stereotype.Service;

@Service
public class PickTeamService {

    private final PickService pickService;
    private final TeamService teamService;

    public PickTeamService(PickService pickService, TeamService teamService) {
        this.pickService = pickService;
        this.teamService = teamService;
    }

    public Pick linkTeamToPick(Long pickId, Long teamId) {
        Pick pick = pickService.getPickById(pickId);
        Team team = teamService.getTeamById(teamId);

        if (pick.getTeams().contains(team)) {
            throw new PickAlreadyHasTeamException(pick, team);
        }
        if (pick.getTeams().size() >= 2) {
            throw new PickTeamLimitException(pick, 2);
        }
        if (team.getPrice() > pick.getBalance()) {
            throw new PickNotEnoughMoneyException(pick, team.getPrice());
        }
        pick.setBalance(pick.getBalance() - team.getPrice());
        pick.getTeams().add(team);
        return pickService.updatePick(pickId, pick);
    }

}
