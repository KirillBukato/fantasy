package by.bsu.fantasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Integer points;
    //TODO add links to players

    public Team() {

    }

    public Team(String name, Double price, Integer points) {
        this.name = name;
        this.price = price;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getPoints() {
        return points;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
