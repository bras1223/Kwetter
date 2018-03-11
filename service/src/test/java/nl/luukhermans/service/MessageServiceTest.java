package nl.luukhermans.service;

import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private MessageDao messageDao;
    @Mock
    private TrendService trendService;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private MessageService messageService;

    @Before
    public void SetUp() throws Exception {
        messageService = new MessageService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addMessage_withNullMessage_expectNullPointerException() throws Exception {
        expectedException.expect(NullPointerException.class);
        String messageText = null;

        messageService.addMessage(Message.builder().sender(User.builder().ID(1L).build()).message(messageText).timestamp(LocalDateTime.now()).build());
    }

    @Test
    public void addMessage_withNullUser_expectException() throws Exception {
        expectedException.expect(Exception.class);
        User sender = null;
        messageService.addMessage(Message.builder().sender(sender).message("Text").timestamp(LocalDateTime.now()).build());
    }

    @Test
    public void addMessage_WithUnknownUser_expectException() throws Exception {
        expectedException.expect(Exception.class);

        messageService.addMessage(Message.builder().sender(User.builder().ID(10L).build()).message("Text").timestamp(LocalDateTime.now()).build());
    }

    @Test
    public void addMessage_WithKnownUserAndMessage_Successful() throws Exception {
        User user = User.builder().ID(1L).firstName("Test").lastName("User").username("test.user").build();
        Message message = Message.builder().sender(user).message("Text").timestamp(LocalDateTime.now()).build();

        when(userDao.findByID(1L)).thenReturn(user);
        messageService.addMessage(message);

        verify(messageDao, times(1)).addMessage(message);
    }
}
