package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.UserNotEnoughMoneyException;

public class UserNotEnoughMoneyAdvice {
    @ExceptionHandler(UserNotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String playerNotFound(UserNotEnoughMoneyException ex) {
        return ex.getMessage();
    }
}
