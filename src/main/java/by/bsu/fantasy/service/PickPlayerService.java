package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.PickAlreadyHasPlayerException;
import by.bsu.fantasy.exceptions.PickNotEnoughMoneyException;
import by.bsu.fantasy.exceptions.PickPlayerLimitException;
import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.Player;
import org.springframework.stereotype.Service;

@Service
public class PickPlayerService {

    private final PickService pickService;
    private final PlayerService playerService;

    public PickPlayerService(PickService pickService, PlayerService playerService) {
        this.pickService = pickService;
        this.playerService = playerService;
    }

    public Pick linkPlayerToPick(Long pickId, Long playerId) {
        Pick pick = pickService.getPickById(pickId);
        Player player = playerService.getPlayerById(playerId);

        if (pick.getPlayers().contains(player)) {
            throw new PickAlreadyHasPlayerException(pick, player);
        }
        if (pick.getPlayers().size() >= 3) {
            throw new PickPlayerLimitException(pick, 3);
        }
        if (player.getPrice() > pick.getBalance()) {
            throw new PickNotEnoughMoneyException(pick, player.getPrice());
        }
        pick.setBalance(pick.getBalance() - player.getPrice());
        pick.getPlayers().add(player);
        return pickService.updatePick(pickId, pick);
    }

    public Pick unlinkPlayerFromPick(Long pickId, Long playerId) {
        Pick pick = pickService.getPickById(pickId);
        Player player = playerService.getPlayerById(playerId);

        if (pick.getPlayers().contains(player)) {
            pick.setBalance(pick.getBalance() + player.getPrice());
            pick.getPlayers().remove(player);
        }

        return pickService.updatePick(pickId, pick);
    }
}
