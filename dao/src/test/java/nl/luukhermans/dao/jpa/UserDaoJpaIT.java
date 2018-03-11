package nl.luukhermans.dao.jpa;

import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;
import nl.luukhermans.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoJpaIT {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;

    private UserDao userDao;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(MessageDaoJpaIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJPA();
        ((UserDaoJPA) userDao).setEm(em);
    }

    @Test
    public void addingUserSuccessful() {
        Integer expectedResult = 1;
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        tx.begin();
        userDao.addUser(user);
        tx.commit();
        tx.begin();
        int userCount = userDao.count();
        tx.commit();
        assertThat(userCount, is(expectedResult));
    }

    @Test(expected = RollbackException.class)
    public void addingDuplicateUsernameRollback() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();
        User user1 = User.builder().firstName("L").lastName("Hermans").username("luuk.hermans").build();

        tx.begin();
        userDao.addUser(user);
        userDao.addUser(user1);
        tx.commit();
        tx.begin();
        tx.commit();
    }

    @Test
    public void removeUserSuccessful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        tx.begin();
        userDao.addUser(user);
        tx.commit();
        tx.begin();
        userDao.removeUser(user);
        tx.commit();
        tx.begin();
        int userCount = userDao.count();
        tx.commit();
        assertThat(userCount, is(0));
    }

    @Test
    public void findUserByIdSuccessful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        tx.begin();
        userDao.addUser(user);
        tx.commit();
        tx.begin();
        User foundUser = userDao.findByID(user.getID());
        tx.commit();
        assertThat(foundUser, is(user));
    }

    @Test
    public void findUserByUsernameSuccessful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        tx.begin();
        userDao.addUser(user);
        tx.commit();
        tx.begin();
        User foundUser = userDao.findByUsername(user.getUsername());
        tx.commit();
        assertThat(foundUser, is(user));
    }

    @Test
    public void updateUserSuccesful() {
        User user = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();

        tx.begin();
        userDao.addUser(user);
        tx.commit();
        user.setBio("Hello, i'm Luuk!");
        tx.begin();
        userDao.updateUser(user);
        tx.commit();
        tx.begin();
        User foundUser = userDao.findByID(user.getID());
        tx.commit();
        assertThat(foundUser.getBio(), is(user.getBio()));
    }
}
