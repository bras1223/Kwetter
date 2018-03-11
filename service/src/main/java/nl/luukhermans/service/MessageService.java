package nl.luukhermans.service;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.Trend;
import nl.luukhermans.service.interceptor.TrendInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Stateless
public class MessageService {

    @Inject
    @JPA
    private MessageDao messageDao;

    @Inject
    private TrendService trendService;

    @Inject
    @JPA
    private UserDao userDao;

    /**
     * Adds a message to the MessageDao
     * Message should not be null, so do the sender and String message.
     *
     * @param message The message that should be added.
     */
    @Interceptors(TrendInterceptor.class)
    public void addMessage(Message message) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(message.getMessage());
        Objects.requireNonNull(message.getSender().getID());

        message.setTimestamp(LocalDateTime.now());
        List<Trend> trends = trendService.addTrendsFromMessage(message.getMessage(), true);
        message.setTrends(trends);
        messageDao.addMessage(message);
    }

    /**
     * Deletes a message from the UserDao
     * Message should not be null.
     *
     * @param message The message that should be deleted.
     */
    public void deleteMessage(Message message) {
        Objects.requireNonNull(message);
        trendService.addTrendsFromMessage(message.getMessage(), false);

        messageDao.deleteMessage(message);
    }



    public Collection<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }
}
