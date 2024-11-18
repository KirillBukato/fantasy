package by.bsu.fantasy.service;

import by.bsu.fantasy.exceptions.UserAlreadyPickedPlayerException;
import by.bsu.fantasy.exceptions.UserNotEnoughMoneyException;
import by.bsu.fantasy.exceptions.UserPlayerPickLimitException;
import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserPlayerService {

    private final UserService userService;
    private final PlayerService playerService;

    public UserPlayerService(UserService userService, PlayerService playerService) {
        this.userService = userService;
        this.playerService = playerService;
    }

    public User linkPlayerToUser(Long userId, Long playerId) {
        User user = userService.getUserById(userId);
        Player player = playerService.getPlayerById(playerId);

        if (user.getPlayers().contains(player)) {
            throw new UserAlreadyPickedPlayerException(user, player);
        }
        if (user.getPlayers().size() >= 3) {
            throw new UserPlayerPickLimitException(user, 3);
        }
        if (player.getPrice() > user.getBalance()) {
            throw new UserNotEnoughMoneyException(user, player.getPrice());
        }
        user.setBalance(user.getBalance() - player.getPrice());
        user.getPlayers().add(player);
        return userService.updateUser(userId, user);
    }
}
