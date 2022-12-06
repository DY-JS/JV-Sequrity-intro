package mate.academy.dao;

import mate.academy.model.User;

import java.util.Optional;

public class UserDaoImpl implements UserDao{
    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.of(new User(1L, username, "1234"));
    }
}
