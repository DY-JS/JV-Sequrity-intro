package mate.academy.dao;

import mate.academy.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByUserName(String username);
}
