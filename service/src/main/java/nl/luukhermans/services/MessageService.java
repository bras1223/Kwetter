package nl.luukhermans.services;

import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Set;

@Stateless
public class MessageService {

    @Inject
    private MessageDao messageDao;

    @Inject
    private TrendService trendService;

    @Inject
    private UserDao userDao;

    public void addMessage(int userID, String messageText, Set<String> trends) {
        User sender = userDao.findByID(userID);
        Message message = Message.builder().message(messageText).sender(sender).build();

        sender.addMessage(message);
        messageDao.addMessage(message);
    }
}
