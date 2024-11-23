package by.bsu.fantasy.exceptions;

public class TeamIncomeNotFoundException extends RuntimeException {
    public TeamIncomeNotFoundException(Long id) {
        super("Couldn't find team income with id: " + id);
    }
}
