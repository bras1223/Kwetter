package nl.luukhermans.service;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Objects;

@Stateless
public class UserService {

    @Inject
    @JPA
    private UserDao userDao;

    /**
     * Adds a user to the UserDao.
     * User must not be null.
     *
     * @param user the user that gets added
     */
    public void addUser(User user) {
        Objects.requireNonNull(user);
        userDao.addUser(user);
    }

    /**
     * Removes a user from the UserDao
     * User must not be null.
     *
     * @param user the user that gets deleted
     */
    public void removeUser(User user) {
        Objects.requireNonNull(user);
        userDao.removeUser(user);
    }

    /**
     * Updates a user at the UserDao
     * User must not be null.
     *
     * @param user the user that gets updated
     */
    public void updateUser(User user) {
        Objects.requireNonNull(user);
        userDao.updateUser(user);
    }

    /**
     * Gets all of the users from the UserDao.
     *
     * @return all known user at the UserDao
     */
    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Searches for the user in the UserDao, that belongs to a ID.
     * ID should not be null.
     *
     * @param ID The ID that should be searched
     * @return The User that is found
     * @throws Exception If user is not found
     */
    public User findUserByID(Long ID) throws Exception {
        Objects.requireNonNull(ID);
        User user = userDao.findByID(ID);
        if (user == null) {
            throw new Exception("User could not be found");
        }
        return user;
    }

    /**
     * Searches for the user in the UserDao, that belongs to a username.
     * username should not be null.
     *
     * @param username The username that should be searched
     * @return The User that is found
     * @throws Exception If user is not found
     */
    public User findUserByUsername(String username) throws Exception {
        Objects.requireNonNull(username);
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new Exception("User could not be found");
        }
        return user;
    }

    /**
     * Lets a user follow an other user.
     * follower and following should not be null.
     *
     * @param follower  The user that wants to follow an other user
     * @param following The user that gets followed
     * @throws Exception When a user is not found.
     */
    public void followUser(User follower, User following) throws Exception {
        if (Objects.isNull(follower) || Objects.isNull(following)) {
            throw new Exception("User is not found");
        }

        follower.followUser(following);
        following.followedBy(follower);

        userDao.updateUser(follower);
        userDao.updateUser(following);
    }

    /**
     * Lets a user unfollow an other user.
     * follower and following should not be null.
     *
     * @param follower  The user that wants to unfollow an other user
     * @param following The user that gets unfollowed
     * @throws Exception When a user is not found.
     */
    public void unfollowUser(User follower, User following) throws Exception {
        if (Objects.isNull(follower) || Objects.isNull(following)) {
            throw new Exception("User is not found");
        }

        follower.unfollowUser(following);
        following.unfollowedBy(follower);

        userDao.updateUser(follower);
        userDao.updateUser(following);
    }
}
