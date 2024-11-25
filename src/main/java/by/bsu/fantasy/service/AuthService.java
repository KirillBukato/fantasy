package by.bsu.fantasy.service;

import org.springframework.stereotype.Service;

import by.bsu.fantasy.config.SecurityConfig;
import by.bsu.fantasy.exceptions.LoginFailedException;
import by.bsu.fantasy.model.AuthResponse;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.util.JwtTokenUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthResponse registerUser(String login, String passw, String role) {
        userService.createUserWithId(login, passw, role);
        return new AuthResponse(jwtTokenUtil.generateAccessToken(login), jwtTokenUtil.generateRefreshToken(login));
    }

    public AuthResponse loginUser(String login, String password) {
        User user = null;
        try { 
            user = userService.getUserByUsername(login);
        } catch(RuntimeException e) {
            throw new RuntimeException("ADASSAD");
        }
        return new AuthResponse(user.getPassword(), SecurityConfig.passwordEncoder().encode(password));
        // if (user.getPassword().equals(SecurityConfig.passwordEncoder().encode(password))) {
        //     return new AuthResponse(jwtTokenUtil.generateAccessToken(login), jwtTokenUtil.generateRefreshToken(login));
        // }
        // throw new LoginFailedException();
    }
}
