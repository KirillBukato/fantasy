package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.PlayerNotFoundException;
import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository
                .findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository
                .findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player player) {
        player.setId(id);
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

}
