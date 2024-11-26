package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickPlayerLimitException;

public class PickPlayerLimitAdvice {
    @ExceptionHandler(PickPlayerLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String playerNotFound(PickPlayerLimitException ex) {
        return ex.getMessage();
    }
}
