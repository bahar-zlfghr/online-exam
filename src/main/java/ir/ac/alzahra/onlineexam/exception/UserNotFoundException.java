package ir.ac.alzahra.onlineexam.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
