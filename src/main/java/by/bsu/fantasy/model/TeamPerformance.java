package by.bsu.fantasy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TeamPerformance {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tournament tournament;

    @OneToMany(mappedBy = "performance")
    private List<TeamIncome> incomes;
}
