package by.bsu.fantasy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.bsu.fantasy.exceptions.UserTeamPickLimitException;

public class UserTeamPickLimitAdvice {
    @ExceptionHandler(UserTeamPickLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String playerNotFound(UserTeamPickLimitException ex) {
        return ex.getMessage();
    }
}
