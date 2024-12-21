package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickAlreadyHasPlayerException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PickAlreadyHasPlayerAdvice {
    @ExceptionHandler(PickAlreadyHasPlayerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String pickAlreadyHasPlayer(PickAlreadyHasPlayerException ex) {
        return ex.getMessage();
    }
}
