package nl.luukhermans.services;

import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

@Stateless
public class UserService {

    @Inject
    private UserDao userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUser(int ID) throws Exception {
        User user = userDao.findByID(ID);
        if (user == null) {
            throw new Exception("User could not be found");
        }
        return user;
    }

    public void followUser(int followerID, int followingID) {
        User follower = userDao.findByID(followerID);
        User following = userDao.findByID(followingID);

        follower.followUser(following);
        following.followedBy(follower);

        userDao.updateUser(follower);
        userDao.updateUser(following);
    }
}
