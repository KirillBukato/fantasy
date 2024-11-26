package by.bsu.fantasy.exceptions;

public class PickNotFoundException extends RuntimeException {
    public PickNotFoundException(Long id) {
        super("Couldn't find pick with id: " + id);
    }
}
