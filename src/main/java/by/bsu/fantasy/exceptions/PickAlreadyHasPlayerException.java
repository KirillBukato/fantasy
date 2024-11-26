package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.Player;

public class PickAlreadyHasPlayerException extends RuntimeException {
    public PickAlreadyHasPlayerException(Pick pick, Player player) {
        super(
                "Pick " + pick.getName() + " already has player " + player.getName()
        );
    }
}
