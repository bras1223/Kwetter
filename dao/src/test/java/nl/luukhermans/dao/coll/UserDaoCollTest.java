package nl.luukhermans.dao.coll;

import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoCollTest {

    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = new UserDaoColl();
    }

    @Test
    public void addingUserSuccessful() {
        Integer expectedResult = 1;
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        userDao.addUser(user);

        int userCount = userDao.count();
        assertThat(userCount, is(expectedResult));
    }

    @Test
    public void removeUserSuccessful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        userDao.addUser(user);
        userDao.removeUser(user);
        int userCount = userDao.count();
        assertThat(userCount, is(0));
    }

    @Test
    public void findUserByIdSuccessful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        userDao.addUser(user);
        User foundUser = userDao.findByID(user.getID());
        assertThat(foundUser, is(user));
    }

    @Test
    public void findUserByUsernameSuccessful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        userDao.addUser(user);
        User foundUser = userDao.findByUsername(user.getUsername());
        assertThat(foundUser, is(user));
    }

    @Test
    public void updateUserSuccesful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        userDao.addUser(user);
        user.setBio("Hello, i'm Luuk!");
        userDao.updateUser(user);

        User foundUser = userDao.findByID(user.getID());
        assertThat(foundUser.getBio(), is(user.getBio()));
    }
}
