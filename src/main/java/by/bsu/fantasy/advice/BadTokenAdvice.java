package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.BadTokenException;

public class BadTokenAdvice {
    @ExceptionHandler(BadTokenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String badToken(BadTokenException ex) {
        return ex.getMessage();
    }
}
