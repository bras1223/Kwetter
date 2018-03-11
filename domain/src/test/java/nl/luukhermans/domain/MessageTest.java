package nl.luukhermans.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class MessageTest {

    private User user;
    private Message message;

    @Before
    public void setUp() {
        user = User.builder().username("test.user").firstName("Test").lastName("User").build();
        message = Message.builder().sender(user).message("Test").timestamp(LocalDateTime.now()).build();
    }

    @Test
    public void addMention_addsMention() {
        message.addMention(user);

        Assert.assertEquals(message.getMentions().size(), 1);
    }

    @Test
    public void addTrend_addsTrend() {
        Trend trend = Trend.builder().hashtag("Tag").timesUsed(1).build();
        message.addTrend(trend);

        Assert.assertEquals(message.getTrends().size(), 1);
    }

}
