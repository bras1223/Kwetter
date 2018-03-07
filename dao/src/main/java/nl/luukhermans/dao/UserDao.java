package nl.luukhermans.dao;

import nl.luukhermans.domain.User;

import java.util.Collection;

public interface UserDao {

    void addUser(User user);

    Collection<User> getAllUsers();

    User findByID(Long ID);

    User findByUsername(String username);

    void updateUser(User user);

    void removeUser(User user);

    int count();
}
