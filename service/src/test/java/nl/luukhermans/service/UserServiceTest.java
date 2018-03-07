package nl.luukhermans.service;

import nl.luukhermans.dao.UserDao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    @Before
    public void SetUp() throws Exception {
        userService = new UserService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserByID_NotKnown_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        userService.findUserByID(10L);
    }

    @Test
    public void findUserByUsername_NotKnown_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        userService.findUserByUsername("FakeUser");
    }

    @Test
    public void findUserByUsername_NullUsername_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        userService.findUserByUsername(null);
    }

    @Test
    public void findUserByID_NullID_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        userService.findUserByID(null);
    }
}
