package by.bsu.fantasy.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Couldn't find user with id: " + id);
    }
}
