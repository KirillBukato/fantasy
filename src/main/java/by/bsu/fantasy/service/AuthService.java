package by.bsu.fantasy.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import by.bsu.fantasy.model.AuthResponse;
import by.bsu.fantasy.util.JwtTokenRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;

    public ResponseEntity<AuthResponse> registerUser(String login, String password, String role) {
        userService.createRecord(login, password, role);
        AuthResponse response = new AuthResponse(login, role);
        CsrfToken token = jwtTokenRepository.generateToken(login);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return jwtTokenRepository.saveToken(token, new ResponseEntity<>(response, headers, HttpStatus.OK));
    }
}
