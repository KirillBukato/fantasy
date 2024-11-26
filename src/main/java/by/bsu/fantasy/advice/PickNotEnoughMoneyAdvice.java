package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickNotEnoughMoneyException;

public class PickNotEnoughMoneyAdvice {
    @ExceptionHandler(PickNotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String playerNotFound(PickNotEnoughMoneyException ex) {
        return ex.getMessage();
    }
}
