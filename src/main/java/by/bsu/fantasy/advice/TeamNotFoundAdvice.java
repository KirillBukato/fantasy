package by.bsu.fantasy.advice;

import by.bsu.fantasy.exceptions.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TeamNotFoundAdvice {

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String teamNotFound(TeamNotFoundException ex) {
        return ex.getMessage();
    }
}
