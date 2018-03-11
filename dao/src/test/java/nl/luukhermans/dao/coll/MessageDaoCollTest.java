package nl.luukhermans.dao.coll;

import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MessageDaoCollTest {

    private MessageDao messageDao;
    private UserDao userDao;

    private User testUser;

    @Before
    public void setup() {
        messageDao = new MessageDaoColl();
        userDao = new UserDaoColl();

        testUser = User.builder().firstName("Luuk").lastName("Hermans").username("luuk.hermans").build();
    }

    @Test
    public void savingMessageSuccessful() {
        Integer expectedMessageCount = 1;
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);
        int messageCount = messageDao.count();
        assertThat(messageCount, is(expectedMessageCount));
    }

    @Test
    public void deleteMessageSuccessful() {
        Integer expectedMessageCount = 0;
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);
        messageDao.deleteMessage(message);
        int messageCount = messageDao.count();
        assertThat(messageCount, is(expectedMessageCount));
    }

    @Test
    public void getAllMessagesSuccessful() {
        int expectedMessageCount = 2;
        Message message = Message.builder().ID(1L).message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();
        Message message2 = Message.builder().ID(2L).message("Hello2").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);
        addMessage(message2);
        int messageCount = messageDao.count();

        assertEquals(messageCount, expectedMessageCount);
    }

    private void addUserAndMessage(Message message) {
        userDao.addUser(testUser);
        messageDao.addMessage(message);
    }

    private void addMessage(Message message) {
        messageDao.addMessage(message);
    }

    @Test
    public void updateMessageSuccesful() {
        Message message = Message.builder().message("Hello").sender(testUser).timestamp(LocalDateTime.now()).build();

        addUserAndMessage(message);
        message.setMessage("Another text");
        messageDao.updateMessage(message);
        Message foundMessage = messageDao.findMessageByID(message.getID());
        assertThat(foundMessage.getMessage(), is(message.getMessage()));
    }
}
