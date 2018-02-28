package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;
import nl.luukhermans.domain.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MessageDaoJPA implements MessageDao {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public void addMessage(Message message) {
        em.persist(message);
    }

    @Override
    public void deleteMessage(Message message) {

    }

    @Override
    public List<Message> getAllMessages() {
        return null;
    }

    @Override
    public List<Message> findUserMessages(User user) {
        return null;
    }


    @PostConstruct
    public void init() {
        //System.out.println("---MessageDaoJPA");
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
