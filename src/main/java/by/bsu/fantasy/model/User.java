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
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double balance;
    private Integer points;
    //TODO: add links to picked teams and players

    public User(String name, Double balance, Integer points) {
        this.name = name;
        this.balance = balance;
        this.points = points;
    }
}
