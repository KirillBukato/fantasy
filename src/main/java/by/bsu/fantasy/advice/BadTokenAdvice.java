package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.BadTokenException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadTokenAdvice {
    @ExceptionHandler(BadTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String badToken(BadTokenException ex) {
        return ex.getMessage();
    }
}
