package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickTeamLimitException;

public class PickTeamLimitAdvice {
    @ExceptionHandler(PickTeamLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String playerNotFound(PickTeamLimitException ex) {
        return ex.getMessage();
    }
}
