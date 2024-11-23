package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.User;

public class UserPlayerPickLimitException extends RuntimeException {
    public UserPlayerPickLimitException(User user, int limit) {
        super(
                "User " + user.getName() + " can't pick more than " + limit + " players"
        );
    }
}
