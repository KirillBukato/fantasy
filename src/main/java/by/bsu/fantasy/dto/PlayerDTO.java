package by.bsu.fantasy.dto;

import by.bsu.fantasy.model.PlayerIncome;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer points;

    private Long team_id;

    private List<Long> pick_ids;

    private List<PlayerIncome> incomes;
}
