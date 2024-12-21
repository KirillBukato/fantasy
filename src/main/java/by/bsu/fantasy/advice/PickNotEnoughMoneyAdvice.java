package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickNotEnoughMoneyException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PickNotEnoughMoneyAdvice {
    @ExceptionHandler(PickNotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String pickNotEnoughMoney(PickNotEnoughMoneyException ex) {
        return ex.getMessage();
    }
}
