package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;

import java.util.Collection;

public interface MessageDao {

    void addMessage(Message message);

    void deleteMessage(Message message);

    void updateMessage(Message message);

    Message findMessageByID(Long ID);

    Collection<Message> getAllMessages();

    int count();
}
