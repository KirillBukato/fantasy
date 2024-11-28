package by.bsu.fantasy.service;

import by.bsu.fantasy.dto.*;
import by.bsu.fantasy.model.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//TODO заиспользовать во всех контроллерах
@Service
public class DTOService {
    public UserDTO getUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getPicks()
                        .stream()
                        .map(Pick::getId)
                        .collect(Collectors.toList())
        );
    }

    public PickDTO getPickDTO(Pick pick) {
        return new PickDTO(
                pick.getId(),
                pick.getBalance(),
                pick.getPoints(),
                pick.getPlayers(),
                pick.getTeams(),
                pick.getUser().getId()
        );
    }

    public PlayerDTO getPlayerDTO(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getPrice(),
                player.getPoints(),
                player.getTeam().getId(),
                player.getPicks()
                        .stream()
                        .map(Pick::getId)
                        .collect(Collectors.toList()),
                player.getIncomes()
        );
    }

    public TeamDTO getTeamDTO(Team team) {
        return new TeamDTO(
                team.getId(),
                team.getName(),
                team.getPrice(),
                team.getPoints(),
                team.getPlayers(),
                team.getPicks()
                        .stream()
                        .map(Pick::getId)
                        .collect(Collectors.toList()),
                team.getIncomes()
        );
    }

    public PlayerIncomeDTO getPlayerIncomeDTO(PlayerIncome playerIncome) {
        return new PlayerIncomeDTO(
                playerIncome.getId(),
                playerIncome.getType(),
                playerIncome.getDescription(),
                playerIncome.getAmount(),
                playerIncome.getPlayer().getId()
        );
    }

    public TeamIncomeDTO getTeamIncomeDTO(TeamIncome teamIncome) {
        return new TeamIncomeDTO(
                teamIncome.getId(),
                teamIncome.getType(),
                teamIncome.getDescription(),
                teamIncome.getAmount(),
                teamIncome.getTeam().getId()
        );
    }

    public TournamentDTO getTournamentDTO(Tournament tournament) {
        return new TournamentDTO(
                tournament.getId(),
                tournament.getName(),
                tournament.getLockDateTime(),
                tournament.getUnlockDateTime(),
                tournament.getTeams()
                        .stream()
                        .map(Team::getId)
                        .collect(Collectors.toList())
        );
    }
}
