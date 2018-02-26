package nl.luukhermans.services;

import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import javax.inject.Inject;

public class MessageService {

    @Inject
    private MessageDao messageDao;

    @Inject
    private UserDao userDao;

    public void addMessage(int userID, String messageText) {
        User sender = userDao.findByID(userID);
        Message message = Message.builder().message(messageText).sender(sender).build();
    }
}
