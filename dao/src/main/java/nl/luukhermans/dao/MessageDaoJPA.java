package nl.luukhermans.dao;

import nl.luukhermans.domain.Message;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@JPA
public class MessageDaoJPA implements MessageDao {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public void addMessage(Message message) {
        em.persist(message);
    }

    @Override
    public void deleteMessage(Message message) {
        em.remove(message);
    }

    @Override
    public List<Message> getAllMessages() {
        TypedQuery<Message> query = em.createNamedQuery("message.findAllMessages", Message.class);
        List<Message> result = query.getResultList();
        return result;
    }

    @Override
    public int count() {
        return getAllMessages().size();
    }

    @PostConstruct
    public void init() {
        System.out.println("---MessageDaoJPA");
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
