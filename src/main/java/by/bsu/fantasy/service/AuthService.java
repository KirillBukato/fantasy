package by.bsu.fantasy.service;

import org.springframework.stereotype.Service;

import by.bsu.fantasy.config.SecurityConfig;
import by.bsu.fantasy.model.AuthRecord;
import by.bsu.fantasy.model.AuthResponse;
import by.bsu.fantasy.util.JwtTokenUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRecordService authRecordService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthResponse registerUser(String login, String passw, String role) {
        authRecordService.createRecord(login, passw, role);
        return new AuthResponse(jwtTokenUtil.generateAccessToken(login), jwtTokenUtil.generateRefreshToken(login));
    }

    public AuthResponse loginUser(String login, String password) {
        AuthRecord authRecord = null;
        try { 
            authRecord = authRecordService.getRecordByUsername(login);
        } catch(RuntimeException e) {
            throw new RuntimeException("ADASSAD");
        }
        return new AuthResponse(authRecord.getPassword(), SecurityConfig.passwordEncoder().encode(password));
        // if (user.getPassword().equals(SecurityConfig.passwordEncoder().encode(password))) {
        //     return new AuthResponse(jwtTokenUtil.generateAccessToken(login), jwtTokenUtil.generateRefreshToken(login));
        // }
        // throw new LoginFailedException();
    }
}
