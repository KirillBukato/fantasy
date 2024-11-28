package by.bsu.fantasy.util;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import by.bsu.fantasy.exceptions.BadTokenException;
import by.bsu.fantasy.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtCsrfFilter extends OncePerRequestFilter {

    private JwtTokenRepository jwtTokenRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            request.setAttribute(HttpServletResponse.class.getName(), response);
            if (request.getServletPath().equals("/register") || request.getServletPath().equals("/login")) {
                filterChain.doFilter(request, response);
                return;
            }
            User user = jwtTokenRepository.getUserFromRequest(request);
            if (user == null) {
                throw new BadTokenException();
            }
            filterChain.doFilter(request, response);
        } catch(RuntimeException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
        }
    }
}