package by.bsu.fantasy.util;

import java.util.Date;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import by.bsu.fantasy.model.User;
import by.bsu.fantasy.repository.UserRepository;
import by.bsu.fantasy.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JwtTokenRepository {

    @Getter
    private static String secret = "meeoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooow";

    @Value("${by.fantasy.token_expire_after}")
    private String expireAfter;

    @Value("${by.fantasy.token_exchange}")
    private String exchange;

    private final UserService userService;
    private final UserRepository userRepository;
    private final ResponseGenerator gen;

    public String getTokenFromRequest(HttpServletRequest authRequest) {
        if (authRequest.getHeader("x-csrf-token") == null) {
            return null;
        }
        try {
            String token = authRequest.getHeader(authRequest.getHeader("x-csrf-token"));
            return token;
        } catch(NullPointerException e) {
            return null;
        }
    }

    public User getUserFromRequest(HttpServletRequest authRequest, HttpStatus flag) {
        try {
            if (getTokenFromRequest(authRequest) == null) {
                flag = HttpStatus.BAD_REQUEST;
                return null;
            }
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(JwtTokenRepository.getSecret())
                .build()
                .parseClaimsJws(getTokenFromRequest(authRequest))
                .getBody();

            if (claims.getExpiration().before(new Date())) {
                flag = HttpStatus.BAD_REQUEST;
                return null;
            }
            try {
                User record = userService.getUserByUsername(claims.getSubject());
                flag = HttpStatus.OK;
                return record;
            } catch(RuntimeException e) {
                flag = HttpStatus.NOT_FOUND;
                return null;
            }
        } catch(NullPointerException e) {
            flag = HttpStatus.BAD_REQUEST;
            return null;
        } catch(io.jsonwebtoken.ExpiredJwtException e) {
            flag = HttpStatus.BAD_REQUEST;
            return null;
        }
    }

    public User getUserFromRequest(HttpServletRequest authRequest) {
        HttpStatus unused = HttpStatus.OK;
        return getUserFromRequest(authRequest, unused);
    }

    public User getAuthentificatedUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(JwtTokenRepository.getSecret())
            .build()
            .parseClaimsJws(token)
            .getBody();
        User user = userService.getUserByUsername(claims.getSubject());
        return user;
    }

    @SuppressWarnings("deprecation")
    public CsrfToken generateToken(String username) {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusMinutes(Integer.parseInt(expireAfter))
                .atZone(ZoneId.systemDefault()).toInstant());

        String token = "";
        try {
            token = Jwts.builder()
                    .setId(id)
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        } catch (JwtException e) {
            e.printStackTrace();
            //ignore
        }
        return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
    }

    public <T> ResponseEntity<T> saveToken(CsrfToken csrfToken, ResponseEntity<T> response) {
        if (Objects.nonNull(csrfToken)) {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(response.getHeaders());
            headers.add(csrfToken.getHeaderName(), csrfToken.getParameterName());
            headers.add(csrfToken.getParameterName(), csrfToken.getToken());
            return new ResponseEntity<T>(response.getBody(), headers, response.getStatusCode());
        }
        return null;
    }

    public <T> ResponseEntity<T> sign(ResponseEntity<T> response, String token) {
        if (exchange.equals("0")) {
            return response;
        }
        if (!(response.getStatusCode().value() == 200)) {
            return response;
        }
        CsrfToken csrfToken = generateToken(getAuthentificatedUserFromToken(token).getUsername());
        User user = getAuthentificatedUserFromToken(token);
        userRepository.save(user);
        return saveToken(csrfToken, response);
    }

    public <T> ResponseEntity<T> sign(T response, String token) {
        if (exchange.equals("0")) {
            return gen.generate(response);
        }
        CsrfToken csrfToken = generateToken(getAuthentificatedUserFromToken(token).getUsername());
        User user = getAuthentificatedUserFromToken(token);
        userRepository.save(user);
        return saveToken(csrfToken, gen.generate(response));
    }
}