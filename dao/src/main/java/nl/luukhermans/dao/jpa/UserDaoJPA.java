package nl.luukhermans.dao.jpa;

import nl.luukhermans.dao.JPA;
import nl.luukhermans.dao.UserDao;
import nl.luukhermans.domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Stateless
@JPA
public class UserDaoJPA implements UserDao {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public Collection<User> getAllUsers() {
        TypedQuery<User> query = em.createNamedQuery("user.getAllUsers", User.class);
        List<User> result = query.getResultList();
        return result;
    }

    @Override
    public User findByID(Long ID) {
        return em.find(User.class, ID);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery("user.findByUsername", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
        em.flush();
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public int count() {
        return getAllUsers().size();
    }

    @PostConstruct
    public void init() {
        //System.out.println("---UserDaoJPA");
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
