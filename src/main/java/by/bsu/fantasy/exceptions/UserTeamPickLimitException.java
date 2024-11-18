package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.User;

//TODO add advice
public class UserTeamPickLimitException extends RuntimeException {
    public UserTeamPickLimitException(User user, int limit) {
        super(
                "User " + user.getName() + " can't pick more than " + limit + " teams"
        );
    }
}
