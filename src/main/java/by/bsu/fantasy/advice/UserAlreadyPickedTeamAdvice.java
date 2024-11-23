package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.UserAlreadyPickedTeamException;

public class UserAlreadyPickedTeamAdvice {
    @ExceptionHandler(UserAlreadyPickedTeamException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String playerNotFound(UserAlreadyPickedTeamException ex) {
        return ex.getMessage();
    }
}
