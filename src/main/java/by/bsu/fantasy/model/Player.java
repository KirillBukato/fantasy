package by.bsu.fantasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private Integer points;
    //TODO add link to team
    //TODO как то надо хранить список полученных очков

    public Player(String name, Double price, Integer points) {
        this.name = name;
        this.price = price;
        this.points = points;
    }
}
