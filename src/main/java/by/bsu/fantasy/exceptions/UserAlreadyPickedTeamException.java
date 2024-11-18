package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.model.User;

//TODO add advice
public class UserAlreadyPickedTeamException extends RuntimeException {
    public UserAlreadyPickedTeamException(User user, Team team) {
        super(
                "User " + user.getName() + " already picked team " + team.getName()
        );
    }
}
