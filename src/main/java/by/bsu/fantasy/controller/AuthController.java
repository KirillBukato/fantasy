package by.bsu.fantasy.controller;


import by.bsu.fantasy.dto.UserDTO;
import by.bsu.fantasy.util.DtoMappingUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.model.AuthRequest;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody AuthRequest authRequest) {
        ResponseEntity<User> response = authService.registerUser(authRequest.getLogin(), authRequest.getPassword(), "basic_user");
        return new ResponseEntity<>(DtoMappingUtil.convert(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody AuthRequest authRequest) {
        ResponseEntity<User> response = authService.loginUser(authRequest.getLogin(), authRequest.getPassword());
        return new ResponseEntity<>(DtoMappingUtil.convert(response.getBody()), response.getHeaders(), response.getStatusCode());
    }
}
