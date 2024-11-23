package by.bsu.fantasy.exceptions;

public class PlayerIncomeNotFoundException extends RuntimeException {
    public PlayerIncomeNotFoundException(Long id) {
        super("Couldn't find player income with id: " + id);
    }
}
