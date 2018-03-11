package nl.luukhermans.service;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    @JPA
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void SetUp() throws Exception {
        userService = new UserService();
        user = User.builder().ID(1L).firstName("Test").lastName("User").username("test.user").build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsers_returnsUsers() {
        userService.getAllUsers();
        verify(userDao, times(1)).getAllUsers();
    }

    @Test
    public void addUser_NullUser_GivesException() throws Exception {
        expectedException.expect(Exception.class);
        userService.addUser(null);
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

    @Test
    public void addUser_Successful() throws Exception {
        User user = User.builder().ID(1L).firstName("Test").lastName("User").username("test.user").build();
        userService.addUser(user);

        verify(userDao, times(1)).addUser(user);
    }

    @Test
    public void updateUser_Successful() throws Exception {
        User user = User.builder().ID(1L).firstName("Test").lastName("User").username("test.user").build();
        userService.updateUser(user);

        verify(userDao, times(1)).updateUser(user);
    }

    @Test
    public void findUserByID_Successful() {
        try {
            userService.findUserByID(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        when(userDao.findByID(1L)).thenReturn(user);
        verify(userDao, times(1)).findByID(1L);
    }

    @Test
    public void findUserByUsername_Successful() {
        try {
            userService.findUserByUsername("luuk.hermans");
        } catch (Exception e) {
            e.printStackTrace();
        }
        when(userDao.findByUsername("luuk.hermans")).thenReturn(user);

        verify(userDao, times(1)).findByUsername("luuk.hermans");
    }
}
