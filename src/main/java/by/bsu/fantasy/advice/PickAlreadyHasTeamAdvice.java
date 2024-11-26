package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickAlreadyHasTeamException;

public class PickAlreadyHasTeamAdvice {
    @ExceptionHandler(PickAlreadyHasTeamException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String playerNotFound(PickAlreadyHasTeamException ex) {
        return ex.getMessage();
    }
}
