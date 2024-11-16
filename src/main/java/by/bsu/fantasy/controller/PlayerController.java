package by.bsu.fantasy.controller;

import by.bsu.fantasy.exceptions.PlayerNotFoundException;
import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerRepository
                .findAll();
    }

    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return playerRepository
                .findById(id)
                .map(p -> {
                    p.setName(player.getName());
                    p.setPrice(player.getPrice());
                    p.setPoints(player.getPoints());
                    return playerRepository.save(p);
                })
                .orElseGet(
                        () -> playerRepository.save(player)
                );
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
    }

}
