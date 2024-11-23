package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.UserPlayerPickLimitException;

public class UserPlayerPickLimitAdvice {
    @ExceptionHandler(UserPlayerPickLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String playerNotFound(UserPlayerPickLimitException ex) {
        return ex.getMessage();
    }
}
