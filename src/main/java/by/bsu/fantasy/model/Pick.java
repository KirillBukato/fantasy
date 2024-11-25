package by.bsu.fantasy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double balance;
    private Integer points;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Player> players;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Team> teams;
}
