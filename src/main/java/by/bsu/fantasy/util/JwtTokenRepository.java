package by.bsu.fantasy.util;

import java.util.Date;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

@Repository
public class JwtTokenRepository {

    @Getter
    private static String secret = "meeoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooow";

    @SuppressWarnings("deprecation")
    public CsrfToken generateToken(String username) {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusMinutes(1)
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
}