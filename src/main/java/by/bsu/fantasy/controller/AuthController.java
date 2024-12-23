package by.bsu.fantasy.controller;


import by.bsu.fantasy.dto.UserDTO;
import by.bsu.fantasy.util.AuthPolicy;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.util.SetAuthPolicy;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import by.bsu.fantasy.model.AuthRequest;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @SetAuthPolicy(policy = AuthPolicy.ALL)
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody AuthRequest authRequest) {
        ResponseEntity<User> response = authService.registerUser(authRequest, "basic_user");
        return new ResponseEntity<>(DtoMappingUtil.convert(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    @SetAuthPolicy(policy = AuthPolicy.ALL)
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        ResponseEntity<User> response = authService.loginUser(login, password);
        return new ResponseEntity<>(DtoMappingUtil.convert(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    @SetAuthPolicy(policy = AuthPolicy.USER)
    @GetMapping("/user/logout")
    public ResponseEntity<UserDTO> logoutUser(HttpServletRequest request) {
        return authService.logoutUser(request);
    }
}
