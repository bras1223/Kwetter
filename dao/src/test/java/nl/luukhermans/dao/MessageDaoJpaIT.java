package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;
import nl.luukhermans.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MessageDaoJpaIT {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private User testUser;
    private MessageDao messageDao;
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

        testUser = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();
        messageDao = new MessageDaoJPA();
        userDao = new UserDaoJPA();
        ((MessageDaoJPA) messageDao).setEm(em);
        ((UserDaoJPA) userDao).setEm(em);
    }

    @Test
    public void savingMessageSuccessful() {
        Integer expectedMessageCount = 1;
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);

        tx.begin();
        int messageCount = messageDao.count();
        tx.commit();
        assertThat(messageCount, is(expectedMessageCount));
    }

    @Test
    public void deleteMessageSuccessful() {
        Integer expectedMessageCount = 0;
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);

        tx.begin();
        messageDao.deleteMessage(message);
        int messageCount = messageDao.count();
        tx.commit();
        assertThat(messageCount, is(expectedMessageCount));
    }

    @Test
    public void getAllMessagesSuccessful() {
        Integer expectedMessageCount = 2;
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();
        Message message2 = Message.builder().message("Hello2").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);
        addUserAndMessage(message2);

        tx.begin();
        int messageCount = messageDao.count();
        tx.commit();
        assertThat(messageCount, is(expectedMessageCount));
    }

    private void addUserAndMessage(Message message) {
        tx.begin();
        userDao.addUser(testUser);
        messageDao.addMessage(message);
        tx.commit();
    }

    @Test
    public void updateMessageSuccesful() {
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);
        message.setMessage("Another text");
        tx.begin();
        messageDao.updateMessage(message);
        tx.commit();
        tx.begin();
        Message foundMessage = messageDao.findMessageByID(message.getID());
        tx.commit();
        assertThat(foundMessage.getMessage(), is(message.getMessage()));
    }

}
