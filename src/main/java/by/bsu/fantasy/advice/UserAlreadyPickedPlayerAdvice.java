package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.UserAlreadyPickedPlayerException;

public class UserAlreadyPickedPlayerAdvice {
    @ExceptionHandler(UserAlreadyPickedPlayerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String playerNotFound(UserAlreadyPickedPlayerException ex) {
        return ex.getMessage();
    }
}
