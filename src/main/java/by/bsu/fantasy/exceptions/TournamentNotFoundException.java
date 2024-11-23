package by.bsu.fantasy.exceptions;

public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException(Long id)
    {
        super("Couldn't find tournament with id: " + id);
    }
}
