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
import by.bsu.fantasy.util.ResponseGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;
    private final ResponseGenerator gen;

    @GetMapping("/users/all")
    private ResponseEntity<List<UserDTO>> getAll(HttpServletRequest request) {
        List<UserDTO> list = userService.getUsers()

                .stream()
                .map(DtoMappingUtil::convert)
                .toList();
        return jwtTokenRepository.sign(gen.generate(list), jwtTokenRepository.getTokenFromRequest(request));
    }

    @SuppressWarnings("null")
    @GetMapping("/users/fromtoken")
    private ResponseEntity<UserDTO> getUserFromToken(HttpServletRequest request) {
        HttpStatus flag = null;
        try {
            User user = jwtTokenRepository.getUserFromRequest(request, flag);
            return new ResponseEntity<>(DtoMappingUtil.convert(user), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, null, flag);
        }
    }
}
