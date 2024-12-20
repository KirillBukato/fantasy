package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.PlayerDTO;
import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.service.PlayerService;
import by.bsu.fantasy.util.AuthPolicy;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.util.SetAuthPolicy;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @SetAuthPolicy(policy = AuthPolicy.BASIC_USER)
    @GetMapping("/players")
    public List<PlayerDTO> getPlayers() {
        return playerService.getPlayers()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }

    @SetAuthPolicy(policy = AuthPolicy.BASIC_USER)
    @GetMapping("/player/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return DtoMappingUtil.convert(
                playerService.getPlayerById(id)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PostMapping("/player")
    public PlayerDTO addPlayer(@RequestBody Player player) {
        return DtoMappingUtil.convert(
                playerService.addPlayer(player)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PutMapping("/player/{id}")
    public PlayerDTO updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return DtoMappingUtil.convert(
                playerService.updatePlayer(id, player)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

}
