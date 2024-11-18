package by.bsu.fantasy.exceptions;

//TODO add advice
public class PlayerIncomeNotFoundException extends RuntimeException {
    public PlayerIncomeNotFoundException(Long id) {
        super("Couldn't find player income with id: " + id);
    }
}
