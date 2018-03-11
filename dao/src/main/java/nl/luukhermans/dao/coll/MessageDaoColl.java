package nl.luukhermans.dao.coll;

import nl.luukhermans.dao.MessageDao;
import nl.luukhermans.domain.Message;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Collection;
import java.util.HashMap;

@Stateless
@Default
public class MessageDaoColl implements MessageDao {

    private HashMap<Long, Message> messages = new HashMap<>();

    @Override
    public void addMessage(Message message) {
        messages.put(message.getID(), message);
    }

    @Override
    public void deleteMessage(Message message) {
        messages.remove(message.getID());
    }

    @Override
    public void updateMessage(Message message) {
        messages.put(message.getID(), message);
    }

    @Override
    public Message findMessageByID(Long ID) {
        return messages.get(ID);
    }

    @Override
    public Collection<Message> getAllMessages() {
        return messages.values();
    }

    @Override
    public int count() {
        return messages.size();
    }

}
