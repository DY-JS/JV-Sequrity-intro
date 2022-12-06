package mate.academy.service;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;

public interface AuthenticationService {
    User login(String username, String password) throws AuthenticationException;
}
