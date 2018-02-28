package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.HashMap;
import java.util.List;

@Stateless
@Default
public class MessageDaoColl implements MessageDao {

    private HashMap<Integer, Message> messages = new HashMap<>();

    @Override
    public void addMessage(Message message) {
        messages.put(message.getID(), message);
    }

    @Override
    public void deleteMessage(Message message) {
        messages.remove(message.getID());
    }

    @Override
    public List<Message> getAllMessages() {
        return null;
    }

    @Override
    public int count() {
        return messages.size();
    }

}
