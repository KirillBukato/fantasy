package by.bsu.fantasy.controller;

import java.util.List;

import by.bsu.fantasy.dto.UserDTO;
import by.bsu.fantasy.util.DtoMappingUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.UserService;
import by.bsu.fantasy.util.JwtTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;

    @GetMapping("/users/all")
    private List<UserDTO> getAll() {
        return userService.getUSers()
                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
    }

    @GetMapping("/users/fromtoken")
    private ResponseEntity<User> getUserFromToken(HttpServletRequest request) {
        try {
            User user = jwtTokenRepository.getUserFromRequest(request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }
}
