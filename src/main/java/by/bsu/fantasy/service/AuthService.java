package by.bsu.fantasy.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import by.bsu.fantasy.exceptions.LoginFailedException;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import by.bsu.fantasy.util.JwtTokenRepository;
import by.bsu.fantasy.util.PasswordUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final PasswordUtil passwordUtil = new PasswordUtil();

    public ResponseEntity<User> registerUser(String login, String password, String role) {
        CsrfToken token = jwtTokenRepository.generateToken(login);
        User response = userService.createUser(login, passwordUtil.encode(password), role, token.getToken());
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return jwtTokenRepository.saveToken(token, new ResponseEntity<>(response, headers, HttpStatus.OK));
    }

    public ResponseEntity<User> loginUser(String login, String password) {
        User response = null;
        try {
            response = userService.getUserByUsername(login);
        } catch (LoginFailedException e) {
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }

        if (passwordUtil.verify(password, response.getPassword())) {
            CsrfToken token = jwtTokenRepository.generateToken(login);
            response.setToken(token.getToken());
            userRepository.save(response);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            return jwtTokenRepository.saveToken(token, new ResponseEntity<>(response, headers, HttpStatus.OK));
        }
        return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
    }
}
