package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickAlreadyHasTeamException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PickAlreadyHasTeamAdvice {
    @ExceptionHandler(PickAlreadyHasTeamException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String pickAlreadyHasTeam(PickAlreadyHasTeamException ex) {
        return ex.getMessage();
    }
}
