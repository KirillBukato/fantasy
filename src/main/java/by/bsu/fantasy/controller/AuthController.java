package by.bsu.fantasy.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.fantasy.config.SecurityConfig;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.model.AuthRequest;
import by.bsu.fantasy.model.AuthResponse;
import by.bsu.fantasy.service.UserService;
import by.bsu.fantasy.service.AuthService;
import by.bsu.fantasy.util.JwtTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest authRequest) {
        return authService.registerUser(authRequest.getLogin(), SecurityConfig.passwordEncoder().encode(authRequest.getPassword()), "basic_user");
    }

    @GetMapping("/authenticate")
    public ResponseEntity<User> checkToken(RequestEntity<AuthRequest> authRequest) {
        if (authRequest.getHeaders().get("x-csrf-token") == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        try {
            String token = authRequest.getHeaders().get(authRequest.getHeaders().get("x-csrf-token").get(0)).get(0);
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(JwtTokenRepository.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();

            if (claims.getExpiration().before(new Date())) {
                return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
            }
            try {
                User record = userService.getRecordByUsername(claims.getSubject());
                return new ResponseEntity<>(record, HttpStatus.OK);
            } catch(RuntimeException e) {
                return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
            }
        } catch(NullPointerException e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
    }
}
