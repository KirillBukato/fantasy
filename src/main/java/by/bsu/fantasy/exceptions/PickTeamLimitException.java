package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Pick;

public class PickTeamLimitException extends RuntimeException {
    public PickTeamLimitException(Pick pick, int limit) {
        super(
                "Pick " + pick.getId() + " can't have more than " + limit + " teams"
        );
    }
}
