package by.bsu.fantasy.dto;

import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.model.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PickDTO {
    private Long id;
    private Double balance;
    private Integer points;

    private List<Player> players;

    private List<Team> teams;

    private Long user_id;
}
