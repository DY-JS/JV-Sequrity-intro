package mate.academy.service;

import java.util.Optional;
import mate.academy.dao.UserDao;
import mate.academy.dao.UserDaoImpl;
import mate.academy.model.User;
import mate.academy.exception.AuthenticationException;


public class AuthenticationServiceImpl implements AuthenticationService {
    private UserDao userDao = new UserDaoImpl(); //для взаимодействия с бд

    @Override
    public User login(String username, String password) throws AuthenticationException {
        Optional<User> user = userDao.findByUserName(username);
        if (user.isEmpty()) {
            throw new AuthenticationException("Username or password is incorrect");
        }
        if(user.get().getPassword().equals(password)) {
            return user.get();
        }
        throw new AuthenticationException("Username or password is incorrect");
    }
}
