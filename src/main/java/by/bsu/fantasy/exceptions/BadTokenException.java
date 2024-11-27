package by.bsu.fantasy.exceptions;

public class BadTokenException extends RuntimeException {
    public BadTokenException() {
        super("No token or bad token in request.");
    }
}
