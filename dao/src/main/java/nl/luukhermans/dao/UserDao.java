package nl.luukhermans.dao;

import nl.luukhermans.domain.User;

import java.util.Collection;

public interface UserDao {

    void addUser(User user);

    Collection<User> getAllUsers();

    User findByID(int ID);

    void updateUser(User user);

    void removeUser(User user);

    void followUser(User follower, User following);
}
