package nl.luukhermans.dao;

import nl.luukhermans.domain.User;

import java.util.Collection;
import java.util.HashMap;

public class UserDaoColl implements UserDao {

    private HashMap<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getID(), user);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public User findByID(int ID) {
        return users.get(ID);
    }

    public void updateUser(User user) {
        users.put(user.getID(), user);
    }

    public void removeUser(User user) {
        users.remove(user.getID());
    }

    public void followUser(User follower, User following) {
    }
}
