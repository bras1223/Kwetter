package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.HashMap;
import java.util.List;

@Stateless
@Default
public class MessageDaoColl implements MessageDao {

    private HashMap<Integer, Message> messages = new HashMap<>();

    public void addMessage(Message message) {
        messages.put(message.getID(), message);
    }

    public void deleteMessage(Message message) {
        messages.remove(message.getID());
    }

    public List<Message> getAllMessages() {
        return null;
    }

    public List<Message> findUserMessages(User user) {
        return null;
    }
}
