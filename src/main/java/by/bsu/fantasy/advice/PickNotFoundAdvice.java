package by.bsu.fantasy.advice;

import by.bsu.fantasy.exceptions.PickNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PickNotFoundAdvice {

    @ExceptionHandler(PickNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pickNotFound(PickNotFoundException ex) {
        return ex.getMessage();
    }
}
