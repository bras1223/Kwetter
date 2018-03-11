package nl.luukhermans.dao.coll;

import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Collection;
import java.util.HashMap;

@Stateless
@Default
public class UserDaoColl implements UserDao {

    private HashMap<Long, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getID(), user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public User findByID(Long ID) {
        return users.get(ID);
    }

    @Override
    public User findByUsername(String username) {
        for (User u : users.values()) {
            if (u.getUsername() == username) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getID(), user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user.getID());
    }

    @Override
    public int count() {
        return users.size();
    }

}
