package by.bsu.fantasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double balance;
    private Integer points;

    public User() {

    }

    public User(String name, Double balance, Integer points) {
        this.name = name;
        this.balance = balance;
        this.points = points;
    }


    public Integer getPoints() {
        return points;
    }

    public Double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
