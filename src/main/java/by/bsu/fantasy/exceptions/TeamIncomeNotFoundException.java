package by.bsu.fantasy.exceptions;

//TODO add advice
public class TeamIncomeNotFoundException extends RuntimeException {
    public TeamIncomeNotFoundException(Long id) {
        super("Couldn't find team income with id: " + id);
    }
}
