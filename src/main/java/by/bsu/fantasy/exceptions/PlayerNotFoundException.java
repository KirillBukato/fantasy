package by.bsu.fantasy.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Couldn't find player with id: " + id);
    }
}
