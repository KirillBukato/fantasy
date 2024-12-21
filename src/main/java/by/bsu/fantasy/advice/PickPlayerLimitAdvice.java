package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickPlayerLimitException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PickPlayerLimitAdvice {
    @ExceptionHandler(PickPlayerLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String pickPlayerLimit(PickPlayerLimitException ex) {
        return ex.getMessage();
    }
}
