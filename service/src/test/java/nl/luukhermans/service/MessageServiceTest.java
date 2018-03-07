package nl.luukhermans.service;

import nl.luukhermans.dao.MessageDao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MessageServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private MessageDao messageDao;
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

        messageService.addMessage(1L, null);
    }

    @Test
    public void addMessage_withNullUser_expectException() throws Exception {
        expectedException.expect(Exception.class);

        messageService.addMessage(null, "Message");
    }

    @Test
    public void addMessage_WithUnknownUser_expectException() throws Exception {
        expectedException.expect(Exception.class);

        messageService.addMessage(1L, "Message");
    }
}
