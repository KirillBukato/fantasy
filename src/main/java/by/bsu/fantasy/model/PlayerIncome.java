package by.bsu.fantasy.model;

import by.bsu.fantasy.util.IncomeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PlayerIncome {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private IncomeType type;
    private String description;
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;
}
