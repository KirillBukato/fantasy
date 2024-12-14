package by.bsu.fantasy.util;

import by.bsu.fantasy.dto.*;
import by.bsu.fantasy.model.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoMappingUtil {
    public static UserDTO convert(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getPicks() == null ? null : user.getPicks()
                        .stream()
                        .map(Pick::getId)
                        .collect(Collectors.toList())
        );
    }

    public static PickDTO convert(Pick pick) {
        if (pick == null) {
            return null;
        }
        return new PickDTO(
                pick.getId(),
                pick.getBalance(),
                pick.getPoints(),
                pick.getPlayers() == null ? null : pick.getPlayers()
                        .stream()
                        .map(DtoMappingUtil::convert)
                        .toList(),
                pick.getPlayers() == null ? null : pick.getTeams()
                        .stream()
                        .map(DtoMappingUtil::convert)
                        .toList(),
                pick.getUser() == null ? null : pick.getUser().getId()
        );
    }

    public static PlayerDTO convert(Player player) {
        if (player == null) {
            return null;
        }
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getPrice(),
                player.getPoints(),
                player.getTeam() == null ? null : player.getTeam().getId(),
                player.getPicks() == null ? null : player.getPicks()
                        .stream()
                        .map(Pick::getId)
                        .collect(Collectors.toList()),
                player.getIncomes() == null ? null : player.getIncomes()
                        .stream()
                        .map(DtoMappingUtil::convert)
                        .toList()
        );
    }

    public static TeamDTO convert(Team team) {
        if (team == null) {
            return null;
        }
        return new TeamDTO(
                team.getId(),
                team.getName(),
                team.getPrice(),
                team.getPoints(),
                team.getPlayers() == null ? null : team.getPlayers()
                        .stream()
                        .map(DtoMappingUtil::convert)
                        .toList(),
                team.getPicks() == null ? null : team.getPicks()
                        .stream()
                        .map(Pick::getId)
                        .collect(Collectors.toList()),
                team.getIncomes() == null ? null : team.getIncomes()
                        .stream()
                        .map(DtoMappingUtil::convert)
                        .toList()
        );
    }

    public static PlayerIncomeDTO convert(PlayerIncome playerIncome) {
        if (playerIncome == null) {
            return null;
        }
        return new PlayerIncomeDTO(
                playerIncome.getId(),
                playerIncome.getType(),
                playerIncome.getDescription(),
                playerIncome.getAmount(),
                playerIncome.getPlayer() == null ? null : playerIncome.getPlayer().getId()
        );
    }

    public static TeamIncomeDTO convert(TeamIncome teamIncome) {
        if (teamIncome == null) {
            return null;
        }
        return new TeamIncomeDTO(
                teamIncome.getId(),
                teamIncome.getType(),
                teamIncome.getDescription(),
                teamIncome.getAmount(),
                teamIncome.getTeam() == null ? null : teamIncome.getTeam().getId()
        );
    }

    public static TournamentDTO convert(Tournament tournament) {
        if (tournament == null) {
            return null;
        }
        return new TournamentDTO(
                tournament.getId(),
                tournament.getName(),
                tournament.getLockDateTime(),
                tournament.getUnlockDateTime(),
                tournament.getTeams() == null ? null : tournament.getTeams()
                        .stream()
                        .map(Team::getId)
                        .collect(Collectors.toList())
        );
    }
}
