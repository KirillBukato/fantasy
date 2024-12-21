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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

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
        // Method method;
        // method.getDeclaringClass()
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
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found or bad token.");
                return;
            }
            if (!user.getTokens().contains(jwtTokenRepository.getTokenFromRequest(request))) {
                System.err.println(user.getTokens());
                response.sendError(HttpServletResponse.SC_CONFLICT, "This token expired because new token was created for this user.");
                return;
            }
            AuthPolicy rights = authPolicyMap.containsKey(makePath(request)) ? authPolicyMap.get(makePath(request)) : AuthPolicy.ADMIN;
            switch (rights) {
            case USER:
                if (user.getRole().equals("basic_user") || user.getRole().equals("admin")) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is unauthorized.");
                    return;
                }
                break;
        
            case ADMIN:
                if (user.getRole().equals("admin")) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient access rights.");
                    return;
                }
                break;

            default:
                break;
            }
        } catch(RuntimeException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
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
}