package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import java.util.List;

public interface MessageDao {

    void addMessage(Message message);

    void deleteMessage(Message message);

    List<Message> getAllMessages();

    List<Message> findUserMessages(User user);

}
