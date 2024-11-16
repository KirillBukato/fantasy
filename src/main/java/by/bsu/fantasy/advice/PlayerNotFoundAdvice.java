package by.bsu.fantasy.advice;

import by.bsu.fantasy.exceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlayerNotFoundAdvice {

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String playerNotFound(PlayerNotFoundException ex) {
        return ex.getMessage();
    }
}
