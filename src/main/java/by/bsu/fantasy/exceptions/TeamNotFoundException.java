package by.bsu.fantasy.exceptions;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Long id) {
        super("Couldn't find team with id: " + id);
    }
}
