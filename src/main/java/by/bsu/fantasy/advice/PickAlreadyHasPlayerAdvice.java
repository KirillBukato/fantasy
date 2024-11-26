package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.PickAlreadyHasPlayerException;

//TODO переименовать функции а то че они все playerNotFound
public class PickAlreadyHasPlayerAdvice {
    @ExceptionHandler(PickAlreadyHasPlayerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String playerNotFound(PickAlreadyHasPlayerException ex) {
        return ex.getMessage();
    }
}
