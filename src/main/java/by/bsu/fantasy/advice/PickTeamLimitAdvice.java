package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickTeamLimitException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PickTeamLimitAdvice {
    @ExceptionHandler(PickTeamLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String pickTeamLimit(PickTeamLimitException ex) {
        return ex.getMessage();
    }
}
