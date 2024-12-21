package by.bsu.fantasy.advice;

import by.bsu.fantasy.exceptions.PlayerIncomeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerIncomeNotFoundAdvice {

    @ExceptionHandler(PlayerIncomeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String playerIncomeNotFound(PlayerIncomeNotFoundException ex) {
        return ex.getMessage();
    }
}
