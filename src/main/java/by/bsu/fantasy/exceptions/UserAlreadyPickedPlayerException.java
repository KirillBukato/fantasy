package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.model.User;

//TODO add advice
public class UserAlreadyPickedPlayerException extends RuntimeException {
    public UserAlreadyPickedPlayerException(User user, Player player) {
        super(
                "User " + user.getName() + " already picked player " + player.getName()
        );
    }
}
