package by.bsu.fantasy.exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login failed! Wrong login or password. Maybe you should firstly register.");
    }
}
