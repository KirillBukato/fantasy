package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.TeamIncomeNotFoundException;

public class TeamIncomeNotFoundAdvice {

    @ExceptionHandler(TeamIncomeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String playerNotFound(TeamIncomeNotFoundException ex) {
        return ex.getMessage();
    }
}
