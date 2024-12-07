package by.bsu.fantasy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer points;

    private Long team_id;

    private List<Long> pick_ids;

    private List<PlayerIncomeDTO> incomes;
}
