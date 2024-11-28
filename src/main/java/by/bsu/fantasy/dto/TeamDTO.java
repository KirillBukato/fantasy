package by.bsu.fantasy.dto;

import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.model.TeamIncome;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer points;

    private List<Player> players;

    private List<Long> pick_ids;

    private List<TeamIncome> incomes;
}
