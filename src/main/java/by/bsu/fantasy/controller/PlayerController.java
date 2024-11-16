package by.bsu.fantasy.controller;

import by.bsu.fantasy.exceptions.PlayerNotFoundException;
import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    private PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerRepository
                .findAll();
    }

    @GetMapping("/players/{id}")
    public Player getPlayerById(Long id) {
        return playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

}
