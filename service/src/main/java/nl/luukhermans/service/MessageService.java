package nl.luukhermans.service;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Objects;

@Stateless
public class MessageService {

    @Inject
    @JPA
    private MessageDao messageDao;

    @Inject
    private TrendService trendService;

    @Inject
    private UserDao userDao;

    public void addMessage(int userID, String messageText) throws Exception {
        Objects.requireNonNull(userID);
        Objects.requireNonNull(messageText);

        User sender = userDao.findByID(userID);
        if (Objects.isNull(sender)) {
            throw new Exception("User doesn't exist");
        }

        Message message = Message.builder().message(messageText).sender(sender).timestamp(LocalDateTime.now()).build();

        sender.addMessage(message);
        messageDao.addMessage(message);
    }
}
