package by.bsu.fantasy.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseGenerator {
    public <T> ResponseEntity<T> generate(T value) {
        return new ResponseEntity<>(value, null, HttpStatus.OK);
    }
}
