package by.bsu.fantasy.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import by.bsu.fantasy.exceptions.AccessDeniedException;
import by.bsu.fantasy.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtCsrfFilter extends OncePerRequestFilter {

    private JwtTokenRepository jwtTokenRepository;

    private Map<String, AuthPolicy> authPolicyMap;
    private static String dummy = "!PAR!";

    public JwtCsrfFilter(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;

        Reflections reflections = new Reflections("by.bsu.fantasy", Scanners.MethodsAnnotated);
        Set<Method> methods = reflections.getMethodsAnnotatedWith(SetAuthPolicy.class);
        authPolicyMap = new HashMap<>(methods.stream()
            .filter(el -> !getPath(el).isEmpty() && (el.getDeclaringClass().isAnnotationPresent(RestController.class) || el.getDeclaringClass().isAnnotationPresent(Controller.class)))
            .collect(Collectors.toMap(el -> getPath(el), el -> el.getAnnotation(SetAuthPolicy.class).policy())));
        System.err.println("AuthPolicyMap:");  // TODO: убрать это в проде
        System.err.println(authPolicyMap);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.err.println(makePath(request));
        try {
            request.setAttribute(HttpServletResponse.class.getName(), response);
            if (authPolicyMap.containsKey(makePath(request)) && authPolicyMap.get(makePath(request)) == AuthPolicy.ALL) {
                filterChain.doFilter(request, response);
                return;
            }
            HttpStatus flag = null;
            User user = jwtTokenRepository.getUserFromRequest(request, flag);
            if (user == null) {
                throw new AccessDeniedException("User not found or bad token.");
            }
            if (user.getBlockedTokens().contains(jwtTokenRepository.getTokenFromRequest(request))) {
                throw new AccessDeniedException("Token is banned. Try to login again.");
            }
            AuthPolicy rights = authPolicyMap.containsKey(makePath(request)) ? authPolicyMap.get(makePath(request)) : AuthPolicy.ADMIN;
            switch (rights) {
            case USER:
                if (user.getRole().equals("basic_user") || user.getRole().equals("admin")) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    throw new AccessDeniedException("User is unauthorized.");
                }
        
            case ADMIN:
                if (user.getRole().equals("admin")) {
                    filterChain.doFilter(request, response);
                } else {
                    throw new AccessDeniedException("Insufficient access rights.");
                }
                break;

            default:
                break;
            }
        } catch(AccessDeniedException exc) {
            handle(request, response, exc);
        } catch(RuntimeException e) {
            e.printStackTrace();
            handle(request, response, new AccessDeniedException("Access denied."));
        }
    }

    private String getPath(Method method) {
        String ans = null;
        if (method.isAnnotationPresent(PostMapping.class)) {
            ans = "POST" + method.getAnnotation(PostMapping.class).value()[0];
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            ans = "GET" + method.getAnnotation(GetMapping.class).value()[0];
        } else if (method.isAnnotationPresent(DeleteMapping.class)) {
            ans = "DELETE" + method.getAnnotation(DeleteMapping.class).value()[0];
        } else if (method.isAnnotationPresent(PutMapping.class)) {
            ans = "PUT" + method.getAnnotation(PutMapping.class).value()[0];
        }
        if (ans == null) {
            return ans;
        }
        return ans.replaceAll("\\{[^}]*\\}", dummy);
    }

    private String generateAndValidateConcats(List<String> list, String prev, int i) {
        if (i == list.size()) {
            return authPolicyMap.containsKey(prev) ? prev : null;
        }
        String left = generateAndValidateConcats(list, prev + "/" + dummy, i + 1);
        String right = generateAndValidateConcats(list, prev + "/" + list.get(i), i + 1);
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    private String makePath(HttpServletRequest request) {
        String path = request.getServletPath();
        ArrayList<String> splitted = new ArrayList<>(Arrays.asList(path.split("/")));
        splitted.remove(0);
        return generateAndValidateConcats(splitted, request.getMethod(), 0);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, by.bsu.fantasy.exceptions.AccessDeniedException exc) {
        try {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"error\": \"" + exc.getMessage() + "\"}");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}