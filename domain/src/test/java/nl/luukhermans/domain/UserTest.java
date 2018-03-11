package nl.luukhermans.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class UserTest {

    private User user;
    private User otherUser;
    private Message message;

    @Before
    public void setUp() {
        user = User.builder().username("test.user").firstName("Test").lastName("User").build();
        otherUser = User.builder().username("test.user2").firstName("Test2").lastName("User").build();
        message = Message.builder().sender(user).message("Test").timestamp(LocalDateTime.now()).build();
    }

    @Test
    public void addMention_addsMention() {
        user.addMention(message);

        Assert.assertEquals(user.getMentions().size(), 1);
    }

    @Test
    public void deleteMention_deletesMention() {
        Message message = Message.builder().sender(user).message("Test").timestamp(LocalDateTime.now()).build();
        user.addMention(message);
        user.deleteMention(message);

        Assert.assertEquals(user.getMentions().size(), 0);
    }

    @Test
    public void addMessage_addsMessage() {
        user.addMessage(message);

        Assert.assertEquals(user.getMessages().size(), 1);
    }

    @Test
    public void followUser_AddsFollowing() {
        user.followUser(otherUser);

        Assert.assertEquals(user.getFollowing().size(), 1);
    }

    @Test
    public void unfollowUser_DeleteFollowing() {
        user.followUser(otherUser);
        user.unfollowUser(otherUser);

        Assert.assertEquals(user.getFollowing().size(), 0);
    }

    @Test
    public void followedbyUser_AddsFollower() {
        user.followUser(otherUser);

        Assert.assertEquals(user.getFollowing().size(), 1);
    }

    @Test
    public void unfollowedByUser_DeleteFollower() {
        user.followedBy(otherUser);
        user.unfollowedBy(otherUser);

        Assert.assertEquals(user.getFollowers().size(), 0);
    }

}
