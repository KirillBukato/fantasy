package by.bsu.fantasy.util;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.filter.OncePerRequestFilter;

import by.bsu.fantasy.exceptions.BadTokenException;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtCsrfFilter extends OncePerRequestFilter {

    private final UserService userService;

    private String checkToken(HttpServletRequest authRequest) {
        if (authRequest.getHeader("x-csrf-token") == null) {
            return null;
        }
        try {
            String token = authRequest.getHeader(authRequest.getHeader("x-csrf-token"));
            if (token == null) {
                return null;
            }
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(JwtTokenRepository.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();

            if (claims.getExpiration().before(new Date())) {
                return null;
            }
            try {
                User record = userService.getRecordByUsername(claims.getSubject());
                return record.getRole();
            } catch(RuntimeException e) {
                return null;
            }
        } catch(NullPointerException e) {
            return null;
        }
    }

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        request.setAttribute(HttpServletResponse.class.getName(), response);
        String role = checkToken(request);
        if (request.getServletPath().equals("/register") || request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (role == null) {
            throw new BadTokenException();
        }
        filterChain.doFilter(request, response);
    }
}