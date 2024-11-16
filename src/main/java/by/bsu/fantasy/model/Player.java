package by.bsu.fantasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private int points;
    //TODO add link to team


    public Player() {}

    public Player(String name, double price, int points) {
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

    public double getPrice() {
        return price;
    }

    public int getPoints() {
        return points;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name) &&
                Objects.equals(price, player.price) &&
                Objects.equals(points, player.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, points);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", points=" + points +
                "}";
    }
}
