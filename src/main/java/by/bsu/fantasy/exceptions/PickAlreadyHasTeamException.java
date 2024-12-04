package by.bsu.fantasy.exceptions;

import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.Team;

public class PickAlreadyHasTeamException extends RuntimeException {
    public PickAlreadyHasTeamException(Pick pick, Team team) {
        super(
                "Pick " + pick.getId() + " already has team " + team.getName()
        );
    }
}
