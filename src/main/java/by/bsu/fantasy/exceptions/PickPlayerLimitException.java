package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Pick;

public class PickPlayerLimitException extends RuntimeException {
    public PickPlayerLimitException(Pick pick, int limit) {
        super(
                "Pick " + pick.getId() + " can't have more than " + limit + " players"
        );
    }
}
